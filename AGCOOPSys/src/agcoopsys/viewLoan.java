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
public class viewLoan extends javax.swing.JPanel {

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
    DbUtils tableUtils = new DbUtils();
    
    public viewLoan() {
        initComponents();
        listMember.setModel(listModel);
        listLoan.getTableHeader().setReorderingAllowed(false);
        this.getList();
    }

    public void getList()
    {
        final String tempQuery = "SELECT distinct member.memberid,member.firstname,member.lastname,member.midinit from MEMBER INNER JOIN loan_hdr on member.memberid = loan_hdr.memberid";
        
        listModel.clear();
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

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Amortization Schedule"));

        listDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AMORTIZATION DATE", "MON AMORTIZATION", "MON INTEREST"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMemberMouseClicked

        try
        {
            try
            {

                int select = listMember.getSelectedIndex();
                String tempFirst = arrayFirstName.get(select);
                String tempLast = arrayLastName.get(select);
                String tempMid = arrayMidName.get(select);
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
                        Logger.getLogger(viewMember.class.getName()).log(Level.SEVERE, null, ex);
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

    public void getListDetails(String id)
    {
        try
        {
            String tempQuery = "select amordate,mon_amort,mon_interest from loan_dtl where loanid="+id+" order by amordate";
        
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
                rs = stmt.executeQuery(tempQuery);
                try
                {
                    tableUtils.updateTableModelData((DefaultTableModel) listDetails.getModel(), rs, 3);
                }
                catch (Exception ex)
                {
                    Logger.getLogger(viewMember.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        catch (SQLException e)
        {
            e.printStackTrace();
	}
        finally{
            this.disconnect();
        }
        }
        
        catch(Exception e)
        {
            
        }
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables
}
