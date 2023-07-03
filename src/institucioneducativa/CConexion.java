/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package institucioneducativa;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConexion {
    
    Connection conectar =null;
    
    String usuario = "root";
    String contraseña = "Jair19951231+";
    String bd = "colegio";
    String ip = "localhost";
    String puerto  = "3306";
    
    String cadena ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    
    
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
           // JOptionPane.showInternalMessageDialog(null,"Conexion realizada con exito");
            
                    
        
        }catch (Exception e) {
        
            JOptionPane.showInternalMessageDialog(null, "Error al conectar a la base de datos, error :"+ e.toString());
        }
        return conectar;
    } 
    
}
