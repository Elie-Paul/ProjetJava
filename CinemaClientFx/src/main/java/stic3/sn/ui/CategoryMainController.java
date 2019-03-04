package stic3.sn.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import stic3.sn.LoadView;

public class CategoryMainController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnQuit;

    @FXML
    private Pane panConte;

    @FXML
    void onClick(ActionEvent event) throws IOException {
    	if(event.getSource()==btnAdd) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("AddCategory.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnUpdate) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("UpadateCategory.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnQuit) {
    		LoadView.showView("Menu principal", "Main.fxml", 1);
    	}
    }

}
