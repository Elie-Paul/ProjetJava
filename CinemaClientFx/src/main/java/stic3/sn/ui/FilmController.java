package stic3.sn.ui;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Film;
import cours.java.stic3.model.Language;
import cours.java.stic3.service.IActor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import stic3.sn.LoadView;
import stic3.sn.Utilitaires;

public class FilmController implements Initializable {

    @FXML
    private ComboBox<Language> combLang;

    @FXML
    private ComboBox<Language> combOrLang;

    @FXML
    private TextField title;

    @FXML
    private TextField desc;

    @FXML
    private DatePicker year;

    @FXML
    private TextField rentalDur;

    @FXML
    private TextField rentalRat;

    @FXML
    private TextField length;

    @FXML
    private TextField cost;

    @FXML
    private Button btnAdd;
    
    private ObservableList<Language> lang;
    private ObservableList<Language> Orlang;


    @FXML
    void onClick(ActionEvent event) {
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry("localhost",1004);
				IActor actor = (IActor)registry.lookup("ActorDistant");
				Film f = new Film();
				Short filmId = null;
				f.setFilmId(filmId);
				Date dt = new Date();
				f.setTitle(title.getText());
				
				f.setDescription(desc.getText());
				
				//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				LocalDate localDate = year.getValue();
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
				System.out.println(date+"\n"+localDate+"\n"+instant+"\n"+LocalDate.now());
				//f.setReleaseYear(date);
				
				f.setLanguageId(combLang.getSelectionModel().getSelectedItem());
				f.setOriginalLanguageId(combOrLang.getSelectionModel().getSelectedItem());
				
				Short rentalDuration = Short.valueOf(rentalDur.getText());
				f.setRentalDuration(rentalDuration);
				
				BigDecimal rentalRate = BigDecimal.valueOf(Double.parseDouble(rentalRat.getText()));
				f.setRentalRate(rentalRate);
				
				Short Length = Short.valueOf(length.getText());
				f.setLength(Length);
				
				BigDecimal replacementCost = BigDecimal.valueOf(Double.parseDouble(cost.getText()));
				f.setReplacementCost(replacementCost);
				
				f.setLastUpdate(dt);
				
				if(actor.InsertOrUpdateObject(f)) {
					Utilitaires.showMessage("Projet java", "Information", "Film enregistrer");
					LoadView.showView("Projet Java", "GestionFilm.fxml", 1);
					title.setText("");
					desc.setText("");
					year.getEditor().clear();
					rentalDur.setText("");
					rentalRat.setText("");
					length.setText("");
					cost.setText("");
				}
				else {
					Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter un film");
					title.setText("");
					desc.setText("");
					rentalDur.setText("");
					rentalRat.setText("");
					year.getEditor().clear();
					length.setText("");
					cost.setText("");
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
				
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		lang = FXCollections.observableArrayList();
		Orlang = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Language");
			for(Object object: objects) {
				lang.add((Language)object);
			}
			combLang.setItems(lang);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> objects = iactor.ListObject("Language");
			for(Object object: objects) {
				Orlang.add((Language)object);
			}
			combOrLang.setItems(Orlang);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
