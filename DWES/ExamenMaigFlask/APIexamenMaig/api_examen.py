from unittest import result
from flask import Flask, request
from flask_restful import Resource, Api
import DBexamen
import configparser
import datetime
from flask_cors import CORS
#from werkzeug.datastructures import MultiDict

server = Flask(__name__)
CORS(server)
api = Api(server)
mt=DBexamen.vols()

class aeroports(Resource):
    def get(self):
        mt.conecta()
        result=mt.cargaAeroports()
        mt.desconecta()
        return result

class arribadesHora(Resource):
    def get(self,aeroport,fecha_hora): # fecha_hora te el format YYYY-MM-DD_HH:MM
        mt.conecta()
        result=mt.cargaArribades(aeroport,fecha_hora)
        print(result)
        mt.desconecta()
        resposta=[]
        for r in result:
            r['departure_time'] = r['departure_time'].strftime("%Y-%m-%d %H:%M")
            r['arrival_time'] = r['arrival_time'].strftime("%Y-%m-%d %H:%M")
            resposta.append(r)
        return resposta

class arribadesHora2(Resource):
    def get(self,aeroport,fecha_hora): # fecha_hora te el format YYYY-MM-DD_HH:MM
        mt.conecta()
        result=mt.cargaArribades(aeroport,fecha_hora)
        print(result)
        mt.desconecta()
        resposta=[]
        for r in result:
            r['departure_time'] = r['departure_time'].strftime("%Y-%m-%d %H:%M")
            r['arrival_time'] = r['arrival_time'].strftime("%Y-%m-%d %H:%M")
            resposta.append(r)
        return resposta

class arribadesNow(Resource):
    def get(self,aeroport):
        mt.conecta()
        dia=datetime.datetime.now().strftime("%Y-%m-%d_%H:%M")
        result=mt.cargaArribades(aeroport,dia)
        mt.desconecta()
        resposta=[]
        for r in result:
            r['departure_time'] = r['departure_time'].strftime("%Y-%m-%d %H:%M")
            r['arrival_time'] = r['arrival_time'].strftime("%Y-%m-%d %H:%M")
            resposta.append(r)
        return resposta

class sortidesHora(Resource):
    def get(self,aeroport,fecha_hora):
        mt.conecta()
        result=mt.cargaSortides(aeroport,fecha_hora)
        mt.desconecta()        
        # Mi codigo:
        resposta=[]
        for r in result:
            r['departure_time'] = r['departure_time'].strftime("%Y-%m-%d %H:%M")
            r['arrival_time'] = r['arrival_time'].strftime("%Y-%m-%d %H:%M")
            resposta.append(r)
        return resposta

class sortidesHora2(Resource):
    def get(self,aeroport,fecha_hora):
        mt.conecta()
        result=mt.cargaSortides2(aeroport,fecha_hora)
        mt.desconecta()        
        # Mi codigo:
        resposta=[]
        for r in result:
            r['departure_time'] = r['departure_time'].strftime("%Y-%m-%d %H:%M")
            r['arrival_time'] = r['arrival_time'].strftime("%Y-%m-%d %H:%M")
            resposta.append(r)
        return resposta

class addRetraso(Resource):
    def put(self,id_vol):
        mt.conecta()
        result=mt.addRetraso(id_vol)
        mt.desconecta()

api.add_resource(aeroports,'/vols/aeroports') #GET
api.add_resource(arribadesHora,'/vols/arribades/<aeroport>/<fecha_hora>') #GET
api.add_resource(arribadesHora2,'/vols/arribades2/<aeroport>/<fecha_hora>') #GET
api.add_resource(arribadesNow,'/vols/arribadesNow/<aeroport>') #GET
api.add_resource(sortidesHora,'/vols/sortides/<aeroport>/<fecha_hora>')  #GET 
api.add_resource(sortidesHora2,'/vols/sortides2/<aeroport>/<fecha_hora>')  #GET 
api.add_resource(addRetraso, '/vols/retraso/<id_vol>')

if __name__ == '__main__':
    server.run(debug=True,port="5001")
