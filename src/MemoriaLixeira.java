
/**
 * A classe MemoriaLixeira aponta par as lixeiras do ambiente para o agente consultar sua posição e se ela já está cheia
 * 
 */
public class MemoriaLixeira extends Lixeira{
    private Lixeira lixeira;
        
    public MemoriaLixeira(Lixeira lixeira){
        super(lixeira.getNome(),lixeira.getCapacidade());
        this.lixeira = lixeira;
    }
    
    public boolean cheia(){
        return super.getCapacidade()==super.getQtdLixos();
    }
}
