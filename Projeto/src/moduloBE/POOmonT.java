package moduloBE;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private Path path;
    private FileWriter fw; 
    private Mediador mediador; 
    private String informacoesLog; 
    private FileOutputStream dadosOPS; 
    private FileInputStream dadosIPS; 
    private Estatistica estatistica; 
    private ObjectOutputStream objectOPS;	
    private ObjectInputStream objectIPS; 
    private boolean achouArquivo;

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
    	this.informacoesLog = "\n \nLog de Batalha \nPOOmon: " + this.nome + " - " + this.getAmbienteOriginario() + "\n \n";
    	this.estatistica = new Estatistica();
    	this.getDadosEstatistica();
    	if(!this.achouArquivo) {
    		this.gerarArquivoDados();
    	}
    	this.estatistica.setQtdAtivacoes(this.estatistica.getQtdAtivacoes() + 1);
    	this.gerarArquivoDados();
    }

    @Override
    public void carregar(int energia) {
       this.energia = this.energia + energia; 
       if(this.energia > getMaiorEnergiaVital()) {
    	   this.setMaiorEnergiaVital(energia);
    	   this.setMomentoMaiorEnergiaVital(LocalDateTime.now());
    	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      	   DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
      	   LocalDateTime hora = LocalDateTime.now().plusHours(-1);
      	   this.informacoesLog += "Minha energia vital: " + this.energia + " - " + LocalDateTime.now().format(formatter) + " - " + hora.format(formatterHour) + "\n";
       }
 
       this.informacoesLog += "Energia recebida: " + energia + "\n";
    }

    @Override
    public void derrota() {
    	this.informacoesLog += "Derrota \n";
    	this.criarArquivoLogs();
   
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

    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
    	LocalDateTime hora = LocalDateTime.now().plusHours(-1);
    	this.informacoesLog += "Minha energia vital: " + this.energia + " - " + LocalDateTime.now().format(formatter) + " - " + hora.format(formatterHour) + "\n";
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
    	this.getDadosEstatistica();
        return this.maiorEnergiaVital;
    }
    
    public void setMaiorEnergiaVital(int energia) {
    	this.getDadosEstatistica();
    	this.estatistica.setMaiorEnergiaVital(energia);
    	this.gerarArquivoDados();
    }

    @Override
    public LocalDateTime getMomentoMaiorEnergiaVital() {
        return this.momentoMaiorEnergiaVital;
    }

    public void setMomentoMaiorEnergiaVital(LocalDateTime momento) {
    	this.getDadosEstatistica();
    	this.estatistica.setMomentoMaiorEnergiaVital(momento);
    	this.gerarArquivoDados();
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getQtdActivacoes() {
    	this.getDadosEstatistica();
        return this.qtdAtivacoes;
    }
    
    public void setQtdAtivacoes() {
    	this.getDadosEstatistica();
    	this.estatistica.setQtdAtivacoes(this.estatistica.getQtdAtivacoes() + 1);
    	this.gerarArquivoDados();
    }


    @Override
    public int getVitorias() {
    	this.getDadosEstatistica();
    	return this.estatistica.getVitorias();
        
    }
    
    public void setVitorias() {
    	this.getDadosEstatistica();
    	this.estatistica.setVitorias(this.estatistica.getVitorias() + 1);
    	this.gerarArquivoDados();
    }

    @Override
    public void vitoria() {
    	this.qtdAtivacoes++;
    	this.setVitorias();
        this.informacoesLog += "Vitória \n";
        if(this.vitorias >= 3) {
        	this.criarArquivoLogs();
        	
        }
        
    }
    
    @Override
    public void setMediador(Mediador mediador) {
    	this.mediador = mediador; 
    }

    @Override
    public void atacar(Ambiente ambiente) {
        Ataque ataque = new Ataque();
        ataque.ataqueCruel();
        if(this.energia < (ataque.getEnergiaConsumida() * 2)) {
            ataque.ataqueAgressivo();
            if(this.energia >= ataque.getEnergiaConsumida()){
            	ataque.ataqueBasico(); 
            }
         }
        if(ambiente.equals(this.getAmbienteOriginario())){
            ataque.setDano(ataque.getDano() * 1.2);
        }
        
        this.energia = this.energia - ataque.getEnergiaConsumida();
        this.oponente.receberAtaque(ataque.getDano(), ambiente);
        this.informacoesLog += "Ataque efetuado: " + ataque.getNome() + " - " + ataque.getDano() + "(" + ataque.getDano() + ")" + " - " + ambiente + "(" + "-" + ataque.getEnergiaConsumida() + ") \n";
        
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
       this.informacoesLog += "Ataque recebido: " +  dano + "(" + dano + ")" + " - " + ambiente + "(" + "-" + dano + ") \n" ;
    }
    
    public void criarArquivoLogs() {
    	try {
    		this.fw = new FileWriter((this.mediador.getPastaLogs() + "\\Evulpes.txt"), true);
    		
    		this.fw.append(this.informacoesLog);
    		this.fw.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }
    
    public void gerarArquivoDados() {
    	try {
    		this.dadosOPS = new FileOutputStream(this.mediador.getPastaDados() + "\\Evulpes.dat", false);
    		this.objectOPS = new ObjectOutputStream(this.dadosOPS);
    		this.objectOPS.writeObject(this.estatistica);
    		this.objectOPS.close();
    		
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }
    
    public void getDadosEstatistica() {
    	try {
    		this.dadosIPS = new FileInputStream(this.mediador.getPastaDados() + "\\Evulpes.dat");
    		this.objectIPS = new ObjectInputStream(dadosIPS);
    		try {
				this.estatistica = (Estatistica)objectIPS.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    		this.achouArquivo = true; 
    		
		} catch(IOException ex) {
			this.achouArquivo = false; 
		}
    }
}
