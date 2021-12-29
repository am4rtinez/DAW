window.onload = () => {
    let actores = getNomsActors() //Funcion que devuelve el nombre de los actores.
    printActorsOrder(actores, "desordenats")
    printActorsOrder(actores,"ordenats", true)
}

function printActorsOrder(actores, parent, ordered) {
    console.log(actores)
    if (ordered) {
        // Lista ordenada
        actores.sort();
        actores.reverse();
        print(actores, parent)
    } else {
        // Lista desordenada.
        print(actores, parent)
    }
}

function print(actores, parent) {
    let ul = document.createElement("ul");
    for (actor in actores) {
        // console.log(actores[actor])
        let li = document.createElement("li")
        let t = document.createTextNode(actores[actor])
        li.appendChild(t)
        ul.appendChild(li)
    }
    document.getElementById(parent).appendChild(ul);
}