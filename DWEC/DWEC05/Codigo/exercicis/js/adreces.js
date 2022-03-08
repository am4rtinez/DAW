/* 
    Exercici 2 (3,5 punts)
    ==============================================================================
    Programa la pàgina adreces.html amb la següent funcionalitat.

    1. (1,5 punts) En canviar el valor de qualsevol camp de l'adreça de contacte s'ha de modificar el camp corresponent de l'adreça de facturació amb el valor acabat de modificar.
    Per exemple, si l'usuari modifica el codi postal de l'adreça de contacte amb el valor 07300, s'ha d'actualitzar el valor de l'adreça de facturació a 07300.

    2. (2 punts) Afegeix el necessari de manera que si l'usuari pitja qualsevol tecla dins d'un dels camps de l'adreça de facturació, ja no s'actualitzin els camps de facturació en modificar els de l'adreça de contacte.
    Per evitar sobrecàrregues, en pitjar una tecla dins qualsevol camp de l'adreça de facturació s'haurien d'anular tots els listeners que intervenen a l'exercici.
*/

/**
 * Correccion aplicada.
 * 
 * Ex 2.1: Podries eliminar les variables i assignar l'esdeveniment directament al getElementById
 * Si programes l'onchange no s'executaria tantes vegades el codi.
 * Podries fer els copyXXValue en una sola funció recuperant l'id des de l'esdeveniment.
 * Ex 2.2: killListeners hauria d'eliminar l'esdeveniment als elements de facturació, no tornar-los a afegir.
 */

window.onload = () => {
    // Boton de cancelar
    let reset = document.getElementById('bSuprimir')
    // Inicializa los listeners.
    initListeners()
    // Listener para que el usuario si cancela el formulario los Listeners vuelvan a funcionar con normalidad.
    // No es algo que se pida en el ejercicio pero en principio sería lo más lógico.
    reset.addEventListener("click", initListeners)
}

// Funcion encargada de inicializar los listeners.
function initListeners(){
    // Inicializacion de los elementos direccion.
    document.getElementById('adreca').addEventListener("change", copyXXValue)
    document.getElementById('codiPostal').addEventListener("change", copyXXValue )
    document.getElementById('localitat').addEventListener("change", copyXXValue)

    // Inicializacion de los elementos de facturacion.
    document.getElementById('adrecaFac').addEventListener("change", killListeners)
    document.getElementById('codiPostalFac').addEventListener("change", killListeners)
    document.getElementById('localitatFac').addEventListener("change", killListeners)
}

// Funcion que copia los datos del campo adreca en facturacion.
function copyXXValue(e){
    document.getElementById(e.srcElement.id + 'Fac').value = document.getElementById(e.srcElement.id).value
}

// Funcion que se encarga de eliminar todos los listeners activos.
function killListeners(e) {
    document.getElementById('adreca').removeEventListener("change", copyXXValue)
    document.getElementById('codiPostal').removeEventListener("change", copyXXValue)
    document.getElementById('localitat').removeEventListener("change", copyXXValue)
    document.getElementById('adrecaFac').removeEventListener("change", killListeners)
    document.getElementById('codiPostalFac').removeEventListener("change", killListeners)
    document.getElementById('localitatFac').removeEventListener("change", killListeners)
}