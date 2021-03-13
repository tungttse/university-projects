(function () {
    "use strict";
    window.onload=function (){
        _handleRemoveItem();
    }

    // function _renderCartLines(cartMap){
    //     let content = "";
    //     let totalPrice = 0;
    //     for(let key in cartMap){
    //         let item = cartMap[key];
    //         content += `<tr>
    //                 <td>${item.quantity}</td>
    //                 <td>${item.name}</td>
    //                 <td>${item.price}</td>
    //                 <td>${item.price * item.quantity}</td>
    //                 <td><input
    //                     class="remove_item"
    //                     type="submit"
    //                     value="Remove"
    //                     data-id="${item.id}"
    //                     data-name="${item.name}"
    //                     data-price="${item.price}"
    //                     data-description="${item.description}"
    //                 ></td>
    //             </tr>`;
    //
    //         totalPrice += item.price * item.quantity;
    //     }
    //
    //     let cartLineContainer = document.getElementById("table_cartline");
    //     cartLineContainer.insertAdjacentHTML('beforeend', content);
    //
    //     let totalPriceContent = `
    //     <tr>
    //         <td colspan="4" class="text-right">Total:</td>
    //         <td id="total_price">${totalPrice}</td>
    //     </tr>`
    //     cartLineContainer.insertAdjacentHTML('beforeend', totalPriceContent);
    //
    // }

    function _handleRemoveItem(){
        let removeItemBtns = document.getElementsByClassName("remove_item");

        for(let i=0; i< removeItemBtns.length; i++) {
            let button = removeItemBtns[i];
            button.addEventListener('click', function(event){
                let data = event.target.dataset;

                let dataPost = {
                    productId: data.id,
                    action: "delete_to_cart"
                };

                fetch("/cart",
                    {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(dataPost) // body data type must match "Content-Type" header
                    }
                )
                    .then(response => response.json())
                    .then(resp => {
                        if(resp.result == "1") {
                            button.closest("tr").remove();

                            let cart = Common.cookie.get("cart");
                            let cartMap = JSON.parse(cart);
                            delete cartMap[data.id];
                            Common.cookie.set("cart", JSON.stringify(cartMap), 7);

                            // show cart info at header
                            Common.Cart.showCartInfo();

                            // refresh total price again
                            _refreshTotalPrice();

                            Common.cookie.set("cart_data", Common.Cart.mapDataCookie(cartMap));

                            // if there are no any item. Disable click button
                            let btnCheckout = document.getElementById("btn_checkout");
                            if(Object.keys(cartMap).length === 0 && cartMap.constructor === Object) {
                                btnCheckout.disabled = true;
                                btnCheckout.href = "#";
                            }
                        }
                    })
                    .catch(err => {
                        console.log('Fetch Error :-S', err);
                    });
            });
        }
    }

    function _refreshTotalPrice(){
        let cart = Common.cookie.get("cart");
        let cartMap = JSON.parse(cart);
        let totalPrice = 0;
        for(var key in cartMap){
            let value = cartMap[key];
            totalPrice += parseInt(value.price) * parseInt(value.quantity);
        }
        let totalPriceContainer = document.getElementById("total_price");
        totalPriceContainer.innerText= totalPrice + "$";
    }

    // function _refreshDataForm(){
    //     let inputCart = document.getElementById("cart_data");
    //
    //     let cart = Common.cookie.get("cart");
    //     let cartMap = JSON.parse(cart);
    //
    //     let data = [];
    //     for(var key in cartMap){
    //         let value = cartMap[key];
    //         console.log(value)
    //         data.push(value);
    //     }
    //     console.log(data);
    //     inputCart.value= data;
    // }
})()