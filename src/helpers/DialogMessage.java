package helpers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.DialogMessageController;

public class DialogMessage {
	
	public static void showMessage(String titulo,String message){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DialogMessage.class.getResource("/view/DialogConfirmation.fxml"));
		AnchorPane pane;
		try {
			pane = loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("MessageError");
			//stage.setResizable(false);
			stage.initStyle(StageStyle.UTILITY);
			
			DialogMessageController controller = loader.getController();
			controller.setStage(stage);
			controller.setMessages(titulo,message);

			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
