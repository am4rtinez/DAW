/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

/**
 * Clase que define la Excepción de tipo SaldoInsuficiente.
 * @author Ángel Martínez Rodríguez
 */
public class InvalidOperationException extends Exception {
    public InvalidOperationException(String msg) {
        super(msg);
    }
}