/* 
    Exercici 2 (3,5 punts)
    ==============================================================================
    Programa la pàgina adreces.html amb la següent funcionalitat.

    1. (1,5 punts) En canviar el valor de qualsevol camp de l'adreça de contacte s'ha de modificar el camp corresponent de l'adreça de facturació amb el valor acabat de modificar.
    Per exemple, si l'usuari modifica el codi postal de l'adreça de contacte amb el valor 07300, s'ha d'actualitzar el valor de l'adreça de facturació a 07300.

    2. (2 punts) Afegeix el necessari de manera que si l'usuari pitja qualsevol tecla dins d'un dels camps de l'adreça de facturació, ja no s'actualitzin els camps de facturació en modificar els de l'adreça de contacte.
    Per evitar sobrecàrregues, en pitjar una tecla dins qualsevol camp de l'adreça de facturació s'haurien d'anular tots els listeners que intervenen a l'exercici.
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
    let adreca = document.getElementById('adreca')
    let codiPostal = document.getElementById('codiPostal')
    let localitat = document.getElementById('localitat')

    // Inicializacion de los elementos de facturacion.
    let adrecaFac = document.getElementById('adrecaFac')
    let codiPostalFac = document.getElementById('codiPostalFac')
    let localitatFac = document.getElementById('localitatFac')

    // Listeners para copiar los valores de los campos de direccion en facturación.
    adreca.addEventListener("keyup", copyAdrecaValue)
    codiPostal.addEventListener("keyup", copyCPValue )
    localitat.addEventListener("keyup", copyLocalitatValue)

    // Se generan listeners para que si se escribe en alguno de los campos de facturación dejen de copiarse los valores de los campos de direccion.
    adrecaFac.addEventListener("keyup", killListeners)
    codiPostalFac.addEventListener("keyup", killListeners)
    localitatFac.addEventListener("keyup", killListeners)
}

// Funcion que copia los datos del campo adreca en adrecaFac.
function copyAdrecaValue(e) {
    document.getElementById('adrecaFac').value = document.getElementById('adreca').value
}

// Funcion que copia los datos del campo codiPostal en codiPostalFac.
function copyCPValue(e) {
    document.getElementById('codiPostalFac').value = document.getElementById('codiPostal').value
}

// Funcion que copia los datos del campo localitat en localitatFac.
function copyLocalitatValue(e) {
    document.getElementById('localitatFac').value = document.getElementById('localitat').value
}

// Funcion que se encarga de eliminar todos los listeners activos.
function killListeners(e) {
    document.getElementById('adreca').removeEventListener("keyup", copyAdrecaValue)
    document.getElementById('codiPostal').removeEventListener("keyup", copyCPValue)
    document.getElementById('localitat').removeEventListener("keyup", copyLocalitatValue)
    document.getElementById('adrecaFac').addEventListener("keyup", killListeners)
    document.getElementById('codiPostalFac').addEventListener("keyup", killListeners)
    document.getElementById('localitatFac').addEventListener("keyup", killListeners)
}