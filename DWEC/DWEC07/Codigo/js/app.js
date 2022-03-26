// URL API editors 
/**
 * URL's de l'api
 * 
 * Exercici 1: 
 * http://52.178.39.51:8080/editors/partNom/TE
 * 
 * Exercici 2:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
 * Insertar un editor nou: POST a http://52.178.39.51:8080/editors
 * Modificar un editor: PUT a http://52.178.39.51:8080/editors/745 
 * 
 * Exercici 3:
 * Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
 * Eliminar l'editor: DELETE a a http://52.178.39.51:8080/editors/745
 */

const server = "52.178.39.51:8080"

$(() => {
    //Inicializa el buscador.
    console.log(window.location.pathname)
    search()

})

createItemsList = (parent, element, id) => {
    modifButton = createModifButton("Modifica", id)
    delButton = createDelButton("Elimina", id)
    itemLi = $('<li></li>')
    text = element + " (" + id + ") " 
    itemLi.append(text)
    itemLi.append(modifButton)
    itemLi.append(delButton)
    parent.append(itemLi)
}

createModifButton = (text, id) => {
    button = $(`<button class="btn btn-warning">${text}</button>`)
    button.click(function (e) { 
        e.preventDefault();
        console.log("Editor id: " + id)
        // Navigate to the Location.reload article
        window.location.assign('formulari.html?idEdit=' + id);
    });
    return button
}

createDelButton = (text, id) => {
    button = $(`<button class="btn btn-danger">${text}</button>`)
    button.click(function (e) { 
        e.preventDefault();
        console.log("Editor id: " + id)
        // Navigate to the Location.reload article
    });
    return button
}

search = () => {
    const llista = $("#llista")
    const input = $('#cercador')

    input.keyup(function () { 
        // e.preventDefault();
        llista.empty()
        if (input.val() != '') { 
            $.get(`http://${server}/editors/partNom/TE/`, "json", function (resultat) {
                $(resultat).each(function () {
                    if (this.nomEdit.includes(input.val().toUpperCase())) {
                        createItemsList(llista, this.nomEdit, this.idEdit)
                    }
                });
            });
        }
    });
}