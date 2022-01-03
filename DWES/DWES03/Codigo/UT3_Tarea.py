from flask import Flask, render_template, request
from database import gimnas
from datetime import datetime, timedelta
from datetime import date

app = Flask(__name__)

# Matriz de la tabla con los diccionarios por fila
tabla = {
    'exterior': {
        '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
    },
    'coberta': {
        '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
    }
}

# Funcion que resetea la matriz tabla para pintarla en el apartado de reservas.


def resetTabla():
    global tabla
    tabla = {
        'exterior': {
            '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        },
        'coberta': {
            '15:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '16:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '17:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '18:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '19:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
            '20:00': {'dilluns': '', 'dimarts': '', 'dimecres': '', 'dijous': '', 'divendres': ''},
        }
    }


# Rellena la tabla con las reservas de la seamana obtenidas a traves de consulta a la BD.
def generarTabla(reservas):
    global tabla
    resetTabla()
    for r in reservas:
        pista = r['pista'].lower()
        hora = r['hora'].lower()
        dia = r['dia'].lower()
        nom = r['nom']
        llinatges = r['llinatges']
        tabla[pista][hora][dia] = nom + " " + llinatges


# Comprobamos si existe la reserva. En caso de no existir devolvera None.
def comprobarReserva(data, pista, usuari):
    reserva = gimnas.comprobarReserva(data, pista, usuari)
    return reserva


# Insercion de los datos de la reserva en BD.
def crearReserva(data, pista, usuari):
    # Definicio de variables necesaries
    global tabla
    gimnas.insertReserva(data, pista, usuari)


@app.route('/')
def index():
    today = date.today()
    lpistas = gimnas.getPistas()
    lusers = gimnas.getUsers()
    return render_template('registre.html', fecha=today, usuaris=lusers, pistes=lpistas)


@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    today = date.today()
    lpistas = gimnas.getPistas()
    lusers = gimnas.getUsers()
    return render_template('registre.html', fecha=today, usuaris=lusers, pistes=lpistas)


@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    dia = request.form.get('dia')
    hora = request.form.get('hora')
    pista = request.form.get('tipopista')
    usuari = request.form.get('usuari')

    today = date.today()

    # Si dia es distino a None es que se ha usado el formulario de registro.
    if (dia != None):
        data = dia + " " + hora + ":00:00"
        existe = comprobarReserva(data, pista, usuari)
        print(existe)
        if (existe == None):
            # Si la reserva no existe entonces la creamos.
            crearReserva(data, pista, usuari)
        else:
            # Si la reserva existe entonces devuelve template registro y muestra error.
            lpistas = gimnas.getPistas()
            lusers = gimnas.getUsers()
            return render_template('registre.html', fecha=today, usuaris=lusers, pistes=lpistas, error=True)

    swd = today - timedelta(days=today.weekday())   # Start day of the week
    ewd = swd + timedelta(days=6)                   # End day of the week

    reserves = gimnas.getReservas(swd, ewd)
    generarTabla(reserves)

    return render_template('reserves.html', fecha=today, swd=swd, ewd=ewd, tab=tabla)


@app.route('/prev_week', methods=['GET', 'POST'])
def prev_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana anterior
    day = datetime_obj.date() - timedelta(days=7)
    swd = day - timedelta(days=day.weekday())   # Start day of the week
    ewd = swd + timedelta(days=6)                   # End day of the week

    print("Day: " + str(day))
    print("Start: " + str(swd))
    print("End: " + str(ewd))

    reserves = gimnas.getReservas(swd, ewd)
    generarTabla(reserves)
    print(reserves)

    return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla)


@app.route('/next_week', methods=['GET', 'POST'])
def next_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana siguiente
    day = datetime_obj.date() + timedelta(days=7)
    swd = day - timedelta(days=day.weekday())   # Start day of the week
    ewd = swd + timedelta(days=6)                   # End day of the week

    print("Day: " + str(day))
    print("Start: " + str(swd))
    print("End: " + str(ewd))

    reserves = gimnas.getReservas(swd, ewd)
    generarTabla(reserves)
    print(reserves)

    return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla)


@app.route('/usuaris', methods=['GET', 'POST'])
def usuaris():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/modifica', methods=['GET', 'POST'])
def modifica():
    # Obtiene el listado de identificador del cliente a modificar.
    idclient = request.args.get('idclient')
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('modifica.html', usuaris=listUsuaris, id=idclient)


@app.route('/updateuser', methods=['GET', 'POST'])
def updateuser():
    # Obtiene los datos.
    idclient = request.form.get('idclient')
    nom = request.form.get('nom')
    llinatges = request.form.get('llinatges')
    telefon = request.form.get('telefon')
    # Hace el update de los datos del usuario.
    gimnas.updateUser(idclient, nom, llinatges, telefon)
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/formuser', methods=['GET', 'POST'])
def formuser():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    # Obtiene el que se le va a asignar al usuario.
    idclient = gimnas.getId()
    return render_template('usuaris.html', usuaris=listUsuaris, id=idclient)


@app.route('/adduser', methods=['GET', 'POST'])
def adduser():
    # Obtiene los datos.
    idclient = request.form.get('idclient')
    nom = request.form.get('nom')
    llinatges = request.form.get('llinatges')
    telefon = request.form.get('telefon')
    # Anade el usuario a la tabla clientes.
    gimnas.addUser(idclient, nom, llinatges, telefon)
    # Obtiene listado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/elimina', methods=['GET', 'POST'])
def elimina():
    # Obtenemos el id del cliente a eliminar.
    idclient = request.args.get('idclient')
    # Elimina las realaciones previas de la tabla reserves.
    gimnas.eliminaReservaUser(idclient)
    # Elimina el usuario de la tabla clients
    gimnas.eliminaUser(idclient)
    # Obtiene listado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
