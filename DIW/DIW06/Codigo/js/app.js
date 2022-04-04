/**
 * DONE: Mètodes d'advents de jQuery (Els heu de fer tots): (2,4p) ◦
 * DONE: on(), 
 * DONE: off(), 
 * DONE: one() (0,6p)◦
 * DONE: click(), 
 * DONE: dblclick(), 
 * mouseenter(), 
 * mouseleave() (0,2p)
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
 * animate():(0,6p)
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
 * Manipulació CSS. (0,5p)
 */

 const server = "52.178.39.51:8080"

$(function () {
    const primaryNav = $(".primary-navigation");
    const navToggle = $(".nav-toggle");
    const navIcon = $('#nav-icon')
    const list = $("#llista")
    const input = $('#cercador')
    let contador = 0
    let vcontador = 0
    search(list, input)

    navToggle.click(function (e) { 
        e.preventDefault();
        navIcon.toggleClass("fa-times");
        primaryNav.slideToggle();
    });

    $(".img-fadeout").click(function (e) { 
        e.preventDefault();
        $(this).fadeOut(500);
    });

    $('#restore').click(function (e) { 
        e.preventDefault();
        $(".img-fadeout").fadeIn(500);
    });

    $('#fadetoggle').on("click", function (e) { 
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
            $(this).css("border", "solid 1px black")
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
                step: function(now,fx) {
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

    $("#flip").click(function(){
        $("#panel").slideDown(2000)
    });
    
    $("#flip").dblclick(function(){
        $("#panel").slideUp(2000)
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

function AnimateRotate(angle) { 
    // caching the object for performance reasons 
    var $elem = $('.sun'); 
    // we use a pseudo object for the animation 
    // (starts from `0` to `angle`), you can name it as you want 
    $({deg: 0}).animate({deg: angle}, { 
        duration: 2000, 
        step: function(now) { 
            // in the step-callback (that is fired each step of the animation), 
            // you can use the `now` paramter which contains the current 
            // animation-position (`0` up to `angle`) 
            $elem.css({ 
                transform: 'rotate(' + now + 'deg)' 
            })
        }
    })
}