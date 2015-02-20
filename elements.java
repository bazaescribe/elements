package mx.gob.condusef.Java.Lib;

import mx.gob.condusef.BD.Conexion.*;
import mx.gob.condusef.BD.Proc.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssocialjjlp1
 */
import java.io.*;
import java.util.*;

public class elements {
    String output="";
    
    public String createCBX(int type, String idCombo, String nombreLabel, String metodo, String stringAux, int numAux){
        output="";
        
        switch(type){           
            case 0:{
            //Combo para explotar Procedimientos Almacenados en Oracle
            //Utilizar variable ---- stringAux ---- para definir el procedimiento a explotar.
                SQL sqldb = new SQL();
                Vector vsqldb = new Vector();
                vsqldb = sqldb.storedProcedure(stringAux);

                output="<label>"+nombreLabel+"</label>"+
                        "<select id='"+idCombo+"' class='combobox' onchange='"+metodo+"'>"+
                        "<option value='0'>- - - - - - - - -</option>";

                for(int i=0; i<vsqldb.size(); i++){
                    //output=output+"<option value='"+((Vector)vsqldb.get(i)).get(0)+"'>"+((Vector)vsqldb.get(i)).get(0)+"_"+((Vector)vsqldb.get(i)).get(1)+"</option>";
                    output=output+"<option value='"+((Vector)vsqldb.get(i)).get(0)+"'>"+((Vector)vsqldb.get(i)).get(1)+"</option>";
                }

                output = output + "</select>";
                
                return output;
            }
            
            case 1:{ 
            /*Combo con contador, utilizar variable numAux para determinar el número de elementos*/
                
                output="<label>"+nombreLabel+"</label>"+
                        "<select id='"+idCombo+"' class='combobox' onchange='"+metodo+"'>";
                
                for(int i=0; i<=numAux; i++){
                    output=output + "<option value="+i+">"+i+"</option>";
                }
                
                return output;  
            }            
            
            case 2:{ //12 Horas
                output="<label>"+nombreLabel+"</label>";
                output=output+"<select class='combobox' id='"+idCombo+"'><option value='0'>- - - - - - - - -</option>";

                for(int i=1; i<13; i++){
                    if(i<10){
                        output=output+"<option>0"+i+":00</option>";
                    }
                    else{
                        output=output+"<option>"+i+":00</option>";
                    }
                }
            }    
            
            case 3:{ //Utiliza numAux para seleccionar entre 12 y 24 horas
                output="<label>"+nombreLabel+"</label>";
                output=output+"<select class='combobox' id='"+idCombo+"'><option value='0'>- - - - - - - - -</option>";

                if(numAux == 24){
                    for(int i=0; i<24; i++){
                        if(i<10){
                            output=output+"<option>0"+i+":00</option>";
                        }
                        else{
                            output=output+"<option>"+i+":00</option>";
                        }
                    }
                }    
                else{
                    if( numAux == 12){   
                        for(int i=1; i<24; i++){
                            if(i<10){
                                output=output+"<option>0"+i+":00 am</option>";
                            }
                            else{
                                if(i<12){
                                    output=output+"<option>"+i+":00 am</option>";
                                }
                                else{
                                    if(i==12){
                                        output=output+"<option>"+i+":00 pm</option>";
                                    }else{
                                        if(i<22){
                                            output=output+"<option>0"+(i-12)+":00 pm</option>";
                                        }
                                        else
                                            output=output+"<option>"+(i-12)+":00 pm</option>";
                                    }    
                                }
                            }

                        }
                    }
                    else{
                        output=output+"<option> UTILIZA NUMAUX PARA INDICAR SI ES CON 12 O 24</option>";
                    }
                }    

                output = output + "</select>";
                
                return output;
            } 
            
            case 4:{ //Combobox con Opciones de Texto, utiliza la variable stringAux para agregar las opciones del outputbox separadas por comas (,).
                String[] opciones = stringAux.split(",");
                
                output="<label>"+nombreLabel+"</label>"+
                        "<select id='"+idCombo+"' class='combobox' >"+
                        "<option value='0'>- - - - - - - - -</option>";
                
                for(int i=0; i<opciones.length; i++){
                    int a = i+1;
                    output=output + "<option value="+a+">"+opciones[i]+"</option>";
                }

                output = output + "</select>";
                
                return output;
            }
            
            case 5:{
                
                SQL sqlObject           = new SQL();
                List<List<String>> list = new ArrayList<List<String>>();
                list                    = sqlObject.sio_act_des(stringAux, numAux);
                int i                   = 0;
                int j                   = 0;
                List<String> celda      = new ArrayList<String>();
                String a;
                
                output="<label>"+nombreLabel+"</label>"+
                        "<select id='"+idCombo+"' class='combobox' onchange='"+metodo+"'>"+
                        "<option value='0'>- - - - - - - - -</option>";

                for(i = 0; i<list.size(); i ++){
                    celda = list.get(i);
                    for(j=0; j < celda.size(); j ++){
                        a = celda.get(j).toString();
                        if(j==0){
                            output = output + "<option value=" + a + ">";
                        }else{
                            output = output + a + "</option>";
                        }
                    }
                }
                
                output = output + "</select>"; 
                
                return output;
            }
            
            case 6:{
                output = "<label>"+ nombreLabel +"</label><select class='combobox' id=" + idCombo+ ">\n" +
                "    <option value=\"MXN\" selected=\"selected\">\n" +
                "        Pesos Mexicanos\n" +
                "    </option>\n" +
                "    <option value=\"USD\">\n" +
                "        Dólar Estadounidense\n" +
                "    </option>\n" +
                "    <option value=\"EUR\">\n" +
                "        Euro\n" +
                "    </option>\n" +
                "    <option value=\"GBP\">\n" +
                "        Libra Esterlina\n" +
                "    </option>\n" +
                "    <option value=\"JPY\">\n" +
                "        Yen Japonés\n" +
                "    </option>\n" +
                "    <option value=\"CHF\">\n" +
                "        Francos Suizos\n" +
                "    </option>\n" +
                "    <option value=\"XAG\">\n" +
                "        Onza Plata\n" +
                "    </option>\n" +
                "    <option value=\"XAU\">\n" +
                "        Onza Oro\n" +
                "    </option>\n" +
                "</select>";
            }
        }   
        return output;    
    }
    
