from flask import render_template, redirect, url_for, request, session, flash, make_response
from flask_login import current_user, login_user, logout_user, login_required
from werkzeug.urls import url_parse
from datetime import datetime, timedelta, date
from app.models import User
from app import login_manager
from . import auth_bp
from .forms import LoginForm, SignupForm

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

    # Establecemos la categoria del mensage de login requerido por defecto.
# Establece tambien el redireccionamiento.
@login_manager.unauthorized_handler
def unauthorized():
    # do stuff
    flash(login_manager.login_message, category='error')
    return redirect(url_for('auth.login'))

# @auth_bp.route('/login', methods=['GET', 'POST'])
# def login():
#     form = LoginForm()
#     if request.method == 'POST':
#         pass
    
#     return render_template('auth/login_form.html', form=form)

@auth_bp.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    # Requests de las cookies.
    user_cookie = request.cookies.get('username_cookie')
    pwd_cookie = request.cookies.get('user_pwd_cookie')

    # Si el usuario esta autenticado lo redirigimos al home.
    if current_user.is_authenticated:
        return redirect(url_for('site.reserves'))
    # Como no esta autenticado se comprueba si se han guardado las credenciales en las cookies (esto quiere decir que cerraron el navegador sin deslogarse)
    if (user_cookie != None):
        user = User()
        if user.from_username(user_cookie) == False:
            flash('Usuario incorrecto.', category='error')
        else:
            if user.check_user(pwd_cookie):
                user.get_user_id()
                login_user(user)
                return redirect(url_for('site.reserves'))
            else:
                flash('Password incorrecto.', category='error')
    # Si se llega al login a traves del formulario es que el usuario no se llego a logar nunca.
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
                cookie = make_response(redirect(url_for('site.reserves')))
                cookie.set_cookie('username_cookie', usuari, expires=datetime.now(
                ) + timedelta(days=30))
                cookie.set_cookie('user_pwd_cookie', pwd, expires=datetime.now(
                ) + timedelta(days=30))
                return cookie
                # return redirect(url_for('home'))
            else:
                flash('Password incorrecto.', category='error')
    # Si no es ninguno de los casos anteriores es que se va a proceder a realizar el login.
    return render_template('auth/login_form.html', form=form)

@auth_bp.route('/signup', methods=['GET', 'POST'])
def signup():
    form = SignupForm()
    if request.method == 'POST':
        username = request.form['username']
        name = request.form['name']
        lastname = request.form['lastname']
        email = request.form['email']
        phone = request.form['phone']
        password = request.form['password']
        password_check = request.form['password_check']
        pass
    if form.validate_on_submit():
        session['usuari'] = form.username.data
        return redirect(url_for("auth.login"))
    return render_template('auth/signup_form.html', form=form)


@auth_bp.route('/logout')
@login_required
def logout():
    # TODO: Cerrar la sesion y eliminar la cookie.
    logout_user()
    session.clear()
    flash('Se ha cerrado la sesion.', category='success')
    # Hacemos que las cookies expiren para que el usuario no pueda logarse aunque indique que cierre la sesion.
    cookie = make_response(redirect(url_for('auth.login')))
    cookie.set_cookie('username_cookie', '', expires=0)
    cookie.set_cookie('user_pwd_cookie', '', expires=0)
    return cookie
    # return redirect(url_for('login'))