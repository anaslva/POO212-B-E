package moduleBE;
import java.awt.Image;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import moduloBGame.*;


public class POOmonH implements POOmonComportamento{
    private String nome; 
    private int energia; 
    private Ambiente ambiente; 
    private String historia; 
    private int maiorEnergiaVital; 
    private LocalDateTime momentoMaiorEnergiaVital; 
    private int qtdAtivacoes; 
    private int vitorias; 

    @Override
    public void atacar(POOmonComportamento arg0, Ambiente arg1) {
        
        
    }

    @Override
    public void carregar(int arg0) {
       
        
    }

    @Override
    public void derrota() {
        
        
    }

    @Override
    public boolean estaVivo() {
       
        return false;
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
            path = "images/Elektrum1.png";
        } else if (this.getEnergia() >= 151) {
            path = "images/Elektrum2.jpg";
        } else if (this.getEnergia() >= 1) {
            path = "images/Elektrum3.jpg";
        } else {
            path = "images/Elektrum4.jpg";
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
        
        return 0;
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
    public void receberAtaque(int arg0, Ambiente arg1) {
       
        
    }

    @Override
    public void setMediador(Mediador arg0) {
       
        
    }

    @Override
    public void vitoria() {
        
        
    }
   
}
