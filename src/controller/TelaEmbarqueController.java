package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<?> tableViewEmbarque;

    @FXML
    private TableColumn<?, ?> columnCodigoEmbarque;

    @FXML
    private TableColumn<?, ?> columnPaisDestion;

    @FXML
    private Label labelCadastrarEmbarque;

    @FXML
    private Label labelCodigoEmbarque;

    @FXML
    private JFXTextField textFieldCodigoNavio;

    @FXML
    private JFXComboBox<?> comboBoxPaisDestino;

    @FXML
    private JFXTextField textFieldQuantidadeAcucar;

    @FXML
    private JFXButton buttonNovo;

    @FXML
    private JFXButton buttonEditar;

    @FXML
    private JFXButton buttonGravar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
