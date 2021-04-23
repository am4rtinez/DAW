/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 *
 * @author jmora
 */
public class OperationException extends Exception{
    private String code;

    public OperationException(String msg, Exception ex){
        super("No se ha podido completar la operación debido a " + msg, ex);
    }

    public OperationException(Exception ex){
        super("Se ha producido un error interno, consulte los logs. \n" + ex.getMessage(), ex);
    }

    public OperationException(String msg, Exception ex, String code){
        super("No se ha podido completar la operación debido a: " + msg, ex);
        this.code = code;
    }

  public OperationException(TransformerFactoryConfigurationError ex) {
    throw new UnsupportedOperationException("Not yet implemented");
  }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
