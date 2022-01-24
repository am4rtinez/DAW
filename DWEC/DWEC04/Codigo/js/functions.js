function ExceptionObres(str) {
   this.message = str;
   this.name = "ExceptionObres";
}

function ExceptionArtistes(str) {
   this.message = str;
   this.name = "ExceptionArtistes";
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
        for (let artista in this.artistes) {
            if (item.nom == this.artistes[artista].nom) {
                throw new ExceptionArtistes("El artista ya existeix al llistat.")
            }
        }
        this.artistes.push(item)
    }

    delArtista(item) {
        let index = this.artistes.indexOf( item.nom );
        if ( index !== -1 ) {
            this.artistes.splice( index, 1 );
        }
    }
}