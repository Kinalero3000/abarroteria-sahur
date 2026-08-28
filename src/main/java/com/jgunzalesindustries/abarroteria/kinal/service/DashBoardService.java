package main.java.com.jgunzalesindustries.abarroteria.kinal.service;

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
}
