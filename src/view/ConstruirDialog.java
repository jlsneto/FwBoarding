package view;


import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ConstruirDialog {

	public void DialogError(String tituloJanela, String tituloErro, int codigoErro, String erro, String detalhes) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(tituloJanela);
		alert.setHeaderText(tituloErro);
		alert.setContentText("Codigo: "
							 +codigoErro
							 +"\n" + erro);

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
	
	public void dialogAlert(String titulo, String header, String conteudo) {
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(conteudo);

        alert.showAndWait();
	}

	public Optional<ButtonType> DialogConfirm(String titulo,String header,String conteudo) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(conteudo);
		Optional<ButtonType> result = alert.showAndWait();
		return result;
		
		/*
		 * OBS: Para utilizar a dialog em outra classe
		
		//Necessário para criar
		ConstruirDialog confirm = new ConstruirDialog();	
		//Chama evento do botão
		Optional<ButtonType> result = confirm.DialogConfirm();
		
		if (result.get() == ButtonType.OK) {

		// ... Usuário clicou ok
			
		} else {
			// ... Usuário cancelou ou fechou a janela

		}
		*/


	}
}
