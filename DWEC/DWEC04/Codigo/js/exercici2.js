/*
    El javascript d'aquest exercici ha d'anar a un fitxer apart.

    Utilitza una funció constructora per crear els objectes que guardin les dades dels artistes que gestiona una aplicació web de centres d'art. 
    De cada artista volem saber el nom, la nacionalitat i les obres que té. Per les obres basta amb el títol. Evidentment les obres no es poden repetir.

    Cada artista ha de tenir una funció que permeti afegir una nova obra a la llista, una altra que permeti eliminar-la i una que torni una cadena de text 
    amb els títols separats per comes.

    Utilitza aquesta funció per crear dos artistes amb al manco dues o tres obres cada un.

    Dins una pàgina html mostra la informació dels artistes, incloses les seves obres.
*/

window.onload = () => {
    let a1 = new Artista("Antonio Lopez Sanchis", "ES", ["Soles soleados", "Ciclista escalador"])
    let a2 = new Artista("Fernando Fernandez Guerrero", "ES", ["Kimberlina", "The Fullers", "Floripondios"])
    try {
        a1.addObra("La Marquesina")
        a2.delObra("Floripondios")
    } catch (exception) {
        console.log(exception.message)
    }
    lartistas = [a1, a2]
    imprimirDatos(lartistas)
}

function imprimirDatos(lartistas) {
    let parent = document.getElementById("list") //Elemento parent donde se imprimiran los datos.
    for (artista in lartistas) {
        let div = document.createElement("div")
        let h3 = document.createElement("h3")
        let th3 = document.createTextNode(lartistas[artista].nom)

        let pnac = document.createElement("p")
        let tpnac = document.createTextNode("Nacionalitat: " + lartistas[artista].nacionalitat)

        let po = document.createElement("p")
        try {
            let tpo = document.createTextNode("Obres: " + lartistas[artista].getObres())
            po.appendChild(tpo)
        } catch (exception) {
            console.log(exception.message)
        }
        h3.appendChild(th3)
        pnac.appendChild(tpnac)
        div.appendChild(h3)
        div.appendChild(pnac)
        div.appendChild(po)
        div.setAttribute("class", "artista")
        parent.appendChild(div)
    }
}