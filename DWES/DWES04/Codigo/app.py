from flask import Flask, render_template, request, flash
from flask_login import login_user, login_required, logout_user, current_user
from database import gimnas
from datetime import datetime, timedelta, date


app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'

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


# Devuelve el dia de la semana (entero) de una fecha pasada por parametro.
# El formato de la decha es yyyy-mm-dd.
def getWeekDay(data):
    d = datetime.strptime(data, '%Y-%m-%d')
    return d.weekday()


# Para optimizar codigo el render template de registre se hace en esta funcion.
# Error = 0 -> No hay error.
# Error = 1 -> El error es intentar reservar en fin de semana.
# Error = 2 -> La reserva ya existe.
def renderRegistre():
    today = date.today()
    # Obtiene la lista de las pistas.
    lpistas = gimnas.getPistas()
    lusers = gimnas.getUsers()                  # Obtiene la lista de usuarios.
    return render_template('registre.html', fecha=today, usuaris=lusers, pistes=lpistas)


# Para optimizar codigo el render template de reserves se hace en esta funcion.
def renderReserves(day):
    swd = day - timedelta(days=day.weekday())   # Dia de inicio de la semana.
    ewd = swd + timedelta(days=6)               # Dia final de la semana.
    # Obtiene la consulta de reservas que comprende los dias de la semana del día pasado por parámetro.
    reserves = gimnas.getReservas(swd, ewd)
    # Genera la tabla de la cual se impriment los datos en el render.
    generarTabla(reserves)
    return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla)


# Para optimizar codigo el render template de usuaris se hace en esta funcion.
def renderUsuaris():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/')
def index():
    return render_template('login.html')
    # return renderRegistre()


@app.route('/login', methods=['GET', 'POST'])
def login():
    pass


@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    # Si el metodo utilizado es POST es que se ha usado el formulario de registro.
    if request.method == 'POST':
        dia = request.form.get('dia')
        hora = request.form.get('hora')
        pista = request.form.get('tipopista')
        usuari = request.form.get('usuari')
        # Comprobamos si el dia seleccionado para la reserva no es sabado o domingo.
        weekday = getWeekDay(dia)
        # Sabado es 5 y Domingo es 6.
        if ((weekday == 5) or (weekday == 6)):
            flash('Ha seleccionado un dia de fin de semana.', category='error')
        else:
            data = dia + " " + hora + ":00:00"
            # Comprobamos si existe la reserva
            existe = comprobarReserva(data, pista, usuari)
            if (existe == None):
                # Si la reserva no existe entonces la creamos.
                crearReserva(data, pista, usuari)
                flash('La reserva se ha realizado.', category='success')
            else:
                # Si la reserva existe entonces devuelve template registro y muestra error.
                flash('La reserva ya existe.', category='error')
    return renderRegistre()


@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    today = date.today()
    # Como se ha accedido directamente desde ver reservas se muestran las reservas de esta semana.
    return renderReserves(today)


@app.route('/prev_week', methods=['GET', 'POST'])
def prev_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana anterior
    day = datetime_obj.date() - timedelta(days=7)
    return renderReserves(day)


@app.route('/next_week', methods=['GET', 'POST'])
def next_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana siguiente
    day = datetime_obj.date() + timedelta(days=7)
    return renderReserves(day)


@app.route('/usuaris', methods=['GET', 'POST'])
def usuaris():
    return renderUsuaris()


@app.route('/modifica', methods=['GET', 'POST'])
def modifica():
    # Obtiene el listado de identificador del cliente a modificar.
    idclient = request.args.get('idclient')
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('modifica.html', usuaris=listUsuaris, id=idclient)


@app.route('/updateuser', methods=['GET', 'POST'])
def updateuser():
    if request.method == 'POST':
        # Obtiene los datos.
        idclient = request.form.get('idclient')
        nom = request.form.get('nom')
        llinatges = request.form.get('llinatges')
        telefon = request.form.get('telefon')
        # Hace el update de los datos del usuario.
        gimnas.updateUser(idclient, nom, llinatges, telefon)

    return renderUsuaris()


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
    if request.method == 'POST':
        idclient = request.form.get('idclient')
        nom = request.form.get('nom')
        llinatges = request.form.get('llinatges')
        telefon = request.form.get('telefon')
        # Anade el usuario a la tabla clientes.
        gimnas.addUser(idclient, nom, llinatges, telefon)

    return renderUsuaris()


@app.route('/elimina', methods=['GET', 'POST'])
def elimina():
    # Obtenemos el id del cliente a eliminar.
    idclient = request.args.get('idclient')
    # Elimina las realaciones previas de la tabla reserves.
    gimnas.eliminaReservaUser(idclient)
    # Elimina el usuario de la tabla clients
    gimnas.eliminaUser(idclient)

    return renderUsuaris()


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
