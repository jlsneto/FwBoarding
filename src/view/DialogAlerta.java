package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogAlerta {
	
	public void DialogError(String titulo, String header, String Content){
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(Content);

		alert.showAndWait();
	}
}
