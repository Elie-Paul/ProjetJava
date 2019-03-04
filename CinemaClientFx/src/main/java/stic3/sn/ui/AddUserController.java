package stic3.sn.ui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import cours.java.stic3.model.Actor;
import cours.java.stic3.model.Address;
import cours.java.stic3.model.Staff;
import cours.java.stic3.model.Store;
import cours.java.stic3.service.IActor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import stic3.sn.Utilitaires;

public class AddUserController implements Initializable {

    @FXML
    private TextField name1;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField name2;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private RadioButton active;

    @FXML
    private ComboBox<Address> combAddr;

    @FXML
    private ComboBox<Store> combSto;

    @FXML
    private PasswordField pwd;

    @FXML
    private ImageView img;
    
    private FileChooser fileChooser;
    private File filePath;
    private FileInputStream fis;
    private byte[] bFile; 
    private ObservableList<Address> addresses;
    private ObservableList<Store> stores;

    @FXML
    private Button btnImg;

    @FXML
    void onClick(ActionEvent event) {
    	if(event.getSource()==btnAdd) {
    		Registry registry;
    		try {
    			registry = LocateRegistry.getRegistry("localhost",1004);
    			IActor actor = (IActor)registry.lookup("ActorDistant");
    			Staff a = new Staff();
    			Short staffId = null;
    			a.setStaffId(staffId);
    			Date dt = new Date();
    			a.setFirstName(name1.getText());
    			a.setLastName(name2.getText());
    			a.setAddressId(combAddr.getSelectionModel().getSelectedItem());
    			//save image 
    			//ouvrir un input du chemin de l'image
    			fis = new FileInputStream(filePath);
    			//Convertir l'image en tableau de byte
    			fis.read(bFile);
    			fis.close();
    			a.setPicture(bFile);
    			a.setEmail(email.getText());
    			a.setStoreId(combSto.getSelectionModel().getSelectedItem());
    			if(active.isSelected()) {
    				a.setActive(true);
    			}else {
    				a.setActive(false);
    			}
    			a.setUsername(username.getText());
    			String pass = crypt(pwd.getText());
    			a.setPassword(pass);
    			a.setLastUpdate(dt);
    			
    			if(actor.InsertOrUpdateObject(a)) {
    				Utilitaires.showMessage("Projet java", "Information", "User enregistrer");
    				name1.setText("");
    				name2.setText("");
    				email.setText("");
    				username.setText("");
    				pwd.setText("");
    			}
    			else {
    				Utilitaires.showMessage("Projet java", "Erreur", "Impossible d\'ajouter un user");
    				name1.setText("");
    				name2.setText("");
    				email.setText("");
    				username.setText("");
    				pwd.setText("");
    			}
			} catch (Exception e) {
				// TODO: handle exception
			}
    		//Utilitaires.showMessage("Cours java", "Information", "User saved");
    	}
    	
    	//selectionner une image dans l'explorateur
    	if(event.getSource()==btnImg) {
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
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Registry registry;
		addresses = FXCollections.observableArrayList();
		stores = FXCollections.observableArrayList();
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Address");
			for(Object object: actor) {
				addresses.add((Address)object);
			}
			combAddr.setItems(addresses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			registry = LocateRegistry.getRegistry("localhost",1004);
			IActor iactor = (IActor)registry.lookup("ActorDistant");
			List<Object> actor = iactor.ListObject("Store");
			for(Object object: actor) {
				stores.add((Store)object);
			}
			combSto.setItems(stores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	//Fonction Pour crypter le mot de passe
	private String crypt(String passwordToHash) {
		String generatedPassword = null;
	    try {
	        // Create MessageDigest instance for MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(passwordToHash.getBytes());
	        //Get the hash's bytes
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        //Get complete hashed password in hex format
	        generatedPassword = sb.toString();
	        return generatedPassword;
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
}
