
window.onload= function() {
    'use strict'
    validation();
}
function validation(){
    const frmLogin = $("#frmLogin").validate({
        rules: {
            username: {
                required: true,
            },
            password: {
                required: true,
            }
        },
        messages: {
            username: {
                required: "Please enter a username"

            },
            password: {
                required: "Please provide a password"

            }


        }
    });


}
