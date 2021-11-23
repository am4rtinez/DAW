from flask import Flask, config, render_template, request, sessions

app = Flask(__name__)
app.config['SECRET_KEY '] = 'secret'
reservas = []

reserva1 = {'nom': 'Angel', 'telefon': '611234567', 'pista': 'coberta', 'dia': 'Dilluns', 'hora': '15:00'}
reserva2 = {'nom': 'Angel', 'telefon': '611234567', 'pista': 'exterior', 'dia': 'Dilluns', 'hora': '15:00'}
reserva3 = {'nom': 'Angel', 'telefon': '611234567', 'pista': 'coberta', 'dia': 'Dimarts', 'hora': '15:00'}
reserva4 = {'nom': 'Angel', 'telefon': '611234567', 'pista': 'exterior', 'dia': 'Dimarts', 'hora': '15:00'}

#horaServidor=datetime.now().strftime("%H:%M:%S")

def crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora):
    global reservas
    #Formato reserva {'Nom':'Nom','Telefon':'Telefon','Pista':'Tipopista','Dia':'Dia','Hora':'Hora'}
    reserva = {'nom':req_nom,'telefon':req_telefon,'pista':req_tipopista,'dia':req_dia,'hora':req_hora}
    print(reserva)

    #Formato reservas [reserva1,reserva2,...]
    reservas.append(reserva)

    #Revertimos el array para que la ultima reserva aparezca la primera.
    reservas.reverse()
    print(reservas)

@app.route('/')
def index():
    #Definicio de variables necesaries
    global reservas
    reservas.append(reserva1)
    reservas.append(reserva2)
    reservas.append(reserva3)
    reservas.append(reserva4)
        
    return render_template('registre.html')

@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    #Definicio de variables necesaries

    return render_template('registre.html')

@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    #Definicio de variables necesaries
    global reservas
    #Controlamos el tipo de request para que no se generen reservas vacias.
    if request.method == 'POST':
        #Imprimimos el request obtenido del formulario.
        print(request)

        #Obtencion de los datos del request
        req_dia = request.form.get('dia')
        req_hora = request.form.get('hora')
        req_tipopista = request.form.get('tipopista')
        req_nom = request.form.get('nom')
        req_telefon = request.form.get('telefon')

        if not reservas: #Si no hay reservas en el array no es necesario comprobar la disponibilidad del dia-hora-pista
            crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora)
            return render_template('reserves.html', data = reservas)
        else:
            #Comprobar si es posible tramitar la reserva.
            reservada = False
            print(len(reservas))
            for i in range(len(reservas)):
                if reservas[i]['dia'] == req_dia: #Comprobacion del dia
                    if reservas[i]['hora'] == req_hora: #Comprobacion de la hora
                        if reservas[i]['pista'] == req_tipopista: #Comprobacion de la pista
                            reservada = True
                            break
            
            if reservada:
                return render_template('registre.html', reservada = True)
            else:
                crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora)
                return render_template('reserves.html', data = reservas)
    else :
        return render_template('reserves.html', data = reservas)

if __name__ == '__main__':
    app.run(host="192.168.1.8", debug=True)
    #app.run(debug=True)