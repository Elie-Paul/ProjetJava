package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Staff;
import cours.java.stic3.service.IActor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import stic3.sn.Utilitaires;

public class LockUser implements Initializable {

	@FXML
    private Button btnLock;

    @FXML
    private ComboBox<Staff> combStaff;

    @FXML
    private Button btnUnlock;
    
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;
    
    @FXML
    private TableView<Staff> table;

    @FXML
    private TableColumn<Staff, String> name1;

    @FXML
    private TableColumn<Staff, String> name2;

    @FXML
    private TableColumn<Staff, String> email;

    @FXML
    private TableColumn<Staff, Boolean> active;
    
    private ObservableList<Staff> staffs;
    private ObservableList<Staff> staffsCombo;

    @FXML
    void handleClick(ActionEvent event) {
    	if (event.getSource()==combStaff) {
    		String first = combStaff.getSelectionModel().getSelectedItem().getFirstName();
    		String last = combStaff.getSelectionModel().getSelectedItem().getLastName();
    		firstname.setText(first);
    		lastname.setText(last);
		}
    	
    	//BLOQUER UN UTILISATEUR
    	if(event.getSource()==btnLock) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
    			IActor actor = (IActor)registry.lookup("ActorDistant");
    			Staff s = new Staff();
    			Short id = combStaff.getSelectionModel().getSelectedItem().getStaffId();
    			Date dt = new Date();
    			s.setStaffId(id);
    			s.setActive(false);
    			s.setAddressId(combStaff.getSelectionModel().getSelectedItem().getAddressId());
    			s.setStoreId(combStaff.getSelectionModel().getSelectedItem().getStoreId());
    			s.setLastName(combStaff.getSelectionModel().getSelectedItem().getLastName());
    			s.setFirstName(combStaff.getSelectionModel().getSelectedItem().getFirstName());
    			s.setUsername(combStaff.getSelectionModel().getSelectedItem().getUsername());
    			s.setEmail(combStaff.getSelectionModel().getSelectedItem().getEmail());
    			s.setPassword(combStaff.getSelectionModel().getSelectedItem().getPassword());
    			s.setPicture(combStaff.getSelectionModel().getSelectedItem().getPicture());
    			s.setLastUpdate(dt);
    			if(combStaff.getSelectionModel().getSelectedItem().getActive()) {
    				if(actor.InsertOrUpdateObject(s)) {
    					Utilitaires.showMessage("Projet java", "Information", "User bloque");
        				firstname.setText("");
        				lastname.setText("");
    				}else {
    					Utilitaires.showMessage("Projet java", "Information", "Operation echouee");
        				firstname.setText("");
        				lastname.setText("");
    				}
    			}
    			else {
    				Alert dialogW = new Alert(AlertType.WARNING);
    				dialogW.setTitle("Projet java");
    				dialogW.setHeaderText(null); // No header
    				dialogW.setContentText("Attention !! Ce user est deja bloque");
    				dialogW.showAndWait();
    				firstname.setText("");
    				lastname.setText("");
    			}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	//DEBLOQUER USER
    	if(event.getSource()==btnUnlock) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
    			IActor actor = (IActor)registry.lookup("ActorDistant");
    			Staff s = new Staff();
    			Short id = combStaff.getSelectionModel().getSelectedItem().getStaffId();
    			Date dt = new Date();
    			s.setStaffId(id);
    			s.setActive(true);
    			s.setAddressId(combStaff.getSelectionModel().getSelectedItem().getAddressId());
    			s.setStoreId(combStaff.getSelectionModel().getSelectedItem().getStoreId());
    			s.setLastName(combStaff.getSelectionModel().getSelectedItem().getLastName());
    			s.setFirstName(combStaff.getSelectionModel().getSelectedItem().getFirstName());
    			s.setUsername(combStaff.getSelectionModel().getSelectedItem().getUsername());
    			s.setEmail(combStaff.getSelectionModel().getSelectedItem().getEmail());
    			s.setPassword(combStaff.getSelectionModel().getSelectedItem().getPassword());
    			s.setPicture(combStaff.getSelectionModel().getSelectedItem().getPicture());
    			s.setLastUpdate(dt);
    			if(!combStaff.getSelectionModel().getSelectedItem().getActive()) {
    				if(actor.InsertOrUpdateObject(s)) {
    					Utilitaires.showMessage("Projet java", "Information", "User debloque");
        				firstname.setText("");
        				lastname.setText("");
    				}else {
    					Utilitaires.showMessage("Projet java", "Information", "Operation echouee");
        				firstname.setText("");
        				lastname.setText("");
    				}
    			}
    			else {
    				Alert dialogW = new Alert(AlertType.WARNING);
    				dialogW.setTitle("Projet java");
    				dialogW.setHeaderText(null); // No header
    				dialogW.setContentText("Attention !! Ce user est deja debloque");
    				dialogW.showAndWait();
    				firstname.setText("");
    				lastname.setText("");
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
		staffs = FXCollections.observableArrayList();
		staffsCombo = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> obj = iactor.ListObject("Staff");
			for(Object a : obj) {
				staffs.add((Staff)a);
			}
			name1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			name2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			email.setCellValueFactory(new PropertyValueFactory<>("email"));
			active.setCellValueFactory(new PropertyValueFactory<>("active"));
			table.setItems(null);
			table.setItems(staffs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Staff");
			for(Object object: objects) {
				staffsCombo.add((Staff)object);
			}
			combStaff.setItems(staffsCombo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
