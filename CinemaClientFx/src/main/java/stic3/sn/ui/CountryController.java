package stic3.sn.ui;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Country;
import cours.java.stic3.service.IActor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import stic3.sn.Utilitaires;

public class CountryController {

    @FXML
    private TextField name;

    @FXML
    private Button btnAdd;

    @FXML
    void addActor(ActionEvent event) {
    	Registry registry;
    	try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			Country c = new Country();
			Short countryId = null;
			c.setCountryId(countryId);
			Date dt = new Date();
			c.setCountry(name.getText());
			c.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(c)) {
				Utilitaires.showMessage("Projet java", "Information", "Country enregistrer");
				name.setText("");
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter un country");
				name.setText("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}
