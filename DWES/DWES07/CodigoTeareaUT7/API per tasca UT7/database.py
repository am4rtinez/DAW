import pymysql.cursors
import sqlalchemy as db
import configparser
from werkzeug.security import generate_password_hash,check_password_hash
import datetime


class gimnas(object):
    def conecta(self):
        #Conexion a la BBDD del servidor mySQL
        self.db = pymysql.connect(host='localhost',
                                     user='root',
                                     db='gimnas',
                                     charset='utf8mb4',
                                     autocommit=True,
                                     cursorclass=pymysql.cursors.DictCursor)
        self.cursor=self.db.cursor()

    def desconecta(self):
        self.db.close()

    def cargaUsuaris(self):
        sql="SELECT * from clients"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        return ResQuery

    def nouUsuari(self,camps):
        sql="SELECT MAX(id)+1 newid from clients";
        self.cursor.execute(sql)
        newid=self.cursor.fetchone()
        sql="insert into clients values ("+str(newid['newid'])+",'"+camps['username']
        print(sql)
        #self.cursor.execute(sql)
        return newid['newid']             

    def cargaUsuariId(self,id_usuari):
        sql="SELECT * from usuaris WHERE id="+str(id_usuari)
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchone()
        return ResQuery

    def modificaUsuari(self,id_edit,camps):
        for canvi in camps:
            sql="UPDATE editors SET "+canvi+"='"+camps[canvi]+"' WHERE ID_EDIT="+str(id_edit)
            self.cursor.execute(sql)

    def esborraUsuari(self,id_usuari):
        sql="DELETE from usuaris WHERE id="+str(id_usuari);
        self.cursor.execute(sql)

    def cargaReserves(self,dia):
        diaP=datetime.datetime.strptime(dia,"%Y-%m-%d")
        lunes=diaP-datetime.timedelta(days=diaP.weekday())
        viernes=lunes+datetime.timedelta(days=4)
        l=lunes.strftime("%Y-%m-%d")
        v=viernes.strftime("%Y-%m-%d")
        sql="SELECT r.data,p.tipo,c.username from reserves r,pistes p,clients c where c.id=r.idclient "
        sql=sql+"and p.idpista=r.idpista and r.data>='"+l+"' and r.data<='"+v+"';"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        reserves=[]
        for r in ResQuery:
            if r['data']:
                r['hora'] = r['data'].strftime("%H")    
                r['data'] = r['data'].strftime("%Y-%m-%d")
            reserves.append(r)
        return reserves

    def cargaReservesId(self,id_usuari):
        sql="SELECT r.data,p.tipo from reserves r,pistes p where r.idclient="+str(id_usuari)+" and p.idpista=r.idpista"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchall()
        reserves=[]
        for r in ResQuery:
            if r['data']:
                r['hora'] = r['data'].strftime("%H")    
                r['data'] = r['data'].strftime("%Y-%m-%d")
            reserves.append(r)
        return reserves

    def novaReserva(self,idusuari,fecha,pista):
        sql="SELECT idpista from pistes where tipo='"+pista+"';"
        self.cursor.execute(sql)
        ResQuery=self.cursor.fetchone()       
        sql="insert into reserves values ('"+fecha+"',"+str(ResQuery['idpista'])+","+str(idusuari)+");"
        self.cursor.execute(sql)

    def esborraReserva(self,id_edit):
        sql="DELETE from editors WHERE ID_EDIT="+str(id_edit);
        self.cursor.execute(sql)
   



