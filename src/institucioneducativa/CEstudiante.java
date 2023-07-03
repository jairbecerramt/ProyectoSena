
package institucioneducativa;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class CEstudiante {
    int codigo;
    String nombreEstudiante;
    String apellidosEstudiante;
    String fechanaciemintoEstudiante;
    String direccionEstudiante;
    String correoEstudiante;
    String telefonoEstudiante;

   
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidosEstudiante() {
        return apellidosEstudiante;
    }

    public void setApellidosEstudiante(String apellidosEstudiante) {
        this.apellidosEstudiante = apellidosEstudiante;
    }

    public String getFechanaciemintoEstudiante() {
        return fechanaciemintoEstudiante;
    }

    public void setFechanaciemintoEstudiante(String fechanaciemintoEstudiante) {
        this.fechanaciemintoEstudiante = fechanaciemintoEstudiante;
    }

    public String getDireccionEstudiante() {
        return direccionEstudiante;
    }

    public void setDireccionEstudiante(String direccionEstudiante) {
        this.direccionEstudiante = direccionEstudiante;
    }

    public String getCorreoEstudiante() {
        return correoEstudiante;
    }

    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }

    public String getTelefonoEstudiante() {
        return telefonoEstudiante;
    }

    public void setTelefonoEstudiante(String telefonoEstudiante) {
        this.telefonoEstudiante = telefonoEstudiante;
    }
    
    public void InsetarEstudiante(JTextField paramNombre,JTextField paramApellidos,JTextField paramFechaNacimiento,JTextField paramDireccion,JTextField paramCorreoElectronico,JTextField paramTelefono) {
        
        
        setNombreEstudiante(paramNombre.getText());
        setApellidosEstudiante(paramApellidos.getText());
        setFechanaciemintoEstudiante(paramFechaNacimiento.getText());
        setDireccionEstudiante(paramDireccion.getText());
        setCorreoEstudiante(paramCorreoElectronico.getText());
        setTelefonoEstudiante(paramTelefono.getText());
        
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into estudiante (Nombre,Apellidos,Fecha_Nacimiento,Direccion,Correo,Telefono) values (?, ?,?,?,?,?);";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreEstudiante());
            cs.setString(2,getApellidosEstudiante());
            cs.setString(3, getFechanaciemintoEstudiante());
            cs.setString(4, getDireccionEstudiante());
            cs.setString(5, getCorreoEstudiante());
            cs.setString(6, getTelefonoEstudiante());
            
            cs.execute();
            
            JOptionPane.showInternalMessageDialog(null, "Se inserto correctamente el estudiante");
            
            
            
        } catch (Exception e) {
            
            JOptionPane.showInternalMessageDialog(null, "No se inserto correctamente el estudiante"+e.toString());
            
            
        }
        
    }
    
    public void MostrarEstudiantes(JTable paramTablaTotalEstudiantes){
        
    
    CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(modelo);
        paramTablaTotalEstudiantes.setRowSorter(OrdenarTabla);
        
    
        String sql ="";       
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fecha Nacimiento");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");
        
        paramTablaTotalEstudiantes.setModel(modelo);
        
        sql ="Select * from estudiante";
        
        String[] datos = new String [7];
        Statement st; 
        
        
        try {
           st= objetoConexion.estableceConexion().createStatement();
           
            ResultSet  rs = st.executeQuery(sql);
            
            while (rs.next())  {
                
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                
                modelo.addRow(datos);
            }
                
             paramTablaTotalEstudiantes.setModel(modelo);
             
            
        } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error :" + e.toString());
        
        
        }
    }    
    
    public void SeleccionarEstudiante(JTable paramTablaEstudiante, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramFechaNacimeinto, JTextField paramDireccion , JTextField paramCorreo,JTextField paramTelefono){
    
        
        try {
            
            int fila= paramTablaEstudiante.getSelectedRow();
            if (fila >=0) {
            
                paramId.setText((paramTablaEstudiante.getValueAt(fila, 0).toString()));
                paramNombres.setText((paramTablaEstudiante.getValueAt(fila, 1).toString()));
                paramApellidos.setText((paramTablaEstudiante.getValueAt(fila, 2).toString()));
                paramFechaNacimeinto.setText((paramTablaEstudiante.getValueAt(fila, 3).toString()));
                paramDireccion.setText((paramTablaEstudiante.getValueAt(fila, 4).toString()));
                paramCorreo.setText((paramTablaEstudiante.getValueAt(fila, 5).toString()));
                paramTelefono.setText((paramTablaEstudiante.getValueAt(fila, 6).toString()));
            }
            
            
            else 
            {
                
                JOptionPane.showMessageDialog(null, "fila no seleccionada ");
            }
            
        } catch (Exception e) {
            
            
            JOptionPane.showMessageDialog(null, "Error de seleccion , error :"+e.toString());
            
        }
    
    }
            
            public void ModificarEstudiantes(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos, JTextField paramFechaNacimiento, JTextField paramDireccion,JTextField paramCorreo, JTextField paramTelefono){
                
                setCodigo(Integer.parseInt(paramCodigo.getText()));
                setNombreEstudiante(paramNombres.getText());
                setApellidosEstudiante(paramApellidos.getText());
                setFechanaciemintoEstudiante(paramFechaNacimiento.getText());
                setDireccionEstudiante(paramDireccion.getText());
                setCorreoEstudiante(paramCorreo.getText());
                setTelefonoEstudiante(paramTelefono.getText());
                
                CConexion objetoConexion= new CConexion();
                
                String consulta = "UPDATE estudiante  SET estudiante.Nombre= ?, estudiante.Apellidos= ?, estudiante.Fecha_Nacimiento= ? ,estudiante.Direccion= ? , estudiante.Correo= ?, estudiante.Telefono = ? WHERE estudiante.idEstudiante= ? ;";
                
                
                try {
                    
                    CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
                    cs.setString(1, getNombreEstudiante());
                    cs.setString(2, getApellidosEstudiante());
                    cs.setString(3, getFechanaciemintoEstudiante());
                    cs.setString(4, getDireccionEstudiante());
                    cs.setString(5, getCorreoEstudiante());
                    cs.setString(6, getTelefonoEstudiante());
                    cs.setInt(7,getCodigo());
                    
                    
                    cs.execute();
                    JOptionPane.showMessageDialog(null, "Modificacion exitosa");
                    
                    
                    
                } catch (SQLException e) {
                    
                    JOptionPane.showMessageDialog(null, "No se modifico , erro : "  + e.toString());
                    
                }
            }
            
            public void EliminarEstudiantes(JTextField paramCodigo){
                setCodigo(Integer.parseInt(paramCodigo.getText()));
                
                CConexion objetoConexion = new CConexion();
                
                String consulta ="Delete from estudiante WHERE estudiante.idEstudiante=?;";
                
                try {
                    
                    CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
                    cs.setInt(1,getCodigo());
                    cs.execute();
                    
                    JOptionPane.showMessageDialog(null, "Se elimino correctamente el alumno");
                } catch (SQLException e) {
                    
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar, error:" +e.toString());
                }
            }
} 
