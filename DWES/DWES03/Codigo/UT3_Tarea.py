from flask import Flask, render_template, request
from database import gimnas

app = Flask(__name__)


@app.route('/')
def index():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('registre.html')


@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('registre.html', usuaris=listUsuaris)


@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    return render_template('reserves.html')


@app.route('/usuaris', methods=['GET', 'POST'])
def usuaris():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/modifica', methods=['GET', 'POST'])
def modifica():
    # Obtiene el listado de identificador del cliente a modificar.
    idclient = request.args.get('idclient')
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('modifica.html', usuaris=listUsuaris, id=idclient)


@app.route('/updateuser', methods=['GET', 'POST'])
def updateuser():
    # Obtiene los datos.
    idclient = request.form.get('idclient')
    nom = request.form.get('nom')
    llinatges = request.form.get('llinatges')
    telefon = request.form.get('telefon')
    # Hace el update de los datos del usuario.
    gimnas.updateUser(idclient, nom, llinatges, telefon)
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/formuser', methods=['GET', 'POST'])
def formuser():
    # Obtiene el lisado de usuarios
    listUsuaris = gimnas.getUsers()
    # Obtiene el que se le va a asignar al usuario.
    idclient = gimnas.getId()
    return render_template('usuaris.html', usuaris=listUsuaris, id=idclient)


@app.route('/adduser', methods=['GET', 'POST'])
def adduser():
    # Obtiene los datos.
    idclient = request.form.get('idclient')
    nom = request.form.get('nom')
    llinatges = request.form.get('llinatges')
    telefon = request.form.get('telefon')
    # Anade el usuario a la tabla clientes.
    gimnas.addUser(idclient, nom, llinatges, telefon)
    # Obtiene listado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


@app.route('/elimina', methods=['GET', 'POST'])
def elimina():
    # Obtenemos el id del cliente a eliminar.
    idclient = request.args.get('idclient')
    # Elimina las realaciones previas de la tabla reserves.
    gimnas.eliminaReservaUser(idclient)
    # Elimina el usuario de la tabla clients
    gimnas.eliminaUser(idclient)
    # Obtiene listado de usuarios
    listUsuaris = gimnas.getUsers()
    return render_template('usuaris.html', usuaris=listUsuaris)


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
