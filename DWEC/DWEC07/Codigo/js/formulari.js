/**
 * Exercici 2:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
 * Insertar un editor nou: POST a http://52.178.39.51:8080/editors
 * Modificar un editor: PUT a http://52.178.39.51:8080/editors/745 
 */

const server = "52.178.39.51:8080"

$(function () {
    let urlparams = false

    //Comprueba si la url contiene parametros ?
    if (checkUrlParams()) {
        urlparams = true
        // let params = new URLSearchParams(window.location.search);
        // id = parseInt(params.get("idEdit"));

        // Obtiene e imprime los datos del editor.
        getEditor(getParam("idEdit"))
    }

    // Captura del evento submit.
    $("#formEditor").submit((e) => {
        e.preventDefault();
        let editor = {};
    
        if (urlparams) {
            editor.idEdit = $('#codi').val();
            editor.nomEdit = $('#nom').val();
            editor.adrEdit = $('#direccion').val();
            putEditor(editor)
        } else {
            editor.idEdit = null;
            editor.nomEdit = $('#nom').val();
            editor.adrEdit = $('#direccion').val();
            postEditor(editor)
        }
    });

    $("#formElimina").submit((e) => {
        e.preventDefault();
        let editor = {};
    
        if (urlparams) {
            editor.idEdit = $('#codi').val();
            editor.nomEdit = $('#nom').val();
            editor.adrEdit = $('#direccion').val();
            deleteEditor(editor)
        }
    });
})

checkUrlParams = () => {
    if (window.location.search != "") { return true } else { return false }
}

getParam = (item) => {
    let params = new URLSearchParams(window.location.search);
    return parseInt(params.get(item))
}

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
                alert(document.createTextNode(missatge.error));
                return null
            }
        }
    });
    req.open("GET", url);
    req.setRequestHeader('Accept', "application/json");
    req.send();
}

loadEditor = (editor) => {
    $('#codi').val(editor.idEdit)
    $('#nom').val(editor.nomEdit)
    $('#direccion').val(editor.adrEdit)
}

postEditor = (editor) => {
    let url = `http://${server}/editors`
    console.log(url)
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                const res = JSON.parse(req.responseText);
                console.log(res.idEdit)
                textAlert =  `Editor insertado correctamente con los siguientes datos: 
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

putEditor = (editor) => {
    let url = `http://${server}/editors/` + editor.idEdit
    console.log(url)
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert("Editor modificat correctament");
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

deleteEditor = (editor) => {
    let url = `http://${server}/editors/${editor.idEdit}`
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert("Editor modificat correctament");
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