package model.domain;

public class Navio {
	
	private long codigoNavio;
	private String descricaoNavio;
	private int qtdPorao;
	private float capacidadePorao;
	private Pais pais;

	public Navio() {

	}

	public Navio(long codigoNavio, int qtdPorao, float capacidadePorao, String descricaoNavio, Pais pais) {
		super();
		this.codigoNavio = codigoNavio;
		this.qtdPorao = qtdPorao;
		this.capacidadePorao = capacidadePorao;
		this.descricaoNavio = descricaoNavio;
		this.pais = pais;
	}

	public long getCodigoNavio() {
		return codigoNavio;
	}

	public void setCodigoNavio(long codigoNavio) {
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public float getCapacidadePorao() {
		return capacidadePorao;
	}

	public void setCapacidadePorao(float capacidadePorao) {
		this.capacidadePorao = capacidadePorao;
	}

}
