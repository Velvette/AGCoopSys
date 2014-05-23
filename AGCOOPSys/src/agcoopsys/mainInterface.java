/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class mainInterface extends javax.swing.JFrame {

    viewCompany vC = new viewCompany();
    viewMember vM = new viewMember();
    viewSupplier vS = new viewSupplier();
    viewLoan vL = new viewLoan();
    
    public mainInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewTab = new javax.swing.JTabbedPane();
        buttonSave = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        buttonEdit = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        labelSearch = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        buttonLoan = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCompany = new javax.swing.JMenuItem();
        menuMember = new javax.swing.JMenuItem();
        menuSupplier = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuLoans = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuCloseTab = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewTab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        viewTab.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                viewTabComponentAdded(evt);
            }
        });

        buttonSave.setText("Add");
        buttonSave.setEnabled(false);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.setEnabled(false);
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        labelSearch.setText("Search:");
        labelSearch.setEnabled(false);

        textSearch.setEnabled(false);

        buttonSearch.setText("Search");
        buttonSearch.setEnabled(false);
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        buttonLoan.setText("New Loan");
        buttonLoan.setEnabled(false);
        buttonLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoanActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        menuCompany.setText("Company");
        menuCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompanyActionPerformed(evt);
            }
        });
        jMenu1.add(menuCompany);

        menuMember.setText("Member");
        menuMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMemberActionPerformed(evt);
            }
        });
        jMenu1.add(menuMember);

        menuSupplier.setText("Supplier");
        menuSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSupplierActionPerformed(evt);
            }
        });
        jMenu1.add(menuSupplier);
        jMenu1.add(jSeparator5);

        menuLoans.setText("Loans");
        menuLoans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoansActionPerformed(evt);
            }
        });
        jMenu1.add(menuLoans);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reports");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("Process");
        jMenuBar1.add(jMenu4);

        jMenu3.setText("Close");

        menuCloseTab.setText("Close Tab");
        menuCloseTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseTabActionPerformed(evt);
            }
        });
        jMenu3.add(menuCloseTab);
        jMenu3.add(jSeparator1);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu3.add(menuExit);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewTab)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 663, Short.MAX_VALUE)
                        .addComponent(buttonRefresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSearch)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSearch)
                        .addComponent(buttonLoan))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompanyActionPerformed

        viewTab.addTab("Company", vC);
        vC.getList();
        // TODO add your handling code here:
    }//GEN-LAST:event_menuCompanyActionPerformed

    private void menuCloseTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseTabActionPerformed

        if(viewTab.getSelectedComponent() != null)
        {
            viewTab.remove(viewTab.getSelectedIndex());
            if(viewTab.getSelectedComponent() == null)
            {
                buttonEdit.setEnabled(false);
                buttonDelete.setEnabled(false);
                buttonSave.setEnabled(false);
                buttonLoan.setEnabled(false);
                buttonSearch.setEnabled(false);
                textSearch.setEnabled(false);
                labelSearch.setEnabled(false);
            }
        }
        
        else
            JOptionPane.showMessageDialog(null, "Error: No tab to close", "Error", JOptionPane.ERROR_MESSAGE);
        // TODO add your handling code here:
    }//GEN-LAST:event_menuCloseTabActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close application", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
            System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_menuExitActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed

        String agcoopCompany = "agcoopsys.viewCompany";
        String agcoopMember = "agcoopsys.viewMember";
        String agcoopSupplier = "agcoopsys.viewSupplier";
        try{
            if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopCompany))
            {
                addCompany aC = new addCompany();
                aC.setVisible(true);
                aC.setLocationRelativeTo(null);
                aC.setTitle("Add Company - Information");
                aC.setResizable(false);
            }
        
            else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopMember))
            {
                addMember aM = new addMember();
                aM.setVisible(true);
                aM.setLocationRelativeTo(null);
                aM.setTitle("Add Member - Information");
                aM.setResizable(false);
            }
        
            else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopSupplier))
            {
                addSupplier aS = new addSupplier();
                aS.setVisible(true);
                aS.setLocationRelativeTo(null);
                aS.setTitle("Add Member - Information");
                aS.setResizable(false);
                
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: Add tab to select where to add", "Error - Adding information", JOptionPane.ERROR_MESSAGE); 
        }
        
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void menuMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMemberActionPerformed
        viewTab.addTab("Member", vM);
        vM.getList();
        // TODO add your handling code here:
    }//GEN-LAST:event_menuMemberActionPerformed

    private void menuSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSupplierActionPerformed

        viewTab.addTab("Supplier", vS);
        vS.getList();
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSupplierActionPerformed

    private void viewTabComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_viewTabComponentAdded
        buttonEdit.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonSave.setEnabled(true);
        buttonLoan.setEnabled(true);
        buttonSearch.setEnabled(true);
        textSearch.setEnabled(true);
        labelSearch.setEnabled(true);
        viewTab.setSelectedIndex(viewTab.getTabCount()-1);
    }//GEN-LAST:event_viewTabComponentAdded

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        vM.getList();
        vC.getList();
        vS.getList();
        
        String agcoopCompany = "agcoopsys.viewCompany";
        String agcoopMember = "agcoopsys.viewMember";
        String agcoopSupplier = "agcoopsys.viewSupplier";
        
        
       try
       {
        if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopCompany))
        {
            
        }
        
        else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopMember))
        {
             
        }
        
        else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopSupplier))
        {
         
        }
       }
            
       catch (Exception ex)
       {
           
       }
        
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed

        String agcoopCompany = "agcoopsys.viewCompany";
        String agcoopMember = "agcoopsys.viewMember";
        String agcoopSupplier = "agcoopsys.viewSupplier";
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete - Information", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {  
                try
                {
                    if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopCompany))
                    {
                        vC.deleteCompany();
                        vC.getList();
                    }
        
                    else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopMember))
                    {
                        vM.deleteMember();
                        vM.getList();
                    }
        
                    else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopSupplier))
                    {
                        vS.deleteSupplier();
                        vS.getList();
                    }
                }
            
                catch (Exception ex)
                {
                    Logger.getLogger(mainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed

        String agcoopCompany = "agcoopsys.viewCompany";
        String agcoopMember = "agcoopsys.viewMember";
        String agcoopSupplier = "agcoopsys.viewSupplier";
        try{
            if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopCompany))
            {
                vC.editCompany();
            }
        
            else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopMember))
            {
                vM.editMember();
            }
        
            else if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopSupplier))
            {
                vS.editSupplier();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: Select row to edit", "Error - Edit information", JOptionPane.ERROR_MESSAGE); 
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoanActionPerformed
        String agcoopMember = "agcoopsys.viewMember";
        try{
            if(viewTab.getSelectedComponent().getClass().getName().equals(agcoopMember))
            {
                    vM.addLoan();
            }
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: Select member to apply for loan", "Error - Loan information", JOptionPane.ERROR_MESSAGE); 
        }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLoanActionPerformed

    private void menuLoansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoansActionPerformed

        viewTab.add("Loans", vL);
        vL.getList();
        // TODO add your handling code here:
    }//GEN-LAST:event_menuLoansActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed

        
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchActionPerformed

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
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainInterface().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonLoan;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JMenuItem menuCloseTab;
    private javax.swing.JMenuItem menuCompany;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuLoans;
    private javax.swing.JMenuItem menuMember;
    private javax.swing.JMenuItem menuSupplier;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTabbedPane viewTab;
    // End of variables declaration//GEN-END:variables
}
