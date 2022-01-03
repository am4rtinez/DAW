from flask import Flask, render_template, request

app = Flask(__name__)

reservas = []

# Matriz de la tabla con los diccionarios por fila
tabla = {
    'Exterior': {
        '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
    },
    'Coberta': {
        '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
    }
}

# Funcion para generar el diccionario de la reserva.


def crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora):
    # Definicio de variables necesaries
    global reservas
    global tabla

    # Formato reserva {'Nom':'Nom','Telefon':'Telefon','Pista':'Tipopista','Dia':'Dia','Hora':'Hora'}
    reserva = {'nom': req_nom, 'telefon': req_telefon,
               'pista': req_tipopista, 'dia': req_dia, 'hora': req_hora}

    # Añade la info a la matriz.
    tabla[req_tipopista][req_hora][req_dia] = req_nom

    # Formato reservas [reserva1,reserva2,...]
    reservas.append(reserva)  # Añadimos el diccionario de la reserva al array.

    # Revertimos el array para que la ultima reserva aparezca la primera.
    reservas.reverse()


@app.route('/')
def index():
    # Definicio de variables necesaries
    global reservas

    return render_template('registre.html')


@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    return render_template('registre.html')


@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    # Definicio de variables necesaries
    # Array que contiene las reservas para imprimirlas individualmente.
    global reservas
    global tabla  # Matriz de diccionarios para pintar la tabla avanzada.

    # Controlamos el tipo de request para que no se generen reservas vacias.
    if request.method == 'POST':
        # Obtencion de los datos del request
        req_dia = request.form.get('dia')
        req_hora = request.form.get('hora')
        req_tipopista = request.form.get('tipopista')
        req_nom = request.form.get('nom')
        req_telefon = request.form.get('telefon')

        if not reservas:  # Si no hay reservas en el array no es necesario comprobar la disponibilidad del dia-hora-pista
            crearReserva(req_nom, req_telefon,
                         req_tipopista, req_dia, req_hora)
            return render_template('reserves.html', data=reservas, tab=tabla)
        else:  # Comprobar si es posible tramitar la reserva.
            reservada = False
            for i in range(len(reservas)):
                if reservas[i]['dia'] == req_dia:  # Comprobacion del dia
                    if reservas[i]['hora'] == req_hora:  # Comprobacion de la hora
                        if reservas[i]['pista'] == req_tipopista:  # Comprobacion de la pista
                            reservada = True
                            break

            # Si la pista esta reservada devuelve pagina inicial y muestra alerta.
            if reservada:
                return render_template('registre.html', reservada=True)
            else:
                crearReserva(req_nom, req_telefon,
                             req_tipopista, req_dia, req_hora)
                return render_template('reserves.html', data=reservas, tab=tabla)
    else:
        return render_template('reserves.html', data=reservas, tab=tabla)


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
