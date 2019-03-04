package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Customer;
import cours.java.stic3.model.Inventory;
import cours.java.stic3.model.Rental;
import cours.java.stic3.model.Staff;
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

public class RentalController implements Initializable{

    @FXML
    private ComboBox<Inventory> comboInv;

    @FXML
    private ComboBox<Customer> comboCust;

    @FXML
    private ComboBox<Staff> comboUser;

    @FXML
    private Button btnRent;

    private ObservableList<Inventory> inven;
    private ObservableList<Customer> cost;
    private ObservableList<Staff> staffs;

    @FXML
    void onClick(ActionEvent event) {
    	Registry registry;
    	try {
    		registry = LocateRegistry.getRegistry("localhost",1004);
			IActor actor = (IActor)registry.lookup("ActorDistant");
			Rental r = new Rental();
			int rentalId = 1;
			r.setRentalId(rentalId);
			r.setCustomerId(comboCust.getSelectionModel().getSelectedItem());
			r.setInventoryId(comboInv.getSelectionModel().getSelectedItem());
			r.setStaffId(comboUser.getSelectionModel().getSelectedItem());
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			//Date rentalDate = formatter.parse(rentaldate.getText());
			Date rentalDate = new Date();
			r.setRentalDate(rentalDate);
			//Date returnDate = formatter.parse(datereturn.getText());
			Date returnDate = new Date();
			r.setReturnDate(returnDate);
			Date d = new Date();
			r.setLastUpdate(d);
			
			if(actor.InsertOrUpdateObject(r)) {
				Utilitaires.showMessage("Projet java", "Information", "Vous venez de louer un film");
				
			}
			else {
				Utilitaires.showMessage("Projet java", "Information", "Location impossible");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		inven = FXCollections.observableArrayList();
		cost = FXCollections.observableArrayList();
		staffs = FXCollections.observableArrayList();
		Registry registry;
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Staff");
			for(Object object: objects) {
				staffs.add((Staff)object);
			}
			comboUser.setItems(staffs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Inventory");
			for(Object object: objects) {
				inven.add((Inventory)object);
			}
			comboInv.setItems(inven);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Customer");
			for(Object object: objects) {
				cost.add((Customer)object);
			}
			comboCust.setItems(cost);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
