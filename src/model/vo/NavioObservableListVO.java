package model.vo;

public class NavioObservableListVO {

	private long codigoNavio;
	private String descricaoNavio;
	private String paisDescricao;

	public NavioObservableListVO(long codigoNavio, String descricaoNavio, String paisDescricao){
		this.codigoNavio = codigoNavio;
		this.descricaoNavio = descricaoNavio;
		this.paisDescricao = paisDescricao;
	}
	public long getCodigoNavio() {
		return codigoNavio;
	}

	public void setCodigoNavio(long codigoNavio) {
		this.codigoNavio = codigoNavio;
	}

	public String getDescricaoNavio() {
		return descricaoNavio;
	}

	public void setDescricaoNavio(String descricaoNavio) {
		this.descricaoNavio = descricaoNavio;
	}

	public String getPaisDescricao() {
		return paisDescricao;
	}

	public void setPaisDescricao(String paisDescricao) {
		this.paisDescricao = paisDescricao;
	}

}
