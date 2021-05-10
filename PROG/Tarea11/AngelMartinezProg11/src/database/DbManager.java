package database;


import controls.Alumno;
import controls.Tutor;
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
        db.setHost(host);
        db.setDb(database);
        db.setPort(port);
        db.setUser(user);
        db.setPass(pass);
    }
       
    public List<Alumno> getListaAlumnos() throws SQLException
    {
        List<Alumno> rtn = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM alumnes";
        Alumno alumno;

        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            alumno = new Alumno(rs.getString("codiAlumne"),
                    rs.getString("nomAlumne"),
                    rs.getInt("codiTutorAlumne"));
            rtn.add(alumno);
        }
        db.desconectarBD();
        return rtn;
    }
    
    public List<Tutor> getListaTutors() throws SQLException
    {
        List<Tutor> rtn = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM tutors";
        Tutor tutor;
        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            tutor = new Tutor(rs.getInt("codiTutor"),
                    rs.getString("nomTutor"));
            rtn.add(tutor);
        }
        db.desconectarBD();
        return rtn;
    }
    
    public List<Alumno> getListaAlumnosTutor(int ct) throws SQLException
    {
        List<Alumno> rtn = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM alumnes WHERE codiTutorAlumne = " + ct;
        Alumno alumno;
        // Establecer la conexión
        Connection con = db.conectarBD();
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            alumno = new Alumno(rs.getString("codiAlumne"),
                    rs.getString("nomAlumne"),
                    rs.getInt("codiTutorAlumne"));
            rtn.add(alumno);
        }
        db.desconectarBD();
        return rtn;
    }

    public void insertTutor(Tutor t) throws SQLException 
    {
        Statement stmt;
        String query = "INSERT INTO tutors VALUES (" + t.getCodiTutor() + ", '" + t.getNomTutor()+ "')";
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        stmt.executeUpdate(query); //Ejecuta el query
        db.desconectarBD();
    }
    
    public void insertAlumno(Alumno al) throws SQLException 
    {
        Statement stmt;
        String query = "INSERT INTO alumnes VALUES ('"+ al.getCodiAlumne() 
                + "', '" + al.getNomAlumne() 
                + "', " + al.getCodiTutorAlumne()
                + ")";
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        stmt.executeUpdate(query); //Ejecuta el query
        con.close();
        db.desconectarBD();
    }
    
    public void eliminarAlumno(Alumno al) throws SQLException 
    {
        Statement stmt;
        String query = "DELETE FROM alumnes WHERE codiAlumne = '" + al.getCodiAlumne() + "'";
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        stmt.executeUpdate(query); //Ejecuta el query
        con.close();
        db.desconectarBD();
    }
    
    public void editarTutor(Tutor t) throws SQLException 
    {
        Statement stmt;
        String query = "UPDATE tutors SET nomTutor = '" + t.getNomTutor() 
                + "' WHERE codiTutor = " + t.getCodiTutor();
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        stmt.executeUpdate(query); //Ejecuta el query
        db.desconectarBD();
    }
    public int existeTutor(Tutor t) throws SQLException 
    {
        Statement stmt;
        ResultSet rs;
        int rtn = 0;
        String query = "SELECT COUNT(*) FROM tutors WHERE codiTutor = " + t.getCodiTutor();
        Connection con = db.conectarBD(); //Establece la conexión.
        stmt = con.createStatement();
        rs = stmt.executeQuery(query); //Ejecuta el query
        while (rs.next()) {
            rtn = rs.getInt("COUNT(*)"); 
        }
        db.desconectarBD();
        return rtn;
    }    
}