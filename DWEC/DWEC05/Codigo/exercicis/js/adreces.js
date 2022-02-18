/* 
 Exercici 2 (3,5 punts)

 Programa la pàgina adreces.html amb la següent funcionalitat.

 1. (1,5 punts) En canviar el valor de qualsevol camp de l'adreça de contacte s'ha de modificar el camp corresponent de l'adreça de facturació amb el valor acabat de modificar.
 Per exemple, si l'usuari modifica el codi postal de l'adreça de contacte amb el valor 07300, s'ha d'actualitzar el valor de l'adreça de facturació a 07300.

 2. (2 punts) Afegeix el necessari de manera que si l'usuari pitja qualsevol tecla dins d'un dels camps de l'adreça de facturació, ja no s'actualitzin els camps de facturació en modificar els de l'adreça de contacte.
 Per evitar sobrecàrregues, en pitjar una tecla dins qualsevol camp de l'adreça de facturació s'haurien d'anular tots els listeners que intervenen a l'exercici.

*/

function adrecaEvent(e) {
    let adrecaFac = document.getElementById('adrecaFac')
    adrecaFac.value = document.getElementById('adreca').value
}

function cpEvent(e) {
    let codiPostalFac = document.getElementById('codiPostalFac')
    codiPostalFac.value = document.getElementById('codiPostal').value
}

function locEvent(e) {
    let localitatFac = document.getElementById('localitatFac')
    localitatFac.value = document.getElementById('localitat').value
}

function killListeners() {
    removeEventListener("keyup", adrecaEvent)
    removeEventListener("keyup", cpEvent)
    removeEventListener("keyup", locEvent)
}

window.onload = () => {
    let adreca = document.getElementById('adreca')
    let codiPostal = document.getElementById('codiPostal')
    let localitat = document.getElementById('localitat')
    let adrecaFac = document.getElementById('adrecaFac')
    let codiPostalFac = document.getElementById('codiPostalFac')
    let localitatFac = document.getElementById('localitatFac')

    adreca.addEventListener("keyup", adrecaEvent)
    codiPostal.addEventListener("keyup", cpEvent )
    localitat.addEventListener("keyup", locEvent)

    adrecaFac.addEventListener("change", killListeners)
    codiPostalFac.addEventListener("change", killListeners )
    localitatFac.addEventListener("change", killListeners)
}