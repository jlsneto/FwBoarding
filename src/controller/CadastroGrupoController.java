package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.vo.GrupoUsuarioVO;

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

	@FXML
	void clickOnCadastrar(ActionEvent event) {
		GrupoUsuarioVO grupoUsuarioDAO = new GrupoUsuarioVO();
		grupoUsuarioDAO.setCodigoGrupo(Integer.valueOf(labelCodigoGrupo.getText()));
		if (checkCadastrarNavio.isSelected()) {
			grupoUsuarioDAO.setPermissaoInsertNavio("T");
		}
		if (checkAlterarNavio.isSelected()) {
			grupoUsuarioDAO.setPermissaoAlterNavio("T");
		}
		if (checkExibirNavio.isSelected()) {
			grupoUsuarioDAO.setPermissaoConsulNavio("T");
		}
		if (checkExcluirNavio.isSelected()) {
			grupoUsuarioDAO.setPermissaoInsertNavio("T");
		}
		if (checkCadastrarUsuario.isSelected()) {
			grupoUsuarioDAO.setPermissaoInsertUser("T");
		}
		if (checkAlterarUsuario.isSelected()) {
			grupoUsuarioDAO.setPermissaoAlterUser("T");
		}
		if (checkExibirUsuario.isSelected()) {
			grupoUsuarioDAO.setPermissaoConsulUser("T");
		}
		if (checkExcluirUsuario.isSelected()) {
			grupoUsuarioDAO.setPermissaoDeletUser("T");
		}
		if (checkIniciarMovimento.isSelected()) {
			grupoUsuarioDAO.setPermissaoInsertMovimento("T");
		}
		if (checkMonitorarMovimento.isSelected()) {
			grupoUsuarioDAO.setPermissaoConsulMovimento("T");
		}
		if (checkPausarMovimento.isSelected()) {
			grupoUsuarioDAO.setPermissaoAlterMovimento("T");
		}
		if (checkCancelarMovimento.isSelected()) {
			grupoUsuarioDAO.setPermissaoDeletMovimento("T");
		}
		if (checkCadastrarEmbarque.isSelected()) {
			grupoUsuarioDAO.setPermissaoInsertMovimento("T");
		}
		if (checkAlterarEmbarque.isSelected()) {
			grupoUsuarioDAO.setPermissaoAlterMovimento("T");
		}
		if (checkExibirEmbarque.isSelected()) {
			grupoUsuarioDAO.setPermissaoConsulMovimento("T");
		}
		if (checkExcluirEmbarque.isSelected()) {
			grupoUsuarioDAO.setPermissaoDeletMovimento("T");
		}

	}

	@FXML
	void clickOnCancelar(ActionEvent event) {

	}

	public void initialize(URL location, ResourceBundle resources) {

	}
}
