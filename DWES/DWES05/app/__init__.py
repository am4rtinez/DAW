from flask import Flask, render_template, request, redirect, url_for
from app.forms import LoginForm

app = Flask(__name__)

# Using a production configuration
# app.config.from_object('config.ProdConfig')

# Using a development configuration
app.config.from_object('config.DevelopmentConfig')

@app.route('/')
def index():
    # TODO: comprobar si esta autenticado el usuario.
    return redirect(url_for('login'))

@app.route('/login')
def login():
    form = LoginForm()
    return render_template("/auth/login.html", form=form)

@app.route("/signup/", methods=["GET", "POST"])
def show_signup_form():
    if request.method == 'POST':
        name = request.form['name']
        email = request.form['email']
        password = request.form['password']
        next = request.args.get('next', None)
        if next:
            return redirect(next)
        return redirect(url_for('index'))
    return render_template("signup_form.html")