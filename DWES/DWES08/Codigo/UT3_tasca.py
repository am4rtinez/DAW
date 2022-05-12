from flask import Flask, render_template, request, session
# from database import gimnas
from database import gimnas
import datetime
from calendarAPI import calendar

app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'


def comprova(reserva):
	retorno=0
	if reserva['idusuari']=="":
		retorno="No has introduit el nom"
	if not reserva['tipopista']:
		retorno="Indica el tipus de pista"
	if retorno==0:
		ocupada=gimnas.comprovaPista(reserva['dia'],reserva['hora'],reserva['tipopista'])
		if ocupada==1:
			retorno="La pista ja està ocupada"
	return retorno

def TaulaPistes(llistaRes):
	taula=[]
	for fila in range(0,5):
		filaTemp=[]
		for columna in range(0,6):
			tempVal=""
			for reserva in llistaRes:
				if reserva['data'].weekday()==fila and reserva['data'].hour==columna+15:
					tempVal=tempVal+reserva['nom']+" "+reserva['llinatges']+" ["+reserva['tipo']+"]"+" "
			filaTemp.append(tempVal)
		taula.append(filaTemp)
	return taula

@app.route('/')
def index():
  hoy=datetime.date.today() #avui
  lunes=hoy-datetime.timedelta(days=hoy.weekday())
  viernes=lunes+datetime.timedelta(days=4)
  session['lunes']=lunes.strftime("%d-%m-%Y")
  session['viernes']=viernes.strftime("%d-%m-%Y")
  # llistaRes=gimnas.cargaReservas(session['lunes'])
  # taulaReserves=TaulaPistes(llistaRes)
  llistaResCalendar=calendar.read_week(session['lunes'])
  taulaReserves=TaulaPistes(llistaResCalendar)
  return render_template('UT3_tasca_reserves.html',taula=taulaReserves)

@app.route('/formulari')
def formulari():
	return render_template('UT3_tasca_registre.html',usuaris=gimnas.cargaUsuaris())

# This page will have the sign up form
@app.route('/reservar')
def reservar():
  idusuari= request.args.get('usuari')
  tipopista= request.args.get('tipopista')
  hora= request.args.get('hora')
  dia= request.args.get('dia')	
  ReservaActual={'idusuari':idusuari,'tipopista':tipopista,'hora':hora,'dia':dia}
  comp=comprova(ReservaActual)
  if comp==0:
    data=dia+" "+str(hora)+":00:00"
    # gimnas.reservaPista(data,idusuari,tipopista)
    calendar.reservaPista(data,idusuari,tipopista)
    llistaRes=gimnas.cargaReservas(session['lunes'])
    taulaReserves=TaulaPistes(llistaRes)
    return render_template('UT3_tasca_reserves.html',taula=taulaReserves)
  else:
    return render_template('UT3_tasca_registre.html',alerta=comp)

@app.route('/reserves')
def reserves():
  # llistaRes=gimnas.cargaReservas(session['lunes'])
  # taulaReserves=TaulaPistes(llistaRes)
  llistaResCalendar=calendar.read_week(session['lunes'])
  taulaReserves=TaulaPistes(llistaResCalendar)
  return render_template('UT3_tasca_reserves.html',taula=taulaReserves)


@app.route('/usuaris')
def usuaris():
	usuaris=gimnas.cargaUsuaris()
	for a in range(0,len(usuaris)):
		usuaris[a]['actualiza']=0
	print(usuaris)
	return render_template('UT3_tasca_usuaris.html',usuaris=usuaris)

@app.route('/borrausuari')
def borrausuari():
	idusuari= request.args.get('idusuari')
	gimnas.borraUsuari(idusuari)
	usuaris=gimnas.cargaUsuaris()
	for a in range(0,len(usuaris)):
		usuaris[a]['actualiza']=0
	return render_template('UT3_tasca_usuaris.html',usuaris=usuaris)

@app.route('/editausuari')
def editausuari():
	idusuari= request.args.get('idusuari')
	print(idusuari)
	usuaris=gimnas.cargaUsuaris()
	for a in range(0,len(usuaris)):
		if idusuari==str(usuaris[a]['idclient']):
			usuaris[a]['actualiza']=1
		else:
			usuaris[a]['actualiza']=0
	print(usuaris)
	return render_template('UT3_tasca_usuaris.html',usuaris=usuaris)

@app.route('/afegeixausuari')
def afegeixusuari():
	idusuari= gimnas.nouIdUsuari()
	usuaris=gimnas.cargaUsuaris()
	for a in range(0,len(usuaris)):
		usuaris[a]['actualiza']=0
	usuaris.append({'idclient':idusuari,'actualiza':1})
	print(usuaris)
	return render_template('UT3_tasca_usuaris.html',usuaris=usuaris)

@app.route('/guardausuari')
def guardausuari():
	idusuari= request.args.get('idusuari')
	nom= request.args.get('nom')
	llinatges= request.args.get('llinatges')
	telefon= request.args.get('telefon')
	gimnas.modificaUsuari(idusuari,nom,llinatges,telefon)
	usuaris=gimnas.cargaUsuaris()
	for a in range(0,len(usuaris)):
		usuaris[a]['actualiza']=0
	return render_template('UT3_tasca_usuaris.html',usuaris=usuaris)

@app.route('/augmentasetmana')
def augmentasetmana():
  lunes=datetime.datetime.strptime(session['lunes'],'%d-%m-%Y')+datetime.timedelta(days=7)
  viernes=datetime.datetime.strptime(session['viernes'],'%d-%m-%Y')+datetime.timedelta(days=7)
  session['lunes']=lunes.strftime("%d-%m-%Y")
  session['viernes']=viernes.strftime("%d-%m-%Y")	
  # llistaRes=gimnas.cargaReservas(session['lunes'])
  # taulaReserves=TaulaPistes(llistaRes)
  llistaResCalendar=calendar.read_week(session['lunes'])
  taulaReserves=TaulaPistes(llistaResCalendar)
  return render_template('UT3_tasca_reserves.html',taula=taulaReserves)

@app.route('/restasetmana')
def restasetmana():
  lunes=datetime.datetime.strptime(session['lunes'],'%d-%m-%Y')-datetime.timedelta(days=7)
  viernes=datetime.datetime.strptime(session['viernes'],'%d-%m-%Y')-datetime.timedelta(days=7)
  session['lunes']=lunes.strftime("%d-%m-%Y")
  session['viernes']=viernes.strftime("%d-%m-%Y")	
  # llistaRes=gimnas.cargaReservas(session['lunes'])
  # taulaReserves=TaulaPistes(llistaRes)
  llistaResCalendar=calendar.read_week(session['lunes'])
  taulaReserves=TaulaPistes(llistaResCalendar)
  return render_template('UT3_tasca_reserves.html',taula=taulaReserves)

if __name__ == '__main__':
	app.run(debug=True)
