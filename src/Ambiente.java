
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A classe Ambiente define o ambiente e a posição dos quadrantes.
 * <dl compact>
 * <dt>Direções<dd> A classe <code>Ambiente</code> define quatro direções em
 * constantes, são elas:
 * <ul>
 * <li><code>LESTE</code></li>
 * <li><code>OESTE</code></li>
 * <li><code>NORTE</code></li>
 * <li><code>SUL</code></li>
 * </ul>
 * </dl>
 */
public class Ambiente {
    private final Quadrante[][] quadrantes;
    private final int dimenssao;
    private final ArrayList<Lixeira> lixeiras;
    private final ArrayList<Agente> agentes;
    public static final int LESTE = 0;
    public static final int OESTE = 1;
    public static final int NORTE = 2;
    public static final int SUL = 3;
    
    public Ambiente(int dimenssao){
        this.dimenssao = dimenssao;
        this.quadrantes = new Quadrante[dimenssao][dimenssao];
        this.lixeiras = new ArrayList<>();
        this.agentes = new ArrayList<>();
        inicializaQuadrantes();
    }

    public Quadrante[][] getQuadrantes() {
        return quadrantes;
    }
    
    public Quadrante getQuadrante(int linha, int coluna){
        if(linha>=this.dimenssao||linha<0||coluna>=this.dimenssao||coluna<0){
            return null;
        }else return quadrantes[linha][coluna];
    }

    public boolean quadranteVazio(int linha, int coluna) throws QuadranteNotExistException{
        if(linha>=this.dimenssao||linha<0||coluna>=this.dimenssao||coluna<0){
            throw new QuadranteNotExistException();
        }else return quadrantes[linha][coluna].estaVazio();
    }
    public int getDimenssao() {
        return dimenssao;
    }

    public ArrayList<Lixeira> getLixeiras() {
        return lixeiras;
    }

    public ArrayList<Agente> getAgentes() {
        return agentes;
    }
    
    private void inicializaQuadrantes(){
        for(int l=0;l<this.dimenssao;l++){
            for(int c=0;c<this.dimenssao;c++){
                this.quadrantes[l][c] = new Quadrante(l,c);
            }
        }
    }
    
    /**
     * Método para buscar uma posição em uma das laterais de uma casa
     * 
     * @param linha
     * @param coluna
     * @param distancia Se a distancia definida for igual à <code>0</code>, então
     * ele irá retornar a posição atual, caso contrário ele ira retornar um quadrante
     * tantos quadrantes de <code>distancia</code>.
     * @param direcao
     * @return <code>Quadrante</code> - Se existir um quadrante na direção definida,
     * caso contrário retorna <code>null</code>.
     */
        public Quadrante quadranteAdjacente(int linha, int coluna, int distancia, int direcao){
        if(distancia>0){
            if(direcao==NORTE){
                if(linha-distancia>=0){
                    return this.quadrantes[linha-distancia][coluna];
                }
            }else if(direcao==SUL){
                if(linha+distancia<this.dimenssao){
                    return this.quadrantes[linha+distancia][coluna];
                }
            }else if(direcao==OESTE){
                if(coluna+distancia<this.dimenssao){
                    return this.quadrantes[linha][coluna+distancia];
                }
            }else{
                if(coluna-distancia>=0){
                    return this.quadrantes[linha][coluna-distancia];
                }
            }
            return null;
        }else return this.quadrantes[linha][coluna];
    }
    
    public boolean quadranteAdjacenteVazio(int linha,int coluna, int distancia, int direcao){
        if(quadranteAdjacente(linha, coluna, distancia, direcao)==null){
            return false;
        }else return quadranteAdjacente(linha, coluna, distancia, direcao).estaVazio();
    }
    
    public boolean posicaoObstruida(int linha,int coluna){
        if(esquerda(linha,coluna)!=null){
            if(esquerda(linha,coluna).estaVazio()) return false;
        }if(direita(linha,coluna)!=null){
            if(direita(linha,coluna).estaVazio()) return false;
        }if(cima(linha,coluna)!=null){
            if(cima(linha,coluna).estaVazio()) return false;
        }if(baixo(linha,coluna)!=null){
            if(baixo(linha,coluna).estaVazio()) return false;
        }
        return true;
    }
    
    public Quadrante esquerda(int linha, int coluna){
        return esquerda(linha,coluna,1);
    }
    public Quadrante esquerda(int linha, int coluna,int distancia){
        return quadranteAdjacente(linha, coluna, distancia, LESTE);
    }
    
    public Quadrante direita(int linha, int coluna){
        return direita(linha,coluna,1);
    }
    public Quadrante direita(int linha,int coluna, int distancia){
        return quadranteAdjacente(linha, coluna, distancia, OESTE);
    }
    
    public Quadrante cima(int linha, int coluna){
        return cima(linha,coluna,1);
    }
    public Quadrante cima(int linha, int coluna, int distancia){
        return quadranteAdjacente(linha, coluna, distancia, NORTE);
    }
    
    public Quadrante baixo(int linha, int coluna){
        return baixo(linha,coluna,1);
    }
    public Quadrante baixo(int linha, int coluna, int distancia){
        return quadranteAdjacente(linha, coluna, distancia, SUL);
    }
    
    public boolean estaLimpo(){
        for(int l=0;l<dimenssao;l++){
            for(int c=0;c<dimenssao;c++){
                if(quadrantes[l][c].temLixo())return false;
            }
        }
        return true;
    }

}
