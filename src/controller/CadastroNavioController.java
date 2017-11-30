package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.vo.NavioVO;
import model.vo.NavioObservableListVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class CadastroNavioController implements Initializable {

	@FXML
	private GridPane gridPane;

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private Label labelErro;
	@FXML
	private TextField textFieldDescricao;

	@FXML
	private Label labelCodigo;

	@FXML
	private Button buttonCadastrar;

	@FXML
	private ComboBox<PaisVO> comboBoxPaisOrigem;

	@FXML
	private ComboBox<Integer> comboBoxQuantidadePorao;

	@FXML
	private TextField textFieldCapacidadePorao;

	private ObservableList<PaisVO> observableListPais;

	private final PaisDAO paisDAO = new PaisDAO();
	private final NavioDAO navioDAO = new NavioDAO();

	// Usado para definir palco e poder utilizar seus métodos neste controller
	private Stage dialogStage;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	@FXML
	public void clickOnCancelar() {
		ConstruirDialog confirm = new ConstruirDialog();
		// Chama evento do botão
		Optional<ButtonType> result = confirm.DialogConfirm("Confirmar Cancelamento",
				"Atenção! Você irá Cancelar o Cadastro", "Não terá volta! :(");

		if (result.get() == ButtonType.OK) {

			// ... Usuário clicou ok
			dialogStage.close();
		}
	}

	@FXML
	public void clickOnCadastrar() {

		if (validarEntrada()) {
			NavioVO navio = new NavioVO();
			navio.setCodigoNavio(Integer.valueOf(labelCodigo.getText()));
			navio.setDescricaoNavio(textFieldDescricao.getText());
			navio.setPais(comboBoxPaisOrigem.getSelectionModel().getSelectedItem());
			navio.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
			navio.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));

			try {
				navioDAO.inserir(navio);
				// Atualiza Tela de Consulta
				ConsultasNavioController.observableListNavio.add(new NavioObservableListVO(navio.getCodigoNavio(),
						navio.getDescricaoNavio(), navio.getPais().getNome()));
				// fechar dialog
				dialogStage.close();
			} catch (SQLException e) {
				// tratar!
				e.printStackTrace();
			}

		}

	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldDescricao.getText() == null || textFieldDescricao.getText().length() == 0) {

			errorMessage = "Descrição inválida ou nula!\n";
			textFieldDescricao.requestFocus();
		} else if (navioDAO.retornaDescricaoNavio(textFieldDescricao.getText()).equals(textFieldDescricao.getText())) {

			errorMessage = "Navio já existe!";
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

		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro do Navio", errorMessage, 0, "", errorMessage);
			return false;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		labelCodigo.setText(Integer.toString(navioDAO.verificaUltimoCodigo() + 1));
		observableListPais = FXCollections.observableArrayList(paisDAO.listarPais());

		comboBoxPaisOrigem.setItems(observableListPais);
		comboBoxQuantidadePorao.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	}
	
    @FXML
    void mascaraCapacidadePorao(InputMethodEvent event) {
    	System.out.println("Executou");
    	textFieldCapacidadePorao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            newValue = newValue.replaceAll(",",".");
            if(newValue.length()>0){
                try{
                    Double.parseDouble(newValue);
                    textFieldCapacidadePorao.setText(newValue.replaceAll(",","."));
                }catch(Exception e){
                	textFieldCapacidadePorao.setText(oldValue);
                }
            }
        });
    }

	

	public Stage getDialogStage() {
		return this.dialogStage;
	}

}
