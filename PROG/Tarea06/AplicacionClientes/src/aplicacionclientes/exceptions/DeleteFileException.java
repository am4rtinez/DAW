/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionclientes.exceptions;

/**
 * Clase que define la Excepción de tipo DeleteFileException.
 * Se utiliza para capturar el error de opción inválida seleccionada.
 * @author amartinez
 */
public class DeleteFileException extends Exception {
    public DeleteFileException(String msg) {
        super(msg);
    }
}