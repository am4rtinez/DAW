import pymysql.cursors
import pypyodbc
import random
import joblib
import json
import sqlalchemy as db
from dateutil.parser import parse


class biblioteca(object):

    def carregaEditors():
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='biblioteca',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT * from editors"
        cursor.execute(sql)
        ResQuery = cursor.fetchall()
        db.close()
        return ResQuery

    def afegeixEditor(nom):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='biblioteca',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "SELECT MAX(id_edit)+1 nouId from editors"
        cursor.execute(sql)
        Id = cursor.fetchone()
        sql = "INSERT INTO editors(id_edit,nom_edit) VALUES (" + \
            str(Id['nouId'])+",'"+nom+"');"
        cursor.execute(sql)
        db.close()

    def eliminaEditor(id_edit):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='biblioteca',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "DELETE from editors WHERE id_edit="+str(id_edit)
        cursor.execute(sql)
        db.close()

    def modificaEditor(nom, id_edit):
        # Conexion a la BBDD del servidor mySQL
        db = pymysql.connect(host='localhost',
                             user='root',
                             db='biblioteca',
                             charset='utf8mb4',
                             autocommit=True,
                             cursorclass=pymysql.cursors.DictCursor)
        cursor = db.cursor()
        sql = "UPDATE editors SET NOM_EDIT='" + \
            nom+"' WHERE id_edit="+str(id_edit)
        cursor.execute(sql)
        db.close()
