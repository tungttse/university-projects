const loader =(function () {
    "use strict";
    window.onload=function (){
        let loaderContainer = document.getElementById("loader");
        loaderContainer.style.display = "none";
    }
    function start(){

        let loaderContainer = document.getElementById("loader");
        loaderContainer.style.display = "block";
    }
    function end(){

        let loaderContainer = document.getElementById("loader");
        loaderContainer.style.display = "none";

    }
    return {
        start: start,
        end: end
    }
})();