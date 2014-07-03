/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maininterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mainaddinterface.AddGoods;
import mainconnect.ConnectToDatabaseSys;
import utilities.DbUtils;
/**
 *
 * @author Lenovo
 */
public class ViewPurchGoods extends javax.swing.JPanel {

    private ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    private String dbUrl;
    private String dbDriver;
    private String username;
    private String password;
    private Connection conn;
    private DbUtils tableUtils = new DbUtils();
    private String query = "select invid,invdt,billingdt from goods_sold_hdr order by invdt desc";
    private DateFormat df;
    private String searchText;
    
    AddGoods aG = new AddGoods();
    
    public ViewPurchGoods() {
        initComponents();
        listGoods.getTableHeader().setReorderingAllowed(false);
        df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
    }

    public void resetView() {
        textInvDate.setText("");
        textBilDate.setText("");
        textAmount.setText("0.00");
        textRemarks.setText("");
        
        try
        {
            DefaultTableModel removeModel = (DefaultTableModel) listDetails.getModel();
            removeModel.setRowCount(0);
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    public void getList(int rep) {
        String tempQuery = "";
        if(rep==0) {
            tempQuery = query;
        }
        else if(rep==1) {
            tempQuery = "";
        }
        
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
            try {
                tableUtils.updateTableModelData((DefaultTableModel) listGoods.getModel(), rs, 3);
            } catch (Exception ex) {
                Logger.getLogger(ViewMember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
	}
        
        finally{
            this.disconnect();
        }
        
        try {
            listGoods.setRowSelectionInterval(0, 0);
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    public void editGoods() {
        try
        {
            int index = listGoods.getSelectedRow();
            String i = listGoods.getValueAt(index, 0).toString();
            aG.reset();
            aG.setVisible(true);
            aG.setResizable(false);
            aG.setLocationRelativeTo(null);
            aG.setTitle("Edit Goods - Information");
            aG.editList(i);
        }
        catch(Exception o)
        {
            DefaultTableModel model = (DefaultTableModel) listGoods.getModel();
            model.removeRow(model.getRowCount()-1);
        } 
    }
    
    public void deleteGoods() {
        Statement stmt = null;
        try
        {
            int index = listGoods.getSelectedRow();
            String i = listGoods.getValueAt(index, 0).toString();
            String tempQuery = "delete from goods_sold_dtl where invid="+i;
            this.connect();
            try
            {
                stmt = conn.createStatement();
            }
            
            catch(Exception p) { }
            
            try
            {
                stmt.addBatch(tempQuery);
                tempQuery = "delete from goods_sold_hdr where invid="+i;
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
            DefaultTableModel removeModel = (DefaultTableModel) listGoods.getModel();
            removeModel.setRowCount(0);
        }
        catch(Exception e)
        {
            
        }
        this.resetView();
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
            ex.printStackTrace();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        listGoods = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textInvDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textBilDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textRemarks = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDetails = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase List"));

        listGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "INVDATE", "BILLING"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listGoods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listGoodsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listGoods);
        if (listGoods.getColumnModel().getColumnCount() > 0) {
            listGoods.getColumnModel().getColumn(0).setPreferredWidth(5);
            listGoods.getColumnModel().getColumn(1).setResizable(false);
            listGoods.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Header"));

        jLabel1.setText("Invoice Date");

        textInvDate.setEditable(false);

        jLabel2.setText("Billing Date");

        textBilDate.setEditable(false);
        textBilDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBilDateActionPerformed(evt);
            }
        });

        jLabel3.setText("Amount");

        textAmount.setEditable(false);
        textAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textAmount.setText("0.00");

        jLabel4.setText("Remark");

        textRemarks.setEditable(false);
        textRemarks.setColumns(20);
        textRemarks.setRows(5);
        jScrollPane2.setViewportView(textRemarks);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textInvDate))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textBilDate)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textInvDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBilDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Details"));

        listDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Amount", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(listDetails);
        if (listDetails.getColumnModel().getColumnCount() > 0) {
            listDetails.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textBilDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBilDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBilDateActionPerformed

    private void listGoodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGoodsMouseClicked
            int index = listGoods.getSelectedRow();
            String i = listGoods.getValueAt(index, 0).toString();
            this.getListDetails(i);
    }//GEN-LAST:event_listGoodsMouseClicked

    public void getListDetails(String i) {
        String query = "select invdt,billingdt,invamt,remarks from goods_sold_hdr where invid="+i;
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
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                textRemarks.setText(rs.getString("remarks"));
                textInvDate.setText(df.format(df.parse(rs.getString("invdt"))));
                textBilDate.setText(df.format(df.parse(rs.getString("billingdt"))));
                textAmount.setText(String.format("%.2f", Float.parseFloat(rs.getString("invamt"))));
            }
            rs = null;
        }
        catch(Exception e)
        {
            
        }
        //PURCHASE DETAILS
        query = "select membername, balance, description from goods_sold_dtl where invid="+i;
        
        try {
            rs = stmt.executeQuery(query);
                try
                {                    
                    tableUtils.updateTableModelData((DefaultTableModel) listDetails.getModel(), rs, 3);
                }
                catch (Exception ex)
                {
                    Logger.getLogger(ViewMember.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        catch(Exception e) {
            
        }
        finally{
            this.disconnect();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable listDetails;
    private javax.swing.JTable listGoods;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textBilDate;
    private javax.swing.JTextField textInvDate;
    private javax.swing.JTextArea textRemarks;
    // End of variables declaration//GEN-END:variables
}
