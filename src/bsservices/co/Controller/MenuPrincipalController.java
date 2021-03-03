/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Controller;

import bsservices.co.Model.Cliente;
import bsservicess.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author fred
 */
public class MenuPrincipalController {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Button idtrabalhadores;
    @FXML
    private Button idparceiros;
    @FXML
    private VBox vboxclientes;
    @FXML
    private Button cadastrar;
    @FXML
    private TextField pes;
    @FXML
    private Label time;
    @FXML
    private Label data;

    @FXML
    TableView<Cliente> tableClientes;

    private int hour, minute, second;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        horas_data();
        BuscarTodos();

    }

    public void horas_data() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();
            LocalDate date = LocalDate.now();
            data.setText(date.getDayOfMonth() + ":" + date.getMonth() + ":" + date.getYear());
            time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    @FXML
    private void actionclientes(ActionEvent event) {
        borderpane.setCenter(vboxclientes);
    }

    @FXML
    private void actiontrabalhadores(ActionEvent event) {

        loadpage(Paths.Trabalhadores);

    }

    @FXML
    private void actionparceiros(ActionEvent event) {
        loadpage(Paths.parceiros);

    }

    @FXML
    private void cadastro(ActionEvent event) throws IOException {
        mostrarJanela(Paths.CadastroCliente, "cadstro");
    }

    @FXML
    private void actionAdiministracao(ActionEvent event)  {
        loadpage(Paths.Admin);
    }

    public void loadpage(String pagina) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(pagina + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

    public void BuscarTodos() {

        ObservableList<Cliente> list = FXCollections.observableArrayList(Cliente.todos());

        tableClientes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("info"));
        tableClientes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableClientes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
        tableClientes.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableClientes.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("remover"));
        tableClientes.setItems(list);
    }

    private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(borderpane.getScene().getWindow());
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
            
            ObservableList<Cliente> oList = FXCollections.observableArrayList(Cliente.findByName(name));
            tableClientes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("info"));
            tableClientes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableClientes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
            tableClientes.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sexo"));
            tableClientes.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("remover"));
            tableClientes.setItems(oList);
        } else if (text.getLength() < 1) {
            BuscarTodos();
        }
    }

}
