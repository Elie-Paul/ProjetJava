package stic3.sn.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import stic3.sn.LoadView;

public class MainController {

    @FXML
    private Button btnUser;
    
    @FXML
    private Button btnMovie;
    
    @FXML
    private Button btnAct;

    @FXML
    private Button btnSto;

    @FXML
    private Button btnAddr;

    @FXML
    private Button btnPay;

    @FXML
    private Button btnRent;

    @FXML
    private Button btnCat;

    @FXML
    void onClick(ActionEvent event) {
    	//System.out.println("test");
    	
    	if (event.getSource()==btnUser) {
			LoadView.showView("Menu principal", "Users.fxml", 2);
		}
    	if (event.getSource()==btnAct) {
			LoadView.showView("Menu principal", "ActorMain.fxml", 1);
		}
    	if (event.getSource()==btnMovie) {
			LoadView.showView("Menu principal", "Films.fxml", 1);
		}
    	if (event.getSource()==btnSto) {
			LoadView.showView("Menu principal", "StoreMain.fxml", 1);
		}

    	if (event.getSource()==btnCat) {
			LoadView.showView("Menu principal", "CategoryMain.fxml", 1);
		}
    	if (event.getSource()==btnAddr) {
			LoadView.showView("Menu principal", "Address.fxml", 1);
		}
    	
    }

}