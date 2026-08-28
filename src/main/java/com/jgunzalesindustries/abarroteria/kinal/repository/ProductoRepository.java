package main.java.com.jgunzalesindustries.abarroteria.kinal.repository;

import javafx.collections.ObservableList;
import main.java.com.jgunzalesindustries.abarroteria.kinal.config.DataBaseConnection;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;

public class ProductoRepository {
    
    
    public ObservableList<Producto> findAll(){
 
        String sql = "select * from productos;";
 
        try(PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareCall(sql);){
 
            ResultSet rs = pstm.executeQuery();
 
            ObservableList<Producto> lista = FXCollections.observableArrayList();
 
            while(rs.next()){
 
                lista.add(new Producto(
 
                rs.getString("id_producto"),
 
                rs.getString("nombre_producto"),
 
                rs.getInt("stock"),
 
                rs.getBigDecimal("precio")        
 
                ));
 
            }
 
            return lista;
 
        }catch(SQLException e){
 
            throw new RuntimeException("Error en la consulta.");
 
        }
 
    }
 
}
 