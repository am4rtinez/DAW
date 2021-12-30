window.onload = () => {
    let lactores = getNomsActors() // Devuelve el array de los actores, no incluye el id.
    listActorsOrder(lactores, "desordenats") // Funcion que lista los actores sin orden, no se le pasa el parámetro de ordenar.
    listActorsOrder(lactores, "ordenats", true) // Funcion que lista los actores ordenados, se le pasa el parámetro de ordenar.
    search(lactores, "cerca")
}

// Funcion que se encarga de listar los actores segun si se le indica por parametro.
// actores - listado de actores
// parent - elemento del cual dependera el listado.
// ordered -  parametro en el que se le indica si es una lista ordenada (en orden descendiente) o no.
function listActorsOrder(actores, parent, ordered) {
    let ac = Array.from(actores); // Hacemos una copia del array para que no modifique el original.
    if (ordered) {  // Lista ordenada
        ac.sort()
        ac.reverse()
        listar(ac, parent)
    } else {    // Lista desordenada.
        listar(ac, parent)
    }
}

// Funcion que se encarga de imprimir en forma de lista el listado de actores que se pasa por parametro.
// actores - listado de actores
// parent - elemento del cual dependera el listado.
function listar(actores, parent) {
    let ul = document.createElement("ul");
    for (actor in actores) {
        let li = document.createElement("li")
        let t = document.createTextNode(actores[actor])
        li.appendChild(t)
        ul.appendChild(li)
    }
    document.getElementById(parent).appendChild(ul)
}

// Funcion que se encarga de generar un array refactorizado para hacer mas facil la tarea de busqueda.
// actores - listado de actores
function refactor(actores) {
    let res = []
    for (actor in actores) {
        let splitarray = actores[actor].split(", ")
        let lastname = splitarray[0]
        let firstname = splitarray[1]
        res.push({'apellido': lastname, 'nombre': firstname})
    }
    return res
}

// Funcion que realiza la busqueda del apellido introducido por el prompt.
// actores - listado de actores
// parent - elemento del cual dependera el listado a imprimir.
function search(actores, parent) {
    let str = prompt("Insertar un apellido a buscar: ");
    str = str.toUpperCase() // Convierte en mayusculas el fichero.
    let sarray = [] // Array para almacenar los resultados de la busqueda.
    let refactores = refactor(actores) // Genero un refactorizado para facilitarme la busqueda.

    for (actor of refactores) {
        if (actor.apellido == str) {
            sarray.push(actor.apellido + ", " + actor.nombre)
        }
    }

    if (sarray.length > 0) {
        listar(sarray, parent)
    } else {
        let p = document.createElement("p");
        let t = document.createTextNode("El criterio de busqueda no coincide con ningún apellido.");
        p.appendChild(t);
        document.getElementById(parent).appendChild(p);
    }
}