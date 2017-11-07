package model.domain;

public class Navio {

	private int codigoNavio;
	private int qtdPorao;
	private float capacidadePorao;
	private String descricaoNavio;
	private Pais codigoPais;

	public Navio() {

	}

	public Navio(int codigoNavio, int qtdPorao, float capacidadePorao, String descricaoNavio, Pais codigoPais) {
		super();
		this.codigoNavio = codigoNavio;
		this.qtdPorao = qtdPorao;
		this.capacidadePorao = capacidadePorao;
		this.descricaoNavio = descricaoNavio;
		this.codigoPais = codigoPais;
	}

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

	public float getCapacidadePorao() {
		return capacidadePorao;
	}

	public void setCapacidadePorao(float capacidadePorao) {
		this.capacidadePorao = capacidadePorao;
	}

}
