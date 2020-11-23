package prog02ejer03;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
  Diseña un programa Java que pida dos números por teclado:
  - x  de tipo real, que representará la tarifa por hora de un trabajador,
  - n  de tipo entero, que representará el número de horas trabajadas.
  
  Mostrar por pantalla utilizando la salida con formato numérico con sólo dos 
  decimales (ejemplo 12654,53):
  - La cantidad a cobrar por las n horas trabajadas,
  - El sueldo semanal (se supone siempre 40 horas por semana),
  - El sueldo mensual (se supone siempre 4 semanas por mes),
  - El sueldo anual (12+2 mensualidades).
 */

public class Prog02Ejer03 {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00"); //Formatea a 2 digitos los decimales.
        float x; //tarifa por hora de trabajador.
        int n; //horas trabajadas.
        System.out.println("Introducior tarifa de trabajador: ");
        x = keyboard.nextFloat();
        System.out.println("Introducir número horas: ");
        n = keyboard.nextInt();
        float trabajadas = x * n; //Cálculo por horas trabajadas.
        float semanal = x * 40; //Calculo por horas semanales
        float mensual = semanal*4; //Calculo mensual.
        float anual = semanal*14; //Calculo anual.
        System.out.println("Cantidad a cobrar por horas trabajadas: " + df.format(trabajadas));
        System.out.println("Sueldo semanal (se supone 40h semanales): " + df.format(semanal));
        System.out.println("Sueldo mensual (se suponen 4 semanas por mes): " + df.format(mensual));
        System.out.println("Sueldo anual (12+2 mensualidades): " + df.format(anual));
    }   
}
