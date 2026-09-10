/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.jgunzalesindustries.abarroteria.kinal.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.jgunzalesindustries.abarroteria.kinal.config.DataBaseConnection;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.request.UserDTORequest;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.User;

/**
 *
 * @author angel
 */
public class UserRepository {
    
     public UserDTORequest findUser(LoginDTORequest request){
        
        String sql = 
                        
                            "select * from usuarios";
        
        try(PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareCall(sql)){
            
            ResultSet rs = pstm.executeQuery();
            ObservableList<User> userlist = FXCollections.observableArrayList(); 
            
            
            if(rs.next()){
                
                return new UserDTORequest (
                rs.getString("id_usuario"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                        rs.getInt("id_rol")
                );
                
            }
            
        }catch(SQLException e){
                    
                    System.out.println("Error al buscar el usuario.");
                    
                    }
        return null;
    }
    
     
    
    
     public boolean deleteUser(String idUser) {
    String sql = "DELETE FROM usuarios WHERE id_usuario = ?;";

    try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

        pstm.setString(1, idUser);
        
        int filasAfectadas = pstm.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Error al eliminar el usuario de la base de datos.", e);
    }
}    
     
       public boolean addUser(String idUser) {
    String sql = "INSERT INTO usuarios";

    try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

        pstm.setString(1, idUser);
        
        int filasAfectadas = pstm.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Error al aññadir un usuario", e);
    }
}
         public boolean changeUser(User user) {
    String sql = "Update user SERT id_usuario = ?, nombre = ?, apellido = ?, email = ?, id_rol = ?";

    try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

pstm.setString(1,user.getIdUser());
pstm.setString(2,user.getName());       
pstm.setString(3,user.getLastName());       
pstm.setString(4,user.getMail());
pstm.setInt(5,user.getIdRol());

        int filasAfectadas = pstm.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Error al cambiar el usuario", e);
    }
}   
}
