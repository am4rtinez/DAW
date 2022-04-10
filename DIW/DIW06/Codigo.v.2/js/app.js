/**
 * TODO: focus(), 
 * TODO: resize(), 
 * TODO: scroll() (0,4p)
 * TODO: prepend(), 
 * TODO: after(), 
 * TODO: before(). (0,8p)
 */

const server = "52.178.39.51:8080"
const imagenes = ['frej2011', 'frej2015', 'frej2016', 'frej2017', 'frej2018', 'frej2019']
let interval
let loop = 0
let $rotator

$(function () {
    const primaryNav = $(".primary-navigation")
    const navToogle = $(".nav-toggle")
    const list = $("#llista")
    const input = $('#cercador')
    let contador = 0
    let vcontador = 0
    
    // Loop para animar el logo
    opacityLoop()
    //Hace el set de los titles para que aparezca la info cuando se pasa el raton encima.
    setTitle('#logo', 'Animate: Modifica la opacidad del logo.')
    setTitle('.fa-ship', 'toogleClass: Cambia la clase para que tenga color al mostrar el panel.\nslideToggle: Muestra/oculta panel con una imagen.')
    setTitle('.fa-shield-halved', 'toogleClass: Cambia la clase para que tenga color al mostrar el panel.\nslideToggle: Muestra/oculta panel con una imagen.')
    setTitle('.fa-cross', 'toogleClass: Cambia la clase para que tenga color al mostrar el panel.\nslideToggle: Muestra/oculta panel con una imagen.')
    setTitle('#cercador', 'FocusIn: Cambia el color del fondo del input.\nFocusOut: Restablece el color de fondo.\nKeyPress: Cuenta teclas presionadas.\nKeyUp: Busca editores\nKeyDown: Cuenta las vocales presionadas.')
    setTitle('#off-img-click', 'Off: Detiene el click en las imagenes.')
    setTitleImg('.content-img', '.content-section', 'Click en la imagen hace que desaparezca (fadeOut).', 'Doble click en la seccion y reaparece la imagen (fadeIn).')
    setTitle('#previous', 'Retrocede una imagen.')
    setTitle('#play', 'Activa el slider de las imagenes.')
    setTitle('#pause', 'Pausa el slider de las imagenes.')
    setTitle('#stop', 'Para el slider de las imagenes. Reinicia.')
    setTitle('#next', 'Avanza una imagen.')
    // Hace la busqueda de editores segun lo insertado en el input.
    search(list, input)

    $('#show').hide()

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
    
    $('.fa-ship').click(function (e) { 
        e.preventDefault();
        $(this).toggleClass('red')
        $("#panel").slideToggle(2000)
    });

    $('.fa-shield-halved').click(function (e) { 
        e.preventDefault();
        $(this).toggleClass('blue')
        $("#panel2").slideToggle(2000)
    });

    $('.fa-cross').click(function (e) { 
        e.preventDefault();
        $(this).toggleClass('gold')
        $("#panel3").slideToggle(2000)
    });

    $('#play').click(function (e) {
        do_slide()
        setFaded($(this), true)
        setFaded($('#stop'), false)
        setFaded($('#pause'), false)
    })

    $('#pause').click(function (e) {
        pause_slide()
        setFaded($(this), true)
        setFaded($('#play'), false)
        setFaded($('#stop'), true)
    })

    $('#stop').click(function (e) {
        stop_slide()
        setFaded($(this), true)
        setFaded($('#play'), false)
        setFaded($('#pause'), true)
    })

    $('#previous').click(function (e) {
        previous()
    })

    $('#next').click(function (e) {
        next()
    })
    
    /**
     * Cuando se hace click sobre la imagen con la clase .content-img esta desaparece.
     */
    $(".content-img").on('click', function (e) { 
        e.preventDefault();
        $(this).fadeOut(500);
    });

    /**
     * Cuando se hace doble clic sobre la section (.content-section) aparece la imagen de la sección.
     */
    $('.content-section').dblclick(function (e) { 
        e.preventDefault();
        $(this).find('.content-img').fadeIn(500)
    });

    $('#show').click(function (e) { 
        e.preventDefault();
        $("#info").show();
    });

    $('#cercador')
        .focusin(function (){
            $(this).css('background-color', '#f3f35d')
        })
        .focusout(function (){
            $(this).css('background-color', '#fff')
        })
        .keypress(function (e) { 
            $(".contador").text(contador += 1);
        })
        .keydown(function (e) { 
            if ((e.code == "KeyA") || (e.code == "KeyE") || (e.code == "KeyI") || (e.code == "KeyO") || (e.code == "KeyU")) {
                $(".vcontador").text(vcontador += 1);
            }
        });

    $('#off-img-click').click(function (e) { 
        e.preventDefault();
        $(".content-img").off()
    });

    $('#info').one("click",function (e) { 
        e.preventDefault();
        alert("Clicar sobre el botón ABRACADABRA hara aparecer un panel oculto. \n" + 
                "Clicar sobre el botón ABRACADABRA dos veces, hara desaparecer el panel."
            )
        $("#show").show(1000);
        $(this).hide()
    });

    $('#rotate').click(function (e) { 
        e.preventDefault();
        $rotator = rotateForEver($('.sun'))
    });

    $('#stop-animation').click(function (e) { 
        e.preventDefault();
        $rotator.stop();
    });

    $("#flip")
        .click(function(){
            $("#magic-panel").slideDown(2000)
        })
        .dblclick(function(){
        $("#magic-panel").slideUp(2000)
    });

    $('.social-media-links > a > i')
        .mouseenter(function () { 
            $(this).css('color', '#ffff2c');
            $(this).css('text-shadow', ' 0 0 8px #ffff0c')
        })
        .mouseout(function() {
            $(this).css('color', 'white');
            $(this).css('text-shadow', ' 0 0 0 ')
        });
})

