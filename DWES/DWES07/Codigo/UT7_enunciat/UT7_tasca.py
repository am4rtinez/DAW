from myproject import app
from flask import render_template, request, session, make_response
from flask_login import login_user, login_required, logout_user
from myproject.models import User
import config
import datetime

@app.route('/')
@app.route('/home')
def home():
	return render_template('UT7_tasca_login.html')

@app.route('/index')
@login_required
def index():
	hoy=datetime.date.today() #avui
	lunes=hoy-datetime.timedelta(days=hoy.weekday())
	viernes=lunes+datetime.timedelta(days=4)
	session['lunes']=lunes.strftime("%d/%m/%Y")
	session['viernes']=viernes.strftime("%d/%m/%Y")
	session['dia']=lunes.strftime("%Y-%m-%d")
	# llistaRes=gimnas.cargaReservas(session['lunes'])
	return render_template('UT7_tasca_main.html')


@app.route('/login', methods=['GET', 'POST'])
def login():   
	usuari= request.form['usuari']
	pwd=request.form['password']
	user=User()
	user.fromUsername(usuari)
	if user.comprovaUsuari(pwd):
		user.getId()
		login_user(user)
		hoy=datetime.date.today() #avui
		lunes=hoy-datetime.timedelta(days=hoy.weekday())
		viernes=lunes+datetime.timedelta(days=4)
		session['lunes']=lunes.strftime("%d/%m/%Y")
		session['viernes']=viernes.strftime("%d/%m/%Y")
		session['dia']=lunes.strftime("%Y-%m-%d")
		resp = make_response(render_template('UT7_tasca_main.html'))
		return resp		
	else:
		return render_template('UT7_tasca_login.html',loginmsg="Login Incorrecte")

@app.route('/logout', methods=['POST'])
@login_required
def logout():
	logout_user()
	resp = make_response(render_template('UT7_tasca_login.html'))
	return resp	

if __name__ == '__main__':
	app.run(debug=True)
	app.config.from_object(config.DevConfig)
