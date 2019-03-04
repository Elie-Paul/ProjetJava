package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Category;
import cours.java.stic3.model.Film;
import cours.java.stic3.model.FilmActor;
import cours.java.stic3.model.FilmActorPK;
import cours.java.stic3.model.FilmCategory;
import cours.java.stic3.model.FilmCategoryPK;
import cours.java.stic3.model.Inventory;
import cours.java.stic3.model.Store;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stic3.sn.LoadView;
import stic3.sn.Utilitaires;
import stic3.sn.test.ActorFilmList;

public class GestionFilmController implements Initializable{

    @FXML
    private ComboBox<Actor> comboAct;

    @FXML
    private ComboBox<Category> comboCat;
    
    @FXML
    private ComboBox<Film> comboFilm;

    @FXML
    private ComboBox<Store> comboSto;
    
    @FXML
    private ComboBox<Film> comboFilm1;

    @FXML
    private ComboBox<Film> comboFilm2;
    
    @FXML
    private Button btnAct;

    @FXML
    private Button btnCat;

    @FXML
    private Button btnSt;

    @FXML
    private Button btnReturn;
    
    private ObservableList<Actor> actors;
    private ObservableList<Category> categories;
    private ObservableList<Store> stores;
    private ObservableList<Film> films;
    private ObservableList<Film> films1;
    private ObservableList<Film> films2;
    
    
    
    @FXML
    void onClick(ActionEvent event) {
    	if(event.getSource()==btnReturn) {
    		LoadView.showView("Menu principal", "Main.fxml", 1);
    	}
    	
    	if(event.getSource()==btnAct) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
				IActor actor = (IActor)registry.lookup("ActorDistant");
				FilmActor fa = new FilmActor();
				fa.setActor(comboAct.getSelectionModel().getSelectedItem());
				fa.setFilm(comboFilm.getSelectionModel().getSelectedItem());
				Short filmId = Short.valueOf(comboFilm.getSelectionModel().getSelectedItem().getFilmId());
				Short actorId = Short.valueOf(comboAct.getSelectionModel().getSelectedItem().getActorId());	
				fa.setFilmActorPK(new FilmActorPK(actorId, filmId));
				Date dt = new Date();
				fa.setLastUpdate(dt);
				
				if(actor.InsertOrUpdateObject(fa)) {
					Utilitaires.showMessage("Projet java", "Information", "Acteur "+comboAct.getSelectionModel().getSelectedItem().getFirstName()
							+" est lier au film "+comboFilm.getSelectionModel().getSelectedItem().getTitle());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	if(event.getSource()==btnCat) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
				IActor actor = (IActor)registry.lookup("ActorDistant");
				FilmCategory fa = new FilmCategory();
				fa.setCategory(comboCat.getSelectionModel().getSelectedItem());
				fa.setFilm(comboFilm1.getSelectionModel().getSelectedItem());
				Short filmId = Short.valueOf(comboFilm1.getSelectionModel().getSelectedItem().getFilmId());
				Short categoryId = Short.valueOf(comboCat.getSelectionModel().getSelectedItem().getCategoryId());
				fa.setFilmCategoryPK(new FilmCategoryPK(filmId, categoryId));
				Date dt = new Date();
				fa.setLastUpdate(dt);
				
				if(actor.InsertOrUpdateObject(fa)) {
					Utilitaires.showMessage("Projet java", "Information", "Categorie "+comboCat.getSelectionModel().getSelectedItem().getName()
							+" est lier au film "+comboFilm1.getSelectionModel().getSelectedItem().getTitle());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	if(event.getSource()==btnSt) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
				IActor actor = (IActor)registry.lookup("ActorDistant");
				Inventory fa = new Inventory();
				fa.setStoreId(comboSto.getSelectionModel().getSelectedItem());
				fa.setFilmId(comboFilm2.getSelectionModel().getSelectedItem());
				int inventoryId = 1; 
				fa.setInventoryId(inventoryId);
				Date dt = new Date();
				fa.setLastUpdate(dt);
				
				if(actor.InsertOrUpdateObject(fa)) {
					Utilitaires.showMessage("Projet java", "Information", "Store "+comboSto.getSelectionModel().getSelectedItem().getStoreName()
							+" est lier au film "+comboFilm.getSelectionModel().getSelectedItem().getTitle());
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
		categories = FXCollections.observableArrayList();
		stores = FXCollections.observableArrayList();
		films = FXCollections.observableArrayList();
		films1 = FXCollections.observableArrayList();
		films2 = FXCollections.observableArrayList();

		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Actor");
			for(Object object: objects) {
				actors.add((Actor)object);
			}
			comboAct.setItems(actors);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Category");
			for(Object object: objects) {
				categories.add((Category)object);
			}
			comboCat.setItems(categories);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Store");
			for(Object object: actor) {
				stores.add((Store)object);
			}
			comboSto.setItems(stores);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Film");
			for(Object object: actor) {
				films.add((Film)object);
			}
			comboFilm.setItems(films);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Film");
			for(Object object: actor) {
				films1.add((Film)object);
			}
			comboFilm1.setItems(films1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Film");
			for(Object object: actor) {
				films2.add((Film)object);
			}
			comboFilm2.setItems(films2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
