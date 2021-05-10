package database;


import controls.Equip;
import controls.Partit;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author amartinez
 */
public class DbManager {
    
    private DataBase db = null;
    
    /**
     * Constructor de la clase. 
     * @param host
     * @param database
     * @param port
     * @param user
     * @param pass
     */
    public DbManager(String host, String database, String port, String user, String pass){
        db = new DataBase();
        DataBase.setHost(host);
        DataBase.setDb(database);
        DataBase.setPort(port);
        DataBase.setUser(user);
        DataBase.setPass(pass);
    }
       
    public void showPartidos() throws SQLException
    {
        Statement stmt;
        ResultSet rs;
        String query = "SELECT local, visitant, golslocal, golsVisitant, g.guanyador() as guanyador FROM partitsmartinez g";
        Partit p;
        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("local") + " vs " + rs.getString("visitant")+ " : " + rs.getInt("golsLocal")+ " - " + rs.getInt("golsVisitant")+ " -> " + rs.getString("guanyador"));
        }
        db.desconectarBD();
    }
    
    public List<Partit> getListaPartidos() throws SQLException
    {
        List<Partit> rtn = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT local, visitant, golslocal, golsVisitant, g.guanyador() as guanyador FROM partitsmartinez g";
        Partit p;
        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            p = new Partit(rs.getString("local"), rs.getString("visitant"), rs.getInt("golsLocal"), rs.getInt("golsVisitant"), rs.getString("guanyador"));
            rtn.add(p);
        }
        db.desconectarBD();
        return rtn;
    }
   
    public List<Equip> getListaEquipos() throws SQLException
    {
        List<Equip> rtn = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT local AS equipo FROM partitsmartinez UNION SELECT visitant AS equipo FROM partitsmartinez";
        Equip e;
        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            e = new Equip();
            e.setNombre(rs.getString("equipo"));
            rtn.add(e);
        }
        db.desconectarBD();
        return rtn;
    }
    
    public void insertPartido(Partit p) throws SQLException 
    {
        Statement stmt;
        String query = "INSERT INTO PARTITSMartinez VALUES ('" + p.getLocal()+ "', '" + p.getVisitant()+ "'," + p.getGolsLocal() + ", " + p.getGolsVisitant() + ")";
//        System.out.println(query);
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        stmt.executeUpdate(query); //Ejecuta el query
        db.desconectarBD();
    }

}