package prog08notes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author amartinez
 */
public class Prog08Notes {
    // Declaramos la constante de los nombres obligatorios de los módulos    
    private static final String[] moduls = {"SiPROG", "SiMSO", "SiWEB", "SiXAR"};
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private ArrayList <Alumno> alumnes;
    private float[][] totesNotes;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Instanciamos la clase Prog08notes para usar sus atributos
        Prog08Notes pn = new Prog08Notes(); 
        byte opcio = 99; 
        //Inicializada a este numero para que una vez capturado 
        //el InputMismatchException no salga de la aplicación.
        do {
            try {
                opcio = menuOpcions();
                switch (opcio) {
                    case 1 -> pn.inserirAlumne();
                    case 2 -> pn.mitjanesAlumnes();
                    case 3 -> pn.mitjanaModul();
                    case 4 -> pn.matriuNotes();    
                    case 5 -> pn.notaAlumneModul();     
                    case 0 -> System.out.println("PROGRAMA FINALITZAT!!!");
                    default -> System.out.println("Aquesta opció no existeix.");
                } 
            }catch (InputMismatchException ex) {
                System.out.println("Opción invalida.");
            } 
        } while (opcio != (byte) 0); 
    }
    
    //Definición de los métodos 
    
    //CONSTRUCTOR POR DEFECTO DE LA CLASE Prog08Notes QUE CREA EL ARRAYLIST
    public Prog08Notes() 
    {
       this.alumnes = new ArrayList<Alumno>();
    }

    //MÉTODO PARA AÑADIR UN ALUMNO A LA LISTA alumnes
    public void inserirAlumne() 
    {
        Alumno al = new Alumno();
        Scanner kb = new Scanner(System.in);
        boolean validCode;
        //Introducción y validación del código de alumno.
        do {
            try {
                System.out.println("Introduce el código de alumno:");
                al.setCodi(kb.nextLine());
                validCode = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validCode = false;
            }
        }while(!validCode);
        
        //Comprobación de si el alumno ya ha sido introducido en la lista.
        //En el caso de que la lista este vacia no es necesario realizar la comprobación
        //de si existe el usuario.
        if (alumnes.isEmpty()||!existe(al)){
            //Almacenamiento de notas en la clase alumno.      
            for (int i = 0; i < moduls.length; i++) {
                boolean validNota;
                System.out.println("Introduce la nota de " + moduls[i] + ":");
                do {
                    try {
                        float n = kb.nextFloat();
                        al.setNota(i, n);
                        validNota = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Dato introducido no válido");
                        System.out.println("Vuelve a introducir la nota de " + moduls[i] + ":");
                        validNota = false;
                        kb.next();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("Vuelve a introducir la nota de " + moduls[i] + ":");
                        validNota = false;
                    }
                } while (!validNota);
            }
            
            //Almacenamiento del objeto alumno en el arrayList.
            alumnes.add(al);
            System.out.println("Alunmo añadido.");
        } else {
            System.out.println("El alumno con código " + al.getCodi()
                    + " ya ha sido introducido anteriormente. No se añadirá a la lista.");
        }
    }
    
    //Metodo que se encarga de comprobar si el alumno esta introducido.
    public boolean existe(Alumno a)
    {
        boolean existe = false;
        for (Alumno item : alumnes){
            if (a.getCodi().equals(item.getCodi())){
                existe = true;
                break;
            }
        }
        return existe;
    }

    //MÉTODO PARA MOSTRAR LA NOTA MEDIA DE CADA ALUMNO
    public void mitjanesAlumnes() 
    {
        if (alumnes.isEmpty()){
            System.out.println("Antes de operar debe proceder con la inserción de alumnos al listado.");
        }else{
            //Recorre la lista de alumnos y utiliza le metodo mitjanaNotes de la clase alumno para mostrar los datos.
            for (Alumno item : alumnes) {
                System.out.println("Nota mijana alumne " + item.getCodi() + ": " 
                        + DF.format(item.mitjanaNotes()));
            }   
        }        
    }

    //MÉTODO PARA CALCULAR Y MOSTRAR LA NOTA MEDIA DE UN MÓDULO PEDIDO POR TECLADO
    public void mitjanaModul() 
    {
        if (alumnes.isEmpty()){
            System.out.println("Antes de operar debe proceder con la inserción de alumnos al listado.");
        }else{
            Scanner kb = new Scanner(System.in);
            boolean validMod = false;
            float sumatorio = 0;
            System.out.println("Módulos:");
            System.out.println(" 1." + moduls[0]);
            System.out.println(" 2." + moduls[1]);
            System.out.println(" 3." + moduls[2]);
            System.out.println(" 4." + moduls[3]);
            System.out.println("Introduzca la opción del módulo a obtener la media de notas:");
            do {
                try {
                    int mod = kb.nextInt();
                    if (mod < 1 || mod > 4) {
                        System.out.println("Opción inválida. Vuelva a introducir la opcion:");
                    } else {
                        for (Alumno item : alumnes) {
                            sumatorio = sumatorio + item.getNota(mod);
                        }
                        System.out.println("La media de notas para el modulo "
                                + moduls[mod - 1] + " es de: " + DF.format(sumatorio / alumnes.size()));
                        validMod = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Opción inválida. Vuelva a introducir la opcion:");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } while (!validMod);
        }        
    }

    //MÉTODO PARA MOSTRAR PARA CADA ALUMNO TODAS SUS NOTAS USANDO
    //EL ARRAY DE DOS DIMENSIONES totesNotes[][]
    public void matriuNotes() 
    {
        if (alumnes.isEmpty()){
            System.out.println("Antes de operar debe proceder con la inserción de alumnos al listado.");
        }else{
            totesNotes = new float[alumnes.size()][4];
            for (int i = 0; i < alumnes.size(); i++) {
                for (int j = 0; j < alumnes.get(i).getNotes().length; j++) {
                    totesNotes[i][j] = alumnes.get(i).getNotes()[j];
                }
            }
            System.out.println("Notas de los alumnos.");
            for (int i1 = 0; i1 < totesNotes.length; i1++) {
                System.out.println("Alumno: " + alumnes.get(i1).getCodi());
                for (int i2 = 0; i2 < totesNotes[i1].length; i2++) {
                    System.out.println(moduls[i2] + ": " + DF.format(totesNotes[i1][i2]));
                }
            }
        }  
    }

    //MÉTODO PARA PEDIR POR TECLADO UN ALUMNO Y UN MÓDULO,
    //BUSCARLO EN LA LISTA Y MOSTRAR SU NOTA
    public void notaAlumneModul() 
    {
        if (alumnes.isEmpty()){
            System.out.println("Antes de operar debe proceder con la inserción de alumnos al listado.");
        }else{
            boolean vc = false;
            boolean vm = false;
            Scanner kb = new Scanner(System.in);
            String codi = null;
            String mod = null;

            //Mostramos listado de alumnos para que así haya una referencia para el usuario.
            System.out.println("Listado de alumnos:");
            for (Alumno item : alumnes) {
                System.out.println("# " + item.getCodi());
            }
            //Mostramos listado de módulos para que el usuario tenga una referencia.
            System.out.println("Listado de módulos:");
            for (String item : moduls) {
                System.out.println("# " + item);
            }

            //Introducir el codigo de alumno.
            do {
                try {
                    System.out.println("Introduce el código de alumno:");
                    codi = kb.nextLine();
                    if (!codi.matches("(^[A-Z]{3}[0-9]{2}$)")) {
                        System.out.println("Código introducido no válido");
                    } else {
                        vc = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Opción no válida.");
                }
            } while (!vc);

            //Introducir el código de módulo
            do {
                try {
                    System.out.println("Introduce el código de módulo:");
                    mod = kb.nextLine();
                    vm = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Opción no válida.");
                }
            } while (!vm);

            //Búsqueda del indice del alumno. Se inicializa ai a -1 para indicar que no existe en caso de no encontrarlo.
            int ai = -1;
            for (int i = 0; i < alumnes.size(); i++) {
                if (codi.equals(alumnes.get(i).getCodi())) {
                    ai = i;
                    break; //Forzamos la sailda del búcle.
                }
            }

            //Búsqueda del indice del módulo. Si no éxiste devuelve -1.
            int mi = Arrays.asList(moduls).indexOf(mod);

            //Comprobaciones de indices y si es correcto impresion de datos.
            if (ai == -1) {
                System.out.println("El código de alumno introducido no existe. Vuelva a realizar la operación.");
            } else if (mi == -1) {
                System.out.println("El código de módulo introducido no existe. Vuelva a realizar la operación.");
            } else {
                try {
                    System.out.println("Alumno " + alumnes.get(ai).getCodi()
                            + " ha obtenido una nota de " + DF.format(alumnes.get(ai).getNota(mi))
                            + " en el módulo " + moduls[mi] + ".");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    //MENÚ DE OPCIONES
    private static byte menuOpcions ()  
    {
        Scanner kb = new Scanner(System.in);
        System.out.println(" 1. Insertar alumno en la lista.");
        System.out.println(" 2. Mostrar media de notas de los alumnos de la lista");
        System.out.println(" 3. Mostrar media de un determinado módulo.");
        System.out.println(" 4. Informe global. Matriz notas alumnos-módulos.");
        System.out.println(" 5. Nota Alumno-Módulo.");
        System.out.println(" 0. Salir del programa.");
        return kb.nextByte();
    } 
}
