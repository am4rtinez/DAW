import pymysql.cursors
import datetime
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

  def cargaUsuaris():
    sql = "SELECT * from clients"
    try:
      db = getConnection()
      cursor = db.cursor()
      cursor.execute(sql)
      ResQuery = cursor.fetchall()
      return ResQuery
    finally:
      db.close()

  def nouIdUsuari():
    sql = "SELECT MAX(idclient)+1 nouId from clients"
    try:
      db = getConnection()
      cursor = db.cursor()
      cursor.execute(sql)
      ResQuery = cursor.fetchone()
      return ResQuery['nouId']
    finally:
      db.close()

  def modificaUsuari(idclient, nom, llinatges, telefon):
    sql = "SELECT count(*) existe from clients WHERE idclient = %s;"
    val = (idclient)
    try:
      db = getConnection()
      cursor = db.cursor()
      cursor.execute(sql, idclient)
      result = cursor.fetchone()
      if result['existe'] == 1:
        sql = "UPDATE clients SET nom = %s, llinatges = %s, telefon = %s WHERE idclient = %s"
        val = (nom, llinatges, telefon, idclient)
        # cursor.execute(sql, val)
      else:
        sql = "INSERT into clients values (%s, %s, %s, %s)"
        val = (idclient, nom, llinatges, telefon)
      cursor.execute(sql, val)
    finally:
      db.close()

  def borraUsuari(idusuari):
    sql = "DELETE from clients WHERE idclient= %s"
    try:
        db = getConnection()
        cursor = db.cursor()
        cursor.execute(sql, idusuari)
    finally:
        db.close()

  def cargaReservas(dia):
    try:
      db = getConnection()
      cursor = db.cursor()
      inici = datetime.datetime.strptime(dia, '%d-%m-%Y')
      final = inici+datetime.timedelta(days=4)
      sql = "SELECT r.data, p.tipo, u.nom, u.llinatges from reserves r, pistes p, clients u WHERE "
      sql = sql+"u.idclient = r.idclient AND r.idpista = p.idpista AND "
      sql = sql+"data>='" + \
          inici.strftime("%Y-%m-%d")+"' AND data<='" + \
          final.strftime("%Y-%m-%d")+"';"
      cursor.execute(sql)
      ResQuery = cursor.fetchall()
      return ResQuery
    finally:
      db.close()

  def reservaPista(data, idusuari, idpista):
    sql = "INSERT INTO reserves(data, idpista, idclient) VALUES (%s, %s, %s);"
    val = (data, idpista, idusuari)
    try:
        db = getConnection()
        cursor = db.cursor()
        cursor.execute(sql, val)
    finally:
        db.close()

  def comprovaPista(dia, hora, idpista):
    fecha = dia+" "+str(hora)+":00:00"
    sql = "SELECT count(*) p from reserves WHERE data = %s and idpista = %s;"
    val = (fecha, idpista)
    try:
      db = getConnection()
      cursor = db.cursor()
      cursor.execute(sql, val)
      ResQuery = cursor.fetchone()
      reservado = ResQuery['p']
      # print(reservado)
      return reservado
    finally:
      db.close()
  
  def get_data_user(idclient):
    sql = "SELECT nom, llinatges from clients WHERE idclient = %s"
    try:
        db = getConnection()
        cursor = db.cursor()
        cursor.execute(sql, idclient)
        ResQuery = cursor.fetchone()
        return ResQuery
    finally:
        db.close()
  
  def get_tipo_pista(idpista):
    sql = "SELECT tipo from pistes WHERE idpista = %s"
    try:
        db = getConnection()
        cursor = db.cursor()
        cursor.execute(sql, idpista)
        ResQuery = cursor.fetchone()
        return ResQuery
    finally:
        db.close()
