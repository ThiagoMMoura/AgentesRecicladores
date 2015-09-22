
/**
 *
 * @author Thiago Moura
 */
public class MemoriaLixeiras extends Lixeira{
    private int qtdLixos;
    private Lixeira lixeira;
        
    public MemoriaLixeiras(Lixeira lixeira,int linha, int coluna){
        super(lixeira.getNome(),lixeira.getCapacidade());
        this.lixeira = lixeira;
    }
}
