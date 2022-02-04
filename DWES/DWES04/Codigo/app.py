from distutils.log import error
from flask import Flask, render_template, request, flash, redirect, url_for
from flask_login import login_user, login_required, logout_user, current_user, LoginManager
from datetime import datetime, timedelta, date
from models import User

app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'

login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'


@login_manager.unauthorized_handler
def unauthorized():
    # do stuff
    flash(login_manager.login_message, category='error')
    return redirect(url_for('login'))


# The user_loader decorator allows flask-login to load the current user
# and grab their id.
@login_manager.user_loader
def load_user(user_id):
    if user_id:
        user = User()
        user.fromID(user_id)
        return user
    return None


@app.route('/')
def index():
    user = User()
    if user.is_authenticated:
        return redirect(url_for('home'))
    return render_template('login.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        usuari = request.form['username']
        pwd = request.form['password']
        user = User()
        user.fromUsername(usuari)
        if user.comprovaUsuari(pwd):
            user.getId()
            login_user(user)
            return redirect(url_for('home'))
        else:
            flash('Usuario / Password incorrecto.', category='error')

    return render_template('login.html')


@app.route('/logout')
@login_required
def logout():
    # TODO: Cerrar la sesion y eliminar la cookie.
    logout_user()
    flash('Se ha cerrado la sesion.', category='success')
    return redirect(url_for('login'))


@app.route('/home', methods=['GET', 'POST'])
@login_required
def home():
    user = User()
    return render_template('home.html')


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
