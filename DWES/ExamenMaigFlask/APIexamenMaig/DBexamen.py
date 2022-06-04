import pymysql.cursors
import sqlalchemy as db
import configparser
from werkzeug.security import generate_password_hash,check_password_hash
import datetime


class vols(object):
    def conecta(self):
        #Conexion a la BBDD del servidor mySQL
        self.db = pymysql.connect(host='localhost',
                                    user='root',
                                    db='vols',
                                    charset='utf8mb4',
                                    autocommit=True,
                                    cursorclass=pymysql.cursors.DictCursor)
        self.cursor=self.db.cursor()

    def desconecta(self):
        self.db.close()

    def cargaAeroports(self):
        sql="SELECT * from airports"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery

    def cargaArribades(self,aeroport,fecha_hora):
        fechasql=fecha_hora.replace("_"," ")+":00"
        sql="SELECT * from flights where arrival_airport='"+aeroport
        sql=sql+"' AND arrival_time>='"+fechasql+"' order by arrival_time limit 10";
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery
    
    def cargaArribades2(self,aeroport,fecha_hora):
        fechasql=fecha_hora.replace("_"," ")+":00"
        sql="SELECT * from flights where arrival_airport='"+aeroport
        sql=sql+"' AND arrival_time='"+fechasql+"' AND arrival_time= ('"+fechasql+"' + INTERVAL 1 DAY)order by arrival_time limit 10";
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery

    def cargaSortides(self,aeroport,fecha_hora):
        fechasql=fecha_hora.replace("_"," ")+":00"
        sql="SELECT * from flights where departure_airport='"+aeroport
        sql=sql+"' AND departure_time>='"+fechasql+"' order by departure_time limit 10";
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery
    
    def cargaSortides2(self,aeroport,fecha_hora):
        fechasql=fecha_hora.replace("_"," ")+":00"
        sql="SELECT * from flights where departure_airport='"+aeroport
        sql=sql+"' AND departure_time='"+fechasql+"' order by departure_time limit 10";
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery
    
    def addRetraso(self,id_vol):
        sql = "UPDATE flights SET arrival_time = arrival_time + INTERVAL 1 HOUR, departure_time = departure_time + INTERVAL 1 HOUR WHERE id_flight = " + str(id_vol)
        self.cursor.execute(sql)