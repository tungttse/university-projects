(function () {
    "use strict";
    window.onload=function (){
        Common.Cart.getCardData();
        _handleAddToCart();

    }

    function _handleAddToCart(){
        let cart = Common.cookie.get("cart");
        let cartMap = cart ? JSON.parse(cart) : {};
        let addToCartBtns = document.getElementsByClassName("add_to_cart");

        for(let i=0; i< addToCartBtns.length; i++) {
            let button = addToCartBtns[i];
            button.addEventListener('click', function(event){
                let data = event.target.dataset;

                let dataPost = {
                    productId: data.id,
                    action: "add_to_cart"
                };

                fetch("/cart",
                    {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(dataPost) // body data type must match "Content-Type" header
                    }
                    )
                    .then(response => response.json())
                    .then(resp => {
                        if(resp.result == "1") {
                            if(cartMap[data.id]) {
                                let p = cartMap[data.id];
                                p.quantity += 1;
                                cartMap[data.id] = p;
                            } else {
                                cartMap[data.id] = {
                                    id: data.id,
                                    name: data.name,
                                    price: data.price,
                                    description: data.description,
                                    quantity: 1
                                };
                            }

                            Common.cookie.set("cart", JSON.stringify(cartMap), 7);
                            Common.cookie.set("cart_data", Common.Cart.mapDataCookie(cartMap));

                            Common.Cart.showCartInfo();
                        }

                    })
                    .catch(err => {
                        console.log('Fetch Error :-S', err);
                    });
            });
        }
    }

})()