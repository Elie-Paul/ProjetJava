package stic3.sn.ui;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import cours.java.stic3.model.Staff;
import cours.java.stic3.service.IActor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stic3.sn.LoadView;
import stic3.sn.Utilitaires;

public class LoginController implements Initializable{

	@FXML
    private TextField username;

	@FXML
    private PasswordField mdp;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnIns;
    
    private ObservableList<Staff> staffs;

    @FXML
    void onClick(ActionEvent event) {
    	if(event.getSource()==btnLogin) {
    		if(username.getText().isEmpty() && mdp.getText().isEmpty()) {
    			//Utilitaires.showMessage("Projet Java", "Information", "Veuillez remplir tous les champs");
    			Alert dialogW = new Alert(AlertType.WARNING);
    			dialogW.setTitle("Projet Java");
    			dialogW.setHeaderText(null); // No header
    			dialogW.setContentText("Veuillez remplir tous les champs !");
    			dialogW.showAndWait();
    		}
    		else if (username.getText().equals("admin") && mdp.getText().equals("admin")) {
    			LoadView.showView("Menu principal", "Main.fxml", 1);
			}
    		else {
    			Registry registry;
    			staffs = FXCollections.observableArrayList();
    			try {
    				registry = LocateRegistry.getRegistry("localhost",1004);
    				IActor iactor = (IActor)registry.lookup("ActorDistant");
    				List<Object> objects = iactor.ListObject("Staff");
    				for(Object object: objects) {
    					staffs.add((Staff)object);
    				}
    				//VERIFICATION DU USERNAME ET DU MOTDEPASSE
    				for(Staff s: staffs) {
    					String pass = crypt(mdp.getText());
    					if(s.getActive() && s.getUsername().equals(username.getText()) && s.getPassword().equals(pass)) {
    						//Utilitaires.showMessage("Projet Java", "Information", "Test REUSSI");
    						LoadView.showView("Menu principal", "CustomerMain.fxml", 1);
    					}
    				}
				} catch (Exception e) {
					// TODO: handle exception
				}
    		}
    	}
    	
    	if(event.getSource()==btnIns) {
    		
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
