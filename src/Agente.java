
import java.util.ArrayList;


/**
 * A classe Agente define as funções e propriedades do agente.
 * 
 */
public class Agente extends Peca{
    private final int maxLixo;
    private final ArrayList<Lixo> sacoLxOrganico; //Saco de lixo organico, armazena objetos do tipo LixoOrganico
    private final ArrayList<Lixo> sacoLxSeco; //Saco de lixo seco, armazena objetos do tipo LixoSeco
    private final ArrayList<MemoriaLixeira> lixeiras; //Armazena a memória do agente com relação as lixeiras presentes no ambiente
    private final Ambiente ambiente;
    private int contadorDeCiclos;
    private int ultimoCicloDeLixoEncontrado;
    private int direcaoFixa;
    
    /**
     *
     * @param nome
     * @param maxLixo
     * @param lixeiras
     * @param ambiente
     */
    public Agente(String nome,int maxLixo,ArrayList<MemoriaLixeira> lixeiras,Ambiente ambiente){
        super(nome);
        this.maxLixo = maxLixo;
        this.lixeiras = lixeiras;
        sacoLxOrganico = new ArrayList<>();
        sacoLxSeco = new ArrayList<>();
        this.ambiente = ambiente;
        this.contadorDeCiclos =0;
        this.ultimoCicloDeLixoEncontrado = 0;
        direcaoFixa = 0;
    }
    
    public int getQtdLixoOrganico(){
        return sacoLxOrganico.size();
    }
    
    public int getQtdLixoSeco(){
        return sacoLxSeco.size();
    }

    public int getMaxLixo() {
        return maxLixo;
    }
    
