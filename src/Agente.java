
import java.util.ArrayList;


/**
 * A classe Agente define as funções e propriedades do agente.
 * 
 */
public class Agente extends Peca{
    private final int maxLixo;
    private final ArrayList<Lixo> sacoLxOrganico; //Saco de lixo organico, armazena objetos do tipo LixoOrganico
    private final ArrayList<Lixo> sacoLxSeco; //Saco de lixo seco, armazena objetos do tipo LixoSeco
    private final ArrayList<MemoriaLixeira> lixeiras; //Armazena a memória do agente com relação as lixeiras presentes no ambiente
    private final Ambiente ambiente;
    
    /**
     *
     * @param nome
     * @param maxLixo
     * @param lixeiras
     * @param ambiente
     */
    public Agente(String nome,int maxLixo,ArrayList<MemoriaLixeira> lixeiras,Ambiente ambiente){
        super(nome);
        this.maxLixo = maxLixo;
        this.lixeiras = lixeiras;
        sacoLxOrganico = new ArrayList<>();
        sacoLxSeco = new ArrayList<>();
        this.ambiente = ambiente;
    }
    
    public int getQtdLixoOrganico(){
        return sacoLxOrganico.size();
    }
    
    public int getQtdLixoSeco(){
        return sacoLxSeco.size();
    }

    public int getMaxLixo() {
        return maxLixo;
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
            quadrante.setPeca(ambiente.getQuadrante(getLinha(), getColuna()).remover());
            this.setLinha(quadrante.getLinha());
            this.setColuna(quadrante.getColuna());
            return true; //retorna verdadeiro se o agente andou
        }else if(quadrante!=null&&quadrante.temLixo()){
            if(guardarLixo(quadrante.getLixo())){
                quadrante.remover();
                return andar(direcao);
            }else {
                System.out.println("Não foi possivel coletar lixo.");
                return false;
            }
        }else if(quadrante!=null&&quadrante.temLixeira()){
            Lixeira lx = (Lixeira) quadrante.getPeca();
            despejarLixo(lx);
            visitarLixeira(lx);
            return true;
        }else return false;
        
    }
    public void despejarLixo(Lixeira lx){
        if(lx instanceof LixeiraSeco){
            while(!sacoLxSeco.isEmpty()){
                lx.addLixo(sacoLxSeco.remove(0));
            }
        }else{
            while(!sacoLxOrganico.isEmpty()){
                lx.addLixo(sacoLxOrganico.remove(0));
            }
        }
    }
    public void visitarLixeira(Lixeira lx){
        for(MemoriaLixeira lxs: lixeiras){
            if(lxs.equals(lx))lxs.Visitada();
        }
    }
    public void perceberAmbiente(){
        
    }
    
    public void jogar(){
        while(!andar((int) (Math.random() * 4))){
            System.out.println("O Agente "+this.getNome()+" não andou.");
        }
    }

}
