
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
    private final Ambiente ambiente;
    
    public Agente(String nome,int maxLixo,ArrayList<MemoriaLixeira> lixeiras,Ambiente ambiente){
        super(nome);
        this.maxLixo = maxLixo;
        this.lixeiras = lixeiras;
        sacoLxOrganico = new ArrayList<>();
        sacoLxSeco = new ArrayList<>();
        this.ambiente = ambiente;
    }
    
    private boolean guardarLixo(Lixo lixo){
        if(lixo!=null){
            if(lixo instanceof LixoOrganico){
                if(sacoLxOrganico.size()<maxLixo){
                    sacoLxOrganico.add(lixo);
                }else return false;
            }else if(lixo instanceof LixoSeco){
                if(sacoLxSeco.size()<maxLixo){
                    sacoLxSeco.add(lixo);
                }else return false;
            }else return false;
            return true;
        }return false;
    }
    
    public boolean andar(int direcao){
        Quadrante quadrante = ambiente.quadranteAdjacente(getLinha(), getColuna(), 1, direcao);
        if(quadrante!=null&&quadrante.estaVazio()){
            quadrante.setPeca(this);
            ambiente.getQuadrante(getLinha(), getColuna()).limpar();
            this.setLinha(quadrante.getLinha());
            this.setColuna(quadrante.getColuna());
            return true; //retorna verdadeiro se o agente andou
        }else if(quadrante!=null&&quadrante.temLixo()){
            if(guardarLixo(quadrante.getLixo())){
                quadrante.limpar();
                return andar(direcao);
            }else {
                System.out.println("Não foi possivel coletar lixo.");
                return false;
            }
        }else return false;
        
    }
    
    public void perceberAmbiente(){
        
    }

}
