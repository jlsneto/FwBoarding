package model.bo;

public class Navio {

	private int codigoNavio;
	private int qtdPorao;
	private String descricaoNavio;
	private Pais codigoPais;

	public int getCodigoNavio() {
		return codigoNavio;
	}

	public void setCodigoNavio(int codigoNavio) {
		this.codigoNavio = codigoNavio;
	}

	public int getQtdPorao() {
		return qtdPorao;
	}

	public void setQtdPorao(int qtdPorao) {
		this.qtdPorao = qtdPorao;
	}

	public String getDescricaoNavio() {
		return descricaoNavio;
	}

	public void setDescricaoNavio(String descricaoNavio) {
		this.descricaoNavio = descricaoNavio;
	}

	public Pais getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Pais codigoPais) {
		this.codigoPais = codigoPais;
	}

}
