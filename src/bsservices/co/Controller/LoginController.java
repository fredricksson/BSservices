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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fred
 */
public class LoginController {

    @FXML
    private AnchorPane anchor;
    private double xOffset;
    private double yOffset;

    @FXML
    public void initialize() {
        moverTela();
    }

    @FXML
    private void entrar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.MenuPrincipal));
        Parent home_page_parent = loader.load();

        MenuPrincipalController controller = loader.getController();

        Scene home_page_scene = new Scene(home_page_parent);

        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        main_stage.close();
        main_stage.setScene(home_page_scene);
        //   main_stage.setTitle(SoftwareSpecifications.getName());
        // main_stage.setResizable(true);
        // main_stage.setMaximized(true);

        //  main_stage.show();
        Stage s = new Stage();
        s.setScene(home_page_scene);
        s.show();

    }

    @FXML
    private void sair(MouseEvent event) {
        System.exit(0);
    }

    public void moverTela() {
        anchor.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        anchor.setOnMouseDragged((MouseEvent event) -> {
            Stage primaryStage = (Stage) anchor.getScene().getWindow();
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
}
