package stic3.sn.ui;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Category;
import cours.java.stic3.service.IActor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import stic3.sn.Utilitaires;

public class AddCategoryController {

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
			Category c = new Category();
			Short categoryId = null;
			c.setCategoryId(categoryId);
			Date dt = new Date();
			c.setName(name.getText());
			c.setLastUpdate(dt);
			
			if(actor.InsertOrUpdateObject(c)) {
				Utilitaires.showMessage("Projet java", "Information", "Category enregistrer");
				name.setText("");
			}
			else {
				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter une categorie");
				name.setText("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}
