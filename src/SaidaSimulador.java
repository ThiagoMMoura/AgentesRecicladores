/**
 *
 * A classe SaidaSimulador determina uma interface padrão para as classes de exibição
 do ambiente do agente.
 */
public interface SaidaSimulador {
    
    public void atualizaCiclo(Ambiente ambiente);
    public void setAmbientePequeno();
    public void setAmbienteMedio();
    public void setAmbienteGrande();
    public void setSimulacao(boolean ativa);
}
