from flask import Flask, render_template
#Se tiene que poner delante from datetime para que importe la libreria instalada.
from datetime import datetime #heu de treure l'hora del servidor a partir d'aquesta llibreria
app = Flask(__name__)

@app.route('/')
def index():
    # Definim les variables
    nomUsuari="Angel Martinez Rodriguez"
    horaServidor=datetime.now().strftime("%H:%M:%S")    
    # Passam les variables al template "index.html" que esta a la carpeta 'templates'
    return render_template('index.html',nom=nomUsuari,hora=horaServidor)

if __name__ == '__main__':
    app.run(host="192.168.1.8", debug=True)