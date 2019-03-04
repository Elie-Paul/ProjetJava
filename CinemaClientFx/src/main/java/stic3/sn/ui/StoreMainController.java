package stic3.sn.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import stic3.sn.LoadView;

public class StoreMainController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnList;

    @FXML
    private Button btnQuit;

    @FXML
    private Pane panConte;

    @FXML
    void onClick(ActionEvent event) throws IOException {
    	if(event.getSource()==btnAdd) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("AddStore.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnList) {
    		panConte.getChildren().clear();
    		panConte.getChildren().add(FXMLLoader.load(getClass().getResource("ListStore.fxml")));
    		panConte.toFront();
    	}
    	if(event.getSource()==btnQuit) {
    		LoadView.showView("Menu principal", "Main.fxml", 1);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
