package moduloBE;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Estatistica implements Serializable {

	private int maiorEnergiaVital; 
    private LocalDateTime momentoMaiorEnergiaVital; 
    private int qtdAtivacoes; 
    private int vitorias;
    
    public Estatistica() {
    	this.maiorEnergiaVital = 0; 
    	this.momentoMaiorEnergiaVital = LocalDateTime.now();
    	this.qtdAtivacoes = 0; 
    	this.vitorias = 0; 
    }
	public int getMaiorEnergiaVital() {
		return maiorEnergiaVital;
	}
	public void setMaiorEnergiaVital(int maiorEnergiaVital) {
		this.maiorEnergiaVital = maiorEnergiaVital;
	}
	public LocalDateTime getMomentoMaiorEnergiaVital() {
		return momentoMaiorEnergiaVital;
	}
	public void setMomentoMaiorEnergiaVital(LocalDateTime momentoMaiorEnergiaVital) {
		this.momentoMaiorEnergiaVital = momentoMaiorEnergiaVital;
	}
	public int getQtdAtivacoes() {
		return qtdAtivacoes;
	}
	public void setQtdAtivacoes(int qtdAtivacoes) {
		this.qtdAtivacoes = qtdAtivacoes;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
    
    
}
