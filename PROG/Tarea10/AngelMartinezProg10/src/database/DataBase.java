/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author amartinez
 */
public class DataBase {

    private Connection conexion = null;
       
    private static String host = "";
    private static String port = "";
    private static String db = "";
    private static String user = "";
    private static String pass = "";
    
    /**
     * @return the host
     */
    public static String getHost() {
        return host;
    }

    /**
     * @param aHost the host to set
     */
    public static void setHost(String aHost) {
        host = aHost;
    }

    /**
     * @return the port
     */
    public static String getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public static void setPort(String aPort) {
        port = aPort;
    }

    /**
     * @return the db
     */
    public static String getDb() {
        return db;
    }

    /**
     * @param aDb the db to set
     */
    public static void setDb(String aDb) {
        db = aDb;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the pass
     */
    public static String getPass() {
        return pass;
    }

    /**
     * @param aPass the pass to set
     */
    public static void setPass(String aPass) {
        pass = aPass;
    }
    
    public Connection conectarBD()
    {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            return conexion = DriverManager.getConnection("jdbc:oracle:thin:@//" 
                    + getHost() + ":" 
                    + getPort() + "/" + getDb(), getUser(), getPass());
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public void desconectarBD()
    {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
