package main.java.com.jgunzalesindustries.abarroteria.kinal.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.jgunzalesindustries.abarroteria.kinal.controller.DashBoardController;
import main.java.com.jgunzalesindustries.abarroteria.kinal.controller.LoginController;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.AuthRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.AuthService;

public class SceneManager {
            
    //atributos
    private final Stage stage; 
        
    //constructor
    public SceneManager(Stage stage){
            this.stage = stage;
        }
    
    //métodos
    public void showLoginView() throws Exception{
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login-view.fxml"));
 
            
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
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            
    }
    
    //dashboard stage
    public void showDashBoardView() throws Exception {
    FXMLLoader dashBoardLoader = new FXMLLoader(getClass().getResource("/main/resources/view/dashboard-view.fxml"));

    dashBoardLoader.setControllerFactory(clazz -> {
        if (clazz == DashBoardController.class) {
            // Instancia aquí las dependencias que necesite tu controlador
            // Ejemplo: DashboardRepository repo = new DashboardRepository();
            // Ejemplo: DashboardService service = new DashboardService(repo);
            return new DashBoardController(/* pasa tus servicios o 'this' aquí */);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el controlador: " + e.getMessage(), e);
        }
    });

    Parent root = dashBoardLoader.load();
    Scene scene = new Scene(root, 600, 400);
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
