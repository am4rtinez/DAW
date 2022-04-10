/**
 * TODO: resize(), 
 * TODO: scroll() (0,4p)
 */

const server = "52.178.39.51:8080"
const imagenes = ['frej2011', 'frej2015', 'frej2016', 'frej2017', 'frej2018', 'frej2019']
let interval
let pos = 0
let $rotator

$(function () {
    const primaryNav = $(".primary-navigation")
    const navToogle = $(".nav-toggle")
    const list = $("#llista")
    const input = $('#cercador')
    let contador = 0
    let vcontador = 0
    
    //Hace el set de los titles para que aparezca la info cuando se pasa el raton encima.
    setTitle('#logo', 'Animate: Modifica la opacidad del logo.\nCon un click para la animación.\nCon doble click vuelve a realizar la animación.')
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
    setTitle('#flip', 'Muestra/oculta un panel mágico. slideDown() y slideUp().')
    setTitle('#info', 'Muestra un alert con información una única vez (one()).')
    setTitle('#show', 'Muestra el boton info nuevamente (show()).')
    setTitle('#rotate', 'Activa la animación del sol.')
    setTitle('#stop-animation', 'Para la animación del sol.')

    // Hace la busqueda de editores segun lo insertado en el input.
    search(list, input)

    // Loop para animar el logo
    opacityLoop($('#logo'))

    //Controla los eventos de click y doble click del logo para animarlo o no.
    $('#logo')
        .click(function (e) { 
            e.preventDefault();
            pauseOpacityLoop($(this))
        })
        .dblclick(function (e) {
            e.preventDefault();
            opacityLoop($(this))
        })
    
    // Oculta el boton show inicialmente.
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
        do_slide($('#image-no'), $('.gallery-img'))
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
        stop_slide($('#image-no'))
        setFaded($(this), true)
        setFaded($('#play'), false)
        setFaded($('#pause'), true)
    })

    $('#previous').click(function (e) {
        previousImage()
    })

    $('#next').click(function (e) {
        nextImage($('#image-no'), $('.gallery-img'))
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

    // Si se clica sobre este elemento pausa el evento de las imagenes.
    $('#off-img-click').click(function (e) { 
        e.preventDefault();
        $(".content-img").off()
    });

    // Habilita un solo click para el boton info. Despues desaparece. Y habilita el boton show.
    $('#info').one("click",function (e) { 
        e.preventDefault();
        alert("Clicar sobre el botón ABRACADABRA hara aparecer un panel oculto. \n" + 
                "Clicar sobre el botón ABRACADABRA dos veces, hara desaparecer el panel."
            )
        $("#show").show(1000);
        $(this).hide()
    });

    //Cuando se clica sobre el boton show aparece el boton info oculto pero sin funcionalidad alguna (one()).
    $('#show').click(function (e) { 
        e.preventDefault();
        $("#info").show();
    });

    $('.animation-section').prepend("<div class='section-header'><h2 class='text-center'>Anima este sol.</h2></div>");
    //Inicia la animación de rotacion.
    $('#rotate')
        .click(function (e) { 
            e.preventDefault();
            $rotator = rotateForEver($('.sun'))
        })
        .focus(function (e) { 
            e.preventDefault();
            $('.animation-p').remove()
            $(this).after("<div class='animation-p text-center'><p>Rotacion iniciada</p></div>")
        });

    //Para la animación de rotación.
    $('#stop-animation').click(function (e) { 
        e.preventDefault();
        $rotator.stop();
        $('.animation-p').remove()
        $(this).before("<div class='animation-p text-center'><p>Rotacion parada</p></div>")
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

/**
 * Función para habilitar la animación infinita de rotación.
 * @param {*} $elem 
 * @param {*} rotator 
 * @returns 
 */
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

/**
 * Función que se encarga de poner el atributo title en los elementos por parametro para que luego se visualice un tooltip.
 * @param {*} element 
 * @param {*} titleElement 
 */
function setTitle(element, titleElement) {
    $(element).attr('title', titleElement);
}

/**
 * Función similar a SetTitle pero en este caso para imagenes y el parent de estas.
 * @param {} element 
 * @param {*} parent 
 * @param {*} titleElement 
 * @param {*} titleParent 
 */
function setTitleImg(element, parent, titleElement, titleParent) {
    $(element).each(function(){
        setTitle('title', titleElement);
        $(this).closest(parent).attr('title', titleParent)
    })
}

/**
 * Funcion que establece el estatus faded de un elemento.
 * @param {*} element 
 * @param {*} status 
 */
function setFaded(element, status) {
    if (status){
        $(element).fadeTo(1000, 0.4);
        $(element).attr('faded', true)
    } else {
        $(element).fadeTo(1000, 1);
        $(element).attr('faded', false)
    }
}

/**
 * Funcion que se encarga de poner la imagen en elelmento correspondiente y el valor de la posicion del array.
 * @param {*} image_no 
 * @param {*} element_img 
 */
function setImage (image_no, element_img) {
    $(image_no).val(pos)
    $(element_img).attr('src', "img/gallery/" + imagenes[pos] + ".jpg")
}

/**
 * Establece la imagen anterior.
 * @param {*} image_no 
 * @param {*} element_img 
 */
function previousImage(image_no, element_img) {
    pos = $(image_no).val()
    if (pos <= 0) {
        pos = imagenes.length -1
        setImage(image_no, element_img)
    } else {
        pos = pos - 1
        setImage(image_no, element_img)
    }
}

/**
 * Función que muestra la imagen siguiente.
 * @param {*} image_no 
 * @param {*} element_img 
 */
function nextImage(image_no, element_img) {
    pos = $(image_no).val()
    if (pos >= (imagenes.length -1)) {
        pos = 0
        setImage(image_no, element_img)
    } else {
        pos++
        setImage(image_no, element_img)
    }
}

/**
 * Función que inicia el slide de las imagenes
 * @param {*} image_no 
 * @param {*} element_img 
 */
function do_slide(image_no, element_img){
    interval = setInterval(function(){
      nextImage(image_no, element_img);
    }, 3000);
}

/**
 * Función que para el slide de las imagenes. Y lo reinicia para cuando se vuelve a reproducir.
 */
function stop_slide(image_no){
    clearInterval(interval)
    pos = 0
    $(image_no).val(pos)
}

/**
 * Función que pausa el slide de imagenes.
 */
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

function opacityLoop(element){
    $(element)
      .animate({opacity:0.9},1000)
      .animate({opacity:0.2},1000, function(){
          opacityLoop(element)
      });
}

/**
 * Pausa la animación de opacidad del logo.
 */
function pauseOpacityLoop(element) {
    $(element).click(function (e) {
        $(element).stop()
    })
}

/**
 * Ejercicio 1. jQuery DWEC07
 * Obtiene una lista de editores (parte del nombre) a partir de un texto.
 * Devuelve 10 editores máximo.
 * @param {*} listElement 
 * @param {*} inputElement 
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