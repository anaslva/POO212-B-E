package moduloBE;

import java.io.Serializable;

public class Ataque implements Serializable{
     private int dano; 
     private String nome; 
     private int energiaConsumida;
     
	public int getDano() {
		return dano;
	}
	public void setDano(double dano) {
		this.dano = (int)dano;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getEnergiaConsumida() {
		return energiaConsumida;
	}
	public void setEnergiaConsumida(int energiaConsumida) {
		this.energiaConsumida = energiaConsumida;
	}
	
	public void ataqueCruel() {
		this.setEnergiaConsumida((int)Math.floor(Math.random()*(200-100+1)+100));
        this.setNome("Cruel");
        int dano = (int)(this.getEnergiaConsumida() * 1.5);
        this.setDano(dano);      
	}
	
	public void ataqueAgressivo() {
		this.setEnergiaConsumida((int)Math.floor(Math.random()*(99-40+1)+40));
		this.setNome("Agressivo");
		this.setDano(this.getEnergiaConsumida());
	}
	
	public void ataqueBasico() {
		this.setEnergiaConsumida(0);
		this.setNome("Básico");
		this.setDano(30);
	}
  
     
}
