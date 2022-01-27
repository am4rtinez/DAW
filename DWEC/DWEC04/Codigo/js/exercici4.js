/*
    Crea una funció que simuli un servei REST.

    Aquesta funció:

        Ha de simular una consulta a una base de dades demanant els centres d'art que hi ha a una determinada població.

        Per a cada centre ha de tornar un objecte amb les mateixes dades que els de l'exercici 1.

        Tornau dos o tres centres, sempre els mateixos.

        Ha de tornar el resultat en format JSON.

    Crea una pàgina html que mostri el resultat d’una crida a aquesta funció en forma de taula, amb una columna per a cada dada.
*/

window.onload = () => {
    let poblacio = "Calvia"
    let str = "Llistat de Centres Culturals de " + poblacio
    setTitol("titol", "h3", str)
    let jsonString = getCentresPoblacio(poblacio)
    let json = parseJSON(jsonString)
    imprimirTabla(json)
}

function setTitol(id, tag, str) {
    let parent = document.getElementById(id)
    let h = document.createElement(tag)
    let t = document.createTextNode(str)
    h.appendChild(t)
    parent.appendChild(h)
}

function imprimirTabla(json){
    let parent = document.getElementById("tabla-body")
    for (item of json) {
        let tr = document.createElement("tr")
        for (campo in item) {
            let td = document.createElement("td")
            let t = document.createTextNode(item[campo])
            if (campo == "web") {
                let a = document.createElement("a")
                a.appendChild(t)
                a.setAttribute("href", item[campo])
                td.appendChild(a)
            } else {
                td.appendChild(t)
            }
            tr.appendChild(td)
        }
        parent.appendChild(tr)
    }
}