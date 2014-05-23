/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class addSupplier extends javax.swing.JFrame {

    private float supplierBalance;
    private String supplierBalanceText;
    private String supplierName;
    private String supplierAddress1;
    private String supplierAddress2;
    private String supplierAddress3;
    private String supplierEmail;
    private String supplierContactPerson;
    private String supplierContact1;
    private String supplierContact2;
    private int supplierID;
    
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    int choice = 0;
    
    public addSupplier() {
        initComponents();
    }

    public void setSupplierAddress1() {
        this.supplierAddress1 = textSupAd1.getText();
    }

    public void setSupplierAddress2() {
        this.supplierAddress2 = textSupAd2.getText();
    }

    public void setSupplierAddress3() {
        this.supplierAddress3 = textSupAd3.getText();
    }

    public void setSupplierBalanceText() {
        this.supplierBalanceText = textSupBalance.getText();
    }

    public void setSupplierContact1() {
        this.supplierContact1 = textSupCon1.getText();
    }

    public void setSupplierContact2() {
        this.supplierContact2 = textSupCon2.getText();
    }

    public void setSupplierContactPerson() {
        this.supplierContactPerson = textSupContactPerson.getText();
    }

    public void setSupplierEmail() {
        this.supplierEmail = textSupEmail.getText();
    }

    public void setSupplierName() {
        this.supplierName = textSupplierName.getText();
    }
    
    public Connection getConnection()
    {
	return conn;
    }
        
    public void connect()
    {
        dbDriver = paramDB.getDbClass();
        dbUrl = paramDB.getDbUrl();
        password = paramDB.getPassword(); // CHANGE PASSWORD
        username = paramDB.getName();
                
        try
        {
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbUrl,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
    public void disconnect()
    {
       try
       {
            conn.close();
       } 
       catch (Exception ex)
       {
            ex.printStackTrace();
       }
    }
    
    public void checkInputAddSupplier()
    {
        String errorMessages = "";
        int errorCount = 0;
        validateAllInputs validateInputs = new validateAllInputs();
        ConnectToDatabaseSys connectDB = new ConnectToDatabaseSys();
        
        supplierAddress1 = validateInputs.formatStringSpaces(supplierAddress1);
        supplierAddress2 = validateInputs.formatStringSpaces(supplierAddress2);
        supplierAddress3 = validateInputs.formatStringSpaces(supplierAddress3);
        supplierContactPerson = validateInputs.formatStringSpaces(supplierContactPerson);
        supplierName = validateInputs.formatStringSpaces(supplierName);
        
        try
        {
            if(!(connectDB.checkDuplicate(supplierName, 0)))
            {
                errorMessages += "Supplier Name: Already exists\n";
                errorCount++;
            }
        }
        catch(Exception t)
        {
            errorMessages += "aaSupplier Name: Already exists\n";
            errorCount++;
        }
                
        if(!(validateInputs.checkIfEmpty(supplierName)))
        {
            errorMessages += "Supplier Name: Field cannot be empty\n";
            errorCount++;
        }
        
        if(!(validateInputs.checkIfEmpty(supplierAddress1)))
        {
            errorMessages += "Supplier Address 1: Field cannot be empty\n";
            errorCount++;
        }
        
        if(!(validateInputs.checkIfEmpty(supplierContactPerson)))
        {
            errorMessages += "Contact Person: Field cannot be empty\n";
            errorCount++;
        }
        
        if(!(validateInputs.checkIfEmpty(supplierBalanceText)))
        {
            errorMessages += "Balance: Field cannot be empty\n";
            errorCount++;
        }
        
        if(!(validateInputs.checkForSpecial(supplierContactPerson)))
        {
            errorMessages += "Contact Person: Must not contain special characters\n";
            errorCount++;
        }
        
        try
        {
            supplierBalance = Float.parseFloat(supplierBalanceText);
        }
        catch(Exception e)
        {
            errorMessages += "Supplier Balance: Must only contain numbers\n";
            errorCount++;
        }
                        
        if(errorCount == 0)
        {
            String query = "";
            if(choice == 0)
            {
                    query = "insert into supplier (suppname,address1,address2,address3,contactperson,contactno1,contactno2,email,balance) values ('"
                    + supplierName + "','" + supplierAddress1 + "','" + supplierAddress2 + "','" + supplierAddress3 + "','" + supplierContactPerson + "','" + supplierContact1 + "','" + supplierContact2 + "','"
                    + supplierEmail + "','" + supplierBalance + "')";
            }
            if(choice == 1)
            {
                query = "update supplier set suppname='"+supplierName+"',address1='"+supplierAddress1+"',address2='"+supplierAddress2+"',address3='"+supplierAddress3+"',"+
                        "contactperson='"+supplierContactPerson+"',contactno1='"+supplierContact1+"',contactno2='"+supplierContact2+"',email='"+supplierEmail+"',balance='"+supplierBalance+"' where suppid="+supplierID;
                choice = 0;
            }
            connectDB.accessInputDatabase(query);
        }
        
        else if(errorCount > 0)
        {
            System.out.println(errorMessages);
            JOptionPane.showMessageDialog(null, errorMessages, "Error: Company Information", JOptionPane.ERROR_MESSAGE);
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

        textSupBalance = new javax.swing.JTextField();
        textSupContactPerson = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textSupCon1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textSupplierName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textSupEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textSupCon2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        textSupAd3 = new javax.swing.JTextField();
        textSupAd2 = new javax.swing.JTextField();
        textSupAd1 = new javax.swing.JTextField();
        buttonClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textSupBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textSupBalance.setText("0");

        jLabel8.setText("Contact person");

        jLabel9.setText("Contact 1");

        jLabel3.setText("Supplier name");

        jLabel7.setText("E-mail");

        jLabel10.setText("Contact 2");

        jLabel11.setText("Balance");

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Address"));

        textSupAd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSupAd2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textSupAd1)
                    .addComponent(textSupAd2)
                    .addComponent(textSupAd3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(textSupAd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSupAd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSupAd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonClear.setText("Clear");
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textSupEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(textSupContactPerson)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(buttonCancel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(198, 198, 198)
                                .addComponent(buttonConfirm))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textSupBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(textSupCon2)
                                .addComponent(textSupCon1, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addComponent(textSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSupEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSupContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSupCon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSupCon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSupBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm)
                    .addComponent(buttonCancel)
                    .addComponent(buttonClear))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addContainerGap(341, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSupAd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSupAd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSupAd2ActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed

        this.setSupplierAddress1();
        this.setSupplierAddress2();
        this.setSupplierAddress3();
        this.setSupplierBalanceText();
        this.setSupplierContact1();
        this.setSupplierContact2();
        this.setSupplierContactPerson();
        this.setSupplierEmail();
        this.setSupplierName();
        this.checkInputAddSupplier();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed

        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed

        textSupplierName.setText("");
        textSupBalance.setText("0");
        textSupAd1.setText("");
        textSupAd2.setText("");
        textSupAd3.setText("");
        textSupEmail.setText("");
        textSupCon1.setText("");
        textSupCon2.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonClearActionPerformed

    public void checkInputEditSupplier(String id)
    {
        ResultSet rs;
        this.connect();
        Statement stmt = null;
        
        String query = "select * from supplier where suppid="+id;
        
        try
        {
            stmt = conn.createStatement();
        }
                
	catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                textSupplierName.setText(rs.getString("suppname"));
                textSupAd1.setText(rs.getString("address1"));
                textSupAd2.setText(rs.getString("address2"));
                textSupAd3.setText(rs.getString("address3"));
                textSupContactPerson.setText(rs.getString("contactperson"));
                textSupCon1.setText(rs.getString("contactno1"));
                textSupCon2.setText(rs.getString("contactno2"));
                textSupEmail.setText(rs.getString("email"));
                textSupBalance.setText(rs.getString("balance"));
                supplierID = rs.getInt("suppID");
            }
            this.disconnect();      
	}
        
        catch (SQLException e)
        {
            e.printStackTrace();
	}   
        choice = 1;
    }
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
            java.util.logging.Logger.getLogger(addSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addSupplier().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textSupAd1;
    private javax.swing.JTextField textSupAd2;
    private javax.swing.JTextField textSupAd3;
    private javax.swing.JTextField textSupBalance;
    private javax.swing.JTextField textSupCon1;
    private javax.swing.JTextField textSupCon2;
    private javax.swing.JTextField textSupContactPerson;
    private javax.swing.JTextField textSupEmail;
    public javax.swing.JTextField textSupplierName;
    // End of variables declaration//GEN-END:variables
}
