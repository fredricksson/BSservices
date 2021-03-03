/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Controller;

import bsservicess.Paths;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class AdministracaoController  {
    @FXML
    private VBox vbosAdmin;
    @FXML
    private Button pesquisar;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void initialize() {
        // TODO
    }    

    @FXML
    private void mudarSenha(ActionEvent event) throws IOException {
        
        mostrarJanela(Paths.mudarSenha, "Alterar senha");
        
    }
    
    private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(vbosAdmin.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
     
    }
    
}
