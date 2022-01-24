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
    console.log(expo.artistes)
    expo.delArtista(a2)
    console.log(expo.artistes)
}