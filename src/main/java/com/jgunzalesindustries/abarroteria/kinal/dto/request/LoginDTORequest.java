package main.java.com.jgunzalesindustries.abarroteria.kinal.dto.request;

import javafx.scene.control.TextField;

public class LoginDTORequest {
    //atributos
    private String email;
    private String password;
    //constructor
        public LoginDTORequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDTORequest(TextField txtFieldEmail, TextField txtFieldPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    //métodos

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
