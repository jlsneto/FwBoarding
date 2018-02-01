package model.vo;

import javafx.scene.control.ButtonBar;

public class EmbarqueVO {

	private long codigoEmbarque;
	private long codigoUsuario;
	private long codigoNavio;
	private PaisVO PaisDestino;
	private String status;
	private float quantidadeDeAcucar;
	private String anoSafra;
	private ButtonBar buttonBar;

	public EmbarqueVO(PaisVO pais) {
		this.PaisDestino = pais; 
	}
	public EmbarqueVO() {
		// TODO Auto-generated constructor stub
	}
	public long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public long getCodigoNavio() {
		return codigoNavio;
	}

	public void setCodigoNavio(long codigoNavio) {
		this.codigoNavio = codigoNavio;
	}

	public PaisVO getPaisDestino() {
		return PaisDestino;
	}

	public void setPaisDestino(PaisVO PaisDestino) {
		this.PaisDestino = PaisDestino;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getQuantidadeDeAcucar() {
		return quantidadeDeAcucar;
	}

	public void setQuantidadeDeAcucar(float quantidadeDeAcucar) {
		this.quantidadeDeAcucar = quantidadeDeAcucar;
	}

	public ButtonBar getButtonBar() {
		return buttonBar;
	}

	public void setButtonBar(ButtonBar buttonBar) {
		this.buttonBar = buttonBar;
	}
	
	public String getAnoSafra() {
		return anoSafra;
	}
	
	public void setAnoSafra(String anoSafra) {
		this.anoSafra = anoSafra;
	}
}
