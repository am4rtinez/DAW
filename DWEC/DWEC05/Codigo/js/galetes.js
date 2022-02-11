function setCookie(c_name, value, exdays, domain, path) {
	var exdate = new Date();
	// expirarà d'avui a exdays dies
	exdate.setDate(exdate.getDate() + exdays);
	var c_value = encodeURI(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toUTCString())
			+ ((domain == null) ? "" : "; domain=" + encodeURI(domain))
			+ ((path == null) ? "" : "; path=" + encodeURI(path));
	document.cookie = c_name + "=" + c_value;
}

function getCookie(c_name) {
	var i, nom, valor;
	var galletes = document.cookie.split(";");
	for (i = 0; i < galletes.length; i++) {
		var parts = galletes[i].split("=");
		nom = parts[0];
		valor = parts[1];
		nom = nom.replace(/^\s+|\s+$/g, ""); // trim del nom
		if (nom === c_name) {
			return decodeURI(valor);
		}
	}
}


function deleteCookie(nom) {
	setCookie(nom, "", -1);
}

