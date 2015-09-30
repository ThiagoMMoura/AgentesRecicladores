
/**
 * A clase <code>Quadrante</code> define as casas do ambiente e a peça que ela contém, caso não tem 
 *nenhuma peça a casa tem valor <code>null</code>.
 * 
 */
public class Quadrante {
    private Peca peca;
    private final int linha;
    private final int coluna;
    
    public Quadrante(Peca peca,int linha,int coluna){
        this.peca = peca;
        this.linha = linha;
        this.coluna = coluna;
    }
    
    public Quadrante(int linha,int coluna){
        this.peca = null;
        this.linha = linha;
        this.coluna = coluna;
    }
    
    /**
     *
     * @return Retorna a peça que tiver nesse quadrante, caso não tenha nenhuma 
     * peça, então ele retorna <code>null</code>.
     */
    public Peca getPeca(){
        return this.peca;
    }
    
    public Lixo getLixo(){
        if(!estaVazio()&&this.peca instanceof Lixo){
            return (Lixo) this.peca;
        }
        return null;
    }
    
    public Lixo recolherLixo(){
        if(getLixo()!=null) return (Lixo) remover();
        return null;
    }
    /**
     *
     * @param peca A peca pode ser um objeto de um dos tipos: Peca, Lixo,
     * LixoSeco, LixoOrganico, Lixeira, LixeriraSeco, LixeiraOrganico, Agente ou
     * qualquer classe que herde uma das classes citadas.
     * @return <code>true</code> - Se o quadrante estiver vazio e a peça tiver sito setada.
     * <p> <code>false</code> - Se o quadrante já estiver ocupado e a peça então não puder ser setada.
     */
    public boolean setPeca(Peca peca) {
        if(estaVazio()){
            this.peca = peca;
            return true;
        }
        return false;
    }
    
    /**
     *
     * @return <code>true</code> - Se não tiver nenhum Agente,Lixo ou Lixeira nesse quadrante.
     */
    public boolean estaVazio(){
        return this.peca==null;
    }
    
    /**
     *
     * @return <code>true</code> - Se tiver um Lixo nesse quadrante.
     */
    public boolean temLixo(){
        if(estaVazio())return false;
        return this.peca instanceof Lixo;
    }
    
    /**
     *
     * @return <code>true</code> - Se tiver uma Lixera nesse quadrante.
     */
    public boolean temLixeira(){
        if(estaVazio())return false;
        return this.peca instanceof Lixeira;
    }
    
    /**
     *
     * @return <code>true</code> - Se tiver um Agente nesse quadrante.
     */
    public boolean temAgente(){
        if(estaVazio())return false;
        return this.peca instanceof Agente;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    public Peca remover(){
        Peca oldPeca = this.peca;
        this.peca = null;
        return oldPeca;
    }
    
}
