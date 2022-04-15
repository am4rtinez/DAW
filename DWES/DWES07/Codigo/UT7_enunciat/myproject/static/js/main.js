// .......................................................
// INICIALITZACIO
// .......................................................

const serverAPI = "http://127.0.0.1:5001";
let pistaActual = "Coberta";

// .......................................................
// FUNCIO QUE CARREGA LES DADES DESDE LA API
// .......................................................

// api.add_resource(usuari,'/gimnas/usuari') #GET i POST
// api.add_resource(usuarisID,'/gimnas/usuari/<int:id>') #GET PUT DELETE
// api.add_resource(reserves,'/gimnas/reserves')  #GET 
// api.add_resource(reservesSet,'/gimnas/reserves/setmana/<string:dia>')  #GET 
// api.add_resource(reservesID,'/gimnas/reserves/<int:id>')  #GET (un concret), POST (afegir), DELETE (eliminar)

$(function () {
	cargaBasic()

	$('#butPU').click(function (e) { 
		e.preventDefault();
		PantallaUsuari()
	});

	$(".btn-close").click(function () {
		console.log('Close alert')
        $('#alertaReserva').hide();
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
    	// console.log(json);  // this will show the info it in firebug console
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

	// Aqui hauras d'afegir el teu codi
	$.getJSON(url, function(json) {
		// Filtrado por el tipo de pista y ordenado por fecha y hora.
		jsonTags = json.filter(res => res.tipo == pistaActual).sort((a, b) => a.data.localeCompare(b.data) || a.hora.localeCompare(b.hora));
    	// console.log(jsonTags);  // this will show the info it in firebug console
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
 * TODO: Implementar funcion.
 * @param {*} dia 
 * @param {*} hora 
 * @param {*} pista 
 */
function comprovaReserva(dia,hora,pista){

}

function ReservarPista(dia,hora,pista){
	let id=$("#idusuari").html();
	url = serverAPI+"/gimnas/reserves/"+id;
	data = dia + " " + hora + ":00:00";
	cadenaJSON ='{ "fecha" :"' + data + '", "pista": "' + pista + '" }';
	console.log(cadenaJSON);
	dades = JSON.parse(cadenaJSON);
	$.post(url,{fecha:data,pista:"Coberta"});
}

// .......................................................
// EN AQUESTA SECCIO DEL CODI MIRAM ELS CANVIS DE PANTALLA
// .......................................................

// TODO: Hacer que el VeureReserves siempre muestre la semana actual cuando se clica sobre este elemento y asi prevenir si se han cargado funciones SemanaMenos y SemanaMas.
function VeureReserves() {
	//marcam els botons de light i primary
	//btn-light
	$('#butVR').removeClass('btn-outline-secondary').addClass('btn-primary');
	$('#butPU').removeClass('btn-primary').addClass('btn-outline-secondary');
	//canviam els <div> hidden per mostrar el que toca
	$('#pantallaReserva').hide();
	$('#alertaReserva').hide();
	$('#pantallaMostra').show();

	// let today = new Date()
	// let monday = new Date()
	// let friday = new Date()
	// monday.setTime(today.getTime()-((today.getDay() - 1)*24*3600000))
	// friday.setTime(monday.getTime()+(5*24*3600000))
	// $("#dilluns").html(formatDate(monday))

	// console.log(formatDate(monday))
	cargaSetmana();
	// $("#titolReserves").html(`Reserves setmana ${formatDateDDMMYYYY(monday)}  a ${formatDateDDMMYYYY(friday)}`);
}

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
 * Funcion que se encarga
 */
 function SemanaMas() {
	let dia = new Date($("#dilluns").html());
	let monday = new Date()
	let friday = new Date()
	monday.setTime(dia.getTime()+(7*24*3600000))
	friday.setTime(monday.getTime()+(5*24*3600000))
	$("#dilluns").html(formatDate(monday))
	$("#titolReserves").html(`Reserves setmana ${formatDateDDMMYYYY(monday)}  a ${formatDateDDMMYYYY(friday)}`);
	cargaSetmana()
}

/**
 * TODO: Implementar funcion.
 */
function SemanaMenos() {
	//agafam el dia actual
	let dia = new Date($("#dilluns").html());
	let monday = new Date()
	let friday = new Date()
	monday.setTime(dia.getTime()-(7*24*3600000))
	friday.setTime(monday.getTime()+(5*24*3600000))
	$("#dilluns").html(formatDate(monday))
	$("#titolReserves").html(`Reserves setmana ${formatDateDDMMYYYY(monday)}  a ${formatDateDDMMYYYY(friday)}`);
	cargaSetmana()
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
	dia = $('#dia').val();
	hora = $('#hora').val();
	pista = $('#tipopista').val();
	multiple = $('#multiple').val();
	// Set the global configs to synchronous 
	$.ajaxSetup({async: false});
	comprova = comprovaReserva(dia,hora,pista);
	if (comprova){
		$('#alertaReserva').html("La pista ja està ocupada");
	} else {
		ReservarPista(dia,hora,pista);
		$('#alertaReserva').html("<div class='text-center'>S'ha gestionat la reserva</div> <button type='button' class='btn-close close' data-dismiss='alert'></button>");
	}		
	$('#alertaReserva').show();
	// Set the global configs back to asynchronous 
	$.ajaxSetup({async: true});
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
			if (table[fil][col] == "") {
				tbody = tbody + '<td><button class="btn libre" hora="' + (fil + 15) + '" weekday="' + (col) + '">Libre</button></td>'
			} else {
				tbody = tbody + "<td>" + table[fil][col] + "</td>"
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
		
		// Comprueba si la fecha para reservar es anterior a hoy. En caso de serlo no se pasa al formulario de reserva.
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
