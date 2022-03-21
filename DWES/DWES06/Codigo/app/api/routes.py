from flask import request
from flask_restful import Resource, Api
from datetime import datetime, timedelta, date
from . import api_bp
import dbmodel

api = Api(api_bp)
mt=dbmodel.gimnas()

# Devuelve el dia de la semana (entero) de una fecha pasada por parametro.
# El formato de la decha es yyyy-mm-dd.
def get_week_from_day(data):
    day = datetime.strptime(data, '%Y-%m-%d')
    return get_week(day)

# Devuelve el inicio de la semana y el final de la semana.
def get_week(day):
    swd = day - timedelta(days=day.weekday())   # Dia de inicio de la semana.
    ewd = swd + timedelta(days=6)               # Dia final de la semana.
    return swd,ewd

class testing(Resource):
    def get(self):
        return {'test': 'Testing "Hello, World!"'}

'''
/gimnas/usuari 
    GET: retorna una llista amb tots els camps de la llista usuaris excepte el password
    POST: Afegeix un usuari (fent la conversió a hash del password)
'''
class usuaris_all(Resource):
    def get(self):
        mt.conecta()
        result=mt.load_all_users()
        mt.desconecta()        
        return result

    def post(self):
        mt.conecta()
        result=mt.add_user(request.json)
        mt.desconecta()  
        return result

'''
/gimnas/usuari/id
    GET: retorna els detalls de l'usuari excepte el password
    PUT: Modifica els camps d'un usuari. Si és el password s'ha de fer el hash
    DELETE: Elimina un usuari
'''
class usuaris(Resource):
    def get(self, id_user):
        mt.conecta()
        result=mt.load_user(id_user)
        mt.desconecta()
        return result
    
    def put(self, id_user):
        mt.conecta()
        result=mt.modify_user(id_user, request.json)
        mt.desconecta()

    def delete(self, id_user,):
        mt.conecta()
        result=mt.delete_user(id_user)
        mt.desconecta()

'''
/gimnas/reserves/setmana/data
    GET: Retorna totes les reserves de la setmana indicada a "data" (en format YYYY-mm-dd ).
         Ha de retornar el nom de la pista i el username de l'usuari.
'''
class reserves_setmana(Resource):
    def get(self,data):
        mt.conecta()
        week = get_week_from_day(data)
        # week[0] - Dia de inicio de la semana.
        # week[1] - Dia final de la semana.
        result = mt.get_reserves(week[0], week[1])
        mt.desconecta()
        return result

'''
/gimnas/reserves
    GET: Retorna totes les reserves de la setmana actual (a partir de datetime.now o today de python).
         Ha de retornar el nom de la pista i el username de l'usuari.
'''
class reserves(Resource):
    def get(self):
        mt.conecta()
        week = get_week(date.today())  # Obtiene los dias de inicio y fin de la semana a partir de la fecha de hoy.
        # week[0] - Dia de inicio de la semana.
        # week[1] - Dia final de la semana.
        result=mt.get_reserves(week[0], week[1])
        mt.desconecta()        
        return result

'''
/gimnas/reserves/usuari/id_usuari
    GET: Retorna totes les reserves del l'usuari amb id=id_usuari, indicant el nom de la pista
    TODO: POST: Afegeix una reserva de l'usuari "id_usuari", els detalls venen a un JSON
    TODO: DELETE: Elimina una reserva de l'usuari "id_usuari", els detalls venen a un JSON
'''
class reserves_usuari(Resource):
    def get(self,id_user):
        mt.conecta()
        result=mt.get_reserves_usuari(id_user)
        mt.desconecta()        
        return result\
    
    def post(self, id_user):
        pass

    def delete(self, id_user):
        pass

api.add_resource(testing, '/test')
api.add_resource(usuaris_all, '/usuari')                                # GET (lista de todos) i POST (añadir usuari)
api.add_resource(usuaris, '/usuari/<int:id_user>')                      # GET (uno concreto), PUT (modificar), DELETE (eliminar)
api.add_resource(reserves, '/reserves')                                 # GET (lista de todas)
api.add_resource(reserves_usuari, '/reserves/usuari/<int:id_user>')
api.add_resource(reserves_setmana, '/reserves/setmana/<string:data>')