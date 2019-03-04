package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Store;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListStoreController implements Initializable {

    @FXML
    private TableView<Store> table;

    @FXML
    private TableColumn<Store, Short> id;

    @FXML
    private TableColumn<Store, String> name;

    @FXML
    private TableColumn<Store, Short> manager;

    @FXML
    private TableColumn<Store, Short> address;
    
    private ObservableList<Store> stores;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		stores = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> obj = iactor.ListObject("Store");
			for(Object a : obj) {
				stores.add((Store)a);
			}
			id.setCellValueFactory(new PropertyValueFactory<>("storeId"));
			name.setCellValueFactory(new PropertyValueFactory<>("storeName"));
			manager.setCellValueFactory(new PropertyValueFactory<>("managerStaffId"));
			address.setCellValueFactory(new PropertyValueFactory<>("addressId"));
			table.setItems(null);
			table.setItems(stores);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
