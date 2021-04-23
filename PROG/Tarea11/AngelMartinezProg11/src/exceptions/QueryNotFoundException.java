/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author jmora
 */
public class QueryNotFoundException extends Exception{
   private String code;
   private String idQuery;

    public QueryNotFoundException(String idQuery){
        super("No se ha encontrado la query a ejecutar: "+idQuery);
        this.idQuery = idQuery;
    }

    public String getCode() {
        return code;
    }
}
