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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListActorController implements Initializable {
	
	 @FXML
	 private TableView<Actor> table;
	 
	 @FXML
	 private TableColumn<Actor, Short> id;

    @FXML
    private TableColumn<Actor, String> name1;

    @FXML
    private TableColumn<Actor, String> name2;

    @FXML
    private TableColumn<Actor, Date> last;
    
    private ObservableList<Actor> actors;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		actors = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> obj = iactor.ListObject("Actor");
			for(Object a : obj) {
				actors.add((Actor)a);
			}
			id.setCellValueFactory(new PropertyValueFactory<>("actorId"));
			name1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			name2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			last.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
			table.setItems(null);
			table.setItems(actors);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
