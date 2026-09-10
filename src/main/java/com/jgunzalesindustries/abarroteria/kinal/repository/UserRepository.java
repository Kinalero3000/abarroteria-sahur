/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.jgunzalesindustries.abarroteria.kinal.repository;

import java.util.UUID;
import main.java.com.jgunzalesindustries.abarroteria.kinal.config.DataBaseConnection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.Usuario;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.response.RolDTOResponse;


public class UserRepository {
     public boolean existsByEmail(String email) {
        String sql = "SELECT id_usuario FROM usuarios WHERE email = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al validar el email.", e);
        }
    }

    public boolean save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, apellido, email, contrasena_hash, id_rol) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, usuario.getIdUsuario());
            pstm.setString(2, usuario.getNombre());
            pstm.setString(3, usuario.getApellido());
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getContrasenaHash());
            pstm.setInt(6, usuario.getIdRol());

            int filasAfectadas = pstm.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el usuario en la base de datos.", e);
        }
    }

    public String generarIdUsuario() {
        return UUID.randomUUID().toString();
    }

 
    public ObservableList<RolDTOResponse> findAllRoles() {
    String sql = "SELECT id_rol, nombre_rol FROM roles;";

    try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

        ResultSet rs = pstm.executeQuery();
        ObservableList<RolDTOResponse> lista = FXCollections.observableArrayList();

        while (rs.next()) {
            lista.add(new RolDTOResponse(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol")
            ));
        }

        return lista;

    } catch (SQLException e) {
        throw new RuntimeException("Error al consultar los roles.", e);
    }
}
}
