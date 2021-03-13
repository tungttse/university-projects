(function (){
  let jqueryFrm;
  let allInput;
  let msgContainer ;
  let btnSubmit;
  let btnCancel;
  window.onload=function (){
    allInput = document.querySelectorAll("input[type=text],input[type=password]");
    msgContainer = document.getElementById('message');
    allInput.forEach((input)=>input.setAttribute('disabled',true));
    btnSubmit = document.getElementById('btnSubmit');
    btnCancel = document.getElementById('btnCancel');
    btnCancel.style.display='none';
    btnSubmit.onclick=toggleAction;
    btnCancel.onclick=cancelEdit;
    for (let i = 0; i < allInput.length; i++) {
      allInput[i].onfocus = resetErrors;
      allInput[i].onkeyup= triggerSave;
    }

    validation();
  }
  function triggerSave(event){
      if (event.keyCode === 13) {
        event.preventDefault();
        btnSubmit.click();
      }
  }
  function cancelEdit(e){
    btnSubmit.value="Edit";
    e.target.style.display='none';
    allInput.forEach((input)=>input.setAttribute('disabled',true));
  }
  function toggleAction(e){
    if(e.target.value==='Edit'){
      e.target.value ='Save';
      btnCancel.style.display="inline-block";
      allInput.forEach((input)=>input.removeAttribute('disabled'));
      removeMessage();
    }else{
      signup();
    }
  }
  function resetErrors(e) {
    let msgContainer = document.getElementById('message');
    removeMessage();
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
            //valid = false;
          }


          return valid;

        }, "Pass must be at least 6 charcter with a number");
    jqueryFrm = $("#frmSignup").validate({
      rules: {
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
    });
  }
  function signup () {
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
      fetch('/user/profile', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
      })
          .then((response) => {
            setTimeout(function () {
              if (response.ok) {
                processData(response);
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


  }
  function processErrors(response) {

    response.json()
        .then((json) => {
          console.log(json);
          if (json.error) {
            showErrorMessage(json.error)
          } else {
            jqueryFrm.showErrors(json);
          }
        });
  }

  function processData(response) {
    response.json().then(message => {
      let msgContainer = document.getElementById('message');
      msgContainer.classList.add("success-message");
      msgContainer.classList.remove("error-message");
      msgContainer.innerHTML = message.success;
      allInput.forEach((input)=>input.setAttribute('disabled',true));
      btnSubmit.value='Edit';
      btnCancel.style.display="none";

    });

  }
  function showErrorMessage(message){
    msgContainer.classList.remove("success-message");
    msgContainer.classList.add("error-message");
    msgContainer.innerHTML = message;


  }
  function removeMessage(){
    msgContainer.className = '';
    msgContainer.innerHTML = '';
  }
})();