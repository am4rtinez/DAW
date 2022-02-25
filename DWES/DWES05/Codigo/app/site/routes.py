from flask import redirect, render_template, url_for, request, flash
from flask_login import current_user, login_required
from datetime import datetime, timedelta, date
from app.dbmodel import dbo
from app import login_manager
from . import site_bp

# Superdiccionario matriz de la tabla.
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
def reset_tabla():
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

# Rellena la el superdiccionario con las reservas de la semana obtenidas a traves de consulta a la BD.
def generar_tabla(reservas):
    global tabla
    reset_tabla()
    for r in reservas:
        pista = r['pista'].lower()
        hora = r['hora'].lower()
        dia = r['dia'].lower()
        idclient = r['idclient']
        if current_user.id == idclient:
            tabla[pista][hora][dia] = "RESERVAT"
        else:
            tabla[pista][hora][dia] = "NO DISPONIBLE"

# Comprobamos si existe la reserva. En caso de no existir devolvera None.
def comprobar_reserva(data, pista, usuari):
    reserva = dbo.comprobar_reserva(data, pista, usuari)
    return reserva


# Insercion de los datos de la reserva en BD.
def crear_reserva(data, pista, usuari):
    # Definicio de variables necesaries
    global tabla
    dbo.insertReserva(data, pista, usuari)


# Devuelve el dia de la semana (entero) de una fecha pasada por parametro.
# El formato de la decha es yyyy-mm-dd.
def get_week_day(data):
    d = datetime.strptime(data, '%Y-%m-%d')
    return d.weekday()


# Para optimizar codigo el render template de registre se hace en esta funcion.
# Error = 0 -> No hay error.
# Error = 1 -> El error es intentar reservar en fin de semana.
# Error = 2 -> La reserva ya existe.
def render_registre():
    today = date.today()
    # Obtiene la lista de las pistas.
    lpistas = dbo.get_pistas()
    # lusers = dbo.getUsers()                  # Obtiene la lista de usuarios.
    return render_template('registre.html', fecha=today, pistes=lpistas)


# Para optimizar codigo el render template de reserves se hace en esta funcion.
def render_reserves(day):
    swd = day - timedelta(days=day.weekday())   # Dia de inicio de la semana.
    ewd = swd + timedelta(days=6)               # Dia final de la semana.
    # Obtiene la consulta de reservas que comprende los dias de la semana del día pasado por parámetro.
    reserves = dbo.get_reservas(swd, ewd)
    # Obtiene la consulta de reservas para no usar el superdiccionario.
    # lres = dbo.getListaReservas(swd, ewd)
    # Genera la tabla de la cual se impriment los datos en el render.
    generar_tabla(reserves)
    # taula = genera_taula_pistes(lres)
    # return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla, taula=taula)
    return render_template('reserves.html', fecha=day, swd=swd, ewd=ewd, tab=tabla)

@site_bp.route("/")
def index():
    return redirect(url_for('auth.login'))

@site_bp.route('/home', methods=['GET', 'POST'])
@login_required
def home():
    return render_template('home.html')

@site_bp.route('/reserves', methods=['GET', 'POST'])
@login_required
def reserves():
    # Si el usuario tiene reservas se obtiene la mas actual y se muestra esa semana.
    if dbo.check_reservas_usuario(current_user.id):
        fecha = dbo.get_fecha_ultima_reserva(current_user.id)
        datetime_obj = datetime.strptime(str(fecha), "%Y-%m-%d").date()
        return render_reserves(datetime_obj)
    # Si no tiene reservas se muestra la semana actual.
    today = date.today()
    return render_reserves(today)
    # return redirect('home')

@site_bp.route('/prev_week', methods=['GET', 'POST'])
@login_required
def prev_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, "%Y-%m-%d")
    # Obtenemos el dia de la semana anterior
    day = datetime_obj.date() - timedelta(days=7)
    return render_reserves(day)


@site_bp.route('/next_week', methods=['GET', 'POST'])
@login_required
def next_week():
    fecha = request.args.get('fecha')
    datetime_obj = datetime.strptime(fecha, '%Y-%m-%d')
    # Obtenemos el dia de la semana siguiente
    day = datetime_obj.date() + timedelta(days=7)
    return render_reserves(day)

@site_bp.route('/formulari', methods=['GET', 'POST'])
@login_required
def formulari():
    # Si el metodo utilizado es POST es que se ha usado el formulario de registro.
    if request.method == 'POST':
        dia = request.form.get('dia')
        hora = request.form.get('hora')
        pista = request.form.get('tipopista')
        usuari = current_user.id
        # Comprobamos si el dia seleccionado para la reserva no es sabado o domingo.
        weekday = get_week_day(dia)
        # Sabado es 5 y Domingo es 6.
        if ((weekday == 5) or (weekday == 6)):
            flash('Ha seleccionado un dia de fin de semana.', category='error')
        else:
            data = dia + " " + hora + ":00:00"
            # Comprobamos si existe la reserva
            existe = comprobar_reserva(data, pista, usuari)
            if (existe == None):
                # Si la reserva no existe entonces la creamos.
                crear_reserva(data, pista, usuari)
                flash('La reserva se ha realizado.', category='success')
            else:
                # Si la reserva existe entonces devuelve template registro y muestra error.
                flash('La reserva que intenta realizar no esta disponible.', category='error')
    return render_registre()
    # return redirect('home')