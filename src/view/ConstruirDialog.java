package view;

<<<<<<< HEAD
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
=======
import java.util.Optional;

>>>>>>> f227893aedb4d0c4dcd353428143c1bccf2ff2b4
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConstruirDialog {

	public void DialogError(String tituloJanela, String tituloErro, int codigoErro, String erro, String detalhes) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(tituloJanela);
		alert.setHeaderText(tituloErro);
		alert.setContentText("ORA-" + codigoErro + ": " + erro);

		Label label = new Label("Detalhes ");

		TextArea textArea = new TextArea(detalhes);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

	public void DialogInformation() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}
}
