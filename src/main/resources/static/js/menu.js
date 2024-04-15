document.addEventListener("DOMContentLoaded", function (){
    const menuMobileButton = document.getElementById("menu_mobile_button");
    const menuMobileList = document.getElementById("header_menu_mobile_list_ul");

    menuMobileButton.addEventListener('click', function (){
        const menuMobileListStyles = window.getComputedStyle(menuMobileList);

        if(menuMobileListStyles.display === "none"){
            menuMobileList.style.display = "block";
        } else {
            menuMobileList.style.display = "none";
        }
    });
});
