/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Controller;

import bsservices.co.Model.Funcionario;
import bsservicess.Paths;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class TrabalhadoresController {

    @FXML
    private VBox vbox;
    @FXML
    private TextField pes;
    @FXML
    private Button pesquisar;
    @FXML
    private TableView<Funcionario> tableTrabalhadores;

    public void initialize() {

        // TODO
        BuscarTodos();

    }

    @FXML
    private void cadastrarFuncionario(ActionEvent event) throws IOException {

        mostrarJanela(Paths.CadastroFuncionario, "cadastro");
    }

    public void BuscarTodos() {

        ObservableList<Funcionario> list = FXCollections.observableArrayList(Funcionario.todos());
        tableTrabalhadores.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("info"));
        tableTrabalhadores.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableTrabalhadores.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
        tableTrabalhadores.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableTrabalhadores.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("remover"));
        tableTrabalhadores.setItems(list);
    }

    private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(vbox.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
        stage.setOnHiding((WindowEvent event) -> {
            BuscarTodos();
        });
    }

 

    @FXML
    private void pesquisar(KeyEvent event) {
        TextField text = (TextField) event.getSource();
        if (text.getLength() > 1) {
            String name = pes.getText();

            ObservableList<Funcionario> oList = FXCollections.observableArrayList(Funcionario.findByName(name));
            tableTrabalhadores.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("info"));
            tableTrabalhadores.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableTrabalhadores.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
            tableTrabalhadores.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sexo"));
            tableTrabalhadores.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("remover"));
            tableTrabalhadores.setItems(oList);
        } else if (text.getLength() < 1) {
            BuscarTodos();
        }
    }

}
