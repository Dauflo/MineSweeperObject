/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperobject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

/**
 *
 * @author alois
 */
public class GameBoard extends javax.swing.JFrame {

    /**
     * Creates new form GameBoard
     */
    private Mine[][] board;
    JToggleButton[][] displayBoard;

    static private int cols;
    static private int rows;
    static private int bombs;

    public GameBoard(int c, int r, int b) {
        initComponents();
        cols = c;
        rows = r;
        bombs = b;

        setup();
    }

    private void setup() {
        board = new Mine[cols][rows];
        displayBoard = new JToggleButton[cols][rows];
        setBombs(bombs);
        
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                displayBoard[i][j] = new JToggleButton();
                displayBoard[i][j].setSize(jPanel1.getWidth() / cols, jPanel1.getHeight() / rows);
                jPanel1.add(displayBoard[i][j]);
                displayBoard[i][j].setLocation(j * jPanel1.getWidth() / rows, i * jPanel1.getHeight() / cols);
                displayBoard[i][j].addActionListener(listen);
                
            }
        }
    }
    
    ActionListener listen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i = 0, j = 0;
            boolean found = false;
            for (i = 0; i < cols; i++) {
                for (j = 0; j < rows; j++) {
                    if (ae.getSource() == displayBoard[i][j]) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            
            displayBoard[i][j].setSelected(true);
            openCase(i, j);
            display();
            loose();
            win();
        }
    };

    private void setBombs(int nbBombs) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = new Mine();
            }
        }
        for (int i = 0; i < nbBombs; i++) {
            int randX = (int) (Math.random() * cols);
            int randY = (int) (Math.random() * rows);

            if (!board[randX][randY].isMine()) {
                board[randX][randY] = new Mine(true);
            } else {
                i -= 1;
            }
        }
    }
    
    private void display() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j].isOpen()) {
                    displayBoard[i][j].setText(board[i][j].display());
                    displayBoard[i][j].setSelected(true);
                }
            }
        }
        jPanel1.repaint();
    }

    private void openCase(int x, int y) {
        if (x < 0 || y < 0 || x > cols - 1 || y > rows - 1 || board[x][y].isOpen()) {
            return;
        }

        int bombs = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i < 0 || i > cols - 1 || j < 0 || j > rows - 1) && board[i][j].isMine()) {
                    bombs++;
                }
            }
        }
        if (bombs == 0) {
            board[x][y].setNbMine("");
            board[x][y].setOpen();

            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (!(i < 0 || i > cols - 1 || j < 0 || j > rows - 1)) {
                        if (!(i == x && j == y)){
                            openCase(i, j);
                        }
                    }
                }
            }
        } else {
            board[x][y].setNbMine(String.valueOf(bombs));
            board[x][y].setOpen();
        }
    }
    
    private void win() {
        boolean left = false;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (!board[i][j].isOpen() && !board[i][j].isMine()) {
                    left = true;
                    break;
                }
            }
            if (left) {
                break;
            }
        }
        if (!left) {
            System.out.println("Win !");
            javax.swing.JOptionPane.showMessageDialog(null, "Win");
            System.exit(1);
        }
    }
    
    private void loose() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j].isMine() && board[i][j].isOpen()) {
                    System.out.println("Loose !");
                    javax.swing.JOptionPane.showMessageDialog(null, "Loose");
                    System.exit(1);
                }
            }
        }
    }

    private void goodSize() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                displayBoard[i][j].setSize(jPanel1.getWidth() / cols, jPanel1.getHeight() / rows);
                jPanel1.add(displayBoard[i][j]);
                displayBoard[i][j].setLocation(j * jPanel1.getWidth() / rows, i * jPanel1.getHeight() / cols);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game On !");

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        goodSize();
    }//GEN-LAST:event_jPanel1ComponentResized

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
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBoard(cols, rows, bombs).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
