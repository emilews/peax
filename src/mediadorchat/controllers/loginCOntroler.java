package mediadorchat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mediadorchat.Cliente;


public class loginCOntroler {
    @FXML
    public TextField nameInput;
    @FXML
    public PasswordField passInput;
    @FXML
    public static Label errorText;
    @FXML
    public Button loginButton;
    @FXML
    public void logIn(){
        if(!nameInput.getText().trim().isEmpty() || !passInput.getText().trim().isEmpty()){
            Cliente.Start(nameInput.getText(), passInput.getText());
        }
    }
    public void register(){
        if(!nameInput.getText().trim().isEmpty() || !passInput.getText().trim().isEmpty()){
        }
    }
}
