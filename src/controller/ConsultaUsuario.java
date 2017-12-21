package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ConsultaUsuario implements Initializable {

	@FXML
	private AnchorPane TelaConsultasAnchorPane;

	@FXML
	private BorderPane AnchorPaneBorderPane;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private ButtonBar AnchorPaneButtonBar;

	@FXML
	private Button ButtonBarButtonExcluir;

	@FXML
	private Button ButtonBarButtonAlterar;

	@FXML
	private Button ButtonBarButtonIncluir;

	@FXML
	private TableView<?> TableView;

	@FXML
	private TableColumn<?, ?> TableColumnCodigo;

	@FXML
	private TableColumn<?, ?> TableColumnNome;

	@FXML
	private TableColumn<?, ?> TableColumnGrupo;

	@FXML
	private TableColumn<?, ?> columnButton;

	@FXML
	void clickOnAlterar(ActionEvent event) {

	}

	@FXML
	void clickOnExcluir(ActionEvent event) {

	}

	@FXML
	void clickOnIncluir(ActionEvent event) {

	}

	@FXML
	void onKeyPressed(KeyEvent event) {

	}

	@FXML
	void onMouseClicked(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
