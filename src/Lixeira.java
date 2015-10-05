
import java.util.ArrayList;

public class Lixeira extends Peca{
    private final int capacidade;
    private final ArrayList<Lixo> lixos;

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
    
    public boolean estaCheia(){
        return capacidade == lixos.size();
    }
    
    public boolean addLixo(Lixo lx){
        if(lixos.size()<capacidade){
            System.out.println("Despejou lixo: "+lx.getNome());
            return lixos.add(lx);
        }
        return false;
    }
    
    @Override
    public String getNome(){
        return super.getNome();
    }

}
