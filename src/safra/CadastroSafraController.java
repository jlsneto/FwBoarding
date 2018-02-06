package safra;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
	private Label labelStatus;

	@FXML
	private Label labelCodigoSafra;

	@FXML
	private JFXCheckBox checkBoxSafraPadrao;

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
				if(checkBoxSafraPadrao.isSelected()) {
					safra.setSafraPadrao("T");
					safraDAO.removerPadrao();
				}else {
					safra.setSafraPadrao("F");
				}
				safra.setCodigoSafra(Long.valueOf(labelCodigoSafra.getText()));
				safra.setAnoSafra(fieldTextAnoSafra.getText());
				safraDAO.inserir(safra);
				if (safra.getAnoSafra().equals(safraDAO.retornaAnoSafra(safra.getAnoSafra()))) {
					SafraViewController.observableListSafra.addAll(safra);

				}
				dialogStage.close();
			} else {
				if(checkBoxSafraPadrao.isSelected()) {
					safraAlterar.setSafraPadrao("T");
					safraDAO.removerPadrao();
				}else {
					safraAlterar.setSafraPadrao("F");
				}
				safraAlterar.setAnoSafra(fieldTextAnoSafra.getText());
				safraDAO.alterar(safraAlterar);
				SafraViewController.itensEncontrados.set(SafraViewController.itensEncontrados.indexOf(safraAlterar),
						safraAlterar);
				dialogStage.close();
			}
		}else {
			labelStatus.setVisible(true);
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
			labelStatus.setVisible(false);
			return true;
		} else {
			labelStatus.setText(errorMessage);
			return false;
		}

	}

	public void setSafraAlterar(SafraVO safra) {
		this.safraAlterar = safra;
		labelCodigoSafra.setText(Long.toString(safraAlterar.getCodigoSafra()));
		fieldTextAnoSafra.setText(safraAlterar.getAnoSafra());
		if(safra.getSafraPadrao().equalsIgnoreCase("T")) {
			checkBoxSafraPadrao.setSelected(true);
		}
		buttonConfirmar.setText("Aplicar");
	}

	@FXML
	void marcouSafraPadrao() {
		if (checkBoxSafraPadrao.isSelected()) {
			confirmaSafraPadrao(labelCodigoSafra.getText());
		}
	}

	public void confirmaSafraPadrao(String anoSafra) {
		ConstruirDialog notifica = new ConstruirDialog();
		notifica.dialogAlert("Definir Safra Padrão",
				"A safra " + anoSafra + " será definida como Safra Padrão para os cadastros de Embarque",
				"Para reverter, desmarque a flag");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (isAlterarSafra == false) {
			labelCodigoSafra.setText(Long.toString(safraDAO.verificaUltimoCodigo() + 1));
		}
		labelStatus.setVisible(false);
	}

}
