import pymysql.cursors
import config

# Inicializamos las variables de la conexion desde el fichero de configuracion.
_host = config.db_host
_user = config.db_user
_password = config.db_password
_db = config.db_name
_charset = config.db_charset


def getConnection():
    # You can change the connection arguments.
    connection = pymysql.connect(host=_host,
                                 user=_user,
                                 password=_password,
                                 db=_db,
                                 charset=_charset,
                                 autocommit=True,
                                 cursorclass=pymysql.cursors.DictCursor)
    # print("Conexión a la BD satisfactoria!")
    return connection


class dbo(object):

    # Establece la conexión a la BD.

    # TRATAMIENTO DE USUARIOS
    # ====================================
    # Obtiene el listado de usuarios.
    def getUsers():
        sql = "SELECT * from clients"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            ResQuery = cursor.fetchall()
            return ResQuery
        finally:
            db.close()

    # Obtiene el id del siguiente usuario que se va a añadir.
    def getNextId():
        sql = "SELECT MAX(idclient)+1 nouId from clients"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            Id = cursor.fetchone()
            return Id['nouId']
        finally:
            db.close()

    def fromID(user_id):
        # Conexion a la BBDD del servidor mySQL
        sql = "SELECT idclient,username,nom,llinatges,email,telefon from clients WHERE idclient= %s"
        val = (user_id)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
            ResQuery = cursor.fetchone()
            if ResQuery:
                return ResQuery
            return None
        finally:
            db.close()
    # GESTION DE RESERVAS
    # ============================

    def getReservas(swd, ewd):
        sql = "SELECT p.tipo as pista, DATE_FORMAT(r.data, '%Y-%m-%d') as data, " + \
            "DATE_FORMAT(r.data, '%H:%i') as hora, DATE_FORMAT(r.data, '%W', 'ca_ES') as dia, " + \
            "c.nom, c.llinatges, p.preu FROM reserves r LEFT JOIN pistes p ON r.idpista = p.idpista LEFT JOIN clients c " + \
            "ON r.idclient = c.idclient WHERE data BETWEEN '" + \
            str(swd) + "' AND '" + str(ewd) + "';"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            ResQuery = cursor.fetchall()
            return ResQuery
        finally:
            db.close()

    def comprobarReserva(data, pista, client):
        sql = "SELECT * FROM reserves WHERE data = %s and idpista = %s and idclient = %s;"
        val = (data, pista, client)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
            ResQuery = cursor.fetchone()
            return ResQuery
        finally:
            db.close()

    def insertReserva(data, pista, client):
        sql = "INSERT INTO reserves(data, idpista, idclient) VALUES (%s, %s, %s);"
        val = (data, pista, client)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
        finally:
            db.close()

    # TRATAMIENTO DE PISTAS
    # ====================================
    # Obtiene el listado de pistas.
    def getPistas():
        sql = "SELECT * from pistes"
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            ResQuery = cursor.fetchall()
            return ResQuery
        finally:
            db.close()
