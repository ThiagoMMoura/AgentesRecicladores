
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

    public Simulador(SaidaSimulador out,int dimenssaoAmbiente,int qtdLixos,int qtdLixeiras, int qtdAgentes) {
        this.out = out;
        ambiente = new Ambiente(dimenssaoAmbiente);
        this.qtdLixos = qtdLixos;
        this.qtdLixeiras = qtdLixeiras;
        this.qtdAgentes = qtdAgentes;
        lixeiras = new ArrayList<>();
        agentes = new ArrayList<>();
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
    
    public void iniciarAmbiente(){
        posicionarLixos(qtdLixos);
        posicionarLixeiras(qtdLixeiras, qtdLixos/qtdLixeiras);
        posicionarAgentes(qtdAgentes, getDimenssao()/2);
        
        out.atualizaCiclo(this.ambiente);
    }
    
    public void iniciarCiclo(){
        for(Agente ag: agentes){
            ag.jogar();
            out.atualizaCiclo(this.ambiente);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        out.atualizaCiclo(this.ambiente);
    }
    
    public void posicionarLixos(int qtd){
        for(int i=0;i<qtd;i++){
            LixoSeco ls = new LixoSeco("LiS"+(i+1));
            Quadrante cs;
            do{
                int linha = (int) (Math.random() * getDimenssao());
                int coluna = (int) (Math.random() * getDimenssao());
                cs = ambiente.getQuadrante(linha, coluna);
            }while(!cs.estaVazio());
            cs.setPeca(ls);
            LixoOrganico lo = new LixoOrganico("LiO"+(i+1));
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
                    if(ambiente.esquerda(linha, coluna)!=null&&
                            (ambiente.esquerda(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.direita(linha, coluna)!=null&&
                            (ambiente.direita(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.baixo(linha, coluna)!=null&&
                            (ambiente.baixo(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.cima(linha, coluna)!=null&&
                            (ambiente.cima(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else b=false;
                }while(b);
            }while(ambiente.posicaoObstruida(linha, coluna));
            LixeiraSeco ls = new LixeiraSeco("LxS"+(i+1), capacidade);
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
                    if(ambiente.esquerda(linha, coluna)!=null&&
                            (ambiente.esquerda(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.direita(linha, coluna)!=null&&
                            (ambiente.direita(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.baixo(linha, coluna)!=null&&
                            (ambiente.baixo(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(ambiente.cima(linha, coluna)!=null&&
                            (ambiente.cima(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else b=false;
                }while(b);
            }while(ambiente.posicaoObstruida(linha, coluna));
            LixeiraOrganico lo = new LixeiraOrganico("LxO"+(i+1), capacidade);
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
            
            Agente ag = new Agente("AG"+(i+1), maxLixo,ml,ambiente);
            ag.setLinha(linha);
            ag.setColuna(coluna);
            qd.setPeca(ag);
            this.agentes.add(ag);
        }
    }

}
