package model.vo;

public class GrupoUsuarioVO {
	private long codigoGrupo;
	private String descricaoGrupo;
	private char permissao;

	public long getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(long codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public char getPermissao() {
		return permissao;
	}

	public void setPermissao(char permissao) {
		this.permissao = permissao;
	}

}
