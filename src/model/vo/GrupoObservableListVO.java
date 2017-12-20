package model.vo;

public class GrupoObservableListVO {

	private long codigoGrupo;
	private String descricaoGrupo;

	public GrupoObservableListVO(long codigo, String descricaoGrupo) {
		this.codigoGrupo = codigoGrupo;
		this.descricaoGrupo = descricaoGrupo;

	}

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
	
	
}
