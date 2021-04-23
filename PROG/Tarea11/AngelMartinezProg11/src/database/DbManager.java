package database;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.OperationException;
import exceptions.QueryNotFoundException;

/**
 * Contiene una función por cada una de las creadas en {@link DuplicadosDataSource}.
 * Es quien se encarga de establecer la conexión con la base de datos, ejecutar
 * la sentencia a través de dicha función y finalizar correctamente la conexión.
 * Utiliza la función de establecimiento de conexión que proviene de Gestor
 * Incidentes así como las librerías de acceso a Oracle y UtilidadesSQL.
 * @author bgomez
 */
public class DbManager {
    
    private DataBase db = null;
    
    private final String detalle = "";
    
    /**
     * Constructor de la clase. Declara el objeto {@link DuplicadosDataSource} utilizado
     * en toda la clase.
     * @param host
     * @param database
     * @param port
     * @param user
     * @param pass
     * @param vendor
     * @throws es.seib.smsmanager.exceptions.OperationException
     */
    public DbManager(String host, String database, String port, String user, String pass, String vendor)
            throws OperationException {
        db = new DataBase();
        db.setHost(host);
        db.setDb(database);
        db.setPort(port);
        db.setUser(user);
        db.setPass(pass);
        db.setVendor(vendor);
    }
    
    public List<Contacto> getListaAlumnos()
            throws OperationException {
        String metodo = "getListaAlumnos";
        List<Contacto> rtn = null;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.getListaContactos();
            return rtn;
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = null;
        } catch (IOException ex) {
            logger.error(ex);
            return rtn = null;
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    } 
  
    public List<Grupo> getListaGrupos()
            throws QueryNotFoundException, OperationException{
        String metodo = "getListaGrupos";
        List<Grupo> rtn = new ArrayList<Grupo>();
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.getListaGrupos();
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public Movil getMovil(int movil)
            throws QueryNotFoundException, OperationException{
        String metodo = "getMovil";
        Movil rtn = new Movil();
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.getMovil(movil);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public int countMovil(int movil)
            throws QueryNotFoundException, OperationException{
        String metodo = "countMovil";
        int rtn;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.countMovil(movil);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return 0;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return 0;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public int countContacto(String id)
            throws QueryNotFoundException, OperationException{
        String metodo = "countContacto";
        int rtn;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.countContacto(id);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = 0;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return rtn = 0;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public int countEmail(String email)
            throws QueryNotFoundException, OperationException{
        String metodo = "countEmail";
        int rtn;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.countEmail(email);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = 0;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return rtn = 0;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public int countRelacionesMovil(int movil)
            throws QueryNotFoundException, OperationException{
        String metodo = "countRelacionesMovil";
        int rtn;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.countRelacionesMovil(movil);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = 0;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return rtn = 0;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public int countRelacionesEmail(String email)
            throws QueryNotFoundException, OperationException{
        String metodo = "countRelacionesEmail";
        int rtn;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.countRelacionesEmail(email);
            return rtn;
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = 0;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return rtn = 0;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public Emails getEmail(String ema)
            throws QueryNotFoundException, OperationException{
        String metodo = "getEmail";
        Emails rtn = new Emails();
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.getEmail(ema);
            return rtn;
        
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            return rtn = null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertDataContacto(Contacto c)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertDataContacto";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertDataContacto(c);       
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertMovilContacto(int mov)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertMovilContacto";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertMovil(mov);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertEmailContacto(String ema)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertEmailContacto";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertEmail(ema);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            //return rtn = null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            //return rtn = null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertRelacion_CG(Contacto c, Grupo g)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertRelacion_CG";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertRelacionCG(c, g);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            //return rtn = null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            //return rtn = null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertRelacion_CM(Contacto c, Movil m)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertRelacion_CM";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertRelacionCM(c.getId(), m.getId());
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            //return rtn = null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            //return rtn = null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void insertRelacion_CE(Contacto c, Emails e)
            throws QueryNotFoundException, OperationException{
        String metodo = "insertRelacion_CE";
        List<Grupo> rtn = new ArrayList<Grupo>();
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.insertRelacionCE(c.getId(), e.getId());;
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
        } catch (JDOMException ex) {
            logger.error(ex);
            //return rtn = null;
        } catch (IOException ex) {
            logger.error(ex + ": " + ex.getMessage());
            //return rtn = null;
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void updateContacto(Contacto c)
            throws OperationException {
        String metodo = "updateContacto";
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            ds.updateContacto(c);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    } 
        
    public void deleteRelacionesCG(String idc) 
            throws OperationException{
        String metodo = "deleteRelacionesCG";
        try {
            ds = new DataSource(db.conectarBD());
            ds.deleteRelacionCG(idc);
            
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void deleteRelacionesCM(String idc) 
            throws OperationException{
        String metodo = "deleteRelacionesCM";
        try {
            ds = new DataSource(db.conectarBD());
            ds.deleteRelacionesCM(idc);
            
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();
        }
    }
    
    public void deleteRelacionesCE(String idc) 
            throws OperationException{
        String metodo = "deleteRelacionesCE";
        try {
            ds = new DataSource(db.conectarBD());
            ds.deleteRelacionesCE(idc);
            
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void deleteMovil(int movil) 
            throws OperationException{
        String metodo = "deleteRelacionesCG";
        try {
            ds = new DataSource(db.conectarBD());
            ds.deleteMovil(movil);            
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void deleteEmail(String email) 
            throws OperationException{
        String metodo = "deleteEmail";
        try {
            ds = new DataSource(db.conectarBD());
            ds.deleteEmail(email);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
    
    public void bajaContacto(String id) 
            throws OperationException{
        String metodo = "deleteContacto";
        try {
            ds = new DataSource(db.conectarBD());
            ds.bajaContacto(id);
        } catch (JDOMException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
       
    /**
     * Obtiene la instancia de {@link DataSource} declarada.
     * @return DuplicadosDataSource - ds
     */
    public DataSource getDs() 
    {
        return ds;
    }

    public List<Contacto> getListaContactosGrupo(String grp)
            throws OperationException {
        String metodo = "getListaContactosGrupo";
        List<Contacto> rtn = null;
        try {
            // Establecer la conexión
            ds = new DataSource(db.conectarBD());
            // Ejecutar query
            rtn = ds.getListaContactosGrupo(grp);
            return rtn;
        } catch (JDOMException ex) {
            logger.error(ex);
            return rtn = null;
        } catch (IOException ex) {
            logger.error(ex);
            return rtn = null;
        } catch (QueryNotFoundException ex) {
            logger.error(metodo + ": Fallo en Query. "+ex);
            throw new OperationException("fallo en Query de " + metodo + ". ",ex);
        } catch (SQLException ex) {
            logger.error(metodo + ": Fallo en SQL. "+ex);
            throw new OperationException("fallo en SQL de " + metodo + ". ",ex);
	}finally {
            db.desconectarBD();    
        }
    }
}