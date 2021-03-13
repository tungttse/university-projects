const Common = {
    cookie : {
        set : function (name, value, days) {
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                var expires = "; expires=" + date.toGMTString();
            }
            else
                var expires = "";
            document.cookie = name + "=" + JSON.stringify(value); + expires + "; path=/";
        },

        get: function(name){
            var nameEQ = name + "=",
                ca = document.cookie.split(';');

            for(var i=0;i < ca.length;i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1,c.length);
                if (c.indexOf(nameEQ) == 0)
                {

                    let sstr = c.substring(nameEQ.length,c.length);
                    if(sstr.length == 0) return null;
                    return  JSON.parse(sstr);
                }

            }
            return null;
        }
    },

    Cart: {
        showCartInfo:function(){
            let cart = Common.cookie.get("cart");
            let cartMap = JSON.parse(cart);

            let topCartInfo = document.getElementById("cart_info_text");

            let cartInfo = {};
            cartInfo.totalItem = 0;
            cartInfo.totalPrice = 0;

            for(var key in cartMap){
                let value = cartMap[key];
                cartInfo.totalItem += parseInt(value.quantity);
                cartInfo.totalPrice += parseInt(value.price) * parseInt(value.quantity);
            }

            topCartInfo.innerText = `Your Cart:${cartInfo.totalItem} items $${cartInfo.totalPrice}`;
        },

        getCardData:  function(){
            const myHeaders = new Headers();
            myHeaders.append('X-Custom-Header', 'isAjax');

            fetch("/cart",
                {
                    headers: myHeaders
                }
            )
                .then(response => response.json())
                .then(cartInfo => {
                    console.log(cartInfo.totalItem);
                    console.log(cartInfo.totalPrice);

                    let topCartInfo = document.getElementById("cart_info_text");
                    topCartInfo.innerText = `Your Cart:${cartInfo.totalItem} items $${cartInfo.totalPrice}`;

                })
                .catch(err => {
                    console.log('Fetch Error :-S', err);
                });
        },

        mapDataCookie: function() {
            let cart = Common.cookie.get("cart");
            let cartMap = cart ? JSON.parse(cart) : {};

            let str = "";
            for(var key in cartMap){
                let value = cartMap[key];
                str += value.id + "-" + value.quantity + "_";
            }
            return str;
        }
    }
}

Common.Cart.showCartInfo();