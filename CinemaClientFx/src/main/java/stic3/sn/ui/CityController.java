package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
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

public class CityController implements Initializable {

    @FXML
    private ComboBox<Country> combCoun;

    @FXML
    private TextField name;

    @FXML
    private Button btnAdd;
    
    private ObservableList<Country> countries;

    @FXML
    void onClick(ActionEvent event) {
    	Registry registry;
    	try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			City c = new City();
			Short cityId = null;
			c.setCityId(cityId);
			Country country = combCoun.getSelectionModel().getSelectedItem();
			Date dt = new Date();
			c.setCountryId(country);
			c.setCity(name.getText());
			c.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(c)) {
				Utilitaires.showMessage("Projet java", "Information", "City enregistrer");
				name.setText("");
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter une city");
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
		countries = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Country");
			for(Object object: objects) {
				countries.add((Country)object);
			}
			combCoun.setItems(countries);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
