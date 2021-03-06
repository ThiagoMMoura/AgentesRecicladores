
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Thiago Moura
 */
public class Tela extends javax.swing.JFrame implements SaidaSimulador{
    private JLabel[][] quadrantes;
    private Simulador simulador;
    private boolean ambienteIniciado;
    private boolean pausado;
    private int dimenssao;
    private int qtdAgentes;
    private int qtdLixos;
    private int qtdLixeiras;
    /**
     * Creates new form Tela
     */
    public Tela(){
        initComponents();
        inicializaBotoes();
        this.setLocationRelativeTo(null);
        setAmbientePequeno();
        this.ambienteIniciado = false;
        this.pausado = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gupoTamanho = new javax.swing.ButtonGroup();
        painelPai = new javax.swing.JPanel();
        painelBarra = new javax.swing.JPanel();
        barra = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        selPequeno = new javax.swing.JToggleButton();
        selMedio = new javax.swing.JToggleButton();
        selGrande = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnIniciar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnSimular = new javax.swing.JButton();
        btnSimluarCiclo = new javax.swing.JButton();
        btnSimularAgente = new javax.swing.JButton();
        painelFilhoAmbiente = new javax.swing.JPanel();
        scrollAmbiente = new javax.swing.JScrollPane();
        painelScrollAmbiente = new javax.swing.JPanel();
        painelAmbiente = new javax.swing.JPanel();
        painelInformacoes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoInformacoes = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agentes Recicladores");

        painelPai.setLayout(new java.awt.BorderLayout(5, 0));

        barra.setFloatable(false);
        barra.setRollover(true);
        barra.add(jSeparator3);

        gupoTamanho.add(selPequeno);
        selPequeno.setSelected(true);
        selPequeno.setText("Pequeno");
        selPequeno.setFocusable(false);
        selPequeno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selPequeno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selPequeno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selPequenoActionPerformed(evt);
            }
        });
        barra.add(selPequeno);

        gupoTamanho.add(selMedio);
        selMedio.setText("Médio");
        selMedio.setAlignmentX(0.5F);
        selMedio.setFocusable(false);
        selMedio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selMedio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selMedioActionPerformed(evt);
            }
        });
        barra.add(selMedio);

        gupoTamanho.add(selGrande);
        selGrande.setText("Grande");
        selGrande.setFocusable(false);
        selGrande.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selGrande.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selGrande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selGrandeActionPerformed(evt);
            }
        });
        barra.add(selGrande);
        barra.add(jSeparator1);

        btnIniciar.setText("Iniciar");
        btnIniciar.setFocusable(false);
        btnIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        barra.add(btnIniciar);
        barra.add(jSeparator2);

        btnSimular.setText("Simular");
        btnSimular.setFocusable(false);
        btnSimular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });
        barra.add(btnSimular);

        btnSimluarCiclo.setText("Simular um Ciclo");
        btnSimluarCiclo.setFocusable(false);
        btnSimluarCiclo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimluarCiclo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimluarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimluarCicloActionPerformed(evt);
            }
        });
        barra.add(btnSimluarCiclo);

        btnSimularAgente.setText("Simular um Agente");
        btnSimularAgente.setAlignmentX(0.5F);
        btnSimularAgente.setFocusable(false);
        btnSimularAgente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimularAgente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimularAgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularAgenteActionPerformed(evt);
            }
        });
        barra.add(btnSimularAgente);

        javax.swing.GroupLayout painelBarraLayout = new javax.swing.GroupLayout(painelBarra);
        painelBarra.setLayout(painelBarraLayout);
        painelBarraLayout.setHorizontalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelBarraLayout.setVerticalGroup(
            painelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        painelPai.add(painelBarra, java.awt.BorderLayout.PAGE_START);

        painelAmbiente.setBackground(new java.awt.Color(255, 255, 255));
        painelAmbiente.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout painelScrollAmbienteLayout = new javax.swing.GroupLayout(painelScrollAmbiente);
        painelScrollAmbiente.setLayout(painelScrollAmbienteLayout);
        painelScrollAmbienteLayout.setHorizontalGroup(
            painelScrollAmbienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        painelScrollAmbienteLayout.setVerticalGroup(
            painelScrollAmbienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        scrollAmbiente.setViewportView(painelScrollAmbiente);

        javax.swing.GroupLayout painelFilhoAmbienteLayout = new javax.swing.GroupLayout(painelFilhoAmbiente);
        painelFilhoAmbiente.setLayout(painelFilhoAmbienteLayout);
        painelFilhoAmbienteLayout.setHorizontalGroup(
            painelFilhoAmbienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollAmbiente, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        painelFilhoAmbienteLayout.setVerticalGroup(
            painelFilhoAmbienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollAmbiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        painelPai.add(painelFilhoAmbiente, java.awt.BorderLayout.CENTER);
        painelFilhoAmbiente.getAccessibleContext().setAccessibleName("Tabuleiro");
        painelFilhoAmbiente.getAccessibleContext().setAccessibleDescription("");

        painelInformacoes.setPreferredSize(new java.awt.Dimension(260, 429));

        campoInformacoes.setEditable(false);
        campoInformacoes.setContentType("text/html"); // NOI18N
        campoInformacoes.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n\n    </p>\r\n  </body>\r\n</html>\r\n");
        campoInformacoes.setToolTipText("");
        jScrollPane1.setViewportView(campoInformacoes);

        javax.swing.GroupLayout painelInformacoesLayout = new javax.swing.GroupLayout(painelInformacoes);
        painelInformacoes.setLayout(painelInformacoesLayout);
        painelInformacoesLayout.setHorizontalGroup(
            painelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        painelInformacoesLayout.setVerticalGroup(
            painelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        painelPai.add(painelInformacoes, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selPequenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selPequenoActionPerformed
        setAmbientePequeno();
    }//GEN-LAST:event_selPequenoActionPerformed

    private void selMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selMedioActionPerformed
        setAmbienteMedio();
    }//GEN-LAST:event_selMedioActionPerformed

    private void selGrandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selGrandeActionPerformed
        setAmbienteGrande();
    }//GEN-LAST:event_selGrandeActionPerformed

    private void btnSimluarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimluarCicloActionPerformed
        simulador.start(true);
    }//GEN-LAST:event_btnSimluarCicloActionPerformed

    private void btnSimularAgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularAgenteActionPerformed
        simulador.iniciarProximoAgente();
    }//GEN-LAST:event_btnSimularAgenteActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if(!ambienteIniciado){
            pausado = false;
            iniciarAmbiente();
        }
        else if (ambienteIniciado&&!pausado){
            pausado = true;
            inicializaBotoes();
            btnIniciar.setText("Continuar");
        }else pausado = false;

        if(!pausado){
            btnIniciar.setText("Pausar");
            btnSimular.setEnabled(true);
            btnSimularAgente.setEnabled(true);
            btnSimluarCiclo.setEnabled(true);
            selPequeno.setEnabled(false);
            selMedio.setEnabled(false);
            selGrande.setEnabled(false);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularActionPerformed
        simulador.start(false);
    }//GEN-LAST:event_btnSimularActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barra;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSimluarCiclo;
    private javax.swing.JButton btnSimular;
    private javax.swing.JButton btnSimularAgente;
    private javax.swing.JTextPane campoInformacoes;
    private javax.swing.ButtonGroup gupoTamanho;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPanel painelAmbiente;
    private javax.swing.JPanel painelBarra;
    private javax.swing.JPanel painelFilhoAmbiente;
    private javax.swing.JPanel painelInformacoes;
    private javax.swing.JPanel painelPai;
    private javax.swing.JPanel painelScrollAmbiente;
    private javax.swing.JScrollPane scrollAmbiente;
    private javax.swing.JToggleButton selGrande;
    private javax.swing.JToggleButton selMedio;
    private javax.swing.JToggleButton selPequeno;
    // End of variables declaration//GEN-END:variables

    private void inicializaBotoes(){
        btnIniciar.setText("Iniciar");
        btnSimular.setEnabled(false);
        btnSimularAgente.setEnabled(false);
        btnSimluarCiclo.setEnabled(false);
        selPequeno.setEnabled(true);
        selMedio.setEnabled(true);
        selGrande.setEnabled(true);
    }
    private void iniciarAmbiente(){
        ambienteIniciado = simulador.iniciarAmbiente();
        atualizarInformacoes();
    }
    
    private void criarAmbiente(){
        ambienteIniciado = false;
        pausado = false;
        btnIniciar.setText("Iniciar");
        
        Dimension d = new Dimension(dimenssao*50, dimenssao*50);
        painelAmbiente.removeAll();
        
        painelAmbiente.setLayout(new java.awt.GridLayout(dimenssao, dimenssao));
        
        painelAmbiente.setPreferredSize(d);
        painelAmbiente.setMinimumSize(d);
        painelAmbiente.setMaximumSize(d);
        
        painelScrollAmbiente.setPreferredSize(d);
        
        this.simulador = new Simulador(this,dimenssao,qtdLixos,qtdLixeiras,qtdAgentes);
        criarQuadrantes();
        atualizaCiclo(simulador.getAmbiente());
    }
    
    private void criarQuadrantes(){
        this.quadrantes = new JLabel[dimenssao][dimenssao];
        for(int l=0;l<dimenssao;l++){
            for(int c=0;c<dimenssao;c++){
                JLabel jl = new JLabel();
                Border eb = new LineBorder(Color.BLACK);
                jl.setBorder(eb);
                jl.setHorizontalAlignment(JLabel.CENTER);
                
                jl.setText(" ");
                
                quadrantes[l][c] = jl;
                painelAmbiente.add(quadrantes[l][c]);
            }
        }
    }
    
    @Override
    public void atualizaCiclo(Ambiente ambiente){
        int d = ambiente.getDimenssao();
        for(int l=0;l<d;l++){
            for(int c=0;c<d;c++){
                Quadrante qd = ambiente.getQuadrante(l, c);
                quadrantes[l][c].setText("");
                if(qd.estaVazio())quadrantes[l][c].setIcon(new ImageIcon(getClass().getResource("/icones/vazio.png")));
                else quadrantes[l][c].setIcon(qd.getPeca().getIcon());
            }
        }
        if(ambienteIniciado){
            atualizarInformacoes();
        }else campoInformacoes.setText("");
    }
    
    public void atualizarInformacoes(){
        String style="<style type=\"text/css\">" +
                    "body{" +
                        "font-family: Verdana;" +
                        "font-style:normal;" +
                        "font-size: 9px;" +
                    "}" +
                    "#maior{" +
                        "font-size:11px;" +
                    "}" +
                    "th{" +
                        "font-size:12px;" +
                    "}" +
                    "  table{" +
                    "     border-bottom: 1px #CCCCCC solid;" +
                    "     border-right: 1px #CCCCCC solid;" +
                    "     margin-bottom: 5px;"+
                    "  }" +
                    "  th, td{" +
                    "	  border-left:1px #CCCCCC solid;" +
                    "	  border-top:1px #CCCCCC solid;" +
                    "	text-align: center;" +
                    "  }" +
                    "</style>";
            String informacoes="<html>" +
                    "<head>"+style+"</head><body>";
            informacoes+="<h2 style=\"text-align:center;\">Agentes</h2>"
                    + "<div>";
            for(Agente ag:simulador.getAgentes()){
                informacoes+="<table border=\"0\" cellpadding=\"4\" align=\"center\" cellspacing=\"0\">" +
                        "<tr>" +
                        "<th rowspan=\"2\"><img src=\""+ag.getIcon().toString()+"\"></th>" +
                        "<td colspan=\"2\">Saco Lixo Seco</td>" +
                        "<td colspan=\"2\">Saco Lixo Orgânico</td>" +
                        "</tr>"+
                        "<tr>" +
                        "<td id=\"maior\">"+ag.getQtdLixoSeco()+"</td>" +
                        "<td id=\"maior\">"+ag.getMaxLixo()+"</td>" +
                        "<td id=\"maior\">"+ag.getQtdLixoOrganico()+"</td>" +
                        "<td id=\"maior\">"+ag.getMaxLixo()+"</td>" +
                        "</tr>"+
                        "</table>";
            }
            informacoes+="</div>";
            informacoes+="<h2 style=\"text-align:center;\">Lixeiras Seco</h2>"
                    + "<div>";
            for(Lixeira lx:simulador.getLixeiras()){
                if(lx instanceof LixeiraSeco)informacoes+="<table border=\"0\" cellpadding=\"4\" align=\"center\" cellspacing=\"0\">" +
                        "<tr>" +
                        "<th rowspan=\"2\"><img src=\""+lx.getIcon().toString()+"\"></th>" +
                        "<td>Ocupado</td>" +
                        "<td>Capacidade</td>" +
                        "</tr>"+
                        "<tr>" +
                        "<td id=\"maior\">"+lx.getQtdLixos()+"</td>" +
                        "<td id=\"maior\">"+lx.getCapacidade()+"</td>" +
                        "</tr>"+
                        "</table>";
            }
            informacoes+="</div>";
            informacoes+="<h2 style=\"text-align:center;\">Lixeiras Orgânico</h2>"
                    + "<div>";
            for(Lixeira lx:simulador.getLixeiras()){
                if(lx instanceof LixeiraOrganico)informacoes+="<table border=\"0\" cellpadding=\"4\" align=\"center\" cellspacing=\"0\">" +
                        "<tr>" +
                        "<th rowspan=\"2\"><img src=\""+lx.getIcon().toString()+"\"></th>" +
                        "<td>Ocupado</td>" +
                        "<td>Capacidade</td>" +
                        "</tr>"+
                        "<tr>" +
                        "<td id=\"maior\">"+lx.getQtdLixos()+"</td>" +
                        "<td id=\"maior\">"+lx.getCapacidade()+"</td>" +
                        "</tr>"+
                        "</table>";
            }
            informacoes+="</div>";
            informacoes+="</body></html>";
            campoInformacoes.setText(informacoes);
    }

    @Override
    public final void setAmbientePequeno() {
        this.qtdLixos = 6;
        this.qtdLixeiras = 1;
        this.qtdAgentes = 2;
        this.dimenssao = 6;
        criarAmbiente();
    }

    @Override
    public void setAmbienteMedio() {
        this.qtdLixos = 20;
        this.qtdLixeiras = 2;
        this.qtdAgentes = 3;
        this.dimenssao = 12;
        criarAmbiente();
    }

    @Override
    public void setAmbienteGrande() {
        this.qtdLixos = 40;
        this.qtdLixeiras = 4;
        this.qtdAgentes = 6;
        this.dimenssao = 18;
        criarAmbiente();
    }

    @Override
    public void setSimulacao(boolean ativa) {
        btnIniciar.setEnabled(!ativa);
        btnSimluarCiclo.setEnabled(!ativa);
        btnSimular.setEnabled(!ativa);
        btnSimularAgente.setEnabled(!ativa);
    }

}
