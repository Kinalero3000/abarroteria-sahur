package main.java.com.jgunzalesindustries.abarroteria.kinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.request.RegistroDTORequest;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.response.RegistroDTOResponse;
import main.java.com.jgunzalesindustries.abarroteria.kinal.dto.response.RolDTOResponse;
import main.java.com.jgunzalesindustries.abarroteria.kinal.repository.UserRepository;
import main.java.com.jgunzalesindustries.abarroteria.kinal.service.RegistroService;
import main.java.com.jgunzalesindustries.abarroteria.kinal.util.SceneManager;

public class RegistroController implements Initializable{
     private final RegistroService registroService;
    private final SceneManager sceneManager;
    private final UserRepository usuarioRepository;
    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextField txtFieldApellido;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private TextField txtFieldConfirmarPassword;
    @FXML
    private ComboBox<RolDTOResponse> comboBoxRol;

     public RegistroController(RegistroService registroService, SceneManager sceneManager, UserRepository usuarioRepository) {
        this.registroService = registroService;
        this.sceneManager = sceneManager;
        this.usuarioRepository = usuarioRepository;
    }

  @Override
public void initialize(URL url, ResourceBundle rb) {
    comboBoxRol.setItems(usuarioRepository.findAllRoles());
}

    public void handleRegistro(ActionEvent event) {
        try {
            RegistroDTORequest request = new RegistroDTORequest(
                    txtFieldNombre.getText(),
                    txtFieldApellido.getText(),
                    txtFieldEmail.getText(),
                    txtFieldPassword.getText(),
                    txtFieldConfirmarPassword.getText(),
                    comboBoxRol.getValue() == null ? 0 : comboBoxRol.getValue().getIdRol()
            );

            RegistroDTOResponse response = registroService.registrar(request);

            sceneManager.showAlertInfo("Registro exitoso", "Bienvenido " + response.getNombre(),
                    "Tu cuenta fue creada correctamente.", AlertType.INFORMATION);
            handleLimpiarCampos();
            sceneManager.showLoginView();

        } catch (IllegalArgumentException e) {
            sceneManager.showAlertInfo("Datos inválidos", "Verifica los campos", e.getMessage(), AlertType.WARNING);
        } catch (Exception e) {
            sceneManager.showAlertInfo("Error", "Error al registrar", "No se pudo completar el registro.", AlertType.ERROR);
        }
    }
    
    public void handleIrALogin() throws Exception{

             sceneManager.showLoginView();
       
    
    };

    public void handleLimpiarCampos() {
        txtFieldNombre.clear();
        txtFieldApellido.clear();
        txtFieldEmail.clear();
        txtFieldPassword.clear();
        txtFieldConfirmarPassword.clear();
        comboBoxRol.setValue(null);
    }

}
