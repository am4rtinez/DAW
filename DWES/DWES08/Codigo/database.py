import pymysql.cursors
# import pypyodbc
# import random
# import joblib
# import json
# import sqlalchemy as db
# from dateutil.parser import parse
import datetime


class gimnas(object):

    def cargaUsuaris():
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT * from clients"
        cursor.execute(sql)
        ResQuery = cursor.fetchall()
        db.close()
        return ResQuery

    def nouIdUsuari():
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT MAX(idclient)+1 nouId from clients"
        cursor.execute(sql)
        ResQuery = cursor.fetchone()
        db.close()
        return ResQuery['nouId']

    def modificaUsuari(idclient, nom, llinatges, telefon):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT count(*) existe from clients WHERE idclient="+idclient
        cursor.execute(sql)
        result = cursor.fetchone()
        if result['existe'] == 1:
            sql = "UPDATE clients SET nom='"+nom+"', llinatges='"+llinatges + \
                "', telefon='"+str(telefon)+"' WHERE idclient="+idclient
        else:
            sql = "INSERT into clients values ("+idclient + \
                ",'"+nom+"','"+llinatges+"','"+str(telefon)+"')"
        cursor.execute(sql)
        db.close()

    def borraUsuari(idusuari):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "DELETE from clients WHERE idclient="+idusuari
        cursor.execute(sql)
        db.close()

    def cargaReservas(dia):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        inici = datetime.datetime.strptime(dia, '%d-%m-%Y')
        final = inici+datetime.timedelta(days=4)
        sql = "SELECT r.data, p.tipo, u.nom, u.llinatges from reserves r, pistes p, clients u WHERE "
        sql = sql+"u.id = r.idclient AND r.idpista = p.idpista AND "
        sql = sql+"data>='" + \
            inici.strftime("%Y-%m-%d")+"' AND data<='" + \
            final.strftime("%Y-%m-%d")+"';"
        cursor.execute(sql)
        ResQuery = cursor.fetchall()
        db.close()
        return ResQuery

    def reservaPista(data, idusuari, idpista):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "INSERT INTO reserves VALUES('" + \
            data+"',"+idpista+","+idusuari+");"
        cursor.execute(sql)
        ResQuery = cursor.fetchall()
        db.close()

    def comprovaPista(dia, hora, idpista):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        fecha = dia+" "+str(hora)+":00:00"
        sql = "SELECT count(*) p from reserves WHERE data='" + \
            fecha+"' and idpista="+idpista+";"
        cursor.execute(sql)
        ResQuery = cursor.fetchone()
        reservado = ResQuery['p']
        print(reservado)
        db.close()
        return reservado
