package model.vo;

import javafx.scene.layout.HBox;

public class UsuarioVO {
	private long codigoUsuario;
	private String nomeUsuario;
	private String senha;
	private GrupoUsuarioVO grupoUsuario;
	private String alteraSenha;
	private HBox buttonBar;


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

	public void setButtonBar(HBox buttonBar) {
		this.buttonBar = buttonBar;
	}

	public HBox getButtonBar() {
		return this.buttonBar;
	}

	public String getAlteraSenha() {
		return alteraSenha;
	}

	public void setAlteraSenha(String alteraSenha) {
		this.alteraSenha = alteraSenha;
	}
}
