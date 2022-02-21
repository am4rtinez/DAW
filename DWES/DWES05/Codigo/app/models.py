from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import UserMixin
from app.dbmodel import dbo
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
    # print("Conexión a la BD satisfactoria!")
    return connection

# By inheriting the UserMixin we get access to a lot of built-in attributes
# which we will be able to call in our views!
# is_authenticated()
# is_active()
# is_anonymous()
# get_id()
class User(UserMixin):

    def __init__(self):
        self.id = "null"
        self.username = "null"
        self.nom = "null"
        self.llinatges = "null"
        self.email = "null"
        self.telefon = "null"

    # Obtiene el id del usuario a partir del username.
    def from_username(self, username):
        self.username = username
        RQ = self.get_user_id()
        if RQ:
            # self.rol = RQ['rol']
            self.id = RQ['idclient']
        else:
            return False

    # Obtiene los datos del usuario a partir del id.
    def from_id(self, user_id):
        # Conexion a la BBDD del servidor mySQL
        sql = "SELECT idclient,username,nom,llinatges,telefon,email from clients WHERE idclient = %s"
        db = getConnection()
        cursor = db.cursor()
        cursor.execute(sql, user_id)
        result = cursor.fetchone()
        if result:
            self.id = result['idclient']
            self.username = result['username']
            self.nom = result['nom']
            self.llinatges = result['llinatges']
            self.telefon = result['telefon']
            self.email = result['email']

    # Funcion encargada de comparar los hashes de los passwords.
    # No en uso.
    def check_pwd(self, pwd):
        sql = "SELECT password from clients WHERE username=%s"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, self.username)
            res_query = cursor.fetchone()
            resposta = check_password_hash(res_query['password'], pwd)
            return resposta
        finally:
            db.close()

    def check_user(self, pwd):
        # Conexion a la BBDD del servidor mySQL
        sql = "SELECT count(*) from clients WHERE username=%s"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, self.username)
            result = cursor.fetchone()
            if result['count(*)'] == 1:
                sql = "SELECT password from clients WHERE username=%s"
                cursor.execute(sql, self.username)
                result = cursor.fetchone()
                resposta = check_password_hash(result['password'], pwd)
                self.get_user_id()
            else:
                resposta = False
            return resposta
        finally:
            db.close()

    # Retorna el identificador de la BD del usuario a partir del username si existe.
    def get_user_id(self):
        sql = "SELECT idclient from clients WHERE username=%s"
        try:
            db = getConnection()
            cursor = db.cursor()
            cursor.execute(sql, self.username)
            res_query = cursor.fetchone()
            if res_query:
                self.id = res_query['idclient']
                return res_query
            else:
                return False
        finally:
            db.close()

    # Declaracion de los getters para la clase user.
    def get_id(self):
        return super().get_id()

    def get_username(self):
        return self.username

    def get_nom(self):
        return self.nom

    def get_llinatges(self):
        return self.llinatges

    def get_telefon(self):
        return self.telefon

    def get_email(self):
        return self.email