package model.vo;

public class MovimentoEmbarqueVO {
	long codigoMovimentoEmbarque;
	EmbarqueVO embarque;
	UsuarioVO usuario;
	PausaVO pausa;
	String dataMovimento;
	String comentarioPausa;
	String cancelado;
	String tipoMovimento;

	public long getCodigoMovimentoEmbarque() {
		return codigoMovimentoEmbarque;
	}

	public void setCodigoMovimentoEmbarque(long codigoEmbarque) {
		this.codigoMovimentoEmbarque = codigoEmbarque;
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

	public PausaVO getPausa() {
		return pausa;
	}

	public void setPausa(PausaVO pausa) {
		this.pausa = pausa;
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
