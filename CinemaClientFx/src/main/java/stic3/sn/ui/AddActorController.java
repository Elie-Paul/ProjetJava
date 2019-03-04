package stic3.sn.ui;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import cours.java.stic3.model.Actor;
import cours.java.stic3.service.IActor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import stic3.sn.Utilitaires;

public class AddActorController {

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private Button btnAdd;

    @FXML
    void addActor(ActionEvent event) {
    	Registry registry;
    	try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			Actor a = new Actor();
			Short actorId = null;
			a.setActorId(actorId);
			Date dt = new Date();
			a.setFirstName(name1.getText());
			a.setLastName(name2.getText());
			a.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(a)) {
				Utilitaires.showMessage("Projet java", "Information", "Acteur enregistrer");
				name1.setText("");
				name2.setText("");
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter un acteur");
				name1.setText("");
				name2.setText("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}