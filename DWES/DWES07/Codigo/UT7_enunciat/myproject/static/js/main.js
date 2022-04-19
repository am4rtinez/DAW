// .......................................................
// INICIALITZACIO
// .......................................................

const serverAPI = "http://127.0.0.1:5001";
let pistaActual = "Coberta";

// .......................................................
// FUNCIO QUE CARREGA LES DADES DESDE LA API
// .......................................................

// /gimnas/usuari #GET i POST
// /gimnas/usuari/<int:id>' #GET PUT DELETE
// /gimnas/reserves #GET 
// /gimnas/reserves/setmana/<string:dia> #GET 
// /gimnas/reserves/<int:id>  #GET (un concret), POST (afegir), DELETE (eliminar)

$(function () {
	$('#alertaReserva').hide();
	$('#alertas').hide();

	cargaBasic()

	// TODO: Limpiar el formulario si se viene de VeureReserves.
	$('#butPU').click(function (e) { 
		e.preventDefault();
		PantallaUsuari()
	});

	// TODO: Set de la fecha actual para mostrar la semana actual si se viene de PantallaUsuari().
	$('#butVR').click(function (e) { 
		e.preventDefault();
		VeureReserves()
	});

	$('#butForm').click(function (e) { 
		e.preventDefault();
		EnviaForm()
	});

	$('#pistaCoberta').click(function (e) { 
		e.preventDefault();
		CanviPistaCoberta()
	});

	$('#pistaExterior').click(function (e) { 
		e.preventDefault();
		CanviPistaExterior()
	});

	$('#semanaMenos').click(function (e) { 
		e.preventDefault();
		SemanaMenos()
	});

	$('#semanaMas').click(function (e) { 
		e.preventDefault();
		SemanaMas()
	});
});

function cargaBasic(){
	//carrega les reserves de l'usuari per mostrar a la pantalla inicial
	let id = $("#idusuari").html();
  	let url = serverAPI + "/gimnas/reserves/" + id;
  	$.getJSON(url, function(json) {
		jsonTags = json.sort((a, b) => a.data.localeCompare(b.data) || a.hora.localeCompare(b.hora));
    	console.log(json);  // this will show the info it in firebug console
    	pistesUsuari = JSON.parse(JSON.stringify(jsonTags));
    	taulaUsuari(pistesUsuari);
  	});
}

/**
 * TODO: Implementar funcion
 */
function cargaSetmana(){
	//carrega les dades de la setmana
	// Agafam el dia de l'element id=dilluns del HTML
	let dia = $("#dilluns").html();
	let url = serverAPI + "/gimnas/reserves/setmana/" + dia;
	console.log(url)
	// Aqui hauras d'afegir el teu codi
	$.getJSON(url, function(json) {
		// Filtrado por el tipo de pista y ordenado por fecha y hora.
		console.log(json);  // this will show the info it in firebug console
		jsonTags = json.filter(res => res.tipo == pistaActual).sort((a, b) => a.data.localeCompare(b.data) || a.hora.localeCompare(b.hora));
    	console.log(jsonTags);  // this will show the info it in firebug console
    	data = JSON.parse(JSON.stringify(jsonTags));
		taulaPista(data)
	})
}

/**
 * Funcion que añade un 0 a los numeros con un solo digito.
 * @param {*} num 
 * @returns 
 */
function padTo2Digits(num) {
	return num.toString().padStart(2, '0');
}

/**
 * Funcion que formatea una fecha al formato yyyy-mm-dd.
 * @param {*} date 
 * @returns 
 */
function formatDate(date) {
	return date.getFullYear()+ '-' + padTo2Digits(date.getMonth() + 1) + '-' + padTo2Digits(date.getDate())
}

/**
 * Funcion que formatea una fecha al formato dd/mm/yyyy.
 * @param {*} date 
 * @returns 
 */
function formatDateDDMMYYYY(date) {
	return padTo2Digits(date.getDate()) + '/' + padTo2Digits(date.getMonth() + 1) + '/' + date.getFullYear()
}

