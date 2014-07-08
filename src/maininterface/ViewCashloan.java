/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maininterface;

import utilities.DbUtils;
import mainaddinterface.AddCashloan;
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
public class ViewCashloan extends javax.swing.JPanel {

    AddCashloan aL = new AddCashloan();
    
    ArrayList<String> arrayLastName = new ArrayList<>();
    ArrayList<String> arrayFirstName = new ArrayList<>();
    ArrayList<String> arrayMidName = new ArrayList<>();
    ArrayList<String> arrayMemberid = new ArrayList<>();
    public DefaultListModel listModel = new DefaultListModel();
    
    public Connection conn;
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    
    DbUtils tableUtils = new DbUtils();
    
    String nameTemp = "";
    
    private static String DEFAULT_QUERY = "SELECT memberid,lastname,firstname,midinit from member order by lastname";
    
    public ViewCashloan() {
        initComponents();
        listMember.setModel(listModel);
    }
    
    public void getList(int rep)
    {
        String tempQuery = "";
        
        if(rep == 0) {
            tempQuery = DEFAULT_QUERY;
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
        
        }
        catch (SQLException e)
        {
        
        }
        finally{
            this.disconnect();      
        }
        listMember.setSelectedIndex(0);
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
    
    public void editLoan()
    {
        try {
            int index = listLoan.getSelectedRow();
            String loanid = listLoan.getValueAt(index, 0).toString();
            String wholeName = (String) listMember.getSelectedValue();
            aL.allReset();
            aL.setVisible(true);
            aL.setLocationRelativeTo(null);
            aL.setResizable(false);
            aL.setTitle("Edit Loan - Information");
            aL.editLoan(wholeName, loanid);
        }
        catch(Exception e) { }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        listLoan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMember = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cashloan Detail"));

        listLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "GRANT DATE", "BILLING DATE", "LOAN AMOUNT", "RATE", "INTEREST", "STATUS", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(listLoan);
        listLoan.getColumnModel().getColumn(0).setPreferredWidth(30);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Member"));

        listMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMemberMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listMember);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setText("Search");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(searchBox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void deleteLoan()
    {
        Statement stmt = null;
        try
        {
            int index = listLoan.getSelectedRow();
            String i = listLoan.getValueAt(index, 0).toString();
            String tempQuery = "delete from cashloan where loanid="+i;
            //System.out.println(tempQuery);
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
                stmt.executeQuery(tempQuery);
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
        }
        catch(Exception e)
        {
            
        }
        
    }
    
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
                //System.out.println("MemberID:  "+lastname + " : " + select);
                String tempQuery = "select loanid,grantdt,billingdt,loanamt,interestrt,interestamt,status,balance from cashloan where memberid = (select memberid from member where firstname = '"+ tempFirst +"' and lastname = '"+tempLast+"' and midinit = '"+tempMid+"')";
        
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
                        tableUtils.updateTableModelData((DefaultTableModel) listLoan.getModel(), rs, 8);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.getList(1);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.getList(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable listLoan;
    private javax.swing.JList listMember;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables
}
