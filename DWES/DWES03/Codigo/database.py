import pymysql.cursors
import config

# Inicializamos las variables de la conexion desde el fichero de configuracion.
_host = config.db_host
_user = config.db_user
_password = config.db_password
_db = config.db_name
_charset = config.db_charset


# Establece la conexión a la BD.
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


class gimnas(object):

    # TRATAMIENTO DE USUARIOS
    # ====================================
    # Obtiene el listado de usuarios.
    def getUsers():
        sql = "SELECT * from clients"
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            ResQuery = cursor.fetchall()
            return ResQuery
        finally:
            db.close()

    # Obtiene el id del siguiente usuario que se va a añadir.
    def getId():
        sql = "SELECT MAX(idclient)+1 nouId from clients"
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            Id = cursor.fetchone()
            return Id['nouId']
        finally:
            db.close()

    # Función para añadir el usuario a la BD en la tabla clientes.
    def addUser(idclient, nom, llinatges, telefon):
        sql = "INSERT INTO clients(idclient, nom, llinatges, telefon) VALUES (%s, %s, %s, %s);"
        val = (idclient, nom, llinatges, telefon)
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
        finally:
            db.close()

    # Función para actualizar el usuario de la BD.
    def updateUser(idclient, nom, llinatges, telefon):
        sql = "UPDATE clients SET nom = %s, llinatges = %s, telefon = %s WHERE idclient = %s"
        val = (idclient, nom, llinatges, telefon)
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
        finally:
            db.close()

    # Define la funcion para eliminar el usuario de la BD en la tabla clientes.
    def eliminaUser(idclient):
        sql = "DELETE from clients WHERE idclient= %s"
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, idclient)
        finally:
            db.close()

    # Define la funcion para eliminar las reservas pertenecientes a un usuario para asi poder eliminar el usuario definitivamente.
    def eliminaReservaUser(idclient):
        sql = "DELETE from reserves WHERE idclient = %s"
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, idclient)
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
        # print(sql)
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
        # print(sql)
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
        # print(sql)
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, val)
        finally:
            db.close()

    # TRATAMIENTO DE USUARIOS
    # ====================================
    # Obtiene el listado de usuarios.
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
