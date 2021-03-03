/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Controller;

import bsservices.co.Factory.Help;
import bsservices.co.Model.Cliente;
import java.io.File;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author fred
 */
public class CadastroClienteController {

    @FXML
    private Label idnome;
    @FXML
    private TextField nome;
    @FXML
    private TextField contacto;
    @FXML
    private TextField nr_bi;
    @FXML
    private ComboBox bairro;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private ComboBox sexo;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView img;

    public void initialize() {
        Rectangle cl = new Rectangle(img.getFitWidth(), img.getFitHeight());
        cl.setArcHeight(100);
        cl.setArcWidth(100);
        cl.setStyle("-fx-border-color: blue;-fx-border-width:2px;");
        img.setClip(cl);
        
        transition();
        sexo.getItems().add("Masculino");
        sexo.getItems().add("Femenino");
        bairro.getItems().add("Magoanine");
        bairro.getItems().add("Mahotas");
        bairro.getItems().add("CMC");
        bairro.getItems().add("Malhazine");
        bairro.getItems().add("Albazine");
        bairro.getItems().add("Outro");

    }

    @FXML
    private void cadastrar(ActionEvent event) {
        boolean erro = false;
        if (nome.getText().isEmpty()) {
            //mensagem de eroo  
            erro = true;
        } else if (bairro.getSelectionModel().isEmpty()) {
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
        if (!erro) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            Cliente c = new Cliente();
            c.setNome(nome.getText());
            c.setBairro(bairro.getSelectionModel().getSelectedItem().toString());
            c.setContacto(contacto.getText());
            System.out.println(dataNascimento.getEditor().getText());
            System.out.println(Help.date_from_string(dataNascimento.getEditor().getText()));
            c.setData_nascimento(Help.date_from_string(dataNascimento.getEditor().getText()));
            c.setSexo(sexo.getSelectionModel().getSelectedItem().toString());
            c.save();
            a.setContentText("Cadastro bem  succedido");
            a.show();
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        anchor.getScene().getWindow().hide();
    }

    public void transition() {
        nome.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    TranslateTransition translateTransition
                            = new TranslateTransition(Duration.millis(1000), idnome);
                    translateTransition.setFromY(0);
                    translateTransition.setToY(50);
                    translateTransition.setCycleCount(1);
                    translateTransition.play();
                    idnome.setVisible(true);
                } else {
                    TranslateTransition translateTransition
                            = new TranslateTransition(Duration.millis(1000), idnome);
                    translateTransition.setFromY(50);
                    translateTransition.setToY(0);
                    translateTransition.setCycleCount(1);
                    translateTransition.play();
                    idnome.setVisible(false);
                }
            }
        });
    }

    @FXML
    private void carregarFoto(MouseEvent event) {

        FileChooser file = new FileChooser();

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("imagens", "*.png", "*.jpeg");
        file.getExtensionFilters().add(extFilter);
        File files = file.showOpenDialog(new Stage());
       

        if (file != null) {
             img.setImage(new Image("file:///"+files.getAbsolutePath()));
             
        }
    }

}
