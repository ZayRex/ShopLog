/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import shoplog.Product;
import shoplog.DBHandler;
import shoplog.OrderLine;
import shoplog.Customer;

/**
 * Date 17/04/2018
 * @author Mohamad Harah
 */
public class ViewBasket extends javax.swing.JFrame {
    
    private Customer loggedInCustomer;
    private int orderId;

    /**
     * Creates new form ViewBasket
     */
    public ViewBasket(Customer c) throws SQLException {
        loggedInCustomer = c;
        
        orderId = loggedInCustomer.findLatestOrder().getOrderId();
        
        initComponents();
        
        tblOrderLines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DefaultTableModel model = (DefaultTableModel)tblOrderLines.getModel();
        for(Map.Entry<Integer, OrderLine> olEntry : 
                loggedInCustomer.findLatestOrder().getOrderLines().entrySet())
        {
            Product orderedProduct = olEntry.getValue().getProduct();
            
         
            
             model.addRow(new Object[]{orderedProduct.getProductId(), orderedProduct.getProductName(), "£"
                     + String.format("%.02f",orderedProduct.getPrice()), loggedInCustomer.findLatestOrder().getQuantityofProduct(orderedProduct.getProductId())});           

        }
        
        lblTotal.setText("£" + String.format("%.02f",loggedInCustomer.findLatestOrder().getOrderTotal()));
    }

    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderLines = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("BACK TO SHOP");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblOrderLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrderLines);

        btnRemove.setText("REMOVE SELECTED ITEM");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnOrder.setText("SUBMIT ORDER");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        jLabel1.setText("TOTAL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal)
                    .addComponent(lblMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnRemove)
                .addGap(18, 18, 18)
                .addComponent(btnOrder)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if(tblOrderLines.getSelectedRow() == -1)
        {
            lblMessage.setText("No Product Selected");
        }
        else
        {
            try {
            DefaultTableModel model = (DefaultTableModel)tblOrderLines.getModel();
            int productId = Integer.parseInt(String.valueOf(model.getValueAt(tblOrderLines.getSelectedRow(),0)));
            
                loggedInCustomer.findLatestOrder().removeOrderLine(productId);
            
            
            model.removeRow(tblOrderLines.getSelectedRow());
            
            lblMessage.setText("Product Has Been Removed");
            lblTotal.setText("£" + String.format("%.02f",loggedInCustomer.findLatestOrder().getOrderTotal()));
            } catch (SQLException ex) {
                Logger.getLogger(ViewBasket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        ViewProducts vP = new ViewProducts(loggedInCustomer);
        this.dispose();
        vP.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        DBHandler db = new DBHandler();
        
        try {
            for(Map.Entry<Integer, OrderLine> olEntry :
                    loggedInCustomer.findLatestOrder().getOrderLines().entrySet())
            {
                Product orderedProduct = olEntry.getValue().getProduct();
                orderedProduct.setStockLevel(orderedProduct.getStockLevel()-loggedInCustomer.findLatestOrder().getQuantityofProduct(orderedProduct.getProductId()));
                db.updateProduct(orderedProduct);
            }
        
        
        loggedInCustomer.findLatestOrder().setStatus("Complete");
        db.completeOrder(orderId);
        JOptionPane.showMessageDialog(null,"Order Completed!");
        //System.out.println("order complete");
        } catch (SQLException ex) {
            Logger.getLogger(ViewBasket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnOrderActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(ViewBasket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBasket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBasket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBasket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
 //               new ViewBasket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblOrderLines;
    // End of variables declaration//GEN-END:variables
}