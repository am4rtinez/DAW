import pymysql.cursors
import sqlalchemy as db
from dateutil.parser import parse


class gimnas(object):

    # Obtiene el listado de usuarios.
    def getUsers():
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
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

    # Obtiene el id del siguiente usuario que se va a añadir.
    def getId():
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT MAX(idclient)+1 nouId from clients"
        cursor.execute(sql)
        Id = cursor.fetchone()
        db.close()
        return Id['nouId']

    # Define la funcion para añadir el usuario a la BD en la tabla clientes.
    def addUser(idclient, nom, llinatges, telefon):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "INSERT INTO clients(idclient, nom, llinatges, telefon) VALUES ('" + \
            idclient + "', '" + nom + "','" + llinatges + "','" + telefon + "');"
        cursor.execute(sql)
        db.close()

    def updateUser(idclient, nom, llinatges, telefon):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "UPDATE clients SET nom = '" + nom + "', llinatges = '" + llinatges + \
            "', telefon = '" + telefon + "' WHERE idclient = " + str(idclient)
        cursor.execute(sql)
        db.close()

     # Define la funcion para eliminar el usuario de la BD en la tabla clientes.
    def eliminaUser(idclient):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "DELETE from clients WHERE idclient="+str(idclient)
        cursor.execute(sql)
        db.close()

    # Define la funcion para eliminar las reservas pertenecientes a un usuario para asi poder eliminar el usuario definitivamente.
    def eliminaReservaUser(idclient):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='192.168.1.10',
                             user='amartinez',
                             password='123456',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "DELETE from reserves WHERE idclient="+str(idclient)
        cursor.execute(sql)
        db.close()
