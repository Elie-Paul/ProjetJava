package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javassist.CtField.Initializer;
import stic3.sn.Utilitaires;

public class UpdateActor implements Initializable {

    @FXML
    private ComboBox<Actor> combAct;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private Button btnUpdate;
    
    private ObservableList<Actor> actors;

    @FXML
    void onClick(ActionEvent event) {
    	if(event.getSource()==combAct) {
    		String first = combAct.getSelectionModel().getSelectedItem().getFirstName();
    		String last = combAct.getSelectionModel().getSelectedItem().getLastName();
    		name1.setText(first);
    		name2.setText(last);
    	}
    	if(event.getSource()==btnUpdate) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
    			IActor actor = (IActor)registry.lookup("ActorDistant");
    			Short id = combAct.getSelectionModel().getSelectedItem().getActorId();
    			Actor a = new Actor();
    			Date dt = new Date();
    			a.setActorId(id);
    			a.setFirstName(name1.getText());
    			a.setLastName(name2.getText());
    			a.setLastUpdate(dt);
    			
    			if(actor.InsertOrUpdateObject(a)) {
    				Utilitaires.showMessage("Projet java", "Information", "Acteur modifie");
    				name1.setText("");
    				name2.setText("");
    			}
    			else {
    				Utilitaires.showMessage("Projet java", "Erreur", "impossible de modifier");
    				name1.setText("");
    				name2.setText("");
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
		actors = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Actor");
			for(Object object: objects) {
				actors.add((Actor)object);
			}
			combAct.setItems(actors);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
