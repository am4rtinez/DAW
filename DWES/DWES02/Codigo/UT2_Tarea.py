from flask import Flask, config, render_template, request, sessions

app = Flask(__name__)
#app.config['SECRET_KEY '] = 'secret'
reservas = []

#Diccionarios para la tabla predefinidos.
#Exterior
p15ext = {'hora':'15:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p16ext = {'hora':'16:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p17ext = {'hora':'17:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p18ext = {'hora':'18:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p19ext = {'hora':'19:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p20ext = {'hora':'20:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
#Coberta
p15cob = {'hora':'15:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p16cob = {'hora':'16:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p17cob = {'hora':'17:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p18cob = {'hora':'18:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p19cob = {'hora':'19:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}
p20cob = {'hora':'20:00','l':'', 'm':'', 'x':'', 'j':'', 'v':''}

#Matriz de la tabla con los diccionarios por fila
tablaExterior = [p15ext,p16ext,p17ext,p18ext,p19ext,p20ext]
tablaCoberta = [p15cob,p16cob,p17cob,p18cob,p19cob,p20cob]

tabla = {
    'exterior':{
        '15:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '16:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '17:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '18:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '19:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '20:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
    },
    'coberta':{
        '15:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '16:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '17:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '18:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '19:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
        '20:00':{
            'dilluns':'', 'dimarts':'', 'dimecres':'', 'dijous':'', 'divendres':''
        },
    }
}

#Funcion para generar el diccionario de la reserva.
def generarMatriz(req_nom, req_tipopista, req_dia, req_hora):
    #Definicio de variables necesaries
    #global tablaExterior
    #global tablaCoberta

    #global p15ext, p16ext, p17ext, p18ext, p19ext, p20ext, p15cob, p16cob, p17cob, p18cob, p19cob, p20cob
    tabla[req_tipopista][req_hora][req_dia] = req_nom
    print(tabla)

    if req_dia == 'dilluns':
        if req_hora == '15:00':
            if req_tipopista == 'exterior':
                p15ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p15cob['l'] = req_nom
        elif req_hora == '16:00':
            if req_tipopista == 'exterior':
                p16ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p16cob['l'] = req_nom
        elif req_hora == '17:00':
            if req_tipopista == 'exterior':
                p17ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p17cob['l'] = req_nom
        elif req_hora == '18:00':
            if req_tipopista == 'exterior':
                p18ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p18cob['l'] = req_nom
        elif req_hora == '19:00':
            if req_tipopista == 'exterior':
                p19ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p19cob['l'] = req_nom
        elif req_hora == '20:00':
            if req_tipopista == 'exterior':
                p20ext['l'] = req_nom
            elif req_tipopista == 'coberta':
                p20cob['l'] = req_nom

    elif req_dia == 'dimarts':
        if req_hora == '15:00':
            if req_tipopista == 'exterior':
                p15ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p15cob['m'] = req_nom
        elif req_hora == '16:00':
            if req_tipopista == 'exterior':
                p16ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p16cob['m'] = req_nom
        elif req_hora == '17:00':
            if req_tipopista == 'exterior':
                p17ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p17cob['m'] = req_nom
        elif req_hora == '18:00':
            if req_tipopista == 'exterior':
                p18ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p18cob['m'] = req_nom
        elif req_hora == '19:00':
            if req_tipopista == 'exterior':
                p19ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p19cob['m'] = req_nom
        elif req_hora == '20:00':
            if req_tipopista == 'exterior':
                p20ext['m'] = req_nom
            elif req_tipopista == 'coberta':
                p20cob['m'] = req_nom

    elif req_dia == 'dimecres':
        if req_hora == '15:00':
            if req_tipopista == 'exterior':
                p15ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p15cob['x'] = req_nom
        elif req_hora == '16:00':
            if req_tipopista == 'exterior':
                p16ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p16cob['x'] = req_nom
        elif req_hora == '17:00':
            if req_tipopista == 'exterior':
                p17ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p17cob['x'] = req_nom
        elif req_hora == '18:00':
            if req_tipopista == 'exterior':
                p18ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p18cob['x'] = req_nom
        elif req_hora == '19:00':
            if req_tipopista == 'exterior':
                p19ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p19cob['x'] = req_nom
        elif req_hora == '20:00':
            if req_tipopista == 'exterior':
                p20ext['x'] = req_nom
            elif req_tipopista == 'coberta':
                p20cob['x'] = req_nom

    elif req_dia == 'dijous':
        if req_hora == '15:00':
            if req_tipopista == 'exterior':
                p15ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p15cob['j'] = req_nom
        elif req_hora == '16:00':
            if req_tipopista == 'exterior':
                p16ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p16cob['j'] = req_nom
        elif req_hora == '17:00':
            if req_tipopista == 'exterior':
                p17ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p17cob['j'] = req_nom
        elif req_hora == '18:00':
            if req_tipopista == 'exterior':
                p18ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p18cob['j'] = req_nom
        elif req_hora == '19:00':
            if req_tipopista == 'exterior':
                p19ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p19cob['j'] = req_nom
        elif req_hora == '20:00':
            if req_tipopista == 'exterior':
                p20ext['j'] = req_nom
            elif req_tipopista == 'coberta':
                p20cob['j'] = req_nom

    elif req_dia == 'divendres':
        if req_hora == '15:00':
            if req_tipopista == 'exterior':
                p15ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p15cob['v'] = req_nom
        elif req_hora == '16:00':
            if req_tipopista == 'exterior':
                p16ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p16cob['v'] = req_nom
        elif req_hora == '17:00':
            if req_tipopista == 'exterior':
                p17ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p17cob['v'] = req_nom
        elif req_hora == '18:00':
            if req_tipopista == 'exterior':
                p18ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p18cob['v'] = req_nom
        elif req_hora == '19:00':
            if req_tipopista == 'exterior':
                p19ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p19cob['v'] = req_nom
        elif req_hora == '20:00':
            if req_tipopista == 'exterior':
                p20ext['v'] = req_nom
            elif req_tipopista == 'coberta':
                p20cob['v'] = req_nom
        
        print(tablaExterior)
        print(tablaCoberta)
    
def crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora):
    #Definicio de variables necesaries
    global reservas
    global tablaExterior, tablaCoberta

    #Formato reserva {'Nom':'Nom','Telefon':'Telefon','Pista':'Tipopista','Dia':'Dia','Hora':'Hora'}
    reserva = {'nom':req_nom,'telefon':req_telefon,'pista':req_tipopista,'dia':req_dia,'hora':req_hora}

    #Añade la info a la matriz.
    generarMatriz(req_nom, req_tipopista, req_dia, req_hora)

    #Formato reservas [reserva1,reserva2,...]
    reservas.append(reserva)

    #Revertimos el array para que la ultima reserva aparezca la primera.
    reservas.reverse()

@app.route('/')
def index():
    #Definicio de variables necesaries
    global reservas
        
    return render_template('registre.html')

@app.route('/formulari', methods=['GET', 'POST'])
def formulari():
    #Definicio de variables necesaries

    return render_template('registre.html')

@app.route('/reserves', methods=['GET', 'POST'])
def reserves():
    #Definicio de variables necesaries
    global reservas
    global tablaExterior
    global tablaCoberta
    global tabla

    #Controlamos el tipo de request para que no se generen reservas vacias.
    if request.method == 'POST':

        #Obtencion de los datos del request
        req_dia = request.form.get('dia')
        req_hora = request.form.get('hora')
        req_tipopista = request.form.get('tipopista')
        req_nom = request.form.get('nom')
        req_telefon = request.form.get('telefon')

        if not reservas: #Si no hay reservas en el array no es necesario comprobar la disponibilidad del dia-hora-pista
            crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora)
            return render_template('reserves.html', data = reservas, tabext = tablaExterior, tabcob = tablaCoberta)

        else: #Comprobar si es posible tramitar la reserva.
            
            reservada = False
            for i in range(len(reservas)):
                if reservas[i]['dia'] == req_dia: #Comprobacion del dia
                    if reservas[i]['hora'] == req_hora: #Comprobacion de la hora
                        if reservas[i]['pista'] == req_tipopista: #Comprobacion de la pista
                            reservada = True
                            break
            
            #Si la pista esta reservada devuelve pagina inicial y muestra alerta.
            if reservada:
                return render_template('registre.html', reservada = True)
            else:
                crearReserva(req_nom, req_telefon, req_tipopista, req_dia, req_hora)
                return render_template('reserves.html', data = reservas, tabext = tablaExterior, tabcob = tablaCoberta)
    else :
        return render_template('reserves.html', data = reservas, tabext = tablaExterior, tabcob = tablaCoberta)

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True) #Modificado para que el host sea visible en toda la red.
    #app.run(debug=True)