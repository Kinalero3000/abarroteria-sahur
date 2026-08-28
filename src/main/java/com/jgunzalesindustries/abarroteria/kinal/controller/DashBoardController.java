package main.java.com.jgunzalesindustries.abarroteria.kinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.Producto;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.ProductoRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.DashBoardService;
import main.java.com.jgunzalesindustries.abarroteria.kinal.util.SceneManager;


public class DashBoardController implements Initializable {
    private ProductoRepository productoRepository;
    private DashBoardService dashBoardService = new DashBoardService(productoRepository);
    private SceneManager sceneManager;
    @FXML
    private TableView<Producto> tableProducto;
    @FXML
    private TableColumn<Producto, String> tableColumnIdProducto;
    @FXML
    private TableColumn<Producto, String> tableColumnNombreProducto;
    @FXML
    private TableColumn<Producto, Integer> tableColumnStock;
    @FXML
    private TableColumn<Producto, BigDecimal> tableColumnPrecio;

    public DashBoardController(DashBoardService dashBoardService, SceneManager sceneManager) {
        this.dashBoardService = dashBoardService;
        this.sceneManager = sceneManager;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        handleLoadDataTableView();
    }
    
    private void handleLoadDataTableView(){
        tableColumnIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        tableColumnNombreProducto.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        tableColumnStock.setCellValueFactory(new PropertyValueFactory("stock"));
        tableColumnPrecio.setCellValueFactory(new PropertyValueFactory("Precio"));
        tableProducto.setItems(dashBoardService.findProducto());
        
    
    }
    
}
