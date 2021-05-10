/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import database.DbManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author amartinez
 */
public class AngelMartinezProg10 {

    private static final String host = "192.168.1.42";
    private static final String database = "xepdb1";
    private static final String port = "1521";
    private static final String user = "hr";
    private static final String pass = "123456";
    
    private static DbManager dbm;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AngelMartinezProg10 pn = new AngelMartinezProg10(); 
        
        dbm = new DbManager(host, database, port, user, pass);
        byte opcio = 99;
        //Inicializada a este numero para que una vez capturado 
        //el InputMismatchException no salga de la aplicación.
        do {
            try {
                opcio = menuOpcions();
                switch (opcio) {
                    case 1 -> pn.insertarPartido();
                    case 2 -> pn.showPartidos();
                    case 3 -> pn.showClasificacion();
                    case 0 -> System.out.println("PROGRAMA FINALIZADO!!!");
                    default -> System.out.println("Aquesta opció no existeix.");
                } 
            }catch (InputMismatchException ex) {
                System.out.println("ERROR: Opción invalida.");
            } 
        } while (opcio != (byte) 0);
    }
    //MENÚ DE OPCIONES
    private static byte menuOpcions ()  
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("| 1. Insertar partido en BD.                 |");
        System.out.println("| 2. Mostrar resultados partidos.            |");
        System.out.println("| 3. Mostrar clasificación.                  |");
        System.out.println("| 0. Salir del programa.                     |");
        System.out.println("==============================================");
        return kb.nextByte();
    }
    
    /**
     * Método encargado de insertar los partidos.
     */
    public void insertarPartido()
    {
        Scanner kb = new Scanner(System.in);
        Scanner kb2 = new Scanner(System.in);
        Partit p = new Partit();
        String local = null;
        String visitant = null;
        int gl = 0;
        int gv = 0;
        boolean vLoc, vVis, vgl, vgv;
        do {
            try {
                System.out.println("Introduce el equipo local:");
                p.setLocal(kb.nextLine());
                vLoc = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                vLoc = false;
            }
        }while(!vLoc);
        
        do {
            try {
                System.out.println("Introduce goles del equipo local:");
                p.setGolsLocal(kb.nextInt());
                vgl = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                vgl = false;
            }
        }while(!vgl);
        
        do {
            try {
                System.out.println("Introduce el equipo visitante:");
                p.setVisitant(kb2.nextLine());
                vVis = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                vVis = false;
            }
        }while(!vVis);

        do {
            try {
                System.out.println("Introduce goles del equipo visitante:");
                p.setGolsVisitant(kb2.nextInt());
                vgv = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                vgv = false;
            }
        }while(!vgv);
        
        try {
            dbm.insertPartido(p);
        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }
    
    /**
     * Método encargado de mostrar los partidos. Lo hace directamente desde la consulta.
     */
    public void showPartidos()
    {
        System.out.println("Resultados de los partidos: ");
        System.out.println(" ");
        try {
            dbm.showPartidos();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(" ");
    }
    
    /**
     * Método encargado de mostrar la clasificación.
     */
    public void showClasificacion()
    {
        try {
            List <Equip> le = dbm.getListaEquipos(); //Ontenemos la lista de partidos.
            List <Partit> lp = dbm.getListaPartidos(); //Obtenemos la lista de equipos.
            if (!lp.isEmpty()){ //Comprueba si la lista de partidos está vacía. Si esta vacia la lista de equipos también.
                //Recorremos el listado de partidos.
                for (int i = 0; i < lp.size(); i++){
                    //Comprobamos si es empate.
                    if (lp.get(i).getGuanyador().equals("Empate")){
                        for (int j = 0; j < le.size(); j++){
                            if (lp.get(i).getLocal().equals(le.get(j).getNombre())){
                                le.get(j).setPe(+1);
                                break;
                            }
                        }
                        for (int j = 0; j < le.size(); j++){
                            if (lp.get(i).getVisitant().equals(le.get(j).getNombre())){
                                le.get(j).setPe(+1);
                                break;
                            }
                        }
                    }else{
                        // Si el ganador es el Local aumentamos partidos ganados y
                        // aumentamos partidos perdidos del visitante.
                        if (lp.get(i).getGuanyador().equals(lp.get(i).getLocal())){
                            for (int j = 0; j < le.size(); j++) {
                                if (lp.get(i).getLocal().equals(le.get(j).getNombre())) {
                                    le.get(j).setPg(+1);
                                    break;
                                }
                            }
                            for (int j = 0; j < le.size(); j++) {
                                if (lp.get(i).getVisitant().equals(le.get(j).getNombre())) {
                                    le.get(j).setPp(+1);
                                    break;
                                }
                            }
                        }
                        // Caso contrario.
                        if (lp.get(i).getGuanyador().equals(lp.get(i).getVisitant())){
                            for (int j = 0; j < le.size(); j++) {
                                if (lp.get(i).getLocal().equals(le.get(j).getNombre())) {
                                    le.get(j).setPp(+1);
                                    break;
                                }
                            }
                            for (int j = 0; j < le.size(); j++) {
                                if (lp.get(i).getVisitant().equals(le.get(j).getNombre())) {
                                    le.get(j).setPg(+1);
                                    break;
                                }
                            }
                        }
                    }
                }
                System.out.println("<----------------------------- CLASIFICACIÓN ----------------------------->");
                System.out.println();
                System.out.println("+-------------------------------------------------------------------------+");
                System.out.printf("%-14s %-14s %10s %10s %10s %10s"," Posición","Equipo","Ganados","Empatados","Perdidos","Puntos");
                System.out.println();
                System.out.println("+-------------------------------------------------------------------------+");
                // Ordena la lista por puntos de forma Descendente.
                // https://stackoverflow.com/questions/5805602/how-to-sort-list-of-objects-by-some-property
                Collections.sort(le, (Equip e1, Equip e2) -> e2.getPuntos()- e1.getPuntos());
                for (int i = 0; i < le.size(); i++){
                    System.out.format("%-14s %-14s %10s %10s %10s %10s",
                            " " + (i + 1), 
                            le.get(i).getNombre(), 
                            le.get(i).getPg(),
                            le.get(i).getPe(),
                            le.get(i).getPp(),
                            le.get(i).getPuntos()
                    );
                    System.out.println();
                }
                System.out.println("+-------------------------------------------------------------------------+");
                System.out.println();
            }else{
                System.out.println("No hay partidos introducidos en la BD");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
