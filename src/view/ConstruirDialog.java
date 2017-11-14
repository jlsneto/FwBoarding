package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ConstruirDialog {

	public void DialogError(String tituloJanela, String tituloErro,int codigoErro, String erro, String detalhes) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(tituloJanela);
		alert.setHeaderText(tituloErro);
		alert.setContentText("ORA-"+codigoErro+": "+erro);

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
}