/**
 * Función que comprueba la reserva. Si existe una reserva retorna true. 
 * @param {*} dia 
 * @param {*} hora 
 * @param {*} pista 
 * @returns 
 */
function comprovaReserva(dia,hora,pista){
	let reserva = false
	// Utilizamos /gimnas/reserves/setmana/<string:dia> para asi no tener que comprobar todo el listado de reservas.
	let url = `${serverAPI}/gimnas/reserves/setmana/${dia}`
	$.getJSON(url, function(json) {
		// Filtrado por la fecha, el tipo de pista y la hora.
		jsonTags = json.filter(res => res.data == dia && res.tipo == pista && res.hora == hora);
    	console.log(jsonTags);  // this will show the info it in firebug console
		// console.log(jsonTags.length) // muestra el length.
		if (jsonTags.length !=0) {
			reserva = true
		}
	})
	return reserva
}

/**
 * Función que realiza la reserva.
 * @param {*} dia 
 * @param {*} hora 
 * @param {*} pista 
 */
function ReservarPista(dia,hora,pista){
	let id=$("#idusuari").html();
	let url = `${serverAPI}/gimnas/reserves/${id}`
	// let url = serverAPI + "/gimnas/reserves/" + id;
	let data = dia + " " + hora + ":00:00";
	let cadenaJSON ='{ "fecha" :"' + data + '", "pista": "' + pista + '" }';
	console.log(url)
	console.log(cadenaJSON);
	dades = JSON.parse(cadenaJSON);
	$.post(url, dades);
}

// .......................................................
// EN AQUESTA SECCIO DEL CODI MIRAM ELS CANVIS DE PANTALLA
// .......................................................

// TODO: Hacer que el VeureReserves siempre muestre la semana actual cuando se clica sobre este elemento 
// TODO: y asi prevenir si se han cargado funciones SemanaMenos y SemanaMas.
function VeureReserves() {
	//marcam els botons de light i primary
	//btn-light
	$('#butVR').removeClass('btn-outline-secondary').addClass('btn-primary');
	$('#butPU').removeClass('btn-primary').addClass('btn-outline-secondary');
	//canviam els <div> hidden per mostrar el que toca
	$('#pantallaReserva').hide();
	$('#alertaReserva').hide();
	$('#pantallaMostra').show();

	// Funcionalidad para que siempre muestre la semana actual cuando se clica sobre este elemento (#butVR)
	let monday = getActualMonday()
	let friday = getFriday(monday)
	setMonday("#dilluns", monday)
	// $("#dilluns").html(formatDate(monday))
	setTitleWeekReservation("#titolReserves", monday, friday)

	cargaSetmana();
}

// Carga la pantalla de usuario (formulario y listado de reservas del usuario).
function PantallaUsuari() {
	//marcam els botons de light i primary
	$('#butPU').removeClass('btn-outline-secondary').addClass('btn-primary');
	$('#butVR').removeClass('btn-primary').addClass('btn-outline-secondary');
	//canviam els <div> hidden per mostrar el que toca
	$('#pantallaReserva').show();
	$('#alertaReserva').hide();
	$('#pantallaMostra').hide();
}

/**
 * Funcion que se encarga de cargar una semana mas en la pantalla de ver reservas.
 */
 function SemanaMas() {
	let dia = new Date($("#dilluns").html());
	let monday = getNextMonday(dia)
	let friday = getFriday(monday)
	setMonday("#dilluns", monday)
	setTitleWeekReservation ("#titolReserves", monday, friday)
	cargaSetmana()
}

/**
 * Funcion que se encarga de cargar una semana menos en la pantalla de ver reservas.
 */
function SemanaMenos() {
	//agafam el dia actual
	let dia = new Date($("#dilluns").html());
	let monday = getPreviousMonday(dia)
	let friday = getFriday(monday)
	setMonday("#dilluns", monday)
	setTitleWeekReservation ("#titolReserves", monday, friday)
	cargaSetmana()
}

/**
 * Funcion que carga la fecha del lunes en el elemento del documento con formato yyyy-mm-dd.
 * @param {*} element 
 * @param {*} date 
 */
