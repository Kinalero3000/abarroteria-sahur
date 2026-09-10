/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.jgunzalesindustries.abarroteria.kinal.controller;

/**
 *
 * @author informatica
 */
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
import main.java.com.jgunzalesindustries.abarroteria.kinal.model.User;
public class DashBoardController {
    
    public void handleDeleteUser (ActionEvent event){
    
    User userSelect = tableUser.getSelectionModel().getSelectedItem();
    
    if (userSelect == null){
    sceneManager.showAlertInfo(
    "seleccion necesaria"
    "precaucion"
    "seleccione un user de la tabla pasa seguir.",
    AlertType.WARNING)
    }
    }
    
    
}