    private boolean limparQuadrante(Quadrante qdm){
        if(qdm.temLixo()){
            Lixo lixo = qdm.getLixo();
            if(lixo instanceof LixoOrganico){
                if(sacoLxOrganico.size()<maxLixo){
                    return sacoLxOrganico.add(qdm.recolherLixo());
                }
            }else if(lixo instanceof LixoSeco){
                if(sacoLxSeco.size()<maxLixo){
                    return sacoLxSeco.add(qdm.recolherLixo());
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param direcao Direcao em que o agente vai andar. Pode ser uma das quatro
     * direções definidas na classe <code>{@link Ambiente}</code>:
     * <code>LESTE</code>, <code>OESTE</code>, <code>NORTE</code> e <code>SUL</code>.
     * 
     * @return <code>true</code> - Se o agente se moveu ou depositou lixo.
     * 
     * @see Ambiente
     */
    private boolean andar(int direcao,boolean despejarLixo){
        Quadrante quadrante = ambiente.quadranteAdjacente(getLinha(), getColuna(), 1, direcao);
        if(quadrante!=null&&quadrante.estaVazio()){
            quadrante.setPeca(ambiente.getQuadrante(getLinha(), getColuna()).remover());
            this.setLinha(quadrante.getLinha());
            this.setColuna(quadrante.getColuna());
            System.out.println("Agente andou.");
            return true; //retorna verdadeiro se o agente andou
        }else if(quadrante!=null&&quadrante.temLixo()){
            if(limparQuadrante(quadrante)){
                System.out.println("Lixo recolhido.");
                return andar(direcao,despejarLixo);
            }else {
                System.out.println("Não foi possivel coletar lixo.");
                return false;
            }
        }else if(despejarLixo&&quadrante!=null&&quadrante.temLixeira()){
            Lixeira lx = (Lixeira) quadrante.getPeca();
            return visitarLixeira(lx);
        }else return false;
        
    }
    
    public boolean despejarLixo(Lixeira lx){
        if(lx instanceof LixeiraSeco){
            if(sacoLxSeco.isEmpty())return false;
            while(!sacoLxSeco.isEmpty()){
                if(lx.estaCheia()) return false;
                lx.addLixo(sacoLxSeco.remove(0));
            }
        }else{
            if(sacoLxOrganico.isEmpty())return false;
            while(!sacoLxOrganico.isEmpty()){
                if(lx.estaCheia()) return false;
                lx.addLixo(sacoLxOrganico.remove(0));
            }
        }return true;
    }
    
    public boolean visitarLixeira(Lixeira lx){
        for(MemoriaLixeira lxs: lixeiras){
            if(lxs.equals(lx)){
                lxs.Visitada();
                System.out.println(lxs.getNome()+" foi visitada.");
                if(lxs.isCheia())return false;
            }
        }
        return despejarLixo(lx);
    }
    private Quadrante[][] perceberAmbiente(){
        Quadrante[][] qdm = new Quadrante[4][2];
        for(int d=0;d<4;d++){
            for(int q=0;q<2;q++){
                qdm[d][q]= this.ambiente.quadranteAdjacente(this.getLinha(), this.getColuna(), q+1, d);
            }
        }
        return qdm;
    }

    private int direcaoLixo(){
        Quadrante[][] qdm = perceberAmbiente();
        boolean usar = false;
        for(int q=0;q<2;q++){
            for(int d=0;d<4;d++){
                if(q==0){
                    if(qdm[d][q]!=null&&qdm[d][q].temLixo())return d;
                }else{
                    if(qdm[d][q]!=null&&qdm[d][q-1].estaVazio()&&qdm[d][q].temLixo()) return d;
                    if(usar&&qdm[d][q]!=null&&qdm[d][q].temLixo()){
                        if(d==Ambiente.LESTE||d==Ambiente.OESTE){
                            if(qdm[Ambiente.NORTE][0]!=null&&qdm[Ambiente.NORTE][0].estaVazio())return Ambiente.NORTE;
                            if(qdm[Ambiente.SUL][0]!=null&&qdm[Ambiente.SUL][0].estaVazio())return Ambiente.SUL;
                        }else{
                            if(qdm[Ambiente.LESTE][0]!=null&&qdm[Ambiente.LESTE][0].estaVazio())return Ambiente.LESTE;
                            if(qdm[Ambiente.OESTE][0]!=null&&qdm[Ambiente.OESTE][0].estaVazio())return Ambiente.OESTE;
                        }
                    }
                }
            }
            if(usar)break;
            if(q==1){
                usar=true;
                q--;
            }
        }
        return -1;
    }
    
    private boolean andarDirecaoLixeiraOrganica(){
        MemoriaLixeira maisProxima = null;
        for(MemoriaLixeira lixeira : lixeiras){
            if(lixeira.isOrganico()){
                if(!lixeira.isCheia()){
                    if(maisProxima==null)maisProxima=lixeira;
                    else{
                        int distanciaAnt = Math.abs((maisProxima.getLinha()+maisProxima.getColuna())-(this.getLinha()+this.getColuna()));
                        int distanciaPro = Math.abs((lixeira.getLinha()+lixeira.getColuna())-(this.getLinha()+this.getColuna()));
                        if(distanciaAnt>distanciaPro)maisProxima = lixeira;
                    }
                }
            }
        }
        int distanciaHorizontal = (maisProxima.getColuna()-this.getColuna());
        int distanciaVertical = (maisProxima.getLinha()-this.getLinha());
        
        return andarDirecaoMaisProxima(distanciaHorizontal, distanciaVertical);
    }
    
    private boolean  andarDirecaoLixeiraSeco(){
        MemoriaLixeira maisProxima = null;
        for(MemoriaLixeira lixeira : lixeiras){
            if(lixeira.isSeco()){
                if(!lixeira.isCheia()){
                    if(maisProxima==null)maisProxima=lixeira;
                    else{
                        int distanciaAnt = Math.abs((maisProxima.getLinha()+maisProxima.getColuna())-(this.getLinha()+this.getColuna()));
                        int distanciaPro = Math.abs((lixeira.getLinha()+lixeira.getColuna())-(this.getLinha()+this.getColuna()));
                        if(distanciaAnt>distanciaPro)maisProxima = lixeira;
                    }
                }
            }
        }
        int distanciaHorizontal = (maisProxima.getColuna()-this.getColuna());
        int distanciaVertical = (maisProxima.getLinha()-this.getLinha());
        
        return andarDirecaoMaisProxima(distanciaHorizontal, distanciaVertical);
    }
    
    private boolean andarDirecaoMaisProxima(int distanciaHorizontal, int distanciaVertical){
        if(distanciaHorizontal==0){
            if(distanciaVertical<0){
                if (andar(Ambiente.NORTE, true)) return true;
                else if(andar(Ambiente.OESTE,true)) return true;
                else if(andar(Ambiente.LESTE,true)) return true;
                else return andar(Ambiente.SUL, true);  
            }else{
                if (andar(Ambiente.SUL, true)) return true;
                else if(andar(Ambiente.OESTE,true)) return true;
                else if(andar(Ambiente.LESTE,true)) return true;
                else return andar(Ambiente.NORTE, true);  
            }
        }else if(distanciaVertical==0){
            if(distanciaHorizontal<0){
                if (andar(Ambiente.LESTE, true)) return true;
                else if(andar(Ambiente.NORTE,true)) return true;
                else if(andar(Ambiente.SUL,true)) return true;
                else return andar(Ambiente.OESTE, true);  
            }else{
                if (andar(Ambiente.OESTE, true)) return true;
                else if(andar(Ambiente.SUL,true)) return true;
                else if(andar(Ambiente.LESTE,true)) return true;
                else return andar(Ambiente.NORTE, true);  
            }
        }else{
            if(distanciaHorizontal<0){
                if(andar(Ambiente.LESTE,true)) return true;
                else return andarDirecaoMaisProxima(0, distanciaVertical);
            }else if(distanciaHorizontal>0){
                if(andar(Ambiente.OESTE,true)) return true;
                else return andarDirecaoMaisProxima(0, distanciaVertical);
            }else if(distanciaVertical<0){
                if(andar(Ambiente.NORTE,true)) return true;
                else return andarDirecaoMaisProxima(distanciaHorizontal, 0);
            }else{
                if(andar(Ambiente.SUL,true)) return true;
                else return andarDirecaoMaisProxima(distanciaHorizontal, 0);
            }
        }
    }
    
    public void jogar(){
        contadorDeCiclos++;
        System.out.println("Ciclo "+contadorDeCiclos+" do "+getNome());
        if(!ambiente.estaLimpo()&&sacoLxSeco.size()<maxLixo&&sacoLxOrganico.size()<maxLixo){
            /**
             * Se o ambiente não estiver limpo e se nenhum dos sacos de lixo 
             * estiver cheio o Agente vai em busca de lixo;
             */
            int dl = direcaoLixo();
            if(dl==-1){
                if(contadorDeCiclos-ultimoCicloDeLixoEncontrado<=3){
                    System.out.println("Não encontrou lixo para pegar.");
                    /**
                     * Se o Agente não tiver encontrado nenhum lixo ao seu redor
                     * ele vai numa direção aleatória.
                     */
                    int direcao;
                    if(!ambiente.posicaoObstruida(getLinha(), getColuna())){
                        do{
                            direcao = (int) (Math.random() * 4);
                        }while(!andar(direcao,false));
                    }else System.out.println("Posição obstruida, passar a vez.");
                }else{
                    System.out.println("A mais de 3 ciclos sem encontrar lixo.");
                    /**
                     * Se a 3 ciclos o agente não encontrar nenhum lixo, ele vai
                     * anda em linha, escolhendo esquerda ou direita aleatóriamente.
                     */
                    int[] direcoes = new int[]{Ambiente.LESTE,Ambiente.OESTE};
                    int direcao;
                    if(contadorDeCiclos-ultimoCicloDeLixoEncontrado==4){
                        if(!ambiente.quadranteAdjacenteVazio(getLinha(), getColuna(), 1, Ambiente.LESTE)&&
                                !ambiente.quadranteAdjacenteVazio(getLinha(), getColuna(), 1, Ambiente.OESTE)){
                            direcoes = new int[]{Ambiente.NORTE,Ambiente.SUL};
                        }
                        if(!ambiente.posicaoObstruida(getLinha(), getColuna())){
                            do{
                                int r = (int)(Math.random()*direcoes.length);
                                direcao = direcoes[r];
                            }while(!andar(direcao,false));
                            direcaoFixa = direcao;
                        }else System.out.println("Posição obstruida, passar a vez.");
                    }else{
                        if(!andar(direcaoFixa, false)){
                            if(!ambiente.posicaoObstruida(getLinha(), getColuna())){
                                direcoes = new int[]{Ambiente.NORTE,Ambiente.SUL};
                                if(!ambiente.quadranteAdjacenteVazio(getLinha(), getColuna(), 1, Ambiente.NORTE)&&
                                        !ambiente.quadranteAdjacenteVazio(getLinha(), getColuna(), 1, Ambiente.SUL)){
                                    direcoes = new int[]{Ambiente.LESTE,Ambiente.OESTE};
                                }
                                do{
                                    direcao = direcoes[(int)(Math.random()*direcoes.length)];
                                }while(!andar(direcao,false));
                                ultimoCicloDeLixoEncontrado=contadorDeCiclos;
                            }else System.out.println("Posição obstruida, passar a vez.");
                        }
                    }
                }
            }else {
                andar(dl,false);
                ultimoCicloDeLixoEncontrado = contadorDeCiclos;
            }
        }else{
            /**
             * Se um dos sacos de lixo do agente estiver cheio ou es o ambiente já
             * estive limpo, ele vai atrás de uma lixeira para despejar o lixo.
             */
            if(sacoLxSeco.size()==maxLixo){
                System.out.println("Saco de lixo Seco cheio, procurando lixeira...");
                andarDirecaoLixeiraSeco();
            }else if(sacoLxOrganico.size()==maxLixo){
                System.out.println("Saco de lixo Orgânico cheio, procurando lixeira...");
                andarDirecaoLixeiraOrganica();
            }else if(sacoLxSeco.size()>0){
                System.out.println("Procurando lixeira para esvaziar saco de lixo Seco...");
                andarDirecaoLixeiraSeco();
            }else if(sacoLxOrganico.size()>0){
                System.out.println("Procurando lixeira para esvaziar saco de lixo Orgânico...");
                andarDirecaoLixeiraOrganica();
            }else {
                int direcao;
                do{
                    direcao = (int) (Math.random() * 4);
                }while(!andar(direcao,false));
                System.out.println("Ambiente limpo!");
            }
            ultimoCicloDeLixoEncontrado = contadorDeCiclos;
        }
        System.out.println("Ultimo ciclo em que foi encontrado lixo: "+ultimoCicloDeLixoEncontrado);
        System.out.println();
    }

}
