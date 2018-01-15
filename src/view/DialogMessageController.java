package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class DialogMessageController implements Initializable {

    @FXML
    private Label labelMessageError;

    @FXML
    private TextArea textAreaMessageError;

    private Stage stage;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

	public void setMessages(String titulo, String message) {
		labelMessageError.setText(titulo);
		textAreaMessageError.setText(message);
	}

}

