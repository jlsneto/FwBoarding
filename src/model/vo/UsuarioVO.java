package model.vo;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;

public class UsuarioVO {
	private long codigoUsuario;
	private String nomeUsuario;
	private String senha;
	private GrupoUsuarioVO grupoUsuario;
	private ButtonBar buttonBar;

	public UsuarioVO() {


	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String usuario) {
		this.nomeUsuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public GrupoUsuarioVO getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuarioVO grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public void setButtonBar(ButtonBar buttonBar) {
		this.buttonBar = buttonBar;
	}

	public ButtonBar getButtonBar() {
		return this.buttonBar;
	}
}
