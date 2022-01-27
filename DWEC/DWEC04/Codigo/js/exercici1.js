/*
    Crea una funció que torni un array d'objectes representant un centre cultural. Has de crear aquests objectes de forma literal.
    Per a cada un d'aquests objectes hem de saber el nom, una breu descripció, la web, l'email, el telèfon i la població.
    Crea una pàgina html que contengui un div amb la informació de cada un dels centres culturals que torna la funció.
*/

window.onload = () => {
    let centres = getListCcultura() //Obtiene el listado de objetos literales.
    let parent = document.getElementById("list") //Elemento parent donde se imprimiran los datos.
    for (centre of centres) {
        let div = document.createElement("div")
        let h3 = document.createElement("h3")
        let ul = document.createElement("ul")
        for (item in centre) {
            if (item == "nom") {    //Para el item nom se genera un h3. Para el resto de elementos se genera una lista.
                let t = document.createTextNode(centre[item]);
                h3.appendChild(t)
                div.appendChild(h3)
            } else if (item == "web") { //Para el item web se genera un enlace.
                let li = document.createElement("li")
                let a = document.createElement("a")
                let t = document.createTextNode(centre[item])
                a.appendChild(t)
                a.setAttribute("href", centre[item])
                li.appendChild(a)
                ul.appendChild(li)
            } else {
                let li = document.createElement("li")
                let t = document.createTextNode(item.charAt(0).toUpperCase() + item.slice(1) + ": " + centre[item]);
                li.appendChild(t)
                ul.appendChild(li)
            }
        }
        div.appendChild(ul)
        parent.appendChild(div)
    }
    parent.setAttribute("class", "centre")
}