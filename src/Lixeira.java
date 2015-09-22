
import java.util.ArrayList;


/**
 *
 * @author Thiago Moura
 */
public class Lixeira extends Peca{
    private int capacidade;
    private ArrayList<Lixo> lixos;

    public Lixeira(String nome, int capacidade) {
        super(nome+"["+capacidade+"]");
        this.lixos = new ArrayList<>();
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public int getQtdLixos(){
        return lixos.size();
    }
    
    public boolean addLixo(Lixo lx){
        if(lixos.size()<capacidade){
            lixos.add(lx);
            super.setNome(super.getNome().split("[")[0]+"["+(capacidade-getQtdLixos())+"]");
            return true;
        }
        return false;
    }

}
