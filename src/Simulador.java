
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Thiago Moura
 */
public class Simulador {
    private final Ambiente ambiente;
    private final int qtdLixos;
    private final int qtdLixeiras;
    private final int qtdAgentes;
    private final ArrayList<Lixeira> lixeiras;
    private final ArrayList<Agente> agentes;
    private final SaidaSimulador out;
    private int proximoAgente;

    public Simulador(SaidaSimulador out,int dimenssaoAmbiente,int qtdLixos,int qtdLixeiras, int qtdAgentes) {
        this.out = out;
        ambiente = new Ambiente(dimenssaoAmbiente);
        this.qtdLixos = qtdLixos;
        this.qtdLixeiras = qtdLixeiras;
        this.qtdAgentes = qtdAgentes;
        lixeiras = new ArrayList<>();
        agentes = new ArrayList<>();
        proximoAgente = 0;
    }
    
    public int getDimenssao(){
        return ambiente.getDimenssao();
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public ArrayList<Lixeira> getLixeiras() {
        return lixeiras;
    }

    public ArrayList<Agente> getAgentes() {
        return agentes;
    }
    
    public boolean iniciarAmbiente(){
        posicionarLixos(qtdLixos);
        posicionarLixeiras(qtdLixeiras, qtdLixos/qtdLixeiras);
        posicionarAgentes(qtdAgentes, getDimenssao()/2);
        
        out.atualizaCiclo(this.ambiente);
        return true;
    }
    
    public void iniciarCiclo(){
        while(proximoAgente<agentes.size()){
            agentes.get(proximoAgente).jogar();
            out.atualizaCiclo(this.ambiente);
            proximoAgente++;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        proximoAgente = 0;
    }
    
    public void iniciarProximoAgente(){
        agentes.get(proximoAgente).jogar();
        out.atualizaCiclo(this.ambiente);
        proximoAgente++;
        if(proximoAgente==agentes.size())proximoAgente=0;
    }
    
    public void posicionarLixos(int qtd){
        for(int i=0;i<qtd;i++){
            LixoSeco ls = new LixoSeco("S"+(i+1));
            Quadrante cs;
            do{
                int linha = (int) (Math.random() * getDimenssao());
                int coluna = (int) (Math.random() * getDimenssao());
                cs = ambiente.getQuadrante(linha, coluna);
            }while(!cs.estaVazio());
            cs.setPeca(ls);
            LixoOrganico lo = new LixoOrganico("O"+(i+1));
            do{
                int linha = (int) (Math.random() * getDimenssao());
                int coluna = (int) (Math.random() * getDimenssao());
                cs = ambiente.getQuadrante(linha, coluna);
            }while(!cs.estaVazio());
            cs.setPeca(lo);
        }
    }
    
    public void posicionarLixeiras(int qtd,int capacidade){
        Quadrante cs;
        int linha,coluna;
        for(int i=0;i<qtd;i++){
            boolean b;
            do{
                do{
                    do{
                        linha = (int) (Math.random() * getDimenssao());
                        coluna = (int) (Math.random() * getDimenssao());
                        cs = ambiente.getQuadrante(linha, coluna);
                    }while(!cs.estaVazio());
                    //Bloco de IFs para impedir que as lixeiras se bloqueiem (Tentar reduzir código)
                    if(isCantoBloqueado(linha, coluna)){
                        b=true;
                    }else if(ambiente.esquerda(linha, coluna)==null||
                            ambiente.direita(linha, coluna)==null||
                            ambiente.baixo(linha, coluna)==null||
                            ambiente.cima(linha, coluna)==null){
                        b=true;
                    }else if(ambiente.esquerda(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else if(ambiente.direita(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else if(ambiente.baixo(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else b = ambiente.cima(linha, coluna).getPeca()instanceof Lixeira;
                }while(b);
            }while(ambiente.posicaoObstruida(linha, coluna));
            LixeiraSeco ls = new LixeiraSeco("Ls"+(i+1), capacidade);
            ls.setLinha(linha);
            ls.setColuna(coluna);
            cs.setPeca(ls);
            this.lixeiras.add(ls);
            
            do{
                do{
                    do{
                        linha = (int) (Math.random() * getDimenssao());
                        coluna = (int) (Math.random() * getDimenssao());
                        cs = ambiente.getQuadrante(linha, coluna);
                    }while(!cs.estaVazio());
                    //Bloco de IFs para impedir que as lixeiras se bloqueiem (Tentar reduzir código)
                    if(isCantoBloqueado(linha, coluna)){
                        b=true;
                    }else if(ambiente.esquerda(linha, coluna)==null||
                            ambiente.direita(linha, coluna)==null||
                            ambiente.baixo(linha, coluna)==null||
                            ambiente.cima(linha, coluna)==null){
                        b=true;
                    }else if(ambiente.esquerda(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else if(ambiente.direita(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else if(ambiente.baixo(linha, coluna).getPeca()instanceof Lixeira){
                        b=true;
                    }else b = ambiente.cima(linha, coluna).getPeca()instanceof Lixeira;
                }while(b);
            }while(ambiente.posicaoObstruida(linha, coluna));
            LixeiraOrganico lo = new LixeiraOrganico("Lo"+(i+1), capacidade);
            lo.setLinha(linha);
            lo.setColuna(coluna);
            cs.setPeca(lo);
            this.lixeiras.add(lo);
        }
    }
    public void posicionarAgentes(int qtd,int maxLixo){
        //Implementar posicionamento dos Agentes no Ambiente
        Quadrante qd;
        int linha,coluna;
        for(int i=0;i<qtd;i++){
            boolean b;
            do{
                do{
                    do{
                        linha = (int) (Math.random() * getDimenssao());
                        coluna = (int) (Math.random() * getDimenssao());
                        qd = ambiente.getQuadrante(linha, coluna);
                    }while(!qd.estaVazio());
                    //Bloco de IFs para impedir que os Agentes se bloqueiem (Tentar reduzir código)
                    if(ambiente.esquerda(linha, coluna)!=null&&
                            (ambiente.esquerda(linha, coluna).getPeca()instanceof Agente)){
                        b=true;
                    }else if(ambiente.direita(linha, coluna)!=null&&
                            (ambiente.direita(linha, coluna).getPeca()instanceof Agente)){
                        b=true;
                    }else if(ambiente.baixo(linha, coluna)!=null&&
                            (ambiente.baixo(linha, coluna).getPeca()instanceof Agente)){
                        b=true;
                    }else if(ambiente.cima(linha, coluna)!=null&&
                            (ambiente.cima(linha, coluna).getPeca()instanceof Agente)){
                        b=true;
                    }else b=false;
                }while(b);
            }while(ambiente.posicaoObstruida(linha, coluna));
            
            ArrayList<MemoriaLixeira> ml = new ArrayList<>();
            Iterator it = lixeiras.iterator();
            while(it.hasNext()) ml.add(new MemoriaLixeira((Lixeira) it.next()));
            
            Agente ag = new Agente("A"+(i+1), maxLixo,ml,ambiente);
            ag.setLinha(linha);
            ag.setColuna(coluna);
            qd.setPeca(ag);
            this.agentes.add(ag);
        }
    }
    
    /**
     * Método para verificar se a lixeira inserida na posição definida pelos 
     * parâmetros não bloqueará os cantos do ambiente, podendo deixar trancado
     * um lixo ou agente.
     * 
     * @param linha
     * @param coluna
     * @return <code>true</code> - Se existir outra lixeira que formará bloqueio,
     * caso contrário retorna <code>false</code>.
     */
    public boolean isCantoBloqueado(int linha, int coluna){
        boolean b;
        int dim = ambiente.getDimenssao();
        if(linha==0 && coluna==1 && 
                ambiente.getQuadrante(1, 0).temLixeira()){
            b=true;
        }else if(linha==1 && coluna==0 && 
                ambiente.getQuadrante(0, 1).temLixeira()){
            b=true;
        }else if(linha==0 && coluna==dim-2 && 
                ambiente.getQuadrante(1, dim-1).temLixeira()){
            b=true;
        }else if(linha==1 && coluna==dim-1 && 
                ambiente.getQuadrante(1, 0).temLixeira()){
            b=true;
        }else if(linha==dim-2 && coluna==0 && 
                ambiente.getQuadrante(dim-1, 1).temLixeira()){
            b=true;
        }else if(linha==dim-1 && coluna==1 && 
                ambiente.getQuadrante(dim-2, 0).temLixeira()){
            b=true;
        }else if(linha==dim-2 && coluna==dim-1 && 
                ambiente.getQuadrante(dim-1, dim-2).temLixeira()){
            b=true;
        }else if(linha==dim-1 && coluna==dim-2 && 
                ambiente.getQuadrante(dim-2, dim-1).temLixeira()){
            b=true;
        }else b=false;
        return b;
    }
    
    public boolean sujeiraEliminada(){
        if(ambiente.estaLimpo()){
            for(Lixeira lx: lixeiras){
                if(!lx.estaCheia())return false;
            }
            return true;
        }
        return false;
    }
}
