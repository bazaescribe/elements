/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.*;
import java.util.*;
import connections.*;
import java.sql.Statement;

/**
 *
 * @author joseluisrosasbaza
 */

public class SQL {
    
    public List<List<String>> storedProcedure(){
        ArrayList<String> fila = new ArrayList<String>();
        List<List<String>> ls2d = new ArrayList<List<String>>();
        
        try{
            int h=0;
            DBConnect conn = new DBConnect();
            Connection con = conn.getConection();
            CallableStatement cs = con.prepareCall("{call country_hos (?)}");
            
            cs.setString(1, "Europa");
            
            cs.execute();

            ResultSet rs            = ((CallableStatement)cs).getResultSet();
            ResultSetMetaData RSMD  = rs.getMetaData();
            
            System.out.println(" - - - Número de Columnas: " + RSMD.getColumnCount());
            
            while(rs.next()){
                for(h = 1; h <= RSMD.getColumnCount(); h++){    
                    String a = rs.getString(h);
                    fila.add(a);
                }
                ls2d.add((ArrayList)fila.clone());
                fila.clear();
            }
            
            cs.close();
            rs.close();
            con.close();
            conn = null;
            
            System.out.println("PROCEDIMIENTO EJECUTADO CON ÉXITO ☻");
        }
        catch(Exception ex){
            System.out.println(ex + " ▒▓▒▓ ERROR EN LA EJECUCIÓN DEL PROCEDIMIENTO DESDE JAVA  ▒▓▒▓");
        }
        /* - - - - - - - El Procedimiento regresa: */
        return (ls2d);
    }
}
