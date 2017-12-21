package controller;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.GrupoUsuarioDAO;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import view.ConstruirDialog;

public class CadastroGrupoController implements Initializable {

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
					grupoUsuario.setPermissaoInsertNavio("F");
				}
				if (checkAlterarNavio.isSelected()) {
					grupoUsuario.setPermissaoAlterNavio("T");
				} else {
					grupoUsuario.setPermissaoAlterNavio("F");
				}
				if (checkExibirNavio.isSelected()) {
					grupoUsuario.setPermissaoConsulNavio("T");
				} else {
					grupoUsuario.setPermissaoConsulNavio("F");
				}
				if (checkExcluirNavio.isSelected()) {
					grupoUsuario.setPermissaoDeletNavio("T");
				} else {
					grupoUsuario.setPermissaoDeletNavio("F");
				}
				if (checkCadastrarUsuario.isSelected()) {
					grupoUsuario.setPermissaoInsertUser("T");
				} else {
					grupoUsuario.setPermissaoInsertUser("F");
				}
				if (checkAlterarUsuario.isSelected()) {
					grupoUsuario.setPermissaoAlterUser("T");
				} else {
					grupoUsuario.setPermissaoAlterUser("F");
				}
				if (checkExibirUsuario.isSelected()) {
					grupoUsuario.setPermissaoConsulUser("T");
				} else {
					grupoUsuario.setPermissaoConsulUser("F");
				}
				if (checkExcluirUsuario.isSelected()) {
					grupoUsuario.setPermissaoDeletUser("T");
				} else {
					grupoUsuario.setPermissaoDeletUser("F");
				}
				if (checkIniciarMovimento.isSelected()) {
					grupoUsuario.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuario.setPermissaoInsertMovimento("F");
				}
				if (checkMonitorarMovimento.isSelected()) {
					grupoUsuario.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuario.setPermissaoConsulMovimento("F");
				}
				if (checkPausarMovimento.isSelected()) {
					grupoUsuario.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuario.setPermissaoAlterMovimento("F");
				}
				if (checkCancelarMovimento.isSelected()) {
					grupoUsuario.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuario.setPermissaoDeletMovimento("F");
				}
				if (checkCadastrarEmbarque.isSelected()) {
					grupoUsuario.setPermissaoInsertEmbarque("T");
				} else {
					grupoUsuario.setPermissaoInsertEmbarque("F");
				}
				if (checkAlterarEmbarque.isSelected()) {
					grupoUsuario.setPermissaoAlterEmbarque("T");
				} else {
					grupoUsuario.setPermissaoAlterEmbarque("F");
				}
				if (checkExibirEmbarque.isSelected()) {
					grupoUsuario.setPermissaoConsulEmbarque("T");
				} else {
					grupoUsuario.setPermissaoConsulEmbarque("F");
				}
				if (checkExcluirEmbarque.isSelected()) {
					grupoUsuario.setPermissaoDeletEmbarque("T");
				} else {
					grupoUsuario.setPermissaoDeletEmbarque("F");
				}
				grupoUsuarioDAO.Inserir(grupoUsuario);
				if (grupoUsuario.getDescricaoGrupo()
						.equals(grupoUsuarioDAO.retornaDescricaoGrupoUsuario(grupoUsuario.getDescricaoGrupo()))) {
					ConsultaGrupoUsuarioController.observableListGrupo.addAll(grupoUsuario);
				}
				dialogStage.close();
			} else {
				grupoUsuarioAlterar.setDescricaoGrupo(textFieldDescricao.getText());
				if (checkCadastrarNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertNavio("F");
				}
				if (checkAlterarNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterNavio("F");
				}
				if (checkExibirNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulNavio("F");
				}
				if (checkExcluirNavio.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletNavio("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletNavio("F");
				}
				if (checkCadastrarUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertUser("F");
				}
				if (checkAlterarUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterUser("F");
				}
				if (checkExibirUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulUser("F");
				}
				if (checkExcluirUsuario.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletUser("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletUser("F");
				}
				if (checkIniciarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertMovimento("F");
				}
				if (checkMonitorarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulMovimento("F");
				}
				if (checkPausarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterMovimento("F");
				}
				if (checkCancelarMovimento.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletMovimento("F");
				}
				if (checkCadastrarEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoInsertEmbarque("T");
				} else {
					grupoUsuarioAlterar.setPermissaoInsertEmbarque("F");
				}
				if (checkAlterarEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoAlterEmbarque("T");
				} else {
					grupoUsuarioAlterar.setPermissaoAlterEmbarque("F");
				}
				if (checkExibirEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoConsulEmbarque("T");
				} else {
					grupoUsuarioAlterar.setPermissaoConsulEmbarque("F");
				}
				if (checkExcluirEmbarque.isSelected()) {
					grupoUsuarioAlterar.setPermissaoDeletEmbarque("T");
				} else {
					grupoUsuarioAlterar.setPermissaoDeletEmbarque("F");
				}
				grupoUsuarioDAO.alterar(grupoUsuarioAlterar);
				ConsultaGrupoUsuarioController.itensEncontrados.set(
						ConsultaGrupoUsuarioController.itensEncontrados.indexOf(grupoUsuarioAlterar),
						grupoUsuarioAlterar);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// BUG ESCROTO DO CARALHO
		labelCodigoGrupo.setText(Long.toString(grupoUsuarioDAO.verificaUltimoCodigo() + 1));

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

	public void setGrupoUsuarioAlterar(GrupoUsuarioVO[] args) {
		this.grupoUsuarioAlterar = args[0];
		labelCodigoGrupo.setText(Long.toString(grupoUsuarioAlterar.getCodigoGrupo()));
		textFieldDescricao.setText(grupoUsuarioAlterar.getDescricaoGrupo());
		///OLHAR ESSA PORRA
		System.out.println(grupoUsuarioAlterar.getPermissaoInsertNavio());
		System.out.println(grupoUsuarioAlterar.getPermissaoAlterNavio());
		System.out.println(grupoUsuarioAlterar.getPermissaoConsulNavio());
		System.out.println(grupoUsuarioAlterar.getPermissaoDeletNavio());
		String a = grupoUsuarioAlterar.getPermissaoInsertNavio();
		if (grupoUsuarioAlterar.getPermissaoInsertNavio() == "T") {
			checkCadastrarNavio.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoAlterNavio() == "T") {
			checkAlterarNavio.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoConsulNavio() == "T") {
			checkExibirNavio.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoDeletNavio() == "T") {
			checkExcluirNavio.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoInsertUser() == "T") {
			checkCadastrarUsuario.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoAlterUser() == "T") {
			checkAlterarUsuario.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoConsulUser() == "T") {
			checkExibirUsuario.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoDeletUser() == "T") {
			checkExcluirUsuario.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoInsertMovimento() == "T") {
			checkIniciarMovimento.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoAlterMovimento() == "T") {
			checkPausarMovimento.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoConsulMovimento() == "T") {
			checkMonitorarMovimento.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoDeletMovimento() == "T") {
			checkCancelarMovimento.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoInsertEmbarque() == "T") {
			checkCadastrarEmbarque.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoAlterEmbarque() == "T") {
			checkAlterarEmbarque.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoConsulEmbarque() == "T") {
			checkExibirEmbarque.setSelected(true);
		}
		if (grupoUsuarioAlterar.getPermissaoDeletEmbarque() == "T") {
			checkExcluirEmbarque.setSelected(true);
		}

		buttomCadastrar.setText("Aplicar");
	}

}