    public String createTXT(String idCampo, String nombreLabel, String stringAux, int numAux){
        output = "<label>"+nombreLabel+"</label><input class='textBox' "+ stringAux +" type='text' id='"+idCampo+"' name='"+idCampo+"'>";
        
        return output;
    }
    
    public String createPSW(String idCampo, String nombreLabel, String stringAux, int numAux){
        output = "<label>"+nombreLabel+"</label><input class='textbox' type='password' id='"+idCampo+"' name='"+idCampo+"'>";
        
        return output;
    }
    
    public String textBox(){
        output = "Hola mundo!";
        
        return output;
    }
    
    public String createRadio(String idRadioGroup, String nombreLabel, String stringAux){
        output = "<label>"+ nombreLabel +"</label>";
        int i= 0;
        String a ="";
        String[] opciones = stringAux.split(",");
        
        for(i=0; i<opciones.length; i++){
            a = opciones[i];
            output = output + "<input type='radio' id='"+idRadioGroup+i+"'name='" + idRadioGroup + "' value='"+i+"'>"+ a +"</input> &nbsp;";
        }
        return output;
    }
    
    public String createCBX2(int type, String idCombo, String nombreLabel, String format, String nombreProcedimiento, String parameters[][]){
        output="";
        
        switch(type){           
            case 0:{
            //Combo para explotar Procedimientos Almacenados en Oracle
            //Utilizar variable ---- stringAux ---- para definir el procedimiento a explotar.
                SQL sqlObject           = new SQL();
                List<List<String>> list = new ArrayList<List<String>>();

                output="<label>"+nombreLabel+"</label>"+
                        "<select id='"+idCombo+"' class='combobox' onchange='"+format+"'>"+
                        "<option value='0'>- - - - - - - - -</option>";
                
                list                    = sqlObject.procedure(nombreProcedimiento, parameters);
                int i                   = 0;
                int j                   = 0;
                List<String> celda      = new ArrayList<String>();
                String a;

                for(i = 0; i<list.size(); i ++){
                    celda = list.get(i);
                    for(j=0; j < celda.size(); j ++){
                        a = celda.get(j).toString();
                        if(j==0){
                            output = output + "<option value="+ a +">";
                        }else{
                            output = output + a + "</option>";
                        }
                    }
                }
                
        /*
                for(int i=0; i<vsqldb.size(); i++){
                    
                    output=output+"<option value='"+((Vector)vsqldb.get(i)).get(0)+"'>"+((Vector)vsqldb.get(i)).get(1)+"</option>";
                }
*/
                output = output + "</select>";
                
                return output;
            }
            
            case 6:{
                output = "<label>"+ nombreLabel +"</label><select class='combobox' id=" + idCombo+ ">\n" +
                "    <option value=\"MXN\" selected=\"selected\">\n" +
                "        Pesos Mexicanos\n" +
                "    </option>\n" +
                "    <option value=\"USD\">\n" +
                "        Dólar Estadounidense\n" +
                "    </option>\n" +
                "    <option value=\"EUR\">\n" +
                "        Euro\n" +
                "    </option>\n" +
                "    <option value=\"GBP\">\n" +
                "        Libra Esterlina\n" +
                "    </option>\n" +
                "    <option value=\"JPY\">\n" +
                "        Yen Japonés\n" +
                "    </option>\n" +
                "    <option value=\"CHF\">\n" +
                "        Francos Suizos\n" +
                "    </option>\n" +
                "    <option value=\"XAG\">\n" +
                "        Onza Plata\n" +
                "    </option>\n" +
                "    <option value=\"XAU\">\n" +
                "        Onza Oro\n" +
                "    </option>\n" +
                "</select>";
            }
        }   
        return output;    
    }
}
