package model.vo;

import javafx.scene.control.CheckBox;

public class SafraVO {
	private long codigoSafra;
	private String anoSafra;
	private CheckBox checkBox;

	public long getCodigoSafra() {
		return codigoSafra;
	}

	public void setCodigoSafra(long codigoSafra) {
		this.codigoSafra = codigoSafra;
	}

	public String getAnoSafra() {
		return anoSafra;
	}

	public void setAnoSafra(String anoSafra) {
		this.anoSafra = anoSafra;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
}
