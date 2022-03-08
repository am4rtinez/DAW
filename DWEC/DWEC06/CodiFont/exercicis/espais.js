window.onload = () => {
    obtenerTipos()
    initListeners()
}

/**
 * Funcion que obtiene los tipos y los pinta.
 */
function obtenerTipos() {
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
function initListeners() {
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
function showEspaisTipus(id){
    let parent = document.getElementById('mostrador')   //Elemento padre
    parent.textContent = ''                             //Elimina todo el contenido del parent (mostrador)
    const espaisTipus = JSON.parse(getEspaisTipus(id))
    if (espaisTipus.length != 0){                       //Comprueba si el id seleccionado contiene elementos. En caso de no tenerlos no hace nada.
        for (let espai of espaisTipus){
            // Creacion de los elementos.
            let div = document.createElement('div')
            let p = document.createElement('p')
            let pdesc = document.createElement('p')
            let psite = document.createElement('p')
            let asite = document.createElement('a')
            let pemail = document.createElement('p')
            let aemail = document.createElement('a')
            let ptel = document.createElement('p')
            let atel = document.createElement('a')
            let btn = document.createElement('button')

            // Definicion de varios atribuos.
            div.className = 'espaiDiv'
            p.className = 'espaiNom'
            pdesc.style.display = 'none'
            psite.style.display = 'none'
            pemail.style.display = 'none'
            ptel.style.display = 'none'

            p.textContent = espai['nom']
            pdesc.textContent = espai['descripcions']['cat']
            asite.setAttribute('href', 'https://' + espai['web'])
            asite.textContent = espai['web']
            aemail.setAttribute('href', 'email:' + espai['web'])
            aemail.textContent = espai['email']
            atel.setAttribute('href', 'tel:'+espai['telefon'])
            atel.textContent = espai['telefon']

            btn.textContent = 'Més'
            // Cuando se clica el boton muestra los elementos ocultos y oculta el boton.
            btn.addEventListener('click', function(){
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
        }
    }
}