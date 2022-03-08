/** 
 * Exercici 1 (5 punts)
 * ==============================================================================
 * Programau el necessari a espais.js de manera que espais.html tengui la següent funcionalitat.
 * 
 * Exercici 1.1 (1 punt)
 * --------------------------
 * En carregar-se la pàgina s'ha de crear una estructura com la següent
 * <div class="itemTipus">Centre Cultural</div>
 * per a cada un dels valor que torna la funció getTipus() de servidor.js.
 * 
 * Exercici 1.2 (1,5 punts)
 * --------------------------
 * Afegeix el codi necessari de manera que quan es pitgi el ratolí a sobre d'un dels elements creats a l'exercici anterior ha de fer el següent:
 * Ha d'assignar al div la classe itemTipusSeleccionat, de manera que sigui l'únic element que la tengui. Els altres han de tenir la classe itemTipus.
 * Ha de cridar la funció del servidor getEspaisTipus() i per a cada un dels valors que torna aquesta funció crear el codi següent dins l'element 
 * amb id mostrador:
 * <div class="espaiDiv">
 *  <p class="espaiNom">Es Baluart<button>Més</button></p>
 * </div>
 * 
 * Exercici 1.3 (2,5 punts)
 * -------------------------
 * Ara toca programar el comportament del botó. En pitjar-lo haurà d'incloure al div de l'espai un paràgraf per a cada una d'aquestes dades: 
 * la descripció, la web, el mail i el telèfon.
 * A més haurà d'eliminar el botó, de manera que no es pugui a tornar a pitjar.
 * El div de l'espai hauria de quedar com aquest:
 * <div class="espaiDiv">
 *  <p class="espaiNom">ArtArtà</p>
 *  <p>Aquest espai ...</p>
 *  <p><a href="https:/www.artarta.es">www.artarta.es</a></p>
 *  <p><a href="email:info@artarta.es">info@artarta.es</a></p>
 *  <p><a href="tel:971835939">971835939</a></p>
 * </div> 
 */

window.onload = () => {
    obtenerTipos()
    initListeners()
}

/**
 * Funcion que obtiene los tipos y los pinta.
 */
obtenerTipos = () => {
    const tipus = JSON.parse(getTipus())
    let parent = document.getElementById("llistaTipus")     //Elemento parent donde se imprimiran los datos.
    for (let tipo of tipus) {
        let div = document.createElement("div")             //Crea el elemento div.
        div.setAttribute("class", "itemTipus")              //Pone la clase itemTipus dentro del atributo class.
        div.setAttribute("id", tipo['id'])                  //Set del id por el que se filtrara despues.
        let t = document.createTextNode(tipo['cat']);       //Muestra el elemento por la key cat.
        div.appendChild(t)
        parent.appendChild(div)
    }
}
/**
 * Inicializa los listeners para los divs itemTipus para mostrar el elemento seleccionado y filtrar a partir de el.
 */
initListeners = () => {
    let itemTipus = document.getElementsByClassName('itemTipus')
    for (let element of itemTipus){
        element.addEventListener("click", function() {
            let current = document.getElementsByClassName("itemTipusSeleccionat");
            if (current.length > 0) { 
                current[0].className = current[0].className.replace(" itemTipusSeleccionat", "");
            }
            this.className += " itemTipusSeleccionat";
            showEspaisTipus(element.id)
        });
    }
}

/**
 * Funcion encargada de mostrar los distintos espacios filtrados por el id del tipo.
 * @param {*} id 
 */
showEspaisTipus = (id) =>{
    let parent = document.getElementById('mostrador')   //Elemento padre
    parent.textContent = ''                             //Elimina todo el contenido del parent (mostrador)
    const espaisTipus = JSON.parse(getEspaisTipus(id))  //Obtenemos los espacios por tipo.
    if (espaisTipus.length != 0){                       //Comprueba si el id seleccionado contiene elementos. En caso de no tenerlos no hace nada.
        espaisTipus.forEach(element => {
            // Creacion de los elementos.
            let div = document.createElement('div')
            let p = document.createElement('p')
            let pdesc = document.createElement('p')
            let psite = document.createElement('p')
            let pemail = document.createElement('p')
            let ptel = document.createElement('p')
            let asite = document.createElement('a')
            let aemail = document.createElement('a')
            let atel = document.createElement('a')
            let btn = document.createElement('button')

            // Definicion de varios atribuos.
            div.className = 'espaiDiv'
            p.className = 'espaiNom'
            pdesc.style.display = 'none'
            psite.style.display = 'none'
            pemail.style.display = 'none'
            ptel.style.display = 'none'

            p.textContent = element['nom'] + " - " + element['registre']    //Aprovecho para poner el registro en el nombre
            pdesc.textContent = element['descripcions']['cat']
            asite.setAttribute('href', 'https://' + element['web'])
            asite.textContent = element['web']
            aemail.setAttribute('href', 'email:' + element['web'])
            aemail.textContent = element['email']
            atel.setAttribute('href', 'tel:'+ ['telefon'])
            atel.textContent = element['telefon']

            btn.textContent = 'Més'
            // Cuando se clica el boton muestra los elementos ocultos y oculta el boton.
            btn.addEventListener('click', () => {
                btn.style.display = 'none'
                pdesc.style.display = ''
                psite.style.display = ''
                pemail.style.display = ''
                ptel.style.display = ''
            })

            //Hacemos los appendChild necesarios.
            p.appendChild(btn)
            psite.appendChild(asite)
            pemail.appendChild(aemail)
            ptel.appendChild(atel)
            div.appendChild(p)
            div.appendChild(pdesc)
            div.appendChild(psite)
            div.appendChild(pemail)
            div.appendChild(ptel)
            parent.appendChild(div)
        });
    }
}