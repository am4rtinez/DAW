package controls;

import database.DbManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author amartinez
 */
public class AngelMartinezProg11 {
    private static List<Alumno> lal;
    private static List<Tutor> ltu;
    
    private static final String host = "192.168.1.10";
    private static final String database = "BDAngelMartinez";
    private static final String port = "3306";
    private static final String user = "amartinez";
    private static final String pass = "12345";
    
    private static DbManager dbm;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instanciamos la clase AngelMartinezProg11 para usar sus atributos
        AngelMartinezProg11 pn = new AngelMartinezProg11(); 
        
        dbm = new DbManager(host, database, port, user, pass);
        
        byte opcio = 99;
        //Inicializada a este numero para que una vez capturado 
        //el InputMismatchException no salga de la aplicación.
        do {
            try {
                opcio = menuOpcions();
                switch (opcio) {
                    case 1 -> pn.getListAlumnos();
                    case 2 -> pn.getListTutores();
                    case 3 -> pn.getAlumnosTutor();
                    case 4 -> pn.inserirTutor();
                    case 5 -> pn.inserirAlumne();
                    case 6 -> pn.eliminarAlumne();
                    case 7 -> pn.editarTutor();
                    case 0 -> System.out.println("PROGRAMA FINALIZADO!!!");
                    default -> System.out.println("Aquesta opció no existeix.");
                } 
            }catch (InputMismatchException ex) {
                System.out.println("ERROR: Opción invalida.");
            } 
        } while (opcio != (byte) 0); 
    }
    
//================ Definición de los métodos =======================
    //Método para mostrar a todos los alumnos de la BD.
    public void getListAlumnos(){
        try {
            lal = dbm.getListaAlumnos();
            System.out.println("Listado de alumnos:");
            System.out.println("-------------------------------------------");
            for (int i = 0; i < lal.size(); i++){
                System.out.println(" " + lal.get(i).getCodiAlumne()+ " - "
                        + lal.get(i).getNomAlumne()+ " - "
                        + lal.get(i).getCodiTutorAlumne());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
    }
    
    public void getListTutores(){
        try {
            ltu = dbm.getListaTutors();
            System.out.println("Listado de tutores:");
            System.out.println("-------------------------------------------");
            for (int i = 0; i < ltu.size(); i++){
                System.out.println(" " + ltu.get(i).getCodiTutor() + " - "
                        + ltu.get(i).getNomTutor());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
    }
    
    public void getAlumnosTutor()
    {
        Scanner kb = new Scanner(System.in);
        List<Alumno> lat = null;
        Boolean validCode = false;
        int ct = 0;
        getListTutores(); //Mostgramos el listado de tutores para que el usuario seleccione.
        System.out.println("-------------------------------------------");
        do {
            try {
                System.out.println("Introduce el código del tutor:");
                ct = kb.nextInt();
                if (existe(ct)){
                    lat = dbm.getListaAlumnosTutor(ct);
                    validCode = true;
                }else{
                    throw new Exception("ERROR: El código de tutor ( " + ct + " ) no esta en el listado.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validCode = false;
            }
        }while(!validCode);
        if (lat.isEmpty()){
            System.out.println("El tutor " + nombreTutor(ct) + " no tiene alumnos asignados.");
        }else{
            System.out.println("Listado de alumnos del tutor (" + nombreTutor(ct) + "):");
            System.out.println("-------------------------------------------");
            for (int i = 0; i < lat.size(); i++) {
                System.out.println("* " + lat.get(i).getCodiAlumne() + " - "
                        + lat.get(i).getNomAlumne());
            }
            System.out.println("-------------------------------------------");
            System.out.println("Número de alunmos: " + lat.size());
        }
    }
    
    //Metodo que se encarga de comprobar si el tutor esta introducido.
    public boolean existe(int ct)
    {
        boolean existe = false;
        for (Tutor item : ltu){
            if (ct == item.getCodiTutor()){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public String nombreTutor(int ct)
    {
        String nombre = "";
        for (Tutor item : ltu){
            if (ct == item.getCodiTutor()){
                nombre = item.getNomTutor();
                break;
            }
        }
        return nombre;
    }

    //Método para añadir un tutor a la BD.
    public void inserirTutor() 
    {
        try {
            Tutor t = new Tutor();
            Scanner kb1 = new Scanner(System.in);
            Scanner kb2 = new Scanner(System.in);
            boolean validCode, validName;
            do {
                try {
                    System.out.println("Introduce el código del tutor:");
                    t.setCodiTutor(kb1.nextInt());
                    validCode = true;
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                    validCode = false;
                }
            }while(!validCode);
            do {
                try {
                    System.out.println("Introduce el nombre del tutor:");
                    t.setNomTutor(kb2.nextLine());
                    validName = true;
                } catch (Exception ex) {
                    System.out.println(ex);
                    validName = false;
                }
            }while(!validName);
            if (dbm.existeTutor(t) != 0){
                System.out.println("ERROR: El tutor ya existe en la BD.");
            }else{
                dbm.insertTutor(t);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    //Método para añadir un alumno a la BD.
    public void inserirAlumne() 
    {
        try {
            Alumno al = new Alumno();
            Scanner kb = new Scanner(System.in);
            boolean validCode, validName, validCodeTutor;
            //Introducción y validación del código de alumno.
            do {
                try {
                    System.out.println("Introduce el código del alumno:");
                    al.setCodiAlumne(kb.nextLine());
                    validCode = true;
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                    validCode = false;
                }
            }while(!validCode);
            do {
                try {
                    System.out.println("Introduce el nombre del alumno:");
                    al.setNomAlumne(kb.nextLine());
                    validName = true;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    validName = false;
                }
            }while(!validName);
            getListTutores();
            do {
                try {
                    System.out.println("Introduce el codigo del tutor:");
                    al.setCodiTutorAlumne(kb.nextInt());
                    validCodeTutor = true;
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                    validCodeTutor = false;
                }
            }while(!validCodeTutor);
            dbm.insertAlumno(al);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    public void eliminarAlumne()
    {
        Alumno a = new Alumno();
        Scanner kb = new Scanner(System.in);
        boolean validCode;
        getListAlumnos();
        //Introducción y validación del código de alumno.
        do {
            try {
                System.out.println("Introduce el código del alumno:");
                a.setCodiAlumne(kb.nextLine());
                validCode = true;
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
                validCode = false;
            }
        } while (!validCode);
        try {
            dbm.eliminarAlumno(a);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
    }
    
    public void editarTutor()
    {
        try {
            Tutor t = new Tutor();
            Scanner kb1 = new Scanner(System.in);
            Scanner kb2 = new Scanner(System.in);
            boolean validCode, validName = false;
            getListTutores();
            do {
                try {
                    System.out.println("Introduce el código del tutor a modificar:");
                    t.setCodiTutor(kb1.nextInt());
                    validCode = true;
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                    validCode = false;
                }
            }while(!validCode);
            do {
                try {
                    System.out.println("Introduce el nombre del tutor:");
                    t.setNomTutor(kb2.nextLine());
                    validName = true;
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                    validName = false;
                }
            }while(!validName);
            dbm.editarTutor(t);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    //MENÚ DE OPCIONES
    private static byte menuOpcions ()  
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("| 1. Mostrar alumnos de la BD.               |");
        System.out.println("| 2. Mostrar tutores de la BD.               |");
        System.out.println("| 3. Mostrar listado de alumnos de un tutor. |");
        System.out.println("| 4. Insertar un nuevo tutor.                |");
        System.out.println("| 5. Insertar un nuevo alumno.               |");
        System.out.println("| 6. Eliminar alumno de la BD.               |");
        System.out.println("| 7. Modificar nombre tutor.                 |");
        System.out.println("| 0. Salir del programa.                     |");
        System.out.println("==============================================");
        return kb.nextByte();
    } 
}
