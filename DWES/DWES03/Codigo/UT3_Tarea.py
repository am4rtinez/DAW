from flask import Flask, render_template, request

app = Flask(__name__)


@app.route('/')
def index():
    # Definicio de variables necesaries

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

    return render_template('usuaris.html')


@app.route('/modifica', methods=['GET', 'POST'])
def modifica():
    # Definicio de variables necesaries

    return render_template('modifica.html')


if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app.run(host="0.0.0.0", debug=True)
    # app.run(debug=True)
