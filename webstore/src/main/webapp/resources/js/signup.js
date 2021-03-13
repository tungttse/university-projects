 (function () {
    "use strict";
    let jqueryFrm,txtUsername,  spnMsg;
    window.onload = function () {
        loader.end();
        const frm = document.getElementById("frmSignup");
        txtUsername = document.getElementById("username");
         spnMsg = document.getElementById("spnMsg");
        username.onblur=checkUser;
        frm.onsubmit = signup;
        let allInput = document.querySelectorAll('input[type=text],input[type=password]');
        for (let i = 0; i < allInput.length; i++) {
            allInput[i].onfocus = resetErrors;
        }

        validation();
    }
     function checkUser(event){

        const username = event.target.value;

         spnMsg.innerText='';
         getData(`/user/signup?username=${username}`).then(resp=>{

             if(resp.result==="hasDupUser" && resp.value==="true")
             {
                 spnMsg.innerText="Username already exists."
             }
         });


     }
     async function getData(url){
         const response = await fetch(url);
         if(!response.ok){
             return new Error("Error occure while processing request");
         }
         return  response.json();
     }
    function resetErrors(e) {
        let msgContainer = document.getElementById('message');
        msgContainer.className = '';
        msgContainer.innerHTML = '';
        e.stopImmediatePropagation();
    }

    function validation() {
        jQuery.validator.addMethod("pass",
            function (value, element, params) {

                var valid = true;
                var password = $('#password').val().trim();
                var patt = new RegExp(/(?=.*\d)(?=.*[a-zA-Z]).{6,}/) // should contain at least one digit // should contain at least one lower case // should contain at least one upper case
                if (!patt.test(password)) {
                    return false;
                }
                return valid;

            }, "Pass must be at least 6 characters with at least one numeric and a letter");
        jqueryFrm = $("#frmSignup").validate({
            //
            rules: {
                username: {
                    required: true,
                    minlength: 5
                },
                password: {
                    required: true,
                    pass: true
                },
                confirmPassword: {
                    required: true,
                    equalTo: {
                        param: '#password',
                        depends: function (element) {
                            return $('#password').val() !== '';
                        }
                    }
                },
                email: {
                    required: true,
                    email: true
                },
                firstName: {
                    required: true,
                },
                lastName: {
                    required: true,
                }
            },
            messages: {
                username: {
                    required: "Please enter username"

                },
                password: {
                    required: "Please provide password"

                },
                confirmPassword: {
                    required: "Please provide confirm password",
                    equalTo: "Password and confirm password do not matches."

                },
                email: {
                    required: "Please provide an email."

                },
                firstName: {
                    required: "Please provide first name."

                },
                lastName: {
                    required: "Please provide last name."

                }
            }

            //
        });
    }

   function signup (e) {

        if ($("#frmSignup").valid()) {
            loader.start();

            const firstName = document.getElementById('firstName').value;
            const lastName = document.getElementById('lastName').value;
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const email = document.getElementById('email').value;

            const user = {
                firstName: firstName,
                lastName: lastName,
                username: username,
                password: password,
                confirmPassword: confirmPassword,
                email: email
            };
            fetch('/user/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            })
                .then((response) => {
                    setTimeout(function () {
                        if (response.ok) {
                            processData(response,e);
                        } else {
                            processErrors(response);
                        }
                        loader.end();
                    }, 2000)


                }).catch((error) => {
                console.error('Error:', error);
                loader.end();
            });
        }
        e.preventDefault();

    }

    function processErrors(response) {
        spnMsg.innerText='';
        response.json()
            .then((json) => {
                console.log(json);
                if (json.error) {
                    let msgContainer = document.getElementById('message');
                    msgContainer.classList.remove("success-message");
                    msgContainer.classList.add("error-message");
                    msgContainer.innerHTML = json.error;
                } else {
                    jqueryFrm.showErrors(json);
                }
            });
    }

    function processData(response,event) {
        response.json().then(message => {
            let msgContainer = document.getElementById('message');
            msgContainer.classList.add("success-message");
            msgContainer.classList.remove("error-message");
            msgContainer.innerHTML = message.success;
        });
        event.target.reset();
        spnMsg.innerText='';
    }

})();