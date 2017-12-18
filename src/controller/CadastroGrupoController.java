package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.GrupoUsuarioDAO;
import model.vo.GrupoUsuarioVO;
import view.ConstruirDialog;

public class CadastroGrupoController {

	@FXML
	private Label labelCodigoGrupo;

	@FXML
	private TextField textFieldDescricao;

	@FXML
	private CheckBox checkExibirNavio;

	@FXML
	private CheckBox checkCadastrarNavio;

	@FXML
	private CheckBox checkAlterarNavio;

	@FXML
	private CheckBox checkExcluirNavio;

	@FXML
	private CheckBox checkCadastrarUsuario;

	@FXML
	private CheckBox checkExibirUsuario;

	@FXML
	private CheckBox checkAlterarUsuario;

	@FXML
	private CheckBox checkExcluirUsuario;

	@FXML
	private CheckBox checkIniciarMovimento;

	@FXML
	private CheckBox checkMonitorarMovimento;

	@FXML
	private CheckBox checkPausarMovimento;

	@FXML
	private CheckBox checkCancelarMovimento;

	@FXML
	private CheckBox checkExibirEmbarque;

	@FXML
	private CheckBox checkCadastrarEmbarque;

	@FXML
	private CheckBox checkAlterarEmbarque;

	@FXML
	private CheckBox checkExcluirEmbarque;

	@FXML
	private Button buttonCancelar;

	@FXML
	private Button buttomCadastrar;

	private final GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();
	public static boolean isAlterarGrupo;
	private GrupoUsuarioVO grupoUsuarioAlterar;
	private Stage dialogStage;

	@FXML
	void clickOnCadastrar(ActionEvent event) {

		if (validarEntrada()) {
			if (isAlterarGrupo == false) {
				GrupoUsuarioVO grupoUsuario = new GrupoUsuarioVO();
				grupoUsuario.setCodigoGrupo(Long.valueOf(labelCodigoGrupo.getText()));
				grupoUsuario.setDescricaoGrupo(textFieldDescricao.getText());
				if (checkCadastrarNavio.isSelected()) {
					grupoUsuario.setPermissaoInsertNavio("T");
				} else {
					grupoUsuario.setPermissaoInsertNavio("R");
				}
				if (checkAlterarNavio.isSelected()) {
					grupoUsuario.setPermissaoAlterNavio("T");
				} else {
					grupoUsuario.setPermissaoAlterNavio("R");
				}
				if (checkExibirNavio.isSelected()) {
					grupoUsuario.setPermissaoConsulNavio("T");
				} else {
					grupoUsuario.setPermissaoConsulNavio("R");
				}
				if (checkExcluirNavio.isSelected()) {
					grupoUsuario.setPermissaoInsertNavio("T");
				} else {
					grupoUsuario.setPermissaoInsertNavio("R");
				}
				if (checkCadastrarUsuario.isSelected()) {
					grupoUsuario.setPermissaoInsertUser("T");
				} else {
					grupoUsuario.setPermissaoInsertUser("R");
				}
				if (checkAlterarUsuario.isSelected()) {
					grupoUsuario.setPermissaoAlterUser("T");
				} else {
					grupoUsuario.setPermissaoAlterUser("R");
				}
				if (checkExibirUsuario.isSelected()) {
					grupoUsuario.setPermissaoConsulUser("T");
				} else {
					grupoUsuario.setPermissaoConsulUser("R");
				}
				if (checkExcluirUsuario.isSelected()) {
					grupoUsuario.setPermissaoDeletUser("T");
				} else {
					grupoUsuario.setPermissaoDeletUser("R");
				}
				if (checkIniciarMovimento.isSelected()) {
					grupoUsuario.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuario.setPermissaoConsulMovimento("R");
				}
				if (checkMonitorarMovimento.isSelected()) {
					grupoUsuario.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuario.setPermissaoConsulMovimento("R");
				}
				if (checkPausarMovimento.isSelected()) {
					grupoUsuario.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuario.setPermissaoAlterMovimento("R");
				}
				if (checkCancelarMovimento.isSelected()) {
					grupoUsuario.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuario.setPermissaoDeletMovimento("R");
				}
				if (checkCadastrarEmbarque.isSelected()) {
					grupoUsuario.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuario.setPermissaoAlterMovimento("R");
				}
				if (checkAlterarEmbarque.isSelected()) {
					grupoUsuario.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuario.setPermissaoConsulMovimento("R");
				}
				if (checkExibirEmbarque.isSelected()) {
					grupoUsuario.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuario.setPermissaoConsulMovimento("R");
				}
				if (checkExcluirEmbarque.isSelected()) {
					grupoUsuario.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuario.setPermissaoDeletMovimento("R");
				}
				grupoUsuarioDAO.Inserir(grupoUsuario);
				dialogStage.close();
			} else {
				grupoUsuarioAlterar.setDescricaoGrupo(textFieldDescricao.getText());
				if (checkCadastrarNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertNavio("R");
				}
				if (checkAlterarNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterNavio("R");
				}
				if (checkExibirNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulNavio("R");
				}
				if (checkExcluirNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertNavio("R");
				}
				if (checkCadastrarUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertUser("R");
				}
				if (checkAlterarUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterUser("R");
				}
				if (checkExibirUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulUser("R");
				}
				if (checkExcluirUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletUser("R");
				}
				if (checkIniciarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("R");
				}
				if (checkMonitorarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("R");
				}
				if (checkPausarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("R");
				}
				if (checkCancelarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("R");
				}
				if (checkCadastrarEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("R");
				}
				if (checkAlterarEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("R");
				}
				if (checkExibirEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("R");
				}
				if (checkExcluirEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("R");
				}
				grupoUsuarioDAO.Inserir(grupoUsuarioAlterar);
				dialogStage.close();
			}
		}
	}

	@FXML
	void clickOnCancelar(ActionEvent event) {
		if (confirmouCancelamentoOuFehamento()) {
			dialogStage.close();

		}
	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldDescricao.getText() == null || textFieldDescricao.getText().length() == 0) {

			errorMessage = "Descrição inválida ou nula!\n";
			textFieldDescricao.requestFocus();

		} else if (grupoUsuarioDAO.retornaDescricaoGrupoUsuario(textFieldDescricao.getText())
				.equals(textFieldDescricao.getText())) {

			// Caso não tenha este if da erro!
			if (isAlterarGrupo == true) {
				if (!textFieldDescricao.getText().equals(grupoUsuarioAlterar.getDescricaoGrupo())) {
					errorMessage = "Grupo de Usuário já existe!";
				}
			} else {
				errorMessage = "Grupo de Usuário já existe!";
				textFieldDescricao.requestFocus();
			}

		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro do Grupo de Usuário", errorMessage, 0, "", errorMessage);
			return false;
		}

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

	public void initialize(URL location, ResourceBundle resources) {

		//BUG ESCROTO DO CARALHO 
		labelCodigoGrupo.setText(Long.toString(grupoUsuarioDAO.verificaUltimoCodigo() + 1));
	}
}
