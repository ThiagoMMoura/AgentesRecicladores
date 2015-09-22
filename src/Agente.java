
import java.util.ArrayList;


/**
 *
 * @author Thiago Moura
 */
public class Agente extends Peca{
    private int maxLixo;
    private ArrayList<Lixo> sacoLxOrganico;
    private ArrayList<Lixo> sacoLxSeco;
    private ArrayList<MemoriaLixeiras> lixeiras;
    
    public Agente(String nome,int maxLixo,ArrayList<MemoriaLixeiras> lixeiras){
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
    
    public void posicionar(Tabuleiro tab){
        Casa cs;
        do{
            this.setLinha((int) (Math.random() * tab.getDimenssao()));
            this.setColuna((int) (Math.random() * tab.getDimenssao()));
            cs = tab.getCasa(this.getLinha(), this.getColuna());
        }while(!cs.casaVazia());
        cs.setPeca(this);
    }

}
