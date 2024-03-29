// Javascript para ocultar y mostrar la navegación para los dispositivos móviles o tablet.

const primaryNav = document.querySelector(".primary-navigation");
const navToggle = document.querySelector(".navbar-toggle");

navToggle.addEventListener('click', () => {
    const visibility = primaryNav.getAttribute('data-visible');
    if (visibility === "false") {
        primaryNav.setAttribute('data-visible', true);
        navToggle.setAttribute('aria-expanded', true);
        document.getElementById('nav-icon').className = "fa fa-times";
    } else {
        primaryNav.setAttribute('data-visible', false);
        navToggle.setAttribute('aria-expanded', false);
        navToggle.getElementsByClassName('fa');
        document.getElementById('nav-icon').className = "fa fa-bars";
    }
})