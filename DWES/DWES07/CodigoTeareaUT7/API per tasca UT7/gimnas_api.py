from flask import Flask, request
from flask_restful import Resource, Api
import database
import datetime
from flask_cors import CORS
from werkzeug.datastructures import MultiDict

server = Flask(__name__)
CORS(server)
api = Api(server)
mt=database.gimnas()

class usuari(Resource):
    def get(self):
        mt.conecta()
        result=mt.cargaUsuaris()
        mt.desconecta()
        return result

    def post(self):
        mt.conecta()
        result=mt.nouUsuari(request.json)
        mt.desconecta()  
        return result    

class usuarisID(Resource):
    def get(self,id):
        mt.conecta()
        result=mt.cargaUsuariId(id)
        mt.desconecta()
        return result

    def put(self,id):
        mt.conecta()
        result=mt.modificaUsuari(id,request.json)
        mt.desconecta()

    def delete(self,id):
        mt.conecta()
        result=mt.esborraUsuari(id)
        mt.desconecta()

class reserves(Resource):
    def get(self):
        mt.conecta()
        dia=datetime.date.today().strftime("%Y-%m-%d")
        result=mt.cargaReserves(dia)
        mt.desconecta()        
        return result

class reservesSet(Resource):
    def get(self,dia):
        mt.conecta()
        result=mt.cargaReserves(dia)
        mt.desconecta()        
        return result


class reservesID(Resource):
    def get(self,id):
        mt.conecta()
        result=mt.cargaReservesId(id)
        mt.desconecta()
        return result

    def post(self,id):
        mt.conecta()
        print(request)
        print(request.json)
        print(request.form.getlist('fecha')[0])        
        print(request.get_json())
        result=mt.novaReserva(id,request.form.getlist('fecha')[0],request.form.getlist('pista')[0])
        mt.desconecta()

    def delete(self,id):
        mt.conecta()
        result=mt.esborraReserva(id)
        mt.desconecta()

api.add_resource(usuari,'/gimnas/usuari') #GET i POST
api.add_resource(usuarisID,'/gimnas/usuari/<int:id>') #GET PUT DELETE
api.add_resource(reserves,'/gimnas/reserves')  #GET 
api.add_resource(reservesSet,'/gimnas/reserves/setmana/<string:dia>')  #GET 
api.add_resource(reservesID,'/gimnas/reserves/<int:id>')  #GET (un concret), POST (afegir), DELETE (eliminar)

if __name__ == '__main__':
    server.run(debug=True,port="5001")
