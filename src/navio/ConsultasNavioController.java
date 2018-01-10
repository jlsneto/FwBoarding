package navio;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import fwboarding.FwBoarding;
import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.NavioDAO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class ConsultasNavioController implements Initializable {


	@FXML
	private TableView<NavioVO> TableColumnNavio;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<PaisVO, String> TableColumnNavioPais;
	
	@FXML
	private TableColumn<Button, String> columnButton;
    
    @FXML
    private JFXTextField textFieldPesquisar;

    @FXML
    private JFXButton buttonAdd;
    
    @FXML
    private JFXTabPane tabPane;

	public static ObservableList<NavioVO> observableListNavio;

	private final NavioDAO navioDAO = new NavioDAO();

	static ObservableList<NavioVO> itensEncontrados;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarTableViewNavio();

	}

	public void carregarTableViewNavio() {

		TableColumnNavioCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoNavio"));
		TableColumnNavioDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoNavio"));
		TableColumnNavioPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
		

		observableListNavio = FXCollections.observableArrayList(navioDAO.listar());
		TableColumnNavio.setItems(observableListNavio);
		
		clickOnPesquisar();

	}

	@FXML
	public void onKeyPressed(KeyEvent event) throws IOException {
		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		if (event.getCode().equals(KeyCode.ENTER) && selectedIndex >= 0) {
			clickOnAlterar();
		}
		else if(event.getCode().isLetterKey() || event.getCode().isWhitespaceKey() || event.getCode().equals(KeyCode.BACK_SPACE) ) {
			//System.out.println(event.getCode().getName());
			clickOnPesquisar();
		}
	}

	@FXML
	public void onMouseClicked(MouseEvent mouseEvent) throws IOException {
		if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
			if (mouseEvent.getClickCount() == 2) {
				clickOnAlterar();
			}
		}
	}

	@FXML
	public void clickOnIncluir() throws IOException {

		CadastroNavioController.isAlterarNavio = false;
		
		AnchorPane cadastroNavioPane = FXMLLoader.load(getClass().getResource(Routes.CADASTRONAVIOVIEW));

		Tab tabCadastro = new Tab("Cadastro de Navio",cadastroNavioPane);
		tabPane.getTabs().add(tabCadastro);
		tabPane.getSelectionModel().selectNext();
		
		//FwBoarding.carregarTelaCadastroNavio();
		//Para Atualizar a ObservableList itensEncontrados
		//clickOnPesquisar();
	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroNavioController.isAlterarNavio = true;
			FwBoarding.carregarTelaCadastroNavio(navio);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnExcluir() throws Exception {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoNavio(navio.getDescricaoNavio().toString())) {
				navioDAO.deletar(navio.getCodigoNavio());
				navioDAO.verificarSeFoiNavioExcluido(navio.getCodigoNavio());
				//TableColumnNavio.getItems().remove(selectedIndex);
				observableListNavio.remove(navio);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	public boolean confirmouExcluisaoDoNavio(String descricaoNavio) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"O navio " + descricaoNavio + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		//adicionar button para cada navio 
		for (NavioVO itens: observableListNavio) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			btnBar.getStylesheets().add(getClass().getResource("../view/styles/styles.css").toExternalForm());
			
			Image excluirIcon = new Image(getClass().getResourceAsStream("../view/images/Icons/excluirIcon.png"));
			//button excluir
			JFXButton buttonExcluir = new JFXButton();
			buttonExcluir.setGraphic(new ImageView(excluirIcon));
			
			buttonExcluir.setOnAction(event -> {
				try {
					TableColumnNavio.getSelectionModel().select(itens);
					clickOnExcluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			Image editarIcon = new Image(getClass().getResourceAsStream("../view/images/Icons/editarIcon.png"));
			//button editar
			JFXButton buttonEdit = new JFXButton();
			buttonEdit.setGraphic(new ImageView(editarIcon));
			buttonEdit.setOnAction(event -> {
				try {
					TableColumnNavio.getSelectionModel().select(itens);
					clickOnAlterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit);
			if (itens.getDescricaoNavio().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableColumnNavio.setItems(itensEncontrados);
	}	
}