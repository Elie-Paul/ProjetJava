package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Category;
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

public class UpdateCategory implements Initializable {

    @FXML
    private ComboBox<Category> combCat;

    @FXML
    private TextField name;

    @FXML
    private Button btnUpdate;
    
    private ObservableList<Category> categories;

    @FXML
    void onClick(ActionEvent event) {
    	if(event.getSource()==combCat) {
    		String cat = combCat.getSelectionModel().getSelectedItem().getName();
    		name.setText(cat);
    	}
    	if (event.getSource()==btnUpdate) {
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry("localhost",1004);
    			IActor actor = (IActor)registry.lookup("ActorDistant");
    			Short id = combCat.getSelectionModel().getSelectedItem().getCategoryId();
    			Category a = new Category();
    			Date dt = new Date();
    			a.setCategoryId(id);
    			a.setName(name.getText());
    			a.setLastUpdate(dt);
    			
    			if(actor.InsertOrUpdateObject(a)) {
    				Utilitaires.showMessage("Projet java", "Information", "Category modifie");
    				name.setText("");
    			}
    			else {
    				Utilitaires.showMessage("Projet java", "Erreur", "impossible de modifier");
    				name.setText("");
    			}
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
    	categories = FXCollections.observableArrayList();
    	try {
    		registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Category");
			for(Object object: objects) {
				categories.add((Category)object);
			}
			combCat.setItems(categories);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
