
import java.util.ArrayList;

/**
 * A classe Tabuleiro define o ambiente e a posição das casas.
 * 
 */
public class Tabuleiro {
    private Casa[][] casas;
    private int dimenssao;
    private ArrayList<Lixeira> lixeiras;
    public static final int LESTE = 0;
    public static final int OESTE = 1;
    public static final int NORTE = 2;
    public static final int SUL = 3;
    
    public Tabuleiro(int dimenssao){
        this.dimenssao = dimenssao;
        this.casas = new Casa[dimenssao][dimenssao];
        this.lixeiras = new ArrayList<>();
        inicializaCasas();
    }

    public Casa[][] getCasas() {
        return casas;
    }
    
    public Casa getCasa(int linha, int coluna){
        return casas[linha][coluna];
    }

    public int getDimenssao() {
        return dimenssao;
    }

    public ArrayList<Lixeira> getLixeiras() {
        return lixeiras;
    }
    
    private void inicializaCasas(){
        for(int l=0;l<this.dimenssao;l++){
            for(int c=0;c<this.dimenssao;c++){
                this.casas[l][c] = new Casa();
            }
        }
    }
    
    public void posicionarLixos(int qtd){
        for(int i=0;i<qtd;i++){
            LixoSeco ls = new LixoSeco("LiS"+(i+1));
            Casa cs;
            do{
                int linha = (int) (Math.random() * dimenssao);
                int coluna = (int) (Math.random() * dimenssao);
                cs = getCasa(linha, coluna);
            }while(!cs.casaVazia());
            cs.setPeca(ls);
            LixoOrganico lo = new LixoOrganico("LiO"+(i+1));
            do{
                int linha = (int) (Math.random() * dimenssao);
                int coluna = (int) (Math.random() * dimenssao);
                cs = getCasa(linha, coluna);
            }while(!cs.casaVazia());
            cs.setPeca(lo);
        }
    }
    
    public void posicionarLixeiras(int qtd,int capacidade){
        Casa cs;
        int linha,coluna;
        for(int i=0;i<qtd;i++){
            boolean b;
            do{
                do{
                    do{
                        linha = (int) (Math.random() * dimenssao);
                        coluna = (int) (Math.random() * dimenssao);
                        cs = getCasa(linha, coluna);
                    }while(!cs.casaVazia());
                    //Bloco de IFs para impedir que as lixeiras se bloqueiem (Tentar reduzir código)
                    if(esquerda(linha, coluna)!=null&&
                            (esquerda(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(direita(linha, coluna)!=null&&
                            (direita(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(baixo(linha, coluna)!=null&&
                            (baixo(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(cima(linha, coluna)!=null&&
                            (cima(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else b=false;
                }while(b);
            }while(posicaoObstruida(linha, coluna));
            LixeiraSeco ls = new LixeiraSeco("LxS"+(i+1), capacidade);
            ls.setLinha(linha);
            ls.setColuna(coluna);
            cs.setPeca(ls);
            this.lixeiras.add(ls);
            
            do{
                do{
                    do{
                        linha = (int) (Math.random() * dimenssao);
                        coluna = (int) (Math.random() * dimenssao);
                        cs = getCasa(linha, coluna);
                    }while(!cs.casaVazia());
                    //Bloco de IFs para impedir que as lixeiras se bloqueiem (Tentar reduzir código)
                    if(esquerda(linha, coluna)!=null&&
                            (esquerda(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(direita(linha, coluna)!=null&&
                            (direita(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(baixo(linha, coluna)!=null&&
                            (baixo(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else if(cima(linha, coluna)!=null&&
                            (cima(linha, coluna).getPeca()instanceof Lixeira)){
                        b=true;
                    }else b=false;
                }while(b);
            }while(posicaoObstruida(linha, coluna));
            LixeiraOrganico lo = new LixeiraOrganico("LxO"+(i+1), capacidade);
            lo.setLinha(linha);
            lo.setColuna(coluna);
            cs.setPeca(lo);
            this.lixeiras.add(lo);
        }
    }
    public void posicionarAgentes(int qtd,int capacidadeSaco){
        //Implementar posicionamento dos Agentes no Tabuleiro
    }
    
    //Método para buscar uma posição em uma das laterais de uma casa
    public Casa posicaoRelativa(int linha, int coluna, int distancia, int direcao){
        if(distancia>0){
            if(direcao==NORTE){
                if(linha-distancia>=0){
                    return this.casas[linha-distancia][coluna];
                }
            }else if(direcao==SUL){
                if(linha+distancia<this.dimenssao){
                    return this.casas[linha+distancia][coluna];
                }
            }else if(direcao==OESTE){
                if(coluna+distancia<this.dimenssao){
                    return this.casas[linha][coluna+distancia];
                }
            }else{
                if(coluna-distancia>=0){
                    return this.casas[linha][coluna-distancia];
                }
            }
            return null;
        }else return this.casas[linha][coluna];
    }
    
    public boolean posicaoObstruida(int linha,int coluna){
        if(esquerda(linha,coluna)!=null){
            if(esquerda(linha,coluna).casaVazia()) return false;
        }if(direita(linha,coluna)!=null){
            if(direita(linha,coluna).casaVazia()) return false;
        }if(cima(linha,coluna)!=null){
            if(cima(linha,coluna).casaVazia()) return false;
        }if(baixo(linha,coluna)!=null){
            if(baixo(linha,coluna).casaVazia()) return false;
        }
        return true;
    }
    
    public Casa esquerda(int linha, int coluna){
        return esquerda(linha,coluna,1);
    }
    public Casa esquerda(int linha, int coluna,int distancia){
        return posicaoRelativa(linha, coluna, distancia, LESTE);
    }
    
    public Casa direita(int linha, int coluna){
        return direita(linha,coluna,1);
    }
    public Casa direita(int linha,int coluna, int distancia){
        return posicaoRelativa(linha, coluna, distancia, OESTE);
    }
    
    public Casa cima(int linha, int coluna){
        return cima(linha,coluna,1);
    }
    public Casa cima(int linha, int coluna, int distancia){
        return posicaoRelativa(linha, coluna, distancia, NORTE);
    }
    
    public Casa baixo(int linha, int coluna){
        return baixo(linha,coluna,1);
    }
    public Casa baixo(int linha, int coluna, int distancia){
        return posicaoRelativa(linha, coluna, distancia, SUL);
    }
}
