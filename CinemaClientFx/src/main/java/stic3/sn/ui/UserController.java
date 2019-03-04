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

public class UserController implements Initializable {

	@FXML
    private Button btnAdd;
	
	@FXML
    private Button btnlock;

    @FXML
    private Button btnUnlock;
	
    @FXML
    private Pane pan;

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
    	if(event.getSource()==btnAdd) {
    		pan.getChildren().clear();
    		pan.getChildren().add(FXMLLoader.load(getClass().getResource("AddUser.fxml")));
    		pan.toFront();
    	}
    	if(event.getSource()==btnlock) {
    		pan.getChildren().clear();
    		pan.getChildren().add(FXMLLoader.load(getClass().getResource("LockUser.fxml")));
    		pan.toFront();
    	}
    }

    
    @FXML
    void quit(ActionEvent event) {
    	LoadView.showView("Menu principal", "Main.fxml", 1);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}


