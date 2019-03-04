package stic3.sn;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utilitaires {

	public static void showMessage(String title, String header, String content) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(title);
		a.setHeaderText(header);
		a.setContentText(content);
		a.showAndWait();
	}

}
