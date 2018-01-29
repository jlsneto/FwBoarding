package model.vo;


import javafx.scene.layout.HBox;
public class NavioVO {
	
	private long codigoNavio;
	private String descricaoNavio;
	private int qtdPorao;
	private double capacidadePorao;
	private PaisVO pais;
	private HBox buttonBar;

	public NavioVO() {
		
	}
	public NavioVO(long codigoNavio, String descricaoNavio, PaisVO pais){
		this.codigoNavio = codigoNavio;
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

	public PaisVO getPais() {
		return pais;
	}

	public void setPais(PaisVO pais) {
		this.pais = pais;
	}

	public double getCapacidadePorao() {
		return capacidadePorao;
	}

	public void setCapacidadePorao(double capacidadePorao) {
		this.capacidadePorao = capacidadePorao;
	}
	public HBox getButtonBar() {
		return buttonBar;
	}
	public void setButtonBar(HBox buttonBar) {
		this.buttonBar = buttonBar;
	}

}
