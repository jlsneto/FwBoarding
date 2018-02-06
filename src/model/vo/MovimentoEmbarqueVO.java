package model.vo;

public class MovimentoEmbarqueVO {
	long codigoEmbarque;
	EmbarqueVO embarque;
	UsuarioVO usuario;
	long codigoPausa;
	String dataMovimento;
	String comentarioPausa;
	String cancelado;
	String tipoMovimento;

	public long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public EmbarqueVO getEmbarque() {
		return embarque;
	}

	public void setEmbarque(EmbarqueVO embarque) {
		this.embarque = embarque;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public long getCodigoPausa() {
		return codigoPausa;
	}

	public void setCodigoPausa(long codigoPausa) {
		this.codigoPausa = codigoPausa;
	}

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getComentarioPausa() {
		return comentarioPausa;
	}

	public void setComentarioPausa(String comentarioPausa) {
		this.comentarioPausa = comentarioPausa;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

}
