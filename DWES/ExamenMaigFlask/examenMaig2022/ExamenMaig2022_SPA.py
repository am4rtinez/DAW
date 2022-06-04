from flask import Flask, render_template, request, session, redirect, url_for
import numpy as np
import datetime
import urllib.request, json

app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'


@app.route('/')
def index():
    session['aeroportCode']="PMI"
    session['pantalla']="arribades"
    session['fecha']="2022-05-01_00:00"
    url="http://127.0.0.1:5001/vols/aeroports"
    response = urllib.request.urlopen(url)
    data = response.read()
    jsonAeroports = json.loads(data)
    session['aeroports']=jsonAeroports
    return redirect(url_for('arribades'))

@app.route('/sortides')
def sortides():
    session['pantalla']="sortides"
    url="http://127.0.0.1:5001/vols/sortides/"+session['aeroportCode']+"/"+session['fecha']
    response = urllib.request.urlopen(url)
    data = response.read()
    jsonVols = json.loads(data)
    print(jsonVols)
    return render_template('aeroport.html',vols=jsonVols)

# This page will have the sign up form
@app.route('/arribades')
def arribades():
    session['pantalla']="arribades"
    url="http://127.0.0.1:5001/vols/arribades/"+session['aeroportCode']+"/"+session['fecha']
    response = urllib.request.urlopen(url)
    data = response.read()
    jsonVols = json.loads(data)
    print(jsonVols)
    return render_template('aeroport.html',vols=jsonVols)

@app.route('/aeroport') #la utilitzam per canviar d'aeroport
def aeroport():
    nouAeroport= request.args.get('codi')
    session['aeroportCode']=nouAeroport
    if session['pantalla']=="arribades":
      return redirect(url_for('arribades'))
    else:
      return redirect(url_for('sortides'))

@app.route('/date_change') #Para hacer el cambio de fecha
def change_date():
    fecha = request.args.get('fecha')
    session['fecha']=fecha+"_00:00"

    # No funciona esta parte. No me ha dado tiempo. Solo me falta 
    if session['pantalla']=="arribades":
      session['pantalla']="arribades"
      url="http://127.0.0.1:5001/vols/arribades2/"+session['aeroportCode']+"/"+session['fecha']
      response = urllib.request.urlopen(url)
      data = response.read()
      jsonVols = json.loads(data)
      print(jsonVols)
      return render_template('aeroport.html',vols=jsonVols)
    else:
      session['pantalla']="sortides"
      url="http://127.0.0.1:5001/vols/sortides2/"+session['aeroportCode']+"/"+session['fecha']
      response = urllib.request.urlopen(url)
      data = response.read()
      jsonVols = json.loads(data)
      print(jsonVols)
      return render_template('aeroport.html',vols=jsonVols)

@app.route('/retraso/<string:id_vol>',methods=['GET'])
def retraso(id_vol):
    url="http://127.0.0.1:5001/vols/retraso/" + id_vol
    print(url)
    req = urllib.request.Request(url, method='PUT')
    response = urllib.request.urlopen(req)
    if session['pantalla']=="arribades":
      return redirect(url_for('arribades'))
    else:
      return redirect(url_for('sortides'))

if __name__ == '__main__':
	app.run(debug=True)
