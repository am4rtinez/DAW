from dataclasses import field
from werkzeug.security import generate_password_hash
import pymysql.cursors
import config

class gimnas(object):
    def conecta(self):
        # You can change the connection arguments.
        self.db = pymysql.connect(host=config.Config.DB_HOST,
                                    user=config.Config.DB_USERNAME,
                                    password=config.Config.DB_PASSWORD,
                                    db=config.Config.DB_NAME,
                                    charset=config.Config.DB_CHARSET,
                                    autocommit=True,
                                    cursorclass=pymysql.cursors.DictCursor)
        self.cursor=self.db.cursor()

    def desconecta(self):
        self.db.close()

    # TRATAMIENTO DE USUARIOS
    # ====================================
    def load_all_users(self):
        # sql="SELECT idclient, username, nom, llinatges, email, telefon, DATE_FORMAT(fecha_alta, '%Y-%m-%d %H:%m') as fecha_alta " + \
        #     "from clients;"
        sql="SELECT idclient, username, nom, llinatges, email, telefon from clients;"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery

    def load_user(self, id_user):
        # sql="SELECT idclient, username, nom, llinatges, email, telefon, DATE_FORMAT(fecha_alta, '%Y-%m-%d %H:%m') as fecha_alta " + \
        #     "from clients WHERE idclient = " + str(id_user) + ";"
        sql="SELECT idclient, username, nom, llinatges, email, telefon from clients WHERE idclient = " + str(id_user) + ";"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchone()
        return ResQuery
    
    def insert_user(self, fields):
        sql="SELECT MAX(idclient)+1 newid from clients";
        self.cursor.execute(sql)
        newid=self.cursor.fetchone()
        sql="insert into clients (idclient"
        for field in fields:
            sql = sql + ", " + field
        sql = sql + ") VALUES ("+str(newid['newid'])
        for field in fields:
            if field == "password" or field == "PASSWORD":
                sql = sql + ", '" + generate_password_hash(fields[field]) + "'"
            else:
                sql = sql + ", '" + fields[field] + "'"
        sql = sql + ")"
        self.cursor.execute(sql)
        return newid['newid']
    
    def update_user(self, id_user, fields):
        sql="UPDATE clients SET "
        for field in fields:
            if field == "password" or field == "PASSWORD":
                sql="UPDATE clients SET " + field + " = '" + generate_password_hash(fields[field]) + "' WHERE idclient = " + str(id_user) + ";"
            else: 
                sql="UPDATE clients SET " + field + " = '" + fields[field] + "' WHERE idclient = " + str(id_user) + ";"
            self.cursor.execute(sql)

    def delete_user(self, id_user):
        # Elimina las reservas del usuario.
        sql="DELETE from reserves WHERE idclient = %s ;"
        self.cursor.execute(sql, id_user)
        # Elimina el usuario.
        sql="DELETE from clients WHERE idclient = %s ;"
        self.cursor.execute(sql, id_user)

    # GESTION DE RESERVAS
    # ============================

    # Funcion get_reserves encargado de listar las reservas de una semana.
    # @swd - Día inicio semana.
    # @ewd - Día fin semana.
    def get_reserves(self, swd, ewd):
        sql = "SELECT p.tipo as pista, DATE_FORMAT(r.data, '%Y-%m-%d') as data, " + \
            "DATE_FORMAT(r.data, '%H:%i') as hora, DATE_FORMAT(r.data, '%W', 'ca_ES') as dia, " + \
            "c.username, p.preu FROM reserves r LEFT JOIN pistes p ON r.idpista = p.idpista LEFT JOIN clients c " + \
            "ON r.idclient = c.idclient WHERE data BETWEEN '" + \
            str(swd) + "' AND '" + str(ewd) + "';"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery

    def get_reserves_usuari(self,id_user):
        sql = "SELECT p.tipo as pista, DATE_FORMAT(r.data, '%Y-%m-%d') as data, " + \
            "DATE_FORMAT(r.data, '%H:%i') as hora, DATE_FORMAT(r.data, '%W', 'ca_ES') as dia, " + \
            "c.username, p.preu FROM reserves r LEFT JOIN pistes p ON r.idpista = p.idpista LEFT JOIN clients c " + \
            "ON r.idclient = c.idclient WHERE c.idclient = " + str(id_user) + ";"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery
    
    def insert_reserva(self, id_user, fields):
        sql = "INSERT INTO reserves (data, idpista, idclient) VALUES ('" + str(fields['data']) + "', '" + str(fields['idpista']) + "', '" + str(id_user) + "');"
        self.cursor.execute(sql)

    def delete_reserva(self, id_user, fields):
        sql = "DELETE FROM reserves WHERE data = '" + str(fields['data']) + "' AND idpista = '" + str(fields['idpista']) + "' AND idclient = '" + str(id_user) + "';"
        self.cursor.execute(sql)






    # Obtiene el listado de usuarios.
    # def getUsersAll(self):
    #     sql = "SELECT * from clients"
    #     self.cursor.execute(sql)
    #     ResQuery = self.cursor.fetchall()
    #     return ResQuery

    # GESTION DE RESERVAS
    # ============================

    # def check_reservas_usuario(user_id):
    #     sql = "SELECT count(*) from reserves WHERE idclient = %s"
    #     try:
    #         db = getConnection()
    #         cursor = db.cursor()
    #         cursor.execute(sql, user_id)
    #         ResQuery = cursor.fetchone()
    #         if ResQuery['count(*)'] > 0:
    #             return True
    #         else:
    #             return False
    #     finally:
    #         db.close

    # def get_fecha_ultima_reserva(user_id):
    #     sql = "SELECT DATE_FORMAT(data, '%Y-%m-%d') as data from reserves WHERE idclient = " + \
    #         str(user_id) + " ORDER BY data DESC LIMIT 1"
    #     try:
    #         db = getConnection()
    #         cursor = db.cursor()
    #         cursor.execute(sql)
    #         result = cursor.fetchone()
    #         return result['data']
    #     finally:
    #         db.close
    
    # ''' Funcion encargada de comprobar la primera reserva libre.'''
    # def get_reserva_libre():
    #     pass

    # ''' 
    # Corregida la funcion de comprobacion de reservas ya que solo comprobaba si existian reservas del usuario
    # y no comprobaba si habia reservas de otros clientes para el dia y franja horaria seleccionada.
    # '''
    # def comprobar_reserva(data, pista, client):
    #     sql = "SELECT * FROM reserves WHERE data = %s and idpista = %s;"
    #     val = (data, pista)
    #     try:
    #         db = getConnection()
    #         cursor = db.cursor()
    #         cursor.execute(sql, val)
    #         ResQuery = cursor.fetchone()
    #         return ResQuery
    #     finally:
    #         db.close()

    # def insertReserva(data, pista, client):
    #     sql = "INSERT INTO reserves(data, idpista, idclient) VALUES (%s, %s, %s);"
    #     val = (data, pista, client)
    #     try:
    #         db = getConnection()
    #         cursor = db.cursor()
    #         cursor.execute(sql, val)
    #     finally:
    #         db.close()

    # TRATAMIENTO DE PISTAS
    # ====================================
    # Obtiene el listado de pistas.
    # def get_pistas():
    #     sql = "SELECT * from pistes"
    #     try:
    #         db = getConnection()
    #         cursor = db.cursor()
    #         cursor.execute(sql)
    #         ResQuery = cursor.fetchall()
    #         return ResQuery
    #     finally:
    #         db.close()
