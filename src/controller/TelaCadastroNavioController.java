package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.vo.Navio;
import model.vo.NavioObservableList;
import model.vo.Pais;
import view.ConstruirDialog;

public class TelaCadastroNavioController implements Initializable {

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private TextField textFieldDescricao;

	@FXML
	private Label labelCodigo;

	@FXML
	private Button buttonCadastrar;

	@FXML
	private ComboBox<Pais> comboBoxPaisOrigem;

	@FXML
	private ComboBox<Integer> comboBoxQuantidadePorao;

	@FXML
	private TextField textFieldCapacidadePorao;

	private ObservableList<Pais> observableListPais;

	private final Database database = DatabaseFactory.getDatabase("oracle");
	private final Connection conn = database.conectar();
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
	public void clickOnCadastrar() throws SQLException {

		Navio navio = new Navio();

		navio.setCodigoNavio(Integer.valueOf(labelCodigo.getText()));
		navio.setDescricaoNavio(textFieldDescricao.getText().toUpperCase());
		navio.setPais(comboBoxPaisOrigem.getSelectionModel().getSelectedItem());
		navio.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
		navio.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));

		navioDAO.inserir(navio);

		// Atualiza Tela de Consulta
		TelaConsultasController.observableListNavio.add(
				new NavioObservableList(navio.getCodigoNavio(), navio.getDescricaoNavio(), navio.getPais().getNome()));

		// fechar dialog
		dialogStage.close();
	}
	
	public void desconectarBanco() {
		
		database.desconectar(conn);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		paisDAO.setConnection(conn);
		navioDAO.setConnection(conn);

		// listaPais = listarDescricao();
		labelCodigo.setText(Integer.toString(navioDAO.verificaUltimoCodigo() + 1));
		observableListPais = FXCollections.observableArrayList(paisDAO.listarPais());

		comboBoxPaisOrigem.setItems(observableListPais);
		comboBoxQuantidadePorao.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	}

}
