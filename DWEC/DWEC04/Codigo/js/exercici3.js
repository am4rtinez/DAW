/*
    Utilitza una classe que representi les dades d'una exposició. El que necessitam saber de l'exposició, a part del títol, són les dates d'inici i fi, 
    el centre on tendrà lloc i els artistes que hi participen.

    Per els artistes podeu utilitzar la funció constructora de l'exercici anterior. Afegeix els mètodes afegir i eliminar artista. 
    Afegir artista ha de controlar els duplicats.

    Crea una exposició amb al manco dos artistes, comprovant que no accepta duplicats.

    Mostra la seva informació a l'html.
*/

window.onload = () => {
    let a1 = new Artista("Antonio Lopez Sanchis", "ES", ["Soles soleados", "Ciclista escalador", "La Marquesina"])
    let a2 = new Artista("Fernando Fernandez Guerrero", "ES", ["Kimberlina", "The Fullers", "Floripondios"])
    let a3 = new Artista("Pedro Perez Guerrero", "ES", ["Manuscrito", "The Alien", "Mitocondrios"])
    let a4 = new Artista("Fernando Fernandez Guerrero", "ES", ["Kimberlina", "The Fullers", "Floripondios"])
    let expo = new Exposicio("Expo Gener 2022", "15/01/2022", "30/01/2022", "Centre Cultural La Colmena", [])
    try {
        expo.addArtista(a1)
        expo.addArtista(a2)
        expo.addArtista(a3)
        expo.addArtista(a4)     // Este artista no se anadira al objeto exposicion. Se lanza un error y se imprime en consola.
    } catch (exception) {
        console.log(exception.message)
    }
    expo.delArtista(a2)
    imprimirDatos(expo)
}

/*
    Funcion que imprime los datos de la exposicion en el html.
    En este caso solo hay una exposicion.
    Parametro tipo objeto Exposicion.
*/
function imprimirDatos(exposicion) {
    let parent = document.getElementById('dades')
    //--------------------------------------------
    // Creacion de los elementos para los datos de la expo.
    let h3 = document.createElement("h3")
    let ul = document.createElement("ul")
    let lidi = document.createElement("li")
    let lifi = document.createElement("li")
    let licentre = document.createElement("li")
    let lart = document.createElement("li")
    //--------------------------------------------
    // Creacion de los nodos tipo texto con la info de la exposicion.
    let th3 = document.createTextNode(exposicion.titol)
    let tlidi = document.createTextNode("Data inici: " + exposicion.data_ini)
    let tlifi = document.createTextNode("Data fi: " + exposicion.data_fi)
    let tcentre = document.createTextNode("Centre: " + exposicion.centre)
    let tart = document.createTextNode("Artistes: ")
    //--------------------------------------------
    h3.appendChild(th3)
    lidi.appendChild(tlidi)
    lifi.appendChild(tlifi)
    licentre.appendChild(tcentre)
    lart.appendChild(tart)
    ul.appendChild(lidi)
    ul.appendChild(lifi)
    ul.appendChild(licentre)
    ul.appendChild(lart)
    //--------------------------------------------
    // Creacion de los elementos de los artistas.
    for (artista in exposicion.artistes) {
        let ulart = document.createElement("ul")
        let udata = document.createElement("ul")

        let lnom = document.createElement("li")
        let lnac = document.createElement("li")
        let lobres = document.createElement("li")

        let tlnom = document.createTextNode("Nom: " + exposicion.artistes[artista].nom)
        let tlnac = document.createTextNode("Nacionalitat: " + exposicion.artistes[artista].nacionalitat)
        let tobres = document.createTextNode("Obres: " + exposicion.artistes[artista].getObres())

        lnom.appendChild(tlnom)
        lnac.appendChild(tlnac)
        lobres.appendChild(tobres)
        udata.appendChild(lnac)
        udata.appendChild(lobres)
        ulart.appendChild(lnom)
        ulart.appendChild(udata)
        ul.appendChild(ulart)
    }
    parent.appendChild(h3)
    parent.appendChild(ul)
}