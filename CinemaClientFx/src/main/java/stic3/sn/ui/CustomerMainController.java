package stic3.sn.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import cours.java.stic3.model.Film;
import cours.java.stic3.model.Staff;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import stic3.sn.LoadView;

public class CustomerMainController implements Initializable {

    @FXML
    private Button btnFilm;

    @FXML
    private Button btnLocat;

    @FXML
    private Button btnPay;

    @FXML
    private Button btnLogout;
    
    @FXML
    private Button btnChange;

    @FXML
    private Pane paneContent;

    @FXML
    private Pane panTitle;
    
    @FXML
    private ComboBox<Film> comboFilm;

    @FXML
    private TableView<Film> table;
    
    @FXML
    private TableColumn<Film, String> title;

    @FXML
    private TableColumn<Film, String> desc;

    @FXML
    private TableColumn<Film, Short> length;

    @FXML
    private TableColumn<Film, String> special;
    
    @FXML
    private ImageView img;
    
    private ObservableList<Film> filmsTable;
    private ObservableList<Film> filmsCombo;
    
    private FileChooser fileChooser;
    private File filePath;
    private FileInputStream fis;
    private byte[] bFile; 

    @FXML
    void onClick(ActionEvent event) throws IOException{
    	if(event.getSource()==btnLogout) {
    		Alert dialogC = new Alert(AlertType.CONFIRMATION);
    		dialogC.setTitle("Confimation");
    		dialogC.setHeaderText(null);
    		dialogC.setContentText("Deconnection ?");
    		Optional<ButtonType> answer = dialogC.showAndWait();
    		if (answer.get() == ButtonType.OK) {
    			LoadView.showView("Connexion", "Login.fxml", 1);
    		}
    		else {
    			LoadView.showView("Menu principal", "CustomerMain.fxml", 1);
    		}
    	}
    	
    	if(event.getSource()==btnChange) {
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		fileChooser = new FileChooser();
    		fileChooser.setTitle("Open image");
    		
    		String userDirectoryString = System.getProperty("user.home") + "\\Pictures" ;
    		File userDirectory = new File(userDirectoryString);
    		
    		if(!userDirectory.canRead())
    			userDirectory = new File("c:/");
    		
    		fileChooser.setInitialDirectory(userDirectory);
    		
    		
    		filePath = fileChooser.showOpenDialog(stage);
    		
    		bFile = new byte[(int)filePath.length()];
    		
    		try {
				BufferedImage bufferedImage = ImageIO.read(filePath);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				img.setImage(image);
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
			}
    	}
    	
    	if(event.getSource()==btnLocat) {
    		paneContent.getChildren().clear();
    		paneContent.getChildren().add(FXMLLoader.load(getClass().getResource("Rental.fxml")));
    		paneContent.toFront();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		filmsTable = FXCollections.observableArrayList();
		filmsCombo = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> obj = iactor.ListObject("Film");
			for(Object a : obj) {
				filmsTable.add((Film)a);
			}
			title.setCellValueFactory(new PropertyValueFactory<>("title"));
			desc.setCellValueFactory(new PropertyValueFactory<>("description"));
			length.setCellValueFactory(new PropertyValueFactory<>("length"));
			special.setCellValueFactory(new PropertyValueFactory<>("specialFeatures"));
			table.setItems(null);
			table.setItems(filmsTable);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> obj = iactor.ListObject("Film");
			for(Object a : obj) {
				filmsCombo.add((Film)a);
			}
			comboFilm.setItems(filmsCombo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
