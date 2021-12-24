//Una vez se haya cargado el documento completo se aplica el script.
//De lo contrario lanza errores del tipo Cannot read property of null (reading 'addEventListener').
window.onload = () => {
    //Listener para el boton anar.
    document.getElementById("bAnar").addEventListener('click', () => {
        let url = document.getElementById("url").value;
        if (!validateURL(url)) {
            alert("La URL insertada no es correcta. \nEl formato de la URL debe ser http:// o https://");
        } else {
            //Comprobamos si la url contiene protocolo http/s.
            //En caso de no contenerlo se suma a la url.
            if (!(url.includes("https://")) || !(url.includes("http://"))) {
                /* Redirigimos a http ya que casi todas las webs redirigen al protocolo https 
                y puede haber webs que aun no lo implementen.*/
                url = "http://" + url;
            }
            window.location = url;
        }
    });
    
    //Listener para el boton tornar.
    document.getElementById("bTornar").addEventListener('click', () => {
        window.history.back();
    });
    
    //Listener para el boton recarregar.
    document.getElementById("bRecarregar").addEventListener('click', () => {
        window.location.reload(true);
    });
}

// Comprueba que la url es correcta. Ya que sin http:// o https:// no funciona.
// La expresión regular es de stackoverflow.
// https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
function validateURL(url) {  
    let regex = new RegExp('https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,}');
    if (url.match(regex)) {
        return true;
    } else {
        return false;
    }
}