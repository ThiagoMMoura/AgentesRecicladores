
import java.util.ArrayList;


/**
 *
 * @author Thiago Moura
 */
public class Lixeira extends Peca{
    private int capacidade;
    private ArrayList<Lixo> lixos;

    public Lixeira(String nome, int capacidade) {
        super(nome);
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
            System.out.println("Recebido lixo: "+lx.getNome());
            return lixos.add(lx);
        }
        return false;
    }
    
    @Override
    public String getNome(){
        return super.getNome()+"["+(capacidade-getQtdLixos())+"]";
    }

}
