// .......................................................
// INICIALITZACIO
// .......................................................

serverAPI = "http://127.0.0.1:5001";
pistaActual = "Coberta";
// .......................................................
// FUNCIO QUE CARREGA LES DADES DESDE LA API
// .......................................................

$(function () {
	cargaBasic()

	$("#alertReserva > .btn-close").click(function () {
		console.log('Close alert')
        $('#alertaReserva').hide();
    });

});

function cargaBasic(){
	//carrega les reserves de l'usuari per mostrar a la pantalla inicial
	id = $("#idusuari").html();
  	arxiu = serverAPI + "/gimnas/reserves/" + id;
  	$.getJSON(arxiu, function(json) {
		jsonTags = json;
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
	dia = $("#dilluns").html();
	arxiu = serverAPI + "/gimnas/reserves/setmana/" + dia;

	// Aqui hauras d'afegir el teu codi
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
	id=$("#idusuari").html();
	arxiu = serverAPI+"/gimnas/reserves/"+id;
	data = dia + " " + hora + ":00:00";
	cadenaJSON ='{ "fecha" :"' + data + '", "pista": "' + pista + '" }';
	console.log(cadenaJSON);
	dades = JSON.parse(cadenaJSON);
	$.post(arxiu,{fecha:data,pista:"Coberta"});
}

// .......................................................
// EN AQUESTA SECCIO DEL CODI MIRAM ELS CANVIS DE PANTALLA
// .......................................................

function VeureReserves() {
	//marcam els botons de light i primary
	//btn-light
	$('#butVR').removeClass('btn-outline-secondary').addClass('btn-primary');
	$('#butPU').removeClass('btn-primary').addClass('btn-outline-secondary');
	//canviam els <div> hidden per mostrar el que toca
	$('#pantallaReserva').hide();
	$('#alertaReserva').hide();
	$('#pantallaMostra').show();
	cargaSetmana();
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
 * TODO: Implementar funcion.
 */
function SemanaMas() {

}

/**
 * TODO: Implementar funcion.
 */
function SemanaMenos() {
	//agafam el dia actual

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
	}
	else{
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
 * TODO: Implementar funcion.
 * @param {*} dades 
 */
function taulaPista(dades){
	//li passam un JSON amb les reserves de la pista

}
