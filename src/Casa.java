
/**
 * A clase Casa define as casas do ambiente e a peça que ela contém, caso não tem 
 * nenhuma peça a casa tem valor null.
 * 
 */
public class Casa {
    private Peca peca;
    
    public Casa(Peca peca){
        this.peca = peca;
    }
    
    public Casa(){
        this.peca = null;
    }
    
    public Peca getPeca(){
        if(peca!=null){
            return peca;
        }else return null;
    }

    public boolean setPeca(Peca peca) {
        if(this.peca==null){
            this.peca = peca;
            return true;
        }
        return false;
    }
    
    public boolean casaVazia(){
        return this.peca==null;
    }
    

}
