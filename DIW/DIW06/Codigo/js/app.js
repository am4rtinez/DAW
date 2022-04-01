/**
 * DONE: Mètodes d'advents de jQuery (Els heu de fer tots): (2,4p) ◦
 * on(), 
 * off(), 
 * one() (0,6p)◦
 * DONE: click(), 
 * dblclick(), 
 * mouseenter(), 
 * mouseleave() (0,2p)
 * Keypress(), 
 * keydown(), 
 * DONE: keyup(). (0,6p)
 * focus(), 
 * focusin(), 
 * focusout(). (0,6p)
 * resize(), 
 * scroll() (0,4p)
 * 
 * DONE: Efectes (Els heu de fer tots) (3,3p)
 * DONE: fadein(), fadeout(), fadetoggle(), fadeTo().  (0,8p). (0,2 respectivament)
 * slideDown(), 
 * slideUp(), 
 * DONE: slideToggle(). (0,6p). (0,2 resp)
 * animate():(0,6p)
 *  amb valors relatius. (0,2p)
 *  amb valors predefinits. (0,2p)
 *  amb funcionalitat de cua. (0,2p)
 * stop(). (0,2p)◦
 * DONE: hide(), 
 * DONE: show(),  (0,4p).
 * DONE: Utilització de seqüències de funcions (Callback). (0,5p)
 * Encadenament() (0,2p)
 * 
 * DONE: jQuery HTML (Els heu de fer tots): (1,3p)
 * DONE: append(), 
 * prepend(), 
 * after(), 
 * before(). (0,8p)
 * Manipulació CSS. (0,5p)
 */

 const server = "52.178.39.51:8080"

$(function () {
    const primaryNav = $(".primary-navigation");
    const navToggle = $(".nav-toggle");
    const navIcon = $('#nav-icon')
    const list = $("#llista")
    const input = $('#cercador')
    search(list, input)

    navToggle.click(function (e) { 
        e.preventDefault();
        navIcon.toggleClass("fa-times");
        primaryNav.slideToggle();
    });

    $(".img-fadeout").click(function (e) { 
        e.preventDefault();
        $(this).fadeOut();
    });

    $('#restore').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeIn();
    });

    $('#fadetoggle').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeToggle(3000);
    });

    $("#fadeto").click(function(){
        let img =  $(".img-fadeout")
        console.log(img.attr('faded'))
        if (img.attr('faded') == "false"){
            img.fadeTo(1000, 0.4);
            img.attr('faded', true)
        } else {
            img.fadeTo(1000, 1);
            img.attr('faded', false)
        }
    });

    $('#hide').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").hide();
    });

    $('#show').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").show();
    });
})

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
