package navio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class CadastroNavioController implements Initializable {

	@FXML
	private GridPane gridPane;

	@FXML
	private JFXTextField textFieldCodigo;

	@FXML
	private Label labelErro;
	@FXML
	private JFXTextField textFieldDescricao;

	@FXML
	private Label labelCodigo;

	@FXML
	private JFXButton buttonGravar;

	@FXML
	private JFXComboBox<PaisVO> comboBoxPaisOrigem;

	@FXML
	private JFXComboBox<Integer> comboBoxQuantidadePorao;

	@FXML
	private JFXTextField textFieldCapacidadePorao;
	
	@FXML
	private AnchorPane anchorPaneCadastroNavio;
	private ObservableList<PaisVO> observableListPais;

	private final PaisDAO paisDAO = new PaisDAO();
	private final NavioDAO navioDAO = new NavioDAO();

	// Usado para definir palco e poder utilizar seus métodos neste controller
	private Stage dialogStage;
	private NavioVO navioAlterar;

	public static boolean isAlterarNavio;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		labelCodigo.setText(Integer.toString(navioDAO.verificaUltimoCodigo() + 1));
		observableListPais = FXCollections.observableArrayList(paisDAO.listarPais());

		comboBoxPaisOrigem.setItems(observableListPais);
		comboBoxQuantidadePorao.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		comboBoxQuantidadePorao.getSelectionModel().select(0);
		
	}
	
	@FXML
	public void clickOnCancelar() {
		if (confirmouCancelamentoOuFehamento()) {
			chamarConsultaNavio();
			

		}
	}

	private void chamarConsultaNavio() {
		AnchorPane parent = (AnchorPane) anchorPaneCadastroNavio.getParent();
		parent.getChildren().clear();
		AnchorPane telaNavio;
		try {
			telaNavio = FXMLLoader.load(getClass().getResource(Routes.NAVIOVIEW));
			parent.getChildren().add((Node) telaNavio);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@FXML
	public void clickOnCadastrar() {

		if (validarEntrada()) {
			if (isAlterarNavio == false) {
				NavioVO navio = new NavioVO();
				navio.setCodigoNavio(Integer.valueOf(labelCodigo.getText()));
				navio.setDescricaoNavio(textFieldDescricao.getText());
				navio.setPais(comboBoxPaisOrigem.getSelectionModel().getSelectedItem());
				navio.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
				navio.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));
				navioDAO.inserir(navio);
				// Atualiza Tela de Consulta
				if (navio.getDescricaoNavio().equals(navioDAO.retornaDescricaoNavio(navio.getDescricaoNavio()))) {
					ConsultasNavioController.observableListNavio.addAll(navio);
				}
				// fechar dialog
				chamarConsultaNavio();
			} else {
				navioAlterar.setDescricaoNavio(textFieldDescricao.getText());
				navioAlterar.setPais(comboBoxPaisOrigem.getSelectionModel().getSelectedItem());
				navioAlterar.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
				navioAlterar.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));
				navioDAO.alterar(navioAlterar);
				ConsultasNavioController.itensEncontrados
						.set(ConsultasNavioController.itensEncontrados.indexOf(navioAlterar), navioAlterar);
				chamarConsultaNavio();
			}

		}

	}
	@FXML
	private void KeyPressedComboBox() {
		//FALTA IMPLEMENTAR
		comboBoxPaisOrigem.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {

			for(PaisVO pais: observableListPais) {
				Integer i = 0;
				if(pais.getNome().startsWith(event.getCode().getName())) {		
						comboBoxPaisOrigem.getSelectionModel().select(pais);
					   ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) comboBoxPaisOrigem.getSkin();
					   skin.getListView().getFocusModel().focus(i);
					   break;
				}
				i++;
			}
		});
	}

	@FXML
	public void onKeyPressed(KeyEvent event){
		        if(event.getCode().equals(KeyCode.ENTER)) {
		        	if(textFieldDescricao.isFocused()) {
		        		comboBoxPaisOrigem.requestFocus();
		        	}else if(comboBoxPaisOrigem.isFocused()) {
		        		comboBoxQuantidadePorao.requestFocus();
		        	}else if(comboBoxQuantidadePorao.isFocused()) {
		        		textFieldCapacidadePorao.requestFocus();
		        	}else if(textFieldCapacidadePorao.isFocused()){
		        		buttonGravar.requestFocus();
		        	}else if(buttonGravar.isFocused()) {
		        		clickOnCadastrar();
		        	}
		        }
	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldDescricao.getText() == null || textFieldDescricao.getText().length() == 0) {
			
			errorMessage = "Descrição inválida ou nula!\n";
			textFieldDescricao.requestFocus();
		} else if (comboBoxPaisOrigem.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione o país!\n";
			comboBoxPaisOrigem.requestFocus();

		} else if (comboBoxQuantidadePorao.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione a quantidade de Porão!\n";
			comboBoxQuantidadePorao.requestFocus();

		} else if (textFieldCapacidadePorao.getText() == null || textFieldCapacidadePorao.getText().length() == 0
				|| Double.valueOf(textFieldCapacidadePorao.getText()) <= 0) {

			errorMessage = "Insira Capacidade !\n";
			textFieldCapacidadePorao.requestFocus();

		} else if (navioDAO.retornaDescricaoNavio(textFieldDescricao.getText()).equals(textFieldDescricao.getText())) {

			// Caso não tenha este if da erro!
			if (isAlterarNavio == true) {
				if (!textFieldDescricao.getText().equals(navioAlterar.getDescricaoNavio())) {
					errorMessage = "Navio já existe!";
				}
			} else {
				errorMessage = "Navio já existe!";
				textFieldDescricao.requestFocus();
			}

		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro do Navio", errorMessage, 0, "", errorMessage);
			return false;
		}

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		dialogStage.setOnCloseRequest(event -> {
			if (confirmouCancelamentoOuFehamento()) {
				// ... Usuário clicou ok
				dialogStage.close();
			} else {
				event.consume();
			}
		});

	}

	// Caso o usuário click em cancelar ou fechar
	public boolean confirmouCancelamentoOuFehamento() {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Cancelamento",
				"Atenção, se continuar seus dados serão perdidos!", "Deseja cancelar?");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	public void setNavioAlterar(NavioVO navio) {
		this.navioAlterar = navio;
		labelCodigo.setText(Long.toString(navio.getCodigoNavio()));
		textFieldDescricao.setText(navio.getDescricaoNavio());
		comboBoxQuantidadePorao.setValue(navio.getQtdPorao());
		
		comboBoxPaisOrigem.getItems().forEach(pais ->{
			if(pais.getNome().equals(navio.getPais().getNome())) {
				comboBoxPaisOrigem.getSelectionModel().select(pais);
			}
		}
		);
		
		buttonGravar.setText("Aplicar");
	}

}
