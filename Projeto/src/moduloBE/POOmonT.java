package moduloBE;

import java.awt.Image;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.Mediador;
import moduloBGame.POOmonComportamento;

public class POOmonT implements POOmonComportamento{

	private String nome; 
    private int energia; 
    private Ambiente ambiente; 
    private String historia; 
    private int maiorEnergiaVital; 
    private LocalDateTime momentoMaiorEnergiaVital; 
    private int qtdAtivacoes; 
    private int vitorias; 
    private POOmonComportamento oponente; 

    public POOmonT() {
    	this.historia = "Envulpes é uma fêmea e é um POOmon do ambiente Terra, um dos "
        		+ "POOmons com maior poder destrutivo. Os seus pelos são compostos "
        		+ "por fósforo vermelho, tornando-a extremamente reativa quando submetida "
        		+ "a qualquer tipo de atrito, uma de suas características mais marcantes. "
        		+ "Envulpes foi criada em um laboratório, com o objetivo de ser um projeto "
        		+ "para facilitar explorações subterrâneas, mas que saiu do controle. Ela se "
        		+ "alimenta de pequenos animais e minerais que contenham enxofre (o que aumenta "
        		+ "sua energia). Considerando sua letalidade, Envulpes possui um temperamento "
        		+ "amigável e calmo, exceto quando se encontra em situações de vulnerabilidade";
    	this.nome = "Envulpes";
    	this.ambiente = Ambiente.TERRA;
    	this.momentoMaiorEnergiaVital = LocalDateTime.now();
    }

    @Override
    public void carregar(int energia) {
       this.energia = this.energia + energia; 
       if(this.energia > getMaiorEnergiaVital()) {
    	   this.maiorEnergiaVital = this.energia; 
    	   this.momentoMaiorEnergiaVital = LocalDateTime.now();
       }
    }

    @Override
    public void derrota() {
        
        
    }

    @Override
    public boolean estaVivo() {
       if(this.energia > 0){
           return true;
       } else {
        return false;
       }
    }

    @Override
    public Ambiente getAmbienteOriginario() {
        return this.ambiente;
    }

    @Override
    public int getEnergia() {
        return this.energia;
    }

    @Override
    public String getHistoria() {
        return this.historia;
    }

    @Override
    public Image getImagem() {
        String path = null; 
        Image image = null;
        if (this.getEnergia() > 350) {
            path = "images/Evulpes1.png";
        } else if (this.getEnergia() >= 151) {
            path = "images/Envulpes2.png";
        } else if (this.getEnergia() >= 1) {
            path = "images/Envulpes3.png";
        } else {
            path = "images/Envulpes4.png";
        }

        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int getMaiorEnergiaVital() {
        return this.maiorEnergiaVital;
    }

    @Override
    public LocalDateTime getMomentoMaiorEnergiaVital() {
        return this.momentoMaiorEnergiaVital;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getQtdActivacoes() {
        return this.qtdAtivacoes;
    }

    @Override
    public int getVitorias() {
        return this.vitorias;
    }

    @Override
    public void setMediador(Mediador mediador) {
      mediador.getPastaDados();
      mediador.getPastaLogs();
        
    }

    @Override
    public void vitoria() {
        
        
    }

    @Override
    public void atacar(Ambiente ambiente) {
        int dano = 0; 
        int energiaConsumida = 0; 

        energiaConsumida = (int)Math.floor(Math.random()*(200-100+1)+100); 
        if(this.energia >= (energiaConsumida * 2)){
            dano = (int)(energiaConsumida * 1.5);
        } else {
            energiaConsumida = (int)Math.floor(Math.random()*(99-40+1)+40);
            if(this.energia >= energiaConsumida){
                dano = energiaConsumida; 
            } else{
                energiaConsumida = 0; 
                dano = 30; 
            }
        }

        if(ambiente.equals(this.getAmbienteOriginario())){
            dano = (int)(dano * 1.2); 
        }

        this.oponente.receberAtaque(dano, ambiente);
    }

    @Override
    public void informarOponente(POOmonComportamento oponente) {
        this.oponente = oponente;   
    }

    @Override
    public void receberAtaque(int dano, Ambiente ambiente) {
       if(ambiente.equals(this.getAmbienteOriginario())){
           dano = dano * 90 / 100; 
       } 
       this.energia = this.energia - dano; 
    }
}
