/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Controller;

import bsservices.co.Factory.Help;
import bsservices.co.Model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class CadastroFuncionarioController  {
    
    @FXML
    private TextField nome;
    @FXML
    private Label idnome;
    @FXML
    private TextField contacto;
    @FXML
    private TextField nr_bi;
    @FXML
    private TextField bairro;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private ComboBox sexo;
     @FXML
    private AnchorPane anchor;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        
        sexo.getItems().add("Masculino");
        sexo.getItems().add("Femenino");
        
    }    

    @FXML
    private void cadastrar(ActionEvent event) {
         boolean erro = false;
        if (nome.getText().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        } else if (bairro.getText().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        } else if (nr_bi.getText().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        } else if (dataNascimento.getEditor().getText().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        } else if (sexo.getSelectionModel().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        }
        if(!erro){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            Funcionario f = new Funcionario();
            f.setNome(nome.getText());
            f.setBairro(bairro.getText());
            f.setContacto(contacto.getText());
            f.setData_nascimento(Help.date_from_string(dataNascimento.getEditor().getText()));
            f.setSexo(sexo.getSelectionModel().getSelectedItem().toString());
            f.setEmail(email.getText());
            f.save();
            a.setContentText("Cadastro bem  succedido");
            a.show();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
         anchor.getScene().getWindow().hide();
    }
    
}
