
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
    private int contadorCiclos;
    
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
        this.contadorCiclos = 0;
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
    
    private boolean guardarLixo(Lixo lixo){
        if(lixo!=null){
            if(lixo instanceof LixoOrganico){
                if(sacoLxOrganico.size()<maxLixo){
                    sacoLxOrganico.add(lixo);
                }else return false;
            }else if(lixo instanceof LixoSeco){
                if(sacoLxSeco.size()<maxLixo){
                    sacoLxSeco.add(lixo);
                }else return false;
            }else return false;
            return true;
        }return false;
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
            return true; //retorna verdadeiro se o agente andou
        }else if(quadrante!=null&&quadrante.temLixo()){
            if(guardarLixo(quadrante.getLixo())){
                quadrante.remover();
                return andar(direcao,despejarLixo);
            }else {
                System.out.println("Não foi possivel coletar lixo.");
                return false;
            }
        }else if(despejarLixo&&quadrante!=null&&quadrante.temLixeira()){
            Lixeira lx = (Lixeira) quadrante.getPeca();
            despejarLixo(lx);
            visitarLixeira(lx);
            return true;
        }else return false;
        
    }
    public void despejarLixo(Lixeira lx){
        if(lx instanceof LixeiraSeco){
            while(!sacoLxSeco.isEmpty()){
                lx.addLixo(sacoLxSeco.remove(0));
            }
        }else{
            while(!sacoLxOrganico.isEmpty()){
                lx.addLixo(sacoLxOrganico.remove(0));
            }
        }
    }
    public void visitarLixeira(Lixeira lx){
        for(MemoriaLixeira lxs: lixeiras){
            if(lxs.equals(lx))lxs.Visitada();
        }
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
    
    private void andarDirecaoLixeiraMaisProxima(){
        MemoriaLixeira maisProxima = null;
        for(MemoriaLixeira lixeira : lixeiras){
            if(!lixeira.isCheia()){
                if(maisProxima==null)maisProxima=lixeira;
                else{
                    int distanciaAnt = Math.abs((maisProxima.getLinha()+maisProxima.getColuna())-(this.getLinha()+this.getColuna()));
                    int distanciaPro = Math.abs((lixeira.getLinha()+lixeira.getColuna())-(this.getLinha()+this.getColuna()));
                    if(distanciaAnt>distanciaPro)maisProxima = lixeira;
                }
            }
        }
        int distanciaHorizontal = (maisProxima.getColuna()-this.getColuna());
        int distanciaVertical = (maisProxima.getLinha()-this.getLinha());
        andarDirecaoMaisProxima(distanciaHorizontal, distanciaVertical);
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
        System.out.println("Horizontal: "+distanciaHorizontal+" | Vertical: "+distanciaVertical);
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
        System.out.println("Horizontal: "+distanciaHorizontal+" | Vertical: "+distanciaVertical);
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
        if(!ambiente.estaLimpo()&&sacoLxSeco.size()<maxLixo&&sacoLxOrganico.size()<maxLixo){
            /**
             * Se o ambiente não estiver limpo e se nenhum dos sacos de lixo 
             * estiver cheio o Agente vai em busca de lixo;
             */
            int dl = direcaoLixo();
            if(dl==-1){
                if(contadorCiclos<3){
                    /**
                     * Se o Agente não tiver encontrado nenhum lixo ao seu redor
                     * ele vai numa direção aleatória.
                     */
                    int direcao;
                    do{
                        direcao = (int) (Math.random() * 4);
                    }while(!andar(direcao,false));
                    contadorCiclos++;
                }else{
                    /**
                     * Se a 3 ciclos o agente não encontrar nenhum lixo, ele vai
                     * anda em linha, escolhendo esquerda ou direita aleatóriamente.
                     */
                    int[] direcoes = new int[]{Ambiente.LESTE,Ambiente.OESTE};
                    int direcao;
                    do{
                        direcao = direcoes[(int)(Math.random()*direcoes.length)];
                    }while(!andar(direcao,false));
                }
            }else {
                andar(dl,false);
                contadorCiclos = 0;
            }
        }else{
            /**
             * Se um dos sacos de lixo do agente estiver cheio ou es o ambiente já
             * estive limpo, ele vai atrás de uma lixeira para despejar o lixo.
             */
            if(sacoLxSeco.size()==maxLixo){
                andarDirecaoLixeiraSeco();
            }else if(sacoLxOrganico.size()==maxLixo){
                andarDirecaoLixeiraOrganica();
            }else andarDirecaoLixeiraMaisProxima();
        }
    }

}
