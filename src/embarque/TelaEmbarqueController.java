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

import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.dao.EmbarqueDAO;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.vo.EmbarqueVO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import navio.CadastroNavioController;
import view.ConstruirDialog;

public class TelaEmbarqueController implements Initializable {

	@FXML
	private AnchorPane anchorPaneEmbarque;

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
	private TableColumn<Button, String> columnButton;

	@FXML
	private JFXButton buttonAdd;

	public static ObservableList<EmbarqueVO> observableListEmbarque;
	private final EmbarqueDAO embarqueDAO = new EmbarqueDAO();
	private EmbarqueVO embarqueAlterar;
	static ObservableList<EmbarqueVO> itensEncontrados;
	public static boolean isAlterarEmbarque;
	private ObservableList<PaisVO> observableListPais;
	private final PaisDAO paisDAO = new PaisDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewEmbarque();
		// TODO Auto-generated method stub
		// carregarTableViewEmbarque();
		// labelCodigoEmbarque.setText(Integer.toString(embarqueDAO.verificaUltimoCodigo()
		// + 1));
		// observableListPais = FXCollections.observableArrayList(paisDAO.listarPais());
		// comboBoxPaisDestino.setItems(observableListPais);
	}

	public void carregarTableViewEmbarque() {

		columnCodigoEmbarque.setCellValueFactory(new PropertyValueFactory<>("codigoEmbarque"));
		columnPaisDestion.setCellValueFactory(new PropertyValueFactory<>("paisDestino"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

		observableListEmbarque = FXCollections.observableArrayList(embarqueDAO.listar());
		tableViewEmbarque.setItems(observableListEmbarque);
		clickOnPesquisar();

	}

	@FXML
	public void clickOnIncluir() throws IOException {
		CadastroEmbarqueController.isAlterarEmbarque = false;
		AnchorPane cadastroEmbarque = FXMLLoader.load(getClass().getResource(Routes.CADASTROEMBARQUEVIEW));
		setNode(cadastroEmbarque);
		clickOnPesquisar();
	}

	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (EmbarqueVO itens : observableListEmbarque) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			btnBar.getStylesheets().add(getClass().getResource("/view/styles/styles.css").toExternalForm());

			Image excluirIcon = new Image(getClass().getResourceAsStream("/view/images/Icons/excluirIcon.png"));
			// button excluir
			JFXButton buttonExcluir = new JFXButton();
			buttonExcluir.setGraphic(new ImageView(excluirIcon));

			buttonExcluir.setOnAction(event -> {
				try {
					tableViewEmbarque.getSelectionModel().select(itens);
					clickOnExcluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Image editarIcon = new Image(getClass().getResourceAsStream("/view/images/Icons/editarIcon.png"));
			// button editar
			JFXButton buttonEdit = new JFXButton();
			buttonEdit.setGraphic(new ImageView(editarIcon));
			buttonEdit.setOnAction(event -> {
				try {
					tableViewEmbarque.getSelectionModel().select(itens);
					clickOnAlterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit);
			//if (itens.getCodigoEmbarque() == (Integer.valueOf(textFieldPesquisaCodigo.getText()))) {
				itensEncontrados.add(itens);
			//}
		}
		tableViewEmbarque.setItems(itensEncontrados);
	}

	@FXML
	public void clickOnExcluir() throws Exception {
		int selectedIndex = tableViewEmbarque.getSelectionModel().getSelectedIndex();
		EmbarqueVO embarque = tableViewEmbarque.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoEmbarque(embarque.getCodigoEmbarque())) {
				embarqueDAO.deletar(embarque.getCodigoEmbarque());
				embarqueDAO.verificarSeFoiEmbarqueExcluido(embarque.getCodigoEmbarque());
				// TableColumnNavio.getItems().remove(selectedIndex);
				observableListEmbarque.remove(embarque);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum embarque selecionado", "Selecione um embarque!");
		}
		// Para Atualizar a ObservableList itensEncontrados
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

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = tableViewEmbarque.getSelectionModel().getSelectedIndex();
		EmbarqueVO embarque = tableViewEmbarque.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Routes.CADASTROEMBARQUEVIEW));
			CadastroEmbarqueController.isAlterarEmbarque = true;

			AnchorPane cadastroNavio = loader.load();
			CadastroEmbarqueController controller = loader.getController();
			controller.setEmbarqueAlterar(embarque);
			setNode(cadastroNavio);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum embarque selecionado", "Selecione um embarque!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	public void setNode(Node node) {
		anchorPaneEmbarque.getChildren().clear();
		anchorPaneEmbarque.getChildren().add((Node) node);
	}

}
