/**
 * Exercici 2:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
 * Insertar un editor nou: POST a http://52.178.39.51:8080/editors
 * Modificar un editor: PUT a http://52.178.39.51:8080/editors/745 
 */

const server = "52.178.39.51:8080"

$(function () {
    let urlparams = false
    let id;

    if (window.location.search != "") {
        urlparams = true
        let params = new URLSearchParams(window.location.search);
        id = parseInt(params.get("idEdit"));

        loadEditor(id)
    }

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
})

loadEditor = (id) => {
    let url = `http://${server}/editors/perId/` + id
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                const resultat = JSON.parse(req.responseText);
                // document.getElementById('codi').value = resultat.idEdit
                // document.getElementById('nom').value = resultat.nomEdit
                // document.getElementById('direccion').value = resultat.adrEdit
                $('#codi').val(resultat.idEdit)
                $('#nom').val(resultat.nomEdit)
                $('#direccion').val(resultat.adrEdit)
            } else {
                const missatge = JSON.parse(req.responseText);
                alert(document.createTextNode(missatge.error));
            }
        }
    });
    req.open("GET", url);
    req.setRequestHeader('Accept', "application/json");
    req.send();
}

postEditor = (editor) => {
    let url = `http://${server}/editors`
    console.log(url)
    const req = new XMLHttpRequest();
    req.addEventListener("readystatechange", function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert("Editor insertat correctament");
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