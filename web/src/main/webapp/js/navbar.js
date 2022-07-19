function dropdownMenu() {
    document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(e){
    if(!e.target.matches('.dropbtn') && !e.target.matches('.burger')){
        var x = document.getElementById('nav-list');
        if(x.classList.contains('responsive')){
            x.classList.remove('responsive');
        }
    }
    if(!e.target.matches('.dropbtn')){
        var myDropdown = document.getElementById('myDropdown');
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}

function dropDownBurger(){
    var x = document.getElementById("nav-list");
    if (x.classList.contains("responsive")) {
        x.classList.remove("responsive");
    } else {
        x.classList.add("responsive");
    }
}