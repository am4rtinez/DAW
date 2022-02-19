/* 
    Exercici 3 (2,5 punts)
    ==============================================================================
    Exercici 3.1 (1,5 punts)
        Al fitxer oferta.js programa el necessari perquè a cap camp del formulari es pugui introduir la lletra a, ni majúscula ni minúscula. Si l'usuari la pitja no s'ha d'incloure al camp del formulari.
    ==============================================================================
    Exercici 3.2 (1 punt)
        Mostra un avís cada vegada que es pitgi una a o una A. Per mostrar l'avís pots mostrar l'element amb id avis canviant la seva classe a "alert alert-danger". 
        Després de x segons aquest missatge ha de desapareixer. Ho pots fer canviant la classe d'avís a hidden.
    ==============================================================================
    Exercici 4 (1,5 punts)
        A oferta.js assignau a l’esdeveniment que trobeu un listener que validi el formulari. Ha de controlar:
        Els elements marcats amb * són obligatoris.
        La data de publicació ha de ser anterior a la de finalització.
        La data de finalització ha de ser posterior a la data actual.
        Si hi ha qualque errada heu de mostrar un alert que ha de dir simplement quin tipus d’errada hi ha: dades obligatòries sense emplenar o dates inconsistents.
        Si hi ha errades també ha d’evitar l’enviament del formulari així com la propagació de l’esdeveniment, incloent a d’altres listeners del mateix esdeveniment.
    ==============================================================================
    Exercici 5 (1 punt)
        A oferta.js afegeix un listener a l’esdeveniment adequat de manera que quan s’enviï el formulari, si no hi ha errades, es generi un objecte JSON que tengui per a cada camp del formulari 
        un atribut amb el name del camp com a nom i el valor del camp com a valor de l’atribut.
        El formulari no s’ha d’enviar.
        Mostra aquest JSON amb un alert o a la consola.
        El codi d'aquest exercici ha d'estar a un listener apart dels dels altres exercicis. No podeu incloure codi dels altres exercicis a aquest listener.
        Per exemple s’hauria de mostrar un JSON com aquest, amb les dades del formulari, no cal amb aquest format en concret:
        {
            "idEmpresa": "8",
            "idOferta": "3",
            "titol": "Aslkda ",
            "dPublicacio": "2020-12-08",
            "dFinal": "2020-12-24",
            "tContracte": "",
            "localitat": "",
            "horari": "",
            "textOferta": "sdkf sldfjs ldkfs"
        }
*/

// En ningun momento se usa las expresiones regulares ya que no lo indica el ejercicio.
window.onload = () => {
    // Array de componentes para que genere los listeners de control de la letra A con una funcion generica.
    let formEventComponents = ['idOferta', 'titol', 'tContracte', 'localitat', 'horari', 'textOferta']
    // Inicialización de los listeners.
    initListeners(formEventComponents)
}

function initListeners(components){
    // Definicion de elementos necesarios.
    let form = document.getElementById('formOferta')
    let bEditar = document.getElementById('bEditar')
    // Inicializa los listeners de los componentes a revisar si escriben a/A
    for (let component in components) {
        document.getElementById(components[component]).addEventListener("keydown", lletraAEvent, false)
    }
    // Listener del evento de click en el boton de submit. Aunque el evento submit se controla en el siguiente listener.
    bEditar.addEventListener("click", formValidation, false);
    // Listener del evento submit. Para que esto suceda el formulario previamente ha sido validado por formValidation.
    form.addEventListener("submit", alertJSON, false);

}

// Funcion que muestra la alerta 2 segundos.
// Si se apreta varias veces seguidas falla. Sin solventar.
function showAlertDiv() {
    document.getElementById('avis').classList.remove('hidden')
    document.getElementById('avis').classList.add('alert');
    document.getElementById('avis').classList.add('alert-danger');
    // Funcion que se ejecuta a los 2 segundos (2000ms).
    setTimeout (function() { 
        document.getElementById('avis').classList.remove('alert');
        document.getElementById('avis').classList.remove('alert-danger');
        document.getElementById('avis').classList.add('hidden');
    }, 2000);

    // Usando jQuery
    // $('#avis').removeClass('hidden');
    // $('#avis').addClass('alert alert-danger');
    // setTimeout(function() { 
    //     $('#avis').removeClass('alert alert-danger');
    //     $('#avis').addClass('hidden');
    // }, 2000);
}

function showAlertRequiredData(){
    alert ('Dades obligatòries sense emplenar.')
}

function showAlertWrongDate(){
    alert ('Dates inconsistents.')
}

// Funcion que compara fechas. 
function compareDates(date1, date2) {
    if (date1.setHours(0,0,0,0) > date2.setHours(0,0,0,0)) {
        return true
    } else {
        return false
    }
}

// Funcion para capturar la letra a/A y mostrar la alerta.
const lletraAEvent = function (e) {
    if (e.keyCode == 65) {
        e.preventDefault();  // Previene que se pinte la letra a/A en el input correspondiente.
        showAlertDiv()
    }
}

// Validación del formulario. Si hay errores muestra una alerta, detiene la propagación del evento y evita el envio del formulario.
const formValidation = function (e) {
    // let form = e.target;
 
    // Comprobación de los campos obligatorios.
    let titol = document.getElementById('titol')
    let dPublicacio = document.getElementById('dPublicacio')
    let dFinal = document.getElementById('dFinal')
    let textOferta = document.getElementById('textOferta')

    let today = new Date()

    // Comprueba que no este vacio el input
    if (titol.value == '') {
        showAlertRequiredData()
        e.preventDefault();
        e.stopPropagation();
        return false
    }

    // Comprueba que no este vacio el input
    if (dPublicacio.value == '') {
        showAlertRequiredData()
        e.preventDefault();
        e.stopPropagation();
        return false
    } else {
        // La data de publicació ha de ser anterior a la de finalització. 
        // dFinal > dPublicacio
        if (!compareDates(new Date(dFinal.value), new Date(dPublicacio.value))) {
            showAlertWrongDate()
            e.preventDefault();
            e.stopPropagation();
            return false
        }
    }
    
    // Comprueba que no este vacio el input
    if (dFinal.value == '') {
        showAlertRequiredData()
        e.preventDefault();
        e.stopPropagation();
        return false
    } else {
        // La data de finalització ha de ser posterior a la data actual.
        // dFinal > today
        if (!compareDates(new Date(dFinal.value), today)) {
            showAlertWrongDate()
            e.preventDefault();
            e.stopPropagation();
            return false
        }
    }

    // Comprueba que no este vacio el input
    if (textOferta.value == '') {
        showAlertRequiredData()
        e.preventDefault();
        e.stopPropagation();
        return false
    }
    alert('Formulario validado.')
    return
}

// Funcion que muestra en formato JSON los datos a traves de un alert.
// En chrome no se pintara correctamente \t (parece ser que hay un bug conocido).
const alertJSON = function (e) {
    // let form = e.target;
    let formInputs = document.getElementsByClassName("form-control"); // Obtiene los inputs form-control para asi hacerlo más generico y no declarar uno a uno los imputs.
    let alertString = "{"
    let endString = "\n}"
    for (let item of formInputs) {
        alertString = alertString + '\n\t"' + item.name + '" : "' + item.value + '"'
    }
    alertString = alertString + endString
    alert(alertString) // Muestra el string con formato JSON en un alert.
    e.preventDefault() // Previene el envio del formulario.
}