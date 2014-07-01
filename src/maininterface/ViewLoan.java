/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maininterface;

import utilities.DbUtils;
import mainaddinterface.AddLoan;
import mainconnect.ConnectToDatabaseSys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ViewLoan extends javax.swing.JPanel {

    AddLoan aL = new AddLoan();
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    public String firstname;
    public String lastname;
    public int memberID;
    public int loanID;
    public String midinit;
    public DefaultListModel listModel = new DefaultListModel();
    String nameTemp = "";
    ArrayList<String> arrayLastName = new ArrayList<>();
    ArrayList<String> arrayFirstName = new ArrayList<>();
    ArrayList<String> arrayMidName = new ArrayList<>();
    ArrayList<String> arrayMemberid = new ArrayList<>();
    
    DbUtils tableUtils = new DbUtils();
    
    public ViewLoan() {
        initComponents();
        listMember.setModel(listModel);
        listLoan.getTableHeader().setReorderingAllowed(false);
        this.getList(0);
    }

    public void getList(int rep)
    {
        String tempQuery = "";
        
        if(rep == 0) {
            tempQuery = "SELECT memberid,lastname,firstname,midinit from member order by lastname";
        }
        
        else if(rep == 1) {
            String searchText = searchBox.getText().toUpperCase();
            tempQuery = "select * from member where lastname like '"+searchText+"'";
        }
        
        listModel.clear();
        arrayLastName.clear();
        arrayFirstName.clear();
        arrayMidName.clear();
        arrayMemberid.clear();
        
        Statement stmt = null;       
        this.connect();
        conn = this.getConnection();
        
        try
        {
            stmt = conn.createStatement();
        }
                
        catch (SQLException e)
        {
            e.printStackTrace();
        }
		
        ResultSet rs;
        try
        {
            String firstname = "";
            String lastname = "";
            String midinit = "";
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {                
                nameTemp += rs.getString("lastname");
                nameTemp += ", " + rs.getString("firstname");
                nameTemp += " " + rs.getString("midinit");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                midinit = rs.getString("midinit");
                arrayFirstName.add(firstname);
                arrayLastName.add(lastname);
                arrayMidName.add(midinit);
                
                arrayMemberid .add(rs.getString("memberid"));
                listModel.addElement(nameTemp);
                nameTemp = "";
                //System.out.println(nameTemp);
            }
        this.disconnect();      
        }
        catch (SQLException e)
        {
        
        }
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
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
        {
            e.printStackTrace();
        }
    }
	
    public Connection getConnection()
    {
	return conn;
    }
	
    public void disconnect()
    {
       try
       {
            conn.close();
       } 
       catch (Exception ex)
       {
       }
    }
    
    public void reset()
    {
        this.getList(0);
    }
    
    public void addLoan()
    {
        int index = listMember.getSelectedIndex();
        String i = arrayMemberid.get(index);
        String wholeName = (String) listMember.getSelectedValue();
        aL.allReset();
        aL.setVisible(true);
        aL.setLocationRelativeTo(null);
        aL.setResizable(false);
        aL.setTitle("New Loan - Information");
        
        aL.addLoan(wholeName, i);
    }
    
    public void deleteLoan()
    {
        Statement stmt = null;
        try
        {
            int index = listLoan.getSelectedRow();
            String i = listLoan.getValueAt(index, 0).toString();
            String tempQuery = "delete from loan_dtl where loanid="+i;
            this.connect();
            try
            {
                stmt = conn.createStatement();
            }
            
            catch(Exception p)
            {
                
            }
            
            try
            {
                stmt.addBatch(tempQuery);
                tempQuery = "delete from loan_hdr where loanid="+i;
                stmt.addBatch(tempQuery);
                int[] executeBatch = stmt.executeBatch();
            }
            
            catch(Exception o)
            {
                
            }
            finally{
                this.disconnect();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: Select row to delete", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
        try
        {
            this.listMemberMouseClicked(null);
            this.listLoanMouseClicked(null);
            DefaultTableModel removeModel = (DefaultTableModel) listDetails.getModel();
            removeModel.setRowCount(0);
            textPayment1.setText("0");
            textPayment.setText("0");
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void editLoan()
    {
        try
        {
            int index = listLoan.getSelectedRow();
            String loanid = listLoan.getValueAt(index, 0).toString();
            String wholeName = (String) listMember.getSelectedValue();
           // System.out.println(loanid);
            aL.allReset();
            aL.setVisible(true);
            aL.setLocationRelativeTo(null);
            aL.setResizable(false);
            aL.setTitle("Edit Loan - Information");
            aL.editLoan(wholeName, loanid);
        }
        catch(Exception e)
        {
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listDetails = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listLoan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMember = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        textPayment = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textPayment1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Amortization Schedule"));

        listDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MONTHLY SCHEDULE", "MONTHLY PAYMENT", "INTEREST", "PREMIUM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(listDetails);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Loan Details"));

        listLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "STATUS", "TYPE", "GRANT", "START", "END", "LOANAMT", "TERMS", "RATE", "INTERESTAMT", "PAYABLE", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listLoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listLoan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Members"));

        listMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMemberMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listMember);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total Payment");

        textPayment.setBackground(new java.awt.Color(0, 0, 0));
        textPayment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textPayment.setForeground(new java.awt.Color(0, 204, 0));
        textPayment.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textPayment.setText("0");
        textPayment.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total Interest");

        textPayment1.setBackground(new java.awt.Color(0, 0, 0));
        textPayment1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textPayment1.setForeground(new java.awt.Color(0, 204, 0));
        textPayment1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textPayment1.setText("0");
        textPayment1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Search");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Print");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(textPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMemberMouseClicked

        try
        {
            try
            {
                int select = listMember.getSelectedIndex();
                listMember.setSelectedIndex(select);
                String tempFirst = arrayFirstName.get(select);
                String tempLast = arrayLastName.get(select);
                String tempMid = arrayMidName.get(select);
               // System.out.println("MemberID:  "+lastname + " : " + select);
                String tempQuery = "select loanid,status,loantype,grantdt,startdt,enddt,loanamt,montopay,interestrt,interestamt,payableamt,balance from loan_hdr where memberid = (select memberid from member where firstname = '"+ tempFirst +"' and lastname = '"+tempLast+"' and midinit = '"+tempMid+"')";
        
                Statement stmt = null;       
                this.connect();
                conn = this.getConnection();
                
                try
                {
                    stmt = conn.createStatement();
                }
                
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
        
                ResultSet rs;
	
                try
                {
                    rs = stmt.executeQuery(tempQuery);
                    try
                    {
                        tableUtils.updateTableModelData((DefaultTableModel) listLoan.getModel(), rs, 12);
                    } 
                    catch (Exception ex)
                    {
                        Logger.getLogger(ViewMember.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    this.disconnect();
                }
            }
        
            catch(Exception e)
            {
                DefaultTableModel model = (DefaultTableModel) listLoan.getModel();
                model.removeRow(model.getRowCount()-1);
            }
        }
        catch(Exception o)
        {
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_listMemberMouseClicked

    private void listLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLoanMouseClicked

        try
        {
            int index = listLoan.getSelectedRow();
            String i = listLoan.getValueAt(index, 0).toString();
            this.getListDetails(i);
        }
        catch(Exception o)
        {
            DefaultTableModel model = (DefaultTableModel) listDetails.getModel();
            model.removeRow(model.getRowCount()-1);
        }
    }//GEN-LAST:event_listLoanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.getList(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.getList(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public void getListDetails(String id)
    {
        float total = 0;
        float totalInterest = 0;
        float payment1 = 0;
        float payment2 = 0;
        try
        {
            String tempQuery = "select TO_DATE(amordate, 'YYYY-MM-DD') as monthly_schedule,mon_amort as monthly_payment,mon_interest as interest, mon_premium as premium from loan_dtl where loanid="+id+" order by amordate";
        
            Statement stmt = null;       
            this.connect();
            conn = this.getConnection();
                
            try
            {
                stmt = conn.createStatement();
            }
                
            catch (SQLException e)
            {
                e.printStackTrace();
            }
		
            ResultSet rs;
            ResultSet ps;
            try
            {
                rs = stmt.executeQuery(tempQuery);
                
                try
                {                    
                    tableUtils.updateTableModelData((DefaultTableModel) listDetails.getModel(), rs, 4);
                }
                catch (Exception ex)
                {
                    Logger.getLogger(ViewMember.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch(Exception e)
            {
            
            }
            
            try
            {
                ps = stmt.executeQuery(tempQuery);
                
                while(ps.next())
                {
                    total += ps.getFloat("monthly_payment");
                    totalInterest += ps.getFloat("interest");
                    
                    payment1 = total;
                    payment2 = totalInterest;
                }
            }
            catch(Exception e)
            {
                
            }
            
            finally
            {
                this.disconnect();
            }
        }
        
        catch(Exception e)
        {
            
        }
        
        payment1 = (float) (Math.round(payment1 * 100.00) / 100.00);
        payment2 = (float) (Math.round(payment2 * 100.00) / 100.00);
        
        textPayment.setText(String.valueOf(payment1));
        textPayment1.setText(String.valueOf(payment2));
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable listDetails;
    private javax.swing.JTable listLoan;
    private javax.swing.JList listMember;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTextField textPayment;
    private javax.swing.JTextField textPayment1;
    // End of variables declaration//GEN-END:variables
}
