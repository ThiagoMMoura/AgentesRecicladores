
/**
 * A classe MemoriaLixeira aponta par as lixeiras do ambiente para o agente consultar sua posição e se ela já está isCheia
 * 
 */
public class MemoriaLixeira extends Lixeira{
    private final Lixeira lixeira;
    private int qtdLixos;
        
    public MemoriaLixeira(Lixeira lixeira){
        super(lixeira.getNome(),lixeira.getCapacidade());
        this.lixeira = lixeira;
        this.qtdLixos = 0;
    }
    
    public boolean isCheia(){
        return super.getCapacidade()==this.qtdLixos;
    }
    
    public boolean isOrganico(){
        return lixeira instanceof LixeiraOrganico;
    }
    
    public boolean isSeco(){
        return lixeira instanceof LixeiraSeco;
    }
    public void Visitada(){
        this.qtdLixos = lixeira.getQtdLixos();
    }
    
    public boolean equals(Lixeira lx){
        return lx.getNome().equals(this.lixeira.getNome());
    }
    
    @Override
    public int getLinha(){
        return lixeira.getLinha();
    }
    
    @Override
    public int getColuna(){
        return lixeira.getColuna();
    }
}
