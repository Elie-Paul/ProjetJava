package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Address;
import cours.java.stic3.model.City;
import cours.java.stic3.model.Store;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import stic3.sn.Utilitaires;

public class AddStoreController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<?> combStaff;

    @FXML
    private ComboBox<Address> comboAddr;
    
    private ObservableList<Address> addresses;

    @FXML
    void onClick(ActionEvent event) {
    	Registry registry;
    	try {
    		registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			Store s = new Store();
			Short storeId = null;
			s.setStoreId(storeId);
			s.setStoreName(name.getText());
			s.setAddressId(comboAddr.getSelectionModel().getSelectedItem());
			Date dt = new Date();			
			s.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(s)) {
				Utilitaires.showMessage("Projet java", "Information", "Store enregistrer");
				name.setText("");				
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter un store");
				name.setText("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		addresses = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Address");
			for(Object object: objects) {
				addresses.add((Address)object);
			}
			comboAddr.setItems(addresses);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
