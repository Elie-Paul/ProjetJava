package stic3.sn.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import stic3.sn.LoadView;

public class AddressController {

    @FXML
    private Button btnCoun;

    @FXML
    private Button btnCity;

    @FXML
    private Button btnAddr;

    @FXML
    private Button btnQuit;

    @FXML
    private Pane panConte;

    @FXML
    void onClick(ActionEvent event) throws IOException {
    	if(event.getSource()==btnAddr) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("AddAddress.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnCity) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("City.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnCoun) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("Country.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnQuit) {
    		LoadView.showView("Menu principal", "Main.fxml", 1);
    	}
    }

}
