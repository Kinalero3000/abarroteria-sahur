package main.java.com.jgunzalesindustries.abarroteria.kinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    
    public void handleDeleteProducto(ActionEvent event){
    Producto productoSeleccionado = tableProducto.getSelectionModel().getSelectedItem();

    if (productoSeleccionado == null) {
        sceneManager.showAlertInfo(
            "Selección requerida", 
            "Advertencia", 
            "Por favor, selecciona un producto de la tabla antes de continuar.", 
            AlertType.WARNING
        );
        return;
    }
    Alert confirmacion = new Alert(AlertType.CONFIRMATION);
    confirmacion.setHeaderText("¿Eliminar producto?");
    confirmacion.setTitle("Confirmar acción");
    confirmacion.setContentText("¿Estás seguro de eliminar: " + productoSeleccionado.getNombreProducto() + "?");
    
    Optional<ButtonType> respuesta = confirmacion.showAndWait();

    if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
        boolean eliminado = dashBoardService.deleteProducto(productoSeleccionado.getIdProducto());

        if (eliminado) {
            tableProducto.getItems().remove(productoSeleccionado);
            sceneManager.showAlertInfo("Éxito", "Operación exitosa", "El producto fue eliminado correctamente.", AlertType.INFORMATION);
        } else {
            sceneManager.showAlertInfo("Error", "Error al eliminar", "No se pudo eliminar el producto de la base de datos.", AlertType.ERROR);
        }
    }
    
    }
}
