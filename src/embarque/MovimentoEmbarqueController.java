package embarque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import login.UsuarioSessao;
import model.dao.EmbarqueDAO;
import model.dao.MovimentoEmbarqueDAO;
import model.vo.EmbarqueVO;
import model.vo.MovimentoEmbarqueVO;

public class MovimentoEmbarqueController implements Initializable {
	
	@FXML
	private AnchorPane anchorPaneMovimentoEmbarque;
    @FXML
    private TableView<MovimentoEmbarqueVO> tableView;

    @FXML
    private TableColumn<MovimentoEmbarqueVO, String> columnOperacao;

    @FXML
    private TableColumn<MovimentoEmbarqueVO, String> columnInicio;

    @FXML
    private JFXButton buttonIniciar;

    @FXML
    private JFXButton buttonPausar;

    @FXML
    private JFXButton buttonFinalizar;

    @FXML
    private Label labelNumeroEmbarqueDetalhe;

    @FXML
    private JFXButton buttonVoltar;
	private EmbarqueVO embarque;
	
	private final MovimentoEmbarqueDAO movimentoEmbarqueDAO = new MovimentoEmbarqueDAO();
	
	public static  ObservableList<MovimentoEmbarqueVO> observableListMovimentoEmbarque;
	
    @FXML
    void clickOnFinalizar(ActionEvent event) {
    	
    }

    @FXML
    void clickOnIniciar(ActionEvent event) {
        	MovimentoEmbarqueVO movimentoEmbarque = new MovimentoEmbarqueVO();
        	movimentoEmbarque.setEmbarque(embarque);
        	movimentoEmbarque.setTipoMovimento("I");
        	movimentoEmbarque.setUsuario(UsuarioSessao.getUsuarioAtivo());
        	movimentoEmbarqueDAO.Inserir(movimentoEmbarque);
    }

    private boolean verificaMovimentos() {
		// Regras de negócio para IniciarEmbarque
		return false;
	}

	@FXML
    void clickOnPausar(ActionEvent event) {

    }

    @FXML
    void clickOnVoltar(ActionEvent event) {
    	retornarViewEmbarque();
    }
    
	private void retornarViewEmbarque() {
		AnchorPane parent = (AnchorPane) anchorPaneMovimentoEmbarque.getParent();
		parent.getChildren().clear();
		;
		try {
			AnchorPane telaGrupoUsuario = FXMLLoader.load(getClass().getResource(Routes.EMBARQUEVIEW));
			parent.getChildren().add((Node) telaGrupoUsuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		columnOperacao.setCellValueFactory(new PropertyValueFactory<>("tipoMovimento"));
		columnInicio.setCellValueFactory(new PropertyValueFactory<>("dataMovimento"));

		try {
			observableListMovimentoEmbarque = FXCollections.observableArrayList(movimentoEmbarqueDAO.listar());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableView.setItems(observableListMovimentoEmbarque);
		
	}

	public void setEmbarque(EmbarqueVO embarque) {
		this.embarque = embarque;
		labelNumeroEmbarqueDetalhe.setText(labelNumeroEmbarqueDetalhe.getText()+"\n"+this.embarque.getSafra().getAnoSafra()+" Código "+this.embarque.getSafra().getSafraOrdem());
		
	}

}
