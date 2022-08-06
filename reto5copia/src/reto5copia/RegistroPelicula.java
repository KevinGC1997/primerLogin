/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto5copia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author kguer
 */
public class RegistroPelicula {
     private String URL = "jdbc:mysql://localhost:3306/netentertainment";
    private String USERNAME = "root";
    private String PASSWORD = "Auto1921*";
    private String User;
    private String Contenido;

    public RegistroPelicula(String User, String Contenido) {
        this.User = User;
        this.Contenido = Contenido;
    }
    
    public void Insert(){
        try {
            Connection con = DriverManager.getConnection(URL,
                            USERNAME, PASSWORD);
            
            Statement statement1;
            statement1 = con.createStatement();
            ResultSet resultSet;
            resultSet = statement1.executeQuery(
                    "select con_id from contenido where con_titulo = '"+Contenido+"'");
            
            int idContenido;
            while (resultSet.next()) {
                idContenido = resultSet.getInt("con_id");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String fecha = dtf.format(LocalDateTime.now());
                String sql = "INSERT INTO transmision(tra_user, tra_con, tra_fecha) VALUES(?,?,?)";
                PreparedStatement statement2 = con.prepareStatement(sql);
                statement2.setString(1, User);
                statement2.setInt(2, idContenido);
                statement2.setString(3, fecha);

                int rowsInserted = statement2.executeUpdate();
                if(rowsInserted > 0){
                    JOptionPane.showMessageDialog(null, "Registro éxitoso");
                }
            }
            resultSet.close(); 
            con.close();

            }catch (SQLException e) {
                e.printStackTrace();
            }

    }
    
}
