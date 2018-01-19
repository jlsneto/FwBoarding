package embarque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import helpers.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class MovimentoEmbarqueController implements Initializable {
	
	@FXML
	private AnchorPane anchorPaneMovimentoEmbarque;
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> columnOperacao;

    @FXML
    private TableColumn<?, ?> columnInicio;

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

    @FXML
    void clickOnFinalizar(ActionEvent event) {
    	
    }

    @FXML
    void clickOnIniciar(ActionEvent event) {

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
		
	}

}
