import pymysql.cursors
import config

def getConnection():
    # You can change the connection arguments.
    connection = pymysql.connect(host=config.Config.DB_HOST,
                                 user=config.Config.DB_USERNAME,
                                 password=config.Config.DB_PASSWORD,
                                 db=config.Config.DB_NAME,
                                 charset=config.Config.DB_CHARSET,
                                 autocommit=True,
                                 cursorclass=pymysql.cursors.DictCursor)
    return connection

class dbo(object):
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

    # GESTION DE RESERVAS
    # ============================

    def get_reservas(swd, ewd):
        sql = "SELECT p.tipo as pista, DATE_FORMAT(r.data, '%Y-%m-%d') as data, " + \
            "DATE_FORMAT(r.data, '%H:%i') as hora, DATE_FORMAT(r.data, '%W', 'ca_ES') as dia, " + \
            "c.idclient, c.nom, c.llinatges, p.preu FROM reserves r LEFT JOIN pistes p ON r.idpista = p.idpista LEFT JOIN clients c " + \
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

    def get_lista_reservas(swd, ewd):
        sql = "SELECT p.tipo as pista, r.data as data, " + \
            "c.idclient, c.nom, c.llinatges, p.preu, p.tipo as tipo FROM reserves r LEFT JOIN pistes p ON r.idpista = p.idpista LEFT JOIN clients c " + \
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

    def check_reservas_usuario(user_id):
        sql = "SELECT count(*) from reserves WHERE idclient = %s"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, user_id)
            ResQuery = cursor.fetchone()
            if ResQuery['count(*)'] > 0:
                return True
            else:
                return False
        finally:
            db.close

    def get_fecha_ultima_reserva(user_id):
        sql = "SELECT DATE_FORMAT(data, '%Y-%m-%d') as data from reserves WHERE idclient = " + \
            str(user_id) + " ORDER BY data DESC LIMIT 1"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            result = cursor.fetchone()
            return result['data']
        finally:
            db.close
    
    ''' Funcion encargada de comprobar la primera reserva libre.'''
    def get_reserva_libre():
        pass

    ''' 
    Corregida la funcion de comprobacion de reservas ya que solo comprobaba si existian reservas del usuario
    y no comprobaba si habia reservas de otros clientes para el dia y franja horaria seleccionada.
    '''
    def comprobar_reserva(data, pista, client):
        sql = "SELECT * FROM reserves WHERE data = %s and idpista = %s;"
        val = (data, pista)
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
    def get_pistas():
        sql = "SELECT * from pistes"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql)
            ResQuery = cursor.fetchall()
            return ResQuery
        finally:
            db.close()