function setMonday(element, mon) {
	$(element).html(formatDate(mon))
}

/**
 * Funcion que pinta el titulo con las fechas de la semana del apartado de veure reserves.
 * @param {*} element 
 * @param {*} mon 
 * @param {*} fri 
 */
function setTitleWeekReservation (element, mon, fri) {
	$(element).html(`Reserves setmana ${formatDateDDMMYYYY(mon)}  a ${formatDateDDMMYYYY(fri)}`);
}

/**
 * Funcion que obtiene la fecha del lunes de la semana actual y lo devuelve.
 * @returns 
 */
function getActualMonday() {
	let day = new Date()
	day.setTime(day.getTime()-((day.getDay()-1)*24*3600000))
	return day
}

/**
 * Funcion que obtiene la fecha del lunes de la semana anterior.
 * @param {*} date 
 * @returns 
 */
function getPreviousMonday(date) {
	let day = new Date()
	day.setTime(date.getTime()-(7*24*3600000))
	return day
}

/**
 * Funcion que obtiene la fecha del lunes de la semana posterior.
 * @param {*} date 
 * @returns 
 */
function getNextMonday(date) {
	let day = new Date()
	day.setTime(date.getTime()+(7*24*3600000))
	return day
}

/**
 * Funcion que devuelve la fecha de la semana siguente al dia pasado por parámetro.
 * @param {*} date 
 * @returns 
 */
function getNextWeekDay(date) {
	let day = new Date(date)
	day.setTime(day.getTime()+(7*24*3600000))
	return formatDate(day)
}

/**
 * Funcion que devuelve la fecha del viernes.
 * @param {*} date 
 * @returns 
 */
function getFriday(date) {
	let day = new Date()
	day.setTime(date.getTime()+(4*24*3600000))
	return day
}

function CanviPistaCoberta() {
	//activam el link 
	$('#pistaCoberta').addClass('active');
	$('#pistaExterior').removeClass('active');
	pistaActual="Coberta";
	cargaSetmana();
}

function CanviPistaExterior() {
	//activam el link 
	$('#pistaCoberta').removeClass('active');
	$('#pistaExterior').addClass('active');
	pistaActual="Exterior";
	cargaSetmana();
}


// .......................................................
// VERIFICACIÓ DEL FORMULARI
// .......................................................
function EnviaForm() {
	let dia = $('#dia').val();
	let hora = $('#hora').val();
	let pista = $('#tipopista').val();
	let multiple = $('#multiple').val();
	let i = 0
	// Set the global configs to synchronous 
	$.ajaxSetup({async: false});
	if (multiple != 1) {
		do {
			i = i + 1;
			do_reservation(dia, hora, pista)
			dia = getNextWeekDay(dia)
		} while (i < multiple);
	} else {
		do_reservation(dia, hora, pista)
	}
	// let comprova = comprovaReserva(dia,hora,pista);
	// Set the global configs back to asynchronous 
	$.ajaxSetup({async: true});

}

/**
 * Funcion que se encarga de realizar los pasos de la reserva como comprobarla y después gestionarla.
 * @param {*} dia 
 * @param {*} hora 
 * @param {*} pista 
 */
function do_reservation (dia, hora, pista){
	let comprova = comprovaReserva(dia,hora,pista);
	console.log(comprova)
	if (comprova){
		showAlert("#alertaReserva", "alert-danger", "#exclamation-triangle-fill", `La pista ${pista} ja està ocupada per dia ${dia} a les ${hora}h.`)
	} else {
		showAlert("#alertaReserva", "alert-success", "#check-circle-fill", `S'ha gestionat la reserva per dia ${dia} a les ${hora}h en pista ${pista}.`)
		ReservarPista(dia,hora,pista);
	}
	$("#alertaReserva").show();
}

/**
 * Funcion que muestra los alerts en función de la reserva.
 * @param {*} parent 
 * @param {*} typeClass 
 * @param {*} icon 
 * @param {*} msg 
 */
