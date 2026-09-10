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
    
    public boolean deleteById(String idProducto) {
    String sql = "DELETE FROM productos WHERE id_producto = ?;";

    try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

        pstm.setString(1, idProducto);
        
        int filasAfectadas = pstm.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Error al eliminar el producto de la base de datos.", e);
    }
}    

    public boolean save(Producto producto) {
        String sql = "INSERT INTO productos (id_producto, nombre_producto, stock, precio) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, producto.getIdProducto());
            pstm.setString(2, producto.getNombreProducto());
            pstm.setInt(3, producto.getStock());
            pstm.setBigDecimal(4, producto.getPrecio());

            int filasAfectadas = pstm.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el producto en la base de datos.", e);
        }
    }

    public boolean update(Producto producto) {
        String sql = "UPDATE productos SET nombre_producto = ?, stock = ?, precio = ? WHERE id_producto = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, producto.getNombreProducto());
            pstm.setInt(2, producto.getStock());
            pstm.setBigDecimal(3, producto.getPrecio());
            pstm.setString(4, producto.getIdProducto());

            int filasAfectadas = pstm.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el producto en la base de datos.", e);
        }
    }

    public boolean existsById(String idProducto) {
        String sql = "SELECT id_producto FROM productos WHERE id_producto = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, idProducto);
            ResultSet rs = pstm.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al validar la existencia del producto.", e);
        }
    }

}
 