// URL API editors 
/**
 * URL's de l'api
 * 
 * Exercici 1: 
 * http://52.178.39.51:8080/editors/partNom/{text}
 * 
 * Exercici 2:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/{id}
 * Insertar un editor nou: POST a http://52.178.39.51:8080/editors
 * Modificar un editor: PUT a http://52.178.39.51:8080/editors/{id} 
 * 
 * Exercici 3:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/{id}
 * Eliminar l'editor: DELETE a a http://52.178.39.51:8080/editors/{id}
 */

const server = "52.178.39.51:8080"

$(function () {
    const page = getPage()
    let urlparams = checkUrlParams()
    switch (page) {
        case 'index.html':
            const list = $("#llista")
            const input = $('#cercador')
            search(list, input)
            break
        case 'formulari.html':
            findEditorParams(urlparams, "idEdit")
            // Captura del evento submit.
            $("#formEditor").submit((e) => {
                e.preventDefault();
                // Si la url contiene parametros se realiza una modificación.
                // En caso contrario se realiza la inserción de un nuevo editor.
                if (urlparams) {
                    putEditor(setEditor($('#codi').val(), $('#nom').val(), $('#direccion').val()))
                } else {
                    postEditor(setEditor(null, $('#nom').val(), $('#direccion').val()))
                }
            });
            break
        case 'elimina.html':
            findEditorParams(urlparams, "idEdit")
            // Captura del evento submit.
            $("#formElimina").submit((e) => {
                e.preventDefault();
                // Si la url contiene parametros se procede a la eliminación.
                if (urlparams) {
                    deleteEditor(setEditor($('#codi').val(), $('#nom').val(), $('#direccion').val()))
                }
            });
            break
    }
})

/**
 * Funcion que obtiene la pagina del pathname para asi poder ejecutar distintas operaciones.
 */
getPage = () => {
    const path = location.pathname
    const lastItem = path.substring(path.lastIndexOf('/') + 1)
    return lastItem
}

/**
 * Funcion que comprueba si la URL contiene parámetros.
 */
checkUrlParams = () => {
    if (window.location.search != "") { return true }
}

/**
 * Funcion que obtiene el parametro "item" de la url.
 * @param {*} item 
 */
getParam = (item) => {
    let params = new URLSearchParams(window.location.search);
    return parseInt(params.get(item))
}

/**
 * Funcion que crea los items li que componen la lista.
 * @param {*} parent - Elemento parent al que pertenecera el item li.
 * @param {*} element - Elemento texto que aparecera en el li.
 * @param {*} id - Identificador del editor.
 */
createItemsList = (parent, element, id) => {
    const modifButton = createButton("btn-warning", "Modifica", "formulari.html", id)
    const delButton = createButton("btn-danger", "Elimina", "elimina.html", id)
    let itemLi = $('<li></li>')
    let text = element + " (" + id + ") " 
    itemLi.append(text)
    itemLi.append(modifButton)
    itemLi.append(delButton)
    parent.append(itemLi)
}

/**
 * Funcion que crea los botones.
 * @param {*} clase 
 * @param {*} text 
 * @param {*} url 
 * @param {*} id 
 */
createButton = (clase, text, url, id) => {
    let button = $(`<button class="btn ${clase}">${text}</button>`)
    button.click(function (e) { 
        e.preventDefault();
        window.location.assign(`${url}?idEdit=${id}`); // Navega a la URL asignando el parametro idEdit.
    });
    return button
}

/**
 * Funcion que realiza la busqueda de los editores cuando se modifica el input de texto.
 */
function search(listElement, inputElement) {
    // Asigna el listener
    inputElement.keyup(function (e) { 
        e.preventDefault();
        listElement.empty()
        getEditorsPartNom(listElement, inputElement)
    });
}

/**
 * Funcion que obtiene el editor si existe el parámetro en la url de idEdit.
 * @param {*} urlparams 
 */
function findEditorParams(urlparams, param) {
    if (urlparams) {
        getEditor(getParam(param))
    }
}

/**
 * Ejercicio 1. jQuery.
 */
getEditorsPartNom = (listElement, inputElement) => {
    if (inputElement.val() != '') {
        $.get(`http://${server}/editors/partNom/${inputElement.val()}`, "json", function (res) {
            $(res).each(function () {
                createItemsList(listElement, this.nomEdit, this.idEdit)
            });
        });
    }
}

/**
 * Funcion que obtiene los datos de un editor a partir de su id.
 * Operacion de GET a traves del API Rest.
 * @param {*} id 
 */
getEditor = (id) => {
    let url = `http://${server}/editors/perId/` + id
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                const res = JSON.parse(req.responseText);
                loadEditor(res)
            } else {
                const missatge = JSON.parse(req.responseText);
                alert(missatge.error);
                return null
            }
        }
    });
    req.open("GET", url);
    req.setRequestHeader('Accept', "application/json");
    req.send();
}

/**
 * Funcion que crea un editor y lo devuelve.
 * @param {*} id 
 * @param {*} nom 
 * @param {*} adr 
 */
setEditor = (id, nom, adr) => {
    let editor = {}
    editor.idEdit = id
    editor.nomEdit = nom
    editor.adrEdit = adr
    return editor
}

/**
 * Funcion que pinta los datos del editor.
 * @param {*} editor 
 */
loadEditor = (editor) => {
    $('#codi').val(editor.idEdit)
    $('#nom').val(editor.nomEdit)
    $('#direccion').val(editor.adrEdit)
}

/**
 * Funcion que se encarga de realizar la inserción de un editor.
 * Operacion de POST a traves del API Rest.
 * @param {*} editor 
 */
postEditor = (editor) => {
    let url = `http://${server}/editors`
    console.log(url)
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                const res = JSON.parse(req.responseText);
                let textAlert =  `Editor insertado correctamente con los siguientes datos: 
                    \n * ID: ${res.idEdit} 
                    \n * Nombre: ${res.nomEdit}
                    \n * Dirección: ${res.adrEdit}`
                alert(textAlert);
                document.location = "index.html";
            } else {
                const missatge = JSON.parse(req.responseText);
                alert(missatge.error);
            }
        }
    });
    req.open("POST", url);
    req.setRequestHeader('Content-Type', "application/json");
    req.setRequestHeader('Accept', "application/json");
    req.send(JSON.stringify(editor));
}

/**
 * Funcion que se encarga de realizar la modificación de un editor.
 * Operacion de PUT a traves del API Rest.
 * @param {*} editor 
 */
putEditor = (editor) => {
    let url = `http://${server}/editors/` + editor.idEdit
    console.log(url)
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert("Editor modificado satisfactoriamente.");
                document.location = "index.html";
            } else {
                const missatge = JSON.parse(req.responseText);
                alert(missatge.error);
            }
        }
    });
    req.open("PUT", url);
    req.setRequestHeader('Content-Type', "application/json");
    req.setRequestHeader('Accept', "application/json");
    req.send(JSON.stringify(editor));
}

/**
 * Funcion que se encarga de realizar la eliminación de un editor.
 * Operacion de DELETE a traves del API Rest.
 * @param {*} editor 
 */
deleteEditor = (editor) => {
    let url = `http://${server}/editors/${editor.idEdit}`
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert("Editor eliminado satisfactoriamente.");          
                document.location = "index.html";
            } else {
                const missatge = JSON.parse(req.responseText);
                alert(missatge.error);
            }
        }
    });
    req.open("DELETE", url);
    req.setRequestHeader('Content-Type', "application/json");
    req.setRequestHeader('Accept', "application/json");
    req.send(JSON.stringify(editor));
}