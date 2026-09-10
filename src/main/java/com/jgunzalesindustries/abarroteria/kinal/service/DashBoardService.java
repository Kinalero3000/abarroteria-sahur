package main.java.com.jgunzalesindustries.abarroteria.kinal.service;

import java.math.BigDecimal;
import javafx.collections.ObservableList;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.Producto;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.ProductoRepository;

public class DashBoardService {
    private ProductoRepository productoRepository = new ProductoRepository();
    
    public DashBoardService(ProductoRepository productoRepository){
    this.productoRepository = productoRepository;
    
    };
    
    public ObservableList<Producto> findProducto(){
    if(productoRepository.findAll() == null){
    throw new RuntimeException("Sin productos");
    }else{
    return productoRepository.findAll();
    }  
    
    }
    
    public boolean deleteProducto(String idProducto){
    if(idProducto == null || idProducto.trim().isEmpty()){
    throw new IllegalArgumentException("El id del producto no es valido");
    }
    return productoRepository.deleteById(idProducto);
    
    }

    public boolean createProducto(Producto producto){
        validarProducto(producto);

        if(productoRepository.existsById(producto.getIdProducto())){
            throw new IllegalArgumentException("Ya existe un producto con ese id.");
        }

        return productoRepository.save(producto);
    }

    public boolean updateProducto(Producto producto){
        validarProducto(producto);

        if(!productoRepository.existsById(producto.getIdProducto())){
            throw new IllegalArgumentException("No existe un producto con ese id.");
        }

        return productoRepository.update(producto);
    }

    private void validarProducto(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("El producto no puede estar vacio.");
        }
        if(producto.getIdProducto() == null || producto.getIdProducto().trim().isEmpty()){
            throw new IllegalArgumentException("El id del producto no es valido.");
        }
        if(producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto no puede estar vacio.");
        }
        if(producto.getStock() < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        if(producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
    }
}
