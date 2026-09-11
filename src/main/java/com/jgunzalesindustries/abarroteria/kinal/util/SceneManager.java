package main.java.com.jgunzalesindustries.abarroteria.kinal.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.jgunzalesindustries.abarroteria.kinal.controller.DashBoardController;
import main.java.com.jgunzalesindustries.abarroteria.kinal.controller.LoginController;
import main.java.com.jgunzalesindustries.abarroteria.kinal.controller.RegistroController;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.AuthRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.ProductoRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.UserRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.AuthService;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.DashBoardService;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.RegistroService;

public class SceneManager {
            
    //atributos
    private final Stage stage; 
    private final String FXML_PATH;
    private  ProductoRepository productoRepository = new ProductoRepository();
    //constructor
    public SceneManager(Stage stage){
            this.stage = stage;
            FXML_PATH = "/main/resources/view";
        }
    
    //métodos
    public void showLoginView() throws Exception{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "/login-view.fxml"));
 
            
            loader.setControllerFactory(
            clazz ->{
                if(clazz == LoginController.class){
                    AuthRepository authRepository = new AuthRepository();
                    AuthService authService = new AuthService(authRepository);
                    return new LoginController(authService, this);
                }
                try{
                return clazz.getDeclaredConstructor().newInstance();
                }catch(Exception e){
                throw new RuntimeException("Error al crear el constructor" + e.getMessage());
                }
            });
              
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            
    }
    
    //dashboard stage
    public void showDashBoardView() throws Exception {
    FXMLLoader dashBoardLoader = new FXMLLoader(getClass().getResource( FXML_PATH + "/product-view.fxml"));

    dashBoardLoader.setControllerFactory(clazz -> {
        if (clazz == DashBoardController.class) {
            // Instancia aquí las dependencias que necesite tu controlador
            // Ejemplo: DashboardRepository repo = new DashboardRepository();
            // Ejemplo: DashboardService service = new DashboardService(repo);
           
            DashBoardService service = new DashBoardService(productoRepository);
            return new DashBoardController(service, this);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el constructor: " + e.getMessage(), e);
        }
    });

    Parent root = dashBoardLoader.load();
    Scene scene = new Scene(root, 1280, 720);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
}
    
    //registro stage
    public void showRegistroView() throws Exception {
        FXMLLoader registroLoader = new FXMLLoader(getClass().getResource(FXML_PATH + "/register-view.fxml"));

        registroLoader.setControllerFactory(clazz -> {
            if (clazz == RegistroController.class) {
                UserRepository usuarioRepository = new UserRepository();
                RegistroService registroService = new RegistroService(usuarioRepository);
                return new RegistroController(registroService, this, usuarioRepository);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el constructor: " + e.getMessage(), e);
            }
        });

        Parent root = registroLoader.load();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    
    
    
    //alerta modal estandar reutilizable.
    public void showAlertInfo(String head, String title, String content, AlertType type){
        Alert alert = new Alert(type);
        alert.initOwner(this.stage);
        alert.setHeaderText(head);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    
    }
    
}
