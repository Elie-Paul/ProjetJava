package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Address;
import cours.java.stic3.model.City;
import cours.java.stic3.model.Country;
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

public class AddAddressController implements Initializable {

    @FXML
    private TextField Addr1;

    @FXML
    private TextField Addr2;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField dist;

    @FXML
    private TextField code;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox<City> combCity;
    
    private ObservableList<City> cities;

    @FXML
    void onClick(ActionEvent event) {
    	Registry registry;
    	try {
    		registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			Address a = new Address();
			Short addressId = null;
			a.setAddressId(addressId);
			Date dt = new Date();
			a.setAddress(Addr1.getText());
			a.setAddress2(Addr2.getText());
			a.setCityId(combCity.getSelectionModel().getSelectedItem());
			a.setDistrict(dist.getText());
			a.setPhone(phone.getText());
			a.setPostalCode(code.getText());
			a.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(a)) {
				Utilitaires.showMessage("Projet java", "Information", "Address enregistrer");
				Addr1.setText("");
				Addr2.setText("");
				dist.setText("");
				phone.setText("");
				code.setText("");
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter une address");
				Addr1.setText("");
				Addr2.setText("");
				dist.setText("");
				phone.setText("");
				code.setText("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		cities = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("City");
			for(Object object: objects) {
				cities.add((City)object);
			}
			combCity.setItems(cities);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
