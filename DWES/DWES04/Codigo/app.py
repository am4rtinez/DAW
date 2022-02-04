from flask import Flask, render_template, request, flash, redirect, url_for, session
from flask_login import login_user, login_required, logout_user, current_user, LoginManager
from datetime import datetime, timedelta, date
from models import User
from dbmodel import dbo

app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'

login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'


# The user_loader decorator allows flask-login to load the current user
# and grab their id.
# tell Flask-Login how to load a user from a Flask request and from its session.
@login_manager.user_loader
def load_user(user_id):
    if user_id:
        user = User()
        user.from_id(user_id)
        return user
    return None


@login_manager.unauthorized_handler
def unauthorized():
    # do stuff
    flash(login_manager.login_message, category='error')
    return redirect(url_for('login'))


# TODO: El "superdiccionari" tabla no és necesari, pots passar les reserves i que sigui el template html qui, amb 2 bucles anidats, vagi generant tots els <td> i amb un if decideixi si hi fica informació o no.
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
    reserva = dbo.comprobarReserva(data, pista, usuari)
    return reserva


# Insercion de los datos de la reserva en BD.
def crearReserva(data, pista, usuari):
    # Definicio de variables necesaries
    global tabla
    dbo.insertReserva(data, pista, usuari)


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
    lpistas = dbo.getPistas()
    # lusers = dbo.getUsers()                  # Obtiene la lista de usuarios.
    return render_template('registre.html', fecha=today, pistes=lpistas)


# Para optimizar codigo el render template de reserves se hace en esta funcion.
def renderReserves(day):
    swd = day - timedelta(days=day.weekday())   # Dia de inicio de la semana.
    ewd = swd + timedelta(days=6)               # Dia final de la semana.
    # Obtiene la consulta de reservas que comprende los dias de la semana del día pasado por parámetro.
    reserves = dbo.getReservas(swd, ewd)
    # Genera la tabla de la cual se impriment los datos en el render.
    generarTabla(reserves)
    return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla)


@app.route('/')
def index():
    # Si el usuario esta autenticado lo redirigimos al home.
    if current_user.is_authenticated:
        return redirect(url_for('home'))
    return redirect(url_for('login'))


@app.route('/login', methods=['GET', 'POST'])
def login():
    # TODO Hay que comprobar si la sesion del usuario esta activa.
    # Si el usuario esta autenticado lo redirigimos al home.
    if current_user.is_authenticated:
        return redirect(url_for('home'))
    # Si se llega al login a traves del formulario es que el usuario aun no esta logado.
    if request.method == 'POST':
        user = User()
        usuari = request.form['username']
        pwd = request.form['password']
        # Comprueba si el username es valido.
        if user.from_username(usuari) == False:
            flash('Usuario incorrecto.', category='error')
        else:
            if user.check_user(pwd):
                user.get_user_id()
                login_user(user)
                return redirect(url_for('home'))
            else:
                flash('Password incorrecto.', category='error')

    return render_template('login.html')


@app.route('/logout')
@login_required
def logout():
    # TODO: Cerrar la sesion y eliminar la cookie.
    logout_user()
    session.clear()
    flash('Se ha cerrado la sesion.', category='success')
    return redirect(url_for('login'))


@app.route('/home', methods=['GET', 'POST'])
@login_required
def home():
    user = User()
    return render_template('home.html')


@app.route('/formulari', methods=['GET', 'POST'])
@login_required
def formulari():
    # Si el metodo utilizado es POST es que se ha usado el formulario de registro.
    if request.method == 'POST':
        dia = request.form.get('dia')
        hora = request.form.get('hora')
        pista = request.form.get('tipopista')
        usuari = current_user.id
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
    # return redirect('home')


@app.route('/reserves', methods=['GET', 'POST'])
@login_required
def reserves():
    today = date.today()
    # Como se ha accedido directamente desde ver reservas se muestran las reservas de esta semana.
    return renderReserves(today)
    # return redirect('home')


@app.route('/prev_week', methods=['GET', 'POST'])
@login_required
def prev_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana anterior
    day = datetime_obj.date() - timedelta(days=7)
    return renderReserves(day)


@app.route('/next_week', methods=['GET', 'POST'])
@login_required
def next_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana siguiente
    day = datetime_obj.date() + timedelta(days=7)
    return renderReserves(day)


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
