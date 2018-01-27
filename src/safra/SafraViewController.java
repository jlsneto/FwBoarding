package safra;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import helpers.Routes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.vo.SafraVO;
import model.vo.UsuarioVO;
import usuario.CadastroUsuarioController;
import view.ConstruirDialog;

public class SafraViewController {

	@FXML
	private AnchorPane TelaSafra;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private TableView<SafraVO> TableView;

	@FXML
	private TableColumn<SafraVO, String> TableColumnCodigo;

	@FXML
	private TableColumn<SafraVO, String> TableColumnAnoSafra;

	@FXML
	private TableColumn<SafraVO, CheckBox> TableColumnSetSafra;

	@FXML
	private TableColumn<SafraVO, Button> columnButton;

	@FXML
	private JFXButton buttonAdd;

	@FXML
	void clickOnIncluir() {
		
	}
	
	@FXML
	public void clickOnAlterar() throws IOException {

	}
	
	@FXML
	public void clickOnExcluir() throws Exception {

	}
	
	@FXML
	private void clickOnPesquisar() {
	
	}
	


}
