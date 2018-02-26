package model.vo;

public class PausaVO {
	long codigoPausa;
	UsuarioVO usuario;
	String descricaoPausa;

	public long getCodigoPausa() {
		return codigoPausa;
	}

	public void setCodigoPausa(long codigoPausa) {
		this.codigoPausa = codigoPausa;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public String getDescricaoPausa() {
		return descricaoPausa;
	}

	public void setDescricaoPausa(String descricaoPausa) {
		this.descricaoPausa = descricaoPausa;
	}
}
