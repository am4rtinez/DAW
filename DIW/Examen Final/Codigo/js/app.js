/**
 * TODO: resize(), 
 * TODO: scroll() (0,4p)
 */

const server = "52.178.39.51:8080"
const imagenes = ['arrosbrut.jpg', 'caracoles.jpg', 'fritomallorquin.jpg', 'gato.jpg', 'tumbet.jpg']
const imgdesc = ['Emplatado de arroz brut', 'Emplatado de caracoles', 'Emplatado de frito mallorquin', 'Emplatado de Gatò', 'Emplatado de Tumbet']
let interval
let pos = 0
let $rotator

$(function () {
  const primaryNav = $(".primary-navigation")
  const navToogle = $(".nav-toggle")

  setTitle('#logo', 'Animate: Modifica la opacidad del logo.\nCon un click para la animación.\nCon doble click vuelve a realizar la animación.')
  setTitle('#previous', 'Retrocede una imagen.')
  setTitle('#play', 'Activa el slider de las imagenes.')
  setTitle('#pause', 'Pausa el slider de las imagenes.')
  setTitle('#stop', 'Para el slider de las imagenes. Reinicia.')
  setTitle('#next', 'Avanza una imagen.')
  
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
})

/**
 * Función que se encarga de poner el atributo title en los elementos por parametro para que luego se visualice un tooltip.
 * @param {*} element 
 * @param {*} titleElement 
 */
function setTitle(element, titleElement) {
  $(element).attr('title', titleElement);
}

/**
 * Funcion que se encarga de poner la imagen en elelmento correspondiente y el valor de la posicion del array.
 * @param {*} image_no 
 * @param {*} element_img 
 */
function setImage(image_no, element_img) {
  $(image_no).val(pos)
  $(element_img).attr('src', "img/" + imagenes[pos])
  $(element_img).attr('alt', imgdesc[pos])
}

/**
 * Establece la imagen anterior.
 * @param {*} image_no 
 * @param {*} element_img 
 */
function previousImage(image_no, element_img) {
  pos = $(image_no).val()
  if (pos <= 0) {
    pos = imagenes.length - 1
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
  if (pos >= (imagenes.length - 1)) {
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
function do_slide(image_no, element_img) {
  interval = setInterval(function () {
    nextImage(image_no, element_img);
  }, 3000);
}

/**
 * Función que para el slide de las imagenes. Y lo reinicia para cuando se vuelve a reproducir.
 */
function stop_slide(image_no) {
  clearInterval(interval)
  pos = 0
  $(image_no).val(pos)
}

/**
 * Función que pausa el slide de imagenes.
 */
function pause_slide() {
  clearInterval(interval)
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