package controller;

import com.jfoenix.controls.JFXDialog;

public class DialogController implements Initializable {
	JFXDialog dialog = new JFXDialog();
	dialog.setContent(new Label("Content"));
	button.setOnAction((action)->dialog.show(rootStackPane));
}
