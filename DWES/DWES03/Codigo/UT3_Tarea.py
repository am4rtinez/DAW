from flask import Flask, render_template, request
from database import gimnas

app = Flask(__name__)


@app.route('/')
def index():
    # Definicio de variables necesaries
    listUsuaris = gimnas.getUsers()
    print(listUsuaris)
    return render_template('registre.html')


@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    return render_template('registre.html')


@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    # Definicio de variables necesaries

    return render_template('reserves.html')


@app.route('/usuaris', methods=['GET', 'POST'])
def usuaris():
    # Definicio de variables necesaries
    listUsuaris = gimnas.getUsers()
    print(listUsuaris)
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/modifica', methods=['GET', 'POST'])
def modifica():
    # Definicio de variables necesaries

    return render_template('modifica.html')


@app.route('/formuser', methods=['GET', 'POST'])
def formuser():
    # Definicio de variables necesaries
    listUsuaris = gimnas.getUsers()
    idclient = gimnas.getId()
    return render_template('usuaris.html', usuaris=listUsuaris, id=idclient)


@app.route('/adduser', methods=['GET', 'POST'])
def adduser():
    # Definicio de variables necesaries
    idclient = request.form.get('idclient')
    nom = request.form.get('nom')
    llinatges = request.form.get('llinatges')
    telefon = request.form.get('telefon')
    gimnas.addUser(idclient, nom, llinatges, telefon)
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/elimina', methods=['GET', 'POST'])
def elimina():
    # Definicio de variables necesaries
    idclient = request.args.get('idclient')
    gimnas.eliminaReservaUser(idclient)
    gimnas.eliminaUser(idclient)
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