function rotateForEver($elem, rotator) {
    if (rotator === void(0)) {
        rotator = $({deg: 0});
    } else {
        rotator.get(0).deg = 0;
    }

    return rotator.animate(
        {deg: 360},
        {
            duration: 5000,
            easing: 'linear',
            step: function(now){
                $elem.css({transform: 'rotate(' + now + 'deg)'});
            },
            complete: function(){
                rotateForEver($elem, rotator);
            },
        }
    );
}

function setTitle(element, titleElement) {
    $(element).attr('title', titleElement);
}

function setTitleImg(element, parent, titleElement, titleParent) {
    $(element).each(function(){
        setTitle('title', titleElement);
        $(this).closest(parent).attr('title', titleParent)
    })
}

function setFaded(element, status) {
    if (status){
        $(element).fadeTo(1000, 0.4);
        $(element).attr('faded', true)
    } else {
        $(element).fadeTo(1000, 1);
        $(element).attr('faded', false)
    }
}

function slideImage() {
    if (loop > (imagenes.length -1)) {
        loop = 0
        $('#image-no').val(loop)
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[loop] + ".jpg")
    } else {
        $('#image-no').val(loop)
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[loop] + ".jpg")
        loop++
    }
}

function previous() {
    let pos = $('#image-no').val()
    if (pos <= 0) {
        pos = imagenes.length -1
        $('#image-no').val(pos)
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[pos] + ".jpg")
    } else {
        pos = pos - 1
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[pos] + ".jpg")
        $('#image-no').val(pos)
    }
}

function next() {
    let pos = $('#image-no').val()
    if (pos >= imagenes.length -1) {
        pos = 0
        $('#image-no').val(pos)
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[pos] + ".jpg")
    } else {
        pos++
        $('.gallery-img').attr('src', "img/gallery/" + imagenes[pos] + ".jpg")
        $('#image-no').val(pos)
    }
}

function do_slide(){
    interval = setInterval(function(){
      slideImage();
    }, 3000);
}

function stop_slide(){
    clearInterval(interval)
    loop = 0
    $('#image-no').val(loop)
}

function pause_slide(){
    clearInterval(interval)
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

function opacityLoop(){
    $('#logo')
      .animate({opacity:0.9},1000)
      .animate({opacity:0.2},1000, opacityLoop);
}

function pauseOpacityLoop() {
    $('#logo').click(function (e) {
        $('#logo').stop()
    })
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