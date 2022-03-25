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

    $('#cercador').change(function (e) {
        e.preventDefault();
        $("#llista").empty();
        if ($('#cercador').val() != '') { 
            $.get(`http://${server}/editors/partNom/TE/`, 
            "json", function (resultat) {
                    $(resultat).each(function () {
                    if (this.nomEdit.includes($('#cercador').val().toUpperCase())) {
                        creaItemsList($("#llista"), this.nomEdit, this.idEdit)
                    }
                });
            });
        }
    });
})

creaItemsList = (parent, element, id) => {
    $(parent).append("<li>" + element + 
        '<button type="button" class="modifEditor">Modifica</button>' +
        '<button type="button" class="delEditor">Elimina</button>' +
        "</li>")
    $('.modifEditor').click(function (e) { 
        e.preventDefault();
        console.log("Editor id: " + id)
    });
    $('.delEditor').click(function (e) { 
        e.preventDefault();
        console.log("Editor id: " + id)
    });
}
