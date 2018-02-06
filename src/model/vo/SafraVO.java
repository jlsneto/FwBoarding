package model.vo;

import com.jfoenix.controls.JFXCheckBox;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;

public class SafraVO {
	private long codigoSafra;
	private String anoSafra;
	//private JFXCheckBox checkBox;
	private ButtonBar buttonBar;
	private String safraPadrao;

	public String getSafraPadrao() {
		return safraPadrao;
	}

	public void setSafraPadrao(String safraPadrao) {
		this.safraPadrao = safraPadrao;
	}

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

	/*public JFXCheckBox getCheckBox() {
		return this.checkBox;
	}

	public void setCheckBox(JFXCheckBox checkBox) {
		this.checkBox = checkBox;
	}*/
	
	public void setButtonBar(ButtonBar buttonBar) {
		this.buttonBar = buttonBar;
	}

	public ButtonBar getButtonBar() {
		return this.buttonBar;
	}
}
