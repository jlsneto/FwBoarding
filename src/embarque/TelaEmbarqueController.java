package embarque;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.EmbarqueDAO;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.vo.EmbarqueVO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class TelaEmbarqueController implements Initializable {

	@FXML
	private JFXTextField textFieldPesquisaSafra;

	@FXML
	private JFXTextField textFieldPesquisaCodigo;

	@FXML
	private JFXDatePicker dateFieldPesquisaInicio;

	@FXML
	private JFXDatePicker dateFieldPesquisaFim;

	@FXML
	private JFXButton buttonPesquisaPesquisar;

	@FXML
	private TableView<EmbarqueVO> tableViewEmbarque;

	@FXML
	private TableColumn<EmbarqueVO, String> columnCodigoEmbarque;

	@FXML
	private TableColumn<EmbarqueVO, String> columnPaisDestion;

	@FXML
	TableColumn<EmbarqueVO, String> columnButton;

	@FXML
	private Label labelCadastrarEmbarque;

	@FXML
	private Label labelCodigoEmbarque;

	@FXML
	private JFXTextField textFieldCodigoNavio;

	@FXML
	private JFXComboBox<PaisVO> comboBoxPaisDestino;

	@FXML
	private JFXTextField textFieldQuantidadeAcucar;

	@FXML
	private JFXButton buttonNovo;

	@FXML
	private JFXButton buttonEditar;

	@FXML
	private JFXButton buttonGravar;

	public static ObservableList<EmbarqueVO> observableListEmbarque;
	private final EmbarqueDAO embarqueDAO = new EmbarqueDAO();
	private EmbarqueVO embarqueAlterar;
	static ObservableList<EmbarqueVO> itensEncontrados;
	public static boolean isAlterarEmbarque;
	private ObservableList<PaisVO> observableListPais;
	private final PaisDAO paisDAO = new PaisDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//carregarTableViewEmbarque();
		//labelCodigoEmbarque.setText(Integer.toString(embarqueDAO.verificaUltimoCodigo() + 1));
		//observableListPais = FXCollections.observableArrayList(paisDAO.listarPais());
		//comboBoxPaisDestino.setItems(observableListPais);
	}

	public void carregarTableViewEmbarque() {
		
		columnCodigoEmbarque.setCellValueFactory(new PropertyValueFactory<>("codigoEmbarque	"));
		columnPaisDestion.setCellValueFactory(new PropertyValueFactory<>("paisDestino"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

		observableListEmbarque = FXCollections.observableArrayList(embarqueDAO.listar());
		tableViewEmbarque.setItems(observableListEmbarque);
		clickOnPesquisar();

	}

	public void Cadastrar() {
		if (validarEntrada()) {
			if (isAlterarEmbarque == false) {
				
				EmbarqueVO embarque = new EmbarqueVO();
				embarque.setCodigoEmbarque(Integer.valueOf(labelCodigoEmbarque.getText()));
				embarque.setCodigoNavio(Integer.valueOf(textFieldCodigoNavio.getText()));
				embarque.setPaisDestino(comboBoxPaisDestino.getSelectionModel().getSelectedItem());
				embarque.setQuantidadeDeAcucar(Float.valueOf(textFieldQuantidadeAcucar.getText()));
				embarqueDAO.Inserir(embarque);
				//olhar isso equals
				if (embarque.getCodigoEmbarque() == (embarqueDAO.retornaCodigoEmbarque(embarque.getCodigoEmbarque()))) {
					observableListEmbarque.addAll(embarque);
				}

			} else {
				embarqueAlterar.setCodigoEmbarque(Integer.valueOf(labelCodigoEmbarque.getText()));
				embarqueAlterar.setCodigoNavio(Integer.valueOf(textFieldCodigoNavio.getText()));
				embarqueAlterar.setPaisDestino(comboBoxPaisDestino.getSelectionModel().getSelectedItem());
				embarqueAlterar.setQuantidadeDeAcucar(Float.valueOf(textFieldQuantidadeAcucar.getText()));
				embarqueDAO.alterar(embarqueAlterar);
				itensEncontrados.set(itensEncontrados.indexOf(embarqueAlterar), embarqueAlterar);
			}

		}
	}

	@FXML
	public void clickOnGravar() throws IOException {
		isAlterarEmbarque = false;
		Cadastrar();
		clickOnPesquisar();
	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldCodigoNavio.getText() == null || textFieldCodigoNavio.getText().length() == 0) {

			errorMessage = "Codigo inválido ou nulo!\n";
			textFieldCodigoNavio.requestFocus();
		} else if (comboBoxPaisDestino.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione o país!\n";
			comboBoxPaisDestino.requestFocus();

		} else if (textFieldQuantidadeAcucar.getText() == null || textFieldQuantidadeAcucar.getText().length() == 0
				|| Double.valueOf(textFieldQuantidadeAcucar.getText()) <= 0) {

			errorMessage = "Insira a Quantidade !\n";
			textFieldQuantidadeAcucar.requestFocus();
			//olhar esse else equals
		} else if (embarqueDAO.retornaCodigoEmbarque(Integer.valueOf(textFieldCodigoNavio.getText())) == (Integer.valueOf(textFieldCodigoNavio.getText()))) {

			// Caso não tenha este if da erro!
			if (isAlterarEmbarque == true) {
				if (!textFieldCodigoNavio.getText().equals(embarqueAlterar.getCodigoEmbarque())) {
					errorMessage = "Embarque já existe!";
				}
			} else {
				errorMessage = "Embarque já existe!";
				textFieldCodigoNavio.requestFocus();
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
	
	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (EmbarqueVO itens: observableListEmbarque) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			Button buttonExcluir = new Button("Excluir");
			buttonExcluir.setOnAction(event -> {
				try {
					tableViewEmbarque.getSelectionModel().select(itens);
					Excluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Button buttonEdit = new Button("Editar");
			buttonEdit.setOnAction(event -> {
				try {
					tableViewEmbarque.getSelectionModel().select(itens);
					Alterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit);
			if (itens.getCodigoEmbarque() == (Integer.valueOf(textFieldPesquisaCodigo.getText()))) {
				itensEncontrados.add(itens);
			}
		}
		tableViewEmbarque.setItems(itensEncontrados);
	}	
	
	public void Excluir() throws Exception {
		int selectedIndex = tableViewEmbarque.getSelectionModel().getSelectedIndex();
		EmbarqueVO embarque = tableViewEmbarque.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoEmbarque(embarque.getCodigoEmbarque())) {
				embarqueDAO.deletar(embarque.getCodigoEmbarque());
				embarqueDAO.verificarSeFoiEmbarqueExcluido(embarque.getCodigoEmbarque());
				//TableColumnNavio.getItems().remove(selectedIndex);
				observableListEmbarque.remove(embarque);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum embarque selecionado", "Selecione um embarque!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}
	
	public boolean confirmouExcluisaoDoEmbarque(long codigoEmbarque) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"O Embarque " + codigoEmbarque + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	public void Alterar() throws IOException {

		int selectedIndex = tableViewEmbarque.getSelectionModel().getSelectedIndex();
		EmbarqueVO embarque = tableViewEmbarque.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			isAlterarEmbarque = true;
			clickOnGravar();

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum embarque selecionado", "Selecione um embarque!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

}
