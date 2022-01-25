function ExceptionObres(str) {
   this.message = str;
   this.name = "ExceptionObres";
}

function ExceptionArtistes(str) {
   this.message = str;
   this.name = "ExceptionArtistes";
}

// Funcion que devuelve el listado en un array de objetos de centros creados de forma literal.
function get_list_ccultura() {
    // Creacion literal del ccultura 1
    let ccultura1 = {
        nom: "Centre 1",
        desc: "Centre Cultura 1",
        web: "www.example1.com",
        email: "ccultura1@example.com",
        telefon: "971121211",
        poblacio: "Palma"
    }
    
    // Creacion literal del ccultura 2
    let ccultura2 = {
        nom: "Centre 2",
        desc: "Centre Cultura 2",
        web: "www.example2.com",
        email: "ccultura2@example.com",
        telefon: "971121212",
        poblacio: "Calvia"
    }
    
    // Creacion literal del ccultura 3
    let ccultura3 = {
        nom: "Centre 3",
        desc: "Centre Cultura 3",
        web: "www.example3.com",
        email: "ccultura3@example.com",
        telefon: "971121213",
        poblacio: "Inca"
    }

    // Creacion literal del ccultura 3
    let ccultura4 = {
        nom: "Centre 4",
        desc: "Centre Cultura 4",
        web: "www.example4.com",
        email: "ccultura4@example.com",
        telefon: "971121213",
        poblacio: "Calvia"
    }
    let lista = [ccultura1, ccultura2, ccultura3, ccultura4]
    return lista
}

function Artista(nom, nacionalitat, obres) {
    this.nom = nom                          // Nombre del artista.
    this.nacionalitat = nacionalitat        // Nacionalidad del artista.
    this.obres = obres                      // Listado de obras.
    this.addObra = function (item) {
        if (!obres.includes(item)) {
            obres.push(item)
        } else {
            throw new ExceptionObres("La obra ya existe en el listado.")
        }
    }
    this.delObra = function (item) {
        let index = obres.indexOf( item );
        if ( index !== -1 ) {
            obres.splice( index, 1 );
        }
    }
    this.getObres = function () {
        if (obres.length > 0) {
            let str = ""
            for (obra in obres) {
                if (obra != obres.length -1) {
                    str = str + obres[obra] + ", "
                } else {
                    str = str + obres[obra] + ". "
                }
            }
            return str
        } else {
            throw new ExceptionObres("No existen obras.")
        }
    }
}

class Exposicio {
    constructor(titol, data_ini, data_fi, centre, artistes) {
        this.titol = titol
        this.data_ini = data_ini
        this.data_fi = data_fi
        this.centre = centre
        this.artistes = artistes
    }

    addArtista(item) {
        // Comprueba si el artista existe (comparando los nombres, ya que no hay un identificador estilo DNI) y si existe lanza un error interrumpiendo la ejecución
        // del codigo restante (hacer un push en el array de artistas).
        for (let artista in this.artistes) {
            if (item.nom == this.artistes[artista].nom) {
                throw new ExceptionArtistes("El artista ya existeix al llistat.")
            }
        }
        this.artistes.push(item)
    }

    delArtista(item) {
        // Comprueba si el artista esta en el listado de artistas. Si existe lo elimina. En caso contrario no realiza ninguna operación.
        let index = this.artistes.indexOf(item);
        if ( index !== -1 ) {
            this.artistes.splice( index, 1 );
        }
    }
}

function get_centres_poblacio(poblacio) {
    let centres = get_list_ccultura()
    let cpoblacio = []
    for (centre in centres) {
        if (centres[centre].poblacio == poblacio) {
            cpoblacio.push(centres[centre])
        }
    }
    // console.log(cpoblacio)
    return toJSON(cpoblacio)
}

// Funcion que simula la llamada a una api rest. Devuelve el json en formato String.
function toJSON(objecte) {
    // jsonArray = JSON.parse(JSON.stringify(objecte))
    // return jsonArray;
    return JSON.stringify(objecte)
}

// Funcion para parsear el JSON en formato String obtenido de la consulta simulada tipo REST.
function parseJSON(objecte) {
    return JSON.parse(objecte)
}