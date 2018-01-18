package embarque;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MovimentoEmbarqueController implements Initializable {

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

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
