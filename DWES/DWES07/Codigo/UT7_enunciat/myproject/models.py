from myproject import login_manager
from werkzeug.security import check_password_hash
from flask_login import UserMixin
import pymysql.cursors
import datetime

# By inheriting the UserMixin we get access to a lot of built-in attributes
# which we will be able to call in our views!
# is_authenticated()
# is_active()
# is_anonymous()
# get_id()


# The user_loader decorator allows flask-login to load the current user
# and grab their id.
@login_manager.user_loader
def load_user(user_id):
    if user_id:
        user=User()
        user.fromID(user_id)
        return user

class gimnas(object):
    def cargaReservas(dia):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        inici=datetime.datetime.strptime(dia,'%d-%m-%Y')
        final=inici+datetime.timedelta(days=4)
        sql="SELECT u.id,r.data,p.tipo,u.nom,u.llinatges from reserves r,pistes p,usuaris u WHERE "
        sql=sql+"u.id=r.idclient AND r.idpista=p.idpista AND "
        sql=sql+"data>='"+inici.strftime("%Y-%m-%d")+"' AND data<='"+final.strftime("%Y-%m-%d")+"';"
        cursor.execute(sql)
        ResQuery=cursor.fetchall()
        db.close()
        return ResQuery

    def reservaPista(data,idusuari,idpista):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        sql="INSERT INTO reserves VALUES('"+data+"',"+str(idpista)+","+str(idusuari)+");"
        cursor.execute(sql)
        ResQuery=cursor.fetchall()
        db.close()

    def comprovaPista(dia,hora,idpista):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        fecha=dia+" "+str(hora)+":00:00"
        sql="SELECT count(*) p from reserves WHERE data='"+fecha+"' and idpista="+idpista+";"
        cursor.execute(sql)
        ResQuery=cursor.fetchone()
        reservado=ResQuery['p']
        print(reservado)
        db.close()
        return reservado


class User(UserMixin):
    id = 0

    def __init__(self):
        self.username = "null"

    def fromUsername(self, username):
        self.username = username
        RQ=self.getId()

    def fromID(self,Userid):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        # sql="SELECT id,username,nom,llinatges from usuaris WHERE id="+str(Userid)
        sql="SELECT id, username, nom, llinatges FROM clients WHERE id="+str(Userid)
        cursor.execute(sql)
        ResQuery=cursor.fetchone()
        if ResQuery:
            self.id=ResQuery['id']
            self.username=ResQuery['username']
            self.nom=ResQuery['nom']
            self.llinatges=ResQuery['llinatges']

    def comprovaUsuari(self,pwd):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        # sql="SELECT count(*) from usuaris WHERE username='"+self.username+"'"
        sql="SELECT count(*) from clients WHERE username='"+self.username+"'"
        cursor.execute(sql)
        ResQuery=cursor.fetchone()
        if ResQuery['count(*)']==1:
            # sql="SELECT password from usuaris  WHERE username='"+self.username+"'"
            sql="SELECT password from clients  WHERE username='"+self.username+"'"
            cursor.execute(sql)
            ResQuery=cursor.fetchone()
            resposta=check_password_hash(ResQuery['password'],pwd)
            self.getId()
        else:
            resposta=False
        db.close()
        return resposta

    def getId(self):
        #Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='gimnas',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor=db.cursor()
        # sql="SELECT id,username,nom,llinatges from usuaris WHERE username='"+self.username+"'"
        sql="SELECT id, username, nom, llinatges FROM clients WHERE username='"+self.username+"'"
        cursor.execute(sql)
        ResQuery=cursor.fetchone()
        if ResQuery:
            self.id=ResQuery['id']
            self.username=ResQuery['username']
            self.nom=ResQuery['nom']
            self.llinatges=ResQuery['llinatges']
            return ResQuery
        else:
            return False

    def getUsername(self):
        return self.username
