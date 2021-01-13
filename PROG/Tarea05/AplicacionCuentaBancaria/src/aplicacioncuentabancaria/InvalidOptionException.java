/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class InvalidOptionException extends Exception {
    public InvalidOptionException(String msg) {
        super(msg);
    }
}