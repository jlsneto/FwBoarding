package safra;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.dao.SafraDAO;
import model.vo.SafraVO;
import usuario.ConsultaUsuario;
import view.ConstruirDialog;

public class CadastroSafraController implements Initializable {

	@FXML
	private JFXTextField fieldTextAnoSafra;

	@FXML
	private JFXButton buttonConfirmar;
	
	@FXML
    private Label labelCodigoSafra;
	

	private Stage dialogStage;

	public static boolean isAlterarSafra = false;

	public static String anoSafra;
	
	private final SafraDAO safraDAO = new SafraDAO();
	
	private SafraVO safraAlterar;
	
	private ObservableList<SafraVO> observableListSafra;

	@FXML
	void clickOnConfirm() {
		if (validarEntrada()) {
			if (isAlterarSafra == false) { 
			SafraVO safra = new SafraVO();
			safra.setCodigoSafra(Long.valueOf(labelCodigoSafra.getText()));
			safra.setAnoSafra(fieldTextAnoSafra.getText());
			safraDAO.inserir(safra);
			if (safra.getAnoSafra().equals(safraDAO.retornaAnoSafra(safra.getAnoSafra()))) {
				SafraViewController.observableListSafra.addAll(safra);
				
			}
			dialogStage.close();
		} else {
			safraAlterar.setAnoSafra(fieldTextAnoSafra.getText());
			safraDAO.alterar(safraAlterar);
			SafraViewController.itensEncontrados.set(SafraViewController.itensEncontrados.indexOf(safraAlterar),
					safraAlterar);
			dialogStage.close();
		}
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
	
	public String getAnoSafra() {
		return anoSafra;
	}
	
	private boolean validarEntrada() {
		String errorMessage = "";
		if (fieldTextAnoSafra.getText() == null || fieldTextAnoSafra.getText().length() == 0) {

			errorMessage = "Ano de Safra invalido ou nulo!\n";
			fieldTextAnoSafra.requestFocus();
		}
		if (errorMessage.length() == 0) {
			anoSafra = fieldTextAnoSafra.getText();	
			return true;
		} else {
			errorMessage = "Ano de Safra invalido !\n";
			return false;
		}

	}
	
	public void setSafraAlterar(SafraVO safra) {
		this.safraAlterar = safra;
		labelCodigoSafra.setText(Long.toString(safraAlterar.getCodigoSafra()));
		fieldTextAnoSafra.setText(safraAlterar.getAnoSafra());
		buttonConfirmar.setText("Aplicar");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(isAlterarSafra == false) {
		labelCodigoSafra.setText(Long.toString(safraDAO.verificaUltimoCodigo() + 1));
		}
	}

}
