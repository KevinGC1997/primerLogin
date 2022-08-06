/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto5copia;


import interfaces.Login;
import interfaces.SeleccionPelicula;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanManuelMartinezMa
 */
public class ValidarLogin {
       private String URL = "jdbc:mysql://localhost:3306/netentertainment";
    private String USERNAME = "root";
    private String PASSWORD = "password";
    private String User;
    private String Clave;

    public ValidarLogin(String User, String Clave) {
        this.User = User;
        this.Clave = Clave;
    }
    
    public void validarLogin(){
       
        try {
            Connection con = DriverManager.getConnection(URL,
                    USERNAME, PASSWORD);
            Statement statement;
            statement = con.createStatement();
            
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from usuario where usr_login = '"+User+"' and usr_clave = '"+Clave+"' ");
            
            if(resultSet.next()) {
                SeleccionPelicula pantalla = new SeleccionPelicula(User);
                pantalla.setVisible(true);
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                Login login = new Login();
                login.setVisible(true);
            }
           
            resultSet.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
