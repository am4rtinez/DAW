/**
 * DONE: Mètodes d'advents de jQuery (Els heu de fer tots): (2,4p) ◦
 * DONE: on(), 
 * DONE: off(), 
 * DONE: one() (0,6p)◦
 * DONE: click(), 
 * DONE: dblclick(), 
 * DONE: mouseenter(), 
 * DONE: mouseleave() (0,2p)
 * DONE: Keypress(), 
 * DONE: keydown(), 
 * DONE: keyup(). (0,6p)
 * focus(), 
 * DONE: focusin(), 
 * DONE: focusout(). (0,6p)
 * resize(), 
 * scroll() (0,4p)
 * 
 * DONE: Efectes (Els heu de fer tots) (3,3p)
 * DONE: fadein(), fadeout(), fadetoggle(), fadeTo().  (0,8p). (0,2 respectivament)
 * DONE: slideDown(), 
 * DONE: slideUp(), 
 * DONE: slideToggle(). (0,6p). (0,2 resp)
 * DONE: animate():(0,6p)
 *  amb valors relatius. (0,2p)
 *  amb valors predefinits. (0,2p)
 *  amb funcionalitat de cua. (0,2p)
 * DONE: stop(). (0,2p)◦
 * DONE: hide(), 
 * DONE: show(),  (0,4p).
 * DONE: Utilització de seqüències de funcions (Callback). (0,5p)
 * DONE: Encadenament() (0,2p)
 * 
 * DONE: jQuery HTML (Els heu de fer tots): (1,3p)
 * DONE: append(), 
 * prepend(), 
 * after(), 
 * before(). (0,8p)
 * DONE: Manipulació CSS. (0,5p)
 */

 const server = "52.178.39.51:8080"

$(function () {
    const primaryNav = $(".primary-navigation")
    const navToogle = $(".nav-toggle")
    const list = $("#llista")
    const input = $('#cercador')
    let contador = 0
    let vcontador = 0
    
    // Loop para animar el logo
    opacityLoop()

    search(list, input)

    navToogle.click(function (e) { 
        e.preventDefault();
        let visibility = primaryNav.attr('data-visible');
        $('#nav-icon').toggleClass("fa-times");
        if (visibility === "false") {
            primaryNav.attr('data-visible', true);
        } else {
            primaryNav.attr('data-visible', false);
        }
    });

    $(".img-fadeout").click(function (e) { 
        e.preventDefault();
        $(this).fadeOut(500);
    });

    $('#restore').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeIn(500);
    });

    $('body').dblclick(function (e) { 
        e.preventDefault();
        $(".banner-area").fadeToggle(500);
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
        $(".img-fadeout").hide(1000);
    });

    $('#show').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").show(1000);
    });

    $('#cercador')
        .keypress(function (e) { 
            $(".contador").text(contador += 1);
        })
        .focusin(function(){
            $(this).css("background", "#D8E0E7")
        })
        .focusout(function(){
            $(this).css("background", "#fff")
            $(this).css("border", "solid 1px grey")
        })
        .keydown(function (e) { 
            if ( (e.code == "KeyA") || (e.code == "KeyE") || (e.code == "KeyI") || (e.code == "KeyO") || (e.code == "KeyU")) {
                $(".vcontador").text(vcontador += 1);
            }
        });

    $('#off-img-click').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").off()
    });

    $('#info').one("click",function (e) { 
        e.preventDefault();
        alert("Clicar sobre uno de los emojis hara que desaparezca. FadeOut(). \n" + 
                "Clicar sobre el boton Restaura imagenes, restaurara las imagenes. FadeIn(). \n" + 
                "Clicar sobre el boton Muesta/Oculta hará que aparezcan/desaparezcan los emojis.\n" +
                "Clicar sobre el boton Transparencia hará que los emojis pierdan o ganen opacidad.\n" +
                "Clicar sobre el boton Ocultar hará que los emojis desaparezcan.\n" + 
                "Clicar sobre el boton Mostrar hará que los emojis reaparezcan.\n" +
                "Clicar sobre el boton Off hará que el click sobre los emojis no funcione.\n" +
                "Clicar sobre el boton Info mostrará este alert una sola vez."
            )
    });

    $('#rotate').click(function (e) { 
        e.preventDefault();
        $('.sun').animate(
            // {  transform: degree },
            {degrees: 359},
            {
                duration: 3500,
                easing: "linear",
                step: function(now) {
                    $(this).css({
                        transform : 'rotate('+now+'deg)'
                    });
                }
            }
        );
    });

    $('#stop').click(function (e) { 
        e.preventDefault();
        $('.sun').stop();
    });

    $("#flip")
        .click(function(){
            $("#panel").slideDown(2000)
        })
        .dblclick(function(){
        $("#panel").slideUp(2000)
    });

    $('.social-media-links > a > i')
        .mouseenter(function () { 
            $(this).css('color', '#23ff00');
        })
        .mouseout(function() {
            $(this).css('color', 'white');
        })
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

function opacityLoop(){
    $('#logo')
      .animate({opacity:0.9},1000)
      .animate({opacity:0.2},1000, opacityLoop);
}

/**
 * Ejercicio 1. jQuery.
 */
function getEditorsPartNom (listElement, inputElement) {
    if (inputElement.val() != '') {
        $('.lista').show()
        $.get(`http://${server}/editors/partNom/${inputElement.val()}`, "json", function (res) {
            $(res).each(function () {
                createItemsList(listElement, this.nomEdit, this.idEdit)
            });
        });
    } else {
        $('.lista').hide()
    }
}

/**
 * Funcion que crea los items li que componen la lista.
 * @param {*} parent - Elemento parent al que pertenecera el item li.
 * @param {*} element - Elemento texto que aparecera en el li.
 * @param {*} id - Identificador del editor.
 */
function createItemsList (parent, element, id) {
    const modifButton = createButton("btn-warning", "Modifica", "#", id)
    const delButton = createButton("btn-danger", "Elimina", "#", id)
    let itemLi = $('<li></li>')
    let itemDiv = $('<div class="flex-container"></div>')
    let text = "<div class='col'><p class='editor'>" + element + " (" + id + ")</p></div>" 
    itemDiv.append(text)
    itemDiv.append(modifButton)
    itemDiv.append(delButton)
    itemLi.append(itemDiv)
    parent.append(itemLi)
}

/**
 * Funcion que crea los botones.
 * @param {*} clase 
 * @param {*} text 
 * @param {*} url 
 * @param {*} id 
 */
function createButton (clase, text, url, id) {
    let button = $(`<div class='col'><button class="btn ${clase}">${text}</button></div>`)
    button.click(function (e) { 
        e.preventDefault();
        window.location.assign(`${url}?idEdit=${id}`); // Navega a la URL asignando el parametro idEdit.
    });
    return button
}