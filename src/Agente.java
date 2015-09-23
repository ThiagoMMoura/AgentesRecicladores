
import java.util.ArrayList;


/**
 * A classe Agente define as funções e propriedades do agente.
 * 
 */
public class Agente extends Peca{
    private int maxLixo;
    private ArrayList<Lixo> sacoLxOrganico; //Saco de lixo organico, armazena objetos do tipo LixoOrganico
    private ArrayList<Lixo> sacoLxSeco; //Saco de lixo seco, armazena objetos do tipo LixoSeco
    private ArrayList<MemoriaLixeira> lixeiras; //Armazena a memória do agente com relação as lixeiras presentes no ambiente
    
    public Agente(String nome,int maxLixo,ArrayList<MemoriaLixeira> lixeiras){
        super(nome);
        this.maxLixo = maxLixo;
        this.lixeiras = lixeiras;
        sacoLxOrganico = new ArrayList<>();
        sacoLxSeco = new ArrayList<>();
    }
    
    public boolean pegarLixo(Lixo lixo){
        if(lixo instanceof LixoOrganico){
            if(sacoLxOrganico.size()<maxLixo){
                sacoLxOrganico.add(lixo);
            }else return false;
        }else{
            if(sacoLxSeco.size()<maxLixo){
                sacoLxSeco.add(lixo);
            }else return false;
        }
        return true;
    }
    
    public boolean andar(int direcao,int passos){
        //implementar verificações para que o agente não se mova para uma casa que não existe,
        //que esta ocupada por uma lixeira ou agente, ou se tiver um lixo ele deve
        //recolher o lixo antes de ir para a casa. 
        //Para saber a direcão, usar cosntantes da classe Tabuleiro, exemplo:
        //Tabuleiro.LESTE = Esquerda
        return true;//retorna verdadeiro se o agente andou
    }

}
