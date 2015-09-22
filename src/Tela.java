
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.*;

/**
 *
 * @author Thiago Moura
 */
public class Tela extends javax.swing.JFrame {
    private JLabel[][] casas;
    private Tabuleiro tab;
    /**
     * Creates new form Tela
     */
    public Tela() {
        initComponents();
        inicializar();
        visualizarPainel(painelFilhoIniciar.getName());
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPai = new javax.swing.JLayeredPane();
        painelFilhoIniciar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ctlDimensao = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        painelFilhoTabuleiro = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPrincipal = new javax.swing.JMenu();
        itemInicio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Configure e Inicie");

        ctlDimensao.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(6), Integer.valueOf(6), null, Integer.valueOf(1)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Dimenssão do tabuleiro:");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelFilhoIniciarLayout = new javax.swing.GroupLayout(painelFilhoIniciar);
        painelFilhoIniciar.setLayout(painelFilhoIniciarLayout);
        painelFilhoIniciarLayout.setHorizontalGroup(
            painelFilhoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFilhoIniciarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFilhoIniciarLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(ctlDimensao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(painelFilhoIniciarLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btnIniciar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFilhoIniciarLayout.setVerticalGroup(
            painelFilhoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFilhoIniciarLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(painelFilhoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctlDimensao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(btnIniciar)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        painelFilhoTabuleiro.setLayout(new java.awt.GridLayout(6, 6));

        javax.swing.GroupLayout painelPaiLayout = new javax.swing.GroupLayout(painelPai);
        painelPai.setLayout(painelPaiLayout);
        painelPaiLayout.setHorizontalGroup(
            painelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFilhoIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(painelFilhoTabuleiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPaiLayout.setVerticalGroup(
            painelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFilhoIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(painelFilhoTabuleiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPai.setLayer(painelFilhoIniciar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        painelFilhoIniciar.getAccessibleContext().setAccessibleName("Iniciar");
        painelPai.setLayer(painelFilhoTabuleiro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        painelFilhoTabuleiro.getAccessibleContext().setAccessibleName("Tabuleiro");
        painelFilhoTabuleiro.getAccessibleContext().setAccessibleDescription("");

        menuPrincipal.setText("Menu");

        itemInicio.setText("Inicio");
        itemInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInicioActionPerformed(evt);
            }
        });
        menuPrincipal.add(itemInicio);

        jMenuBar1.add(menuPrincipal);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPai)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPai)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        iniciarTabuleiro((int) ctlDimensao.getValue());
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void itemInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInicioActionPerformed
        visualizarPainel(this.painelFilhoIniciar.getName());
    }//GEN-LAST:event_itemInicioActionPerformed

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
    private javax.swing.JButton btnIniciar;
    private javax.swing.JSpinner ctlDimensao;
    private javax.swing.JMenuItem itemInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JPanel painelFilhoIniciar;
    private javax.swing.JPanel painelFilhoTabuleiro;
    private javax.swing.JLayeredPane painelPai;
    // End of variables declaration//GEN-END:variables

    private void inicializar(){
        painelFilhoIniciar.setName("Iniciar");
        painelFilhoTabuleiro.setName("Tabuleiro");
    }
    
    private void visualizarPainel(String nome){
        painelFilhoIniciar.setVisible(painelFilhoIniciar.getName().equalsIgnoreCase(nome));
        painelFilhoTabuleiro.setVisible(painelFilhoTabuleiro.getName().equalsIgnoreCase(nome));
    }
    private void iniciarTabuleiro(int dimenssao){
        painelFilhoTabuleiro.removeAll();
        painelFilhoTabuleiro.setSize(dimenssao*50, dimenssao*50);
        painelFilhoTabuleiro.setLayout(new java.awt.GridLayout(dimenssao, dimenssao));
        this.tab = new Tabuleiro(dimenssao);
        this.casas = new JLabel[dimenssao][dimenssao];
        tab.posicionarLixos(dimenssao);
        tab.posicionarLixeiras(2, dimenssao);
        atualizarTabuleiro();
        visualizarPainel(painelFilhoTabuleiro.getName());
    }
    private void atualizarTabuleiro(){
        int d = tab.getDimenssao();
        for(int l=0;l<d;l++){
            for(int c=0;c<d;c++){
                JLabel jl = new JLabel();Border eb = new LineBorder(Color.BLACK);
                jl.setBorder(eb);
                jl.setHorizontalAlignment(JLabel.CENTER);
                
                Casa cs = tab.getCasa(l, c);
                if(cs.casaVazia())jl.setText("");
                else jl.setText(cs.getPeca().getNome());
                
                casas[l][c] = jl;
                painelFilhoTabuleiro.add(casas[l][c]);
            }
        }
    }
    
}