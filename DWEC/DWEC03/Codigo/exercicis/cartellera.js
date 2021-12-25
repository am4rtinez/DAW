window.onload = () => {
    let imagenes = getDestacades()
    const DELAY_SEG = 1500
    let posicionActual = 0;
    let player = false;
    let intervalo;

    document.getElementById("boto").addEventListener('click', () => {
        if (!player) {
            play()
        } else {
            stop()
        }
    });

    /**
     * Funcion que pone el carrusel en marcha con autoplay de las imagenes.
     */
    function play() {
        intervalo = setInterval(avanzaImg, DELAY_SEG);
        player = true
    }
    
    /**
     * Funcion que para el carrusel de las imagenes.
     */
    function stop() {
        clearInterval(intervalo);
        player = false
    }

    /**
     * Funcion que cambia la foto de la siguiente posicion del array
     */
    function avanzaImg() {
        if(posicionActual >= imagenes.length - 1) {
            posicionActual = 0;
        } else {
            posicionActual++;
        }
        setImagen();
    }

    /**
     * Funcion que actualiza la imagen de imagen dependiendo de posicionActual
     */
    function setImagen () {
       document.getElementById("mostrador").setAttribute("src", imagenes[posicionActual]);
    }

    setImagen()

}