function showAlert(parent, typeClass, icon, msg) { 
	let newalertdiv = $(`<div id="alerta" class="alert ${typeClass} alter-dismissable fade show row" role="alert"></div>`)
	let string = `<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="${icon}"/></svg>${msg}`
	let textdiv = $(`<div class="text-center msg">${string}</div>`)
	$(newalertdiv).append(textdiv);
	$(parent).append(newalertdiv);

	setTimeout(function() {
		// $(newalertdiv).hide('slow');
		$(parent).hide('slow');
		$(parent).html('');
	}, 8000);
}

// .......................................................
// EN AQUESTA SECCIO DEL CODI TENIM LES FUNCIONS QUE ACTUALITZEN ELS CAMPS DE DADES
// A PARTIR DELS JSON DE DADES QUE ARRIBA DEL SERVIDOR
// .......................................................

function taulaUsuari(pistesUsuari){
	html = "";
	for(a = 0; a < pistesUsuari.length; a++){
		fila = pistesUsuari[a];
		html = html + "<tr><td>" + fila.data + "</td><td>" + fila.hora + "</td><td>" + fila.tipo + "</td></tr>";
	}
	$("#TaulaUsuari > tbody").html(html);
}

/**
 * Funcion que genera la tabla de reservas de las pistas.
 * @param {*} dades 
 */
function taulaPista(dades){
	//li passam un JSON amb les reserves de la pista
	let username = $('#username').html()		//Obtenemos el username para comparar con las reservas.
	let tbody
	let table = []

	// Genera mapeo de la tabla.
	// Sunday = 0
	// Monday = 1
	// Tuesday = 2
	// Wednesday = 3
	// Thursday = 4
	// Friday = 5 
	// Saturday = 6

	for (let fila = 0; fila < 6; fila++) {
		let filaTemp = []
		for (let columna = 0; columna < 5; columna++) {
			let tempVal = ""
			// Recorremos los datos de reservas.
			for (i = 0; i < dades.length; i++) {
				let d = new Date(dades[i].data);
				let weekday = d.getDay()
				if (((weekday - 1) == columna) && (dades[i].hora == fila + 15)) {
					if (dades[i].username == username) {
						tempVal = tempVal + "RESERVADA"
					} else {
						tempVal = tempVal + "NO DISPONIBLE"
					}
				}
			}
			filaTemp.push(tempVal)
		}
		table.push(filaTemp)
	}
	// Genera el body de la tabla
	for (let fil = 0; fil < 6; fil++) {
		tbody = tbody + "<tr><th>" + (fil + 15) + "</th>"
		for (let col = 0; col < 5; col++) {
			// console.log(fil + " - " + col + ": " + table[fil][col])
			if (table[fil][col] == "RESERVADA") {
				tbody = tbody + "<td class='reservauser'>" + table[fil][col] + "</td>"
			} else if (table[fil][col] == "NO DISPONIBLE"){
				tbody = tbody + "<td class='nodispo'>" + table[fil][col] + "</td>"
			} else {
				tbody = tbody + '<td><button class="btn libre" hora="' + (fil + 15) + '" weekday="' + (col) + '">Libre</button></td>'
			}
		}
		tbody = tbody + "</tr>"
	}
	$('#reservesPista > tbody').html(tbody);

	// Genera el evento de click para la clase libre.
	$('.libre').click(function (e) { 
		e.preventDefault();
		// Si el dia de la reserva es anterior a hoy el boton lanza un alert
		let today = new Date()
		let dia = new Date($("#dilluns").html())
		dia.setTime(dia.getTime() + ($(this).attr("weekday")*24*3600000))
		
		// Comprueba si la fecha para reservar es anterior a hoy. En caso de serlo no se pasa al formulario de reserva y muestra un alert en pantalla.
		if(dia.setHours(0, 0, 0, 0) >= today.setHours(0, 0, 0, 0)) {
			let hora = $(this).attr("hora")
			$('#hora').val(hora);
			$('#tipopista').val(pistaActual)
			$('#dia').val(formatDate(dia))
			PantallaUsuari()
		} else {
			alert("No es posible realizar reservas en fechas pasadas.")
		}
	});
}
