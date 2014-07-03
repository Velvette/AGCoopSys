/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maininterface;

import utilities.DbUtils;
import mainaddinterface.AddPurchaseGoods;
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
public class ViewGoods extends javax.swing.JPanel {

    AddPurchaseGoods aG = new AddPurchaseGoods();
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    ArrayList<String> companyID = new ArrayList<>();
    ArrayList<String> compName = new ArrayList<>();
    DbUtils tableUtils = new DbUtils();
    
    public ViewGoods() {
        initComponents();
        listGoodsHeader.getTableHeader().setReorderingAllowed(false);
    }
    
    public void getList()
    {
        String tempQuery = "select invid,invdt,billingdt,invamt,remarks from goods_sold_hdr";
        
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
                tableUtils.updateTableModelData((DefaultTableModel) listGoodsHeader.getModel(), rs, 5);
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
        finally{
            this.disconnect();
        }
        try{
            listGoodsHeader.setRowSelectionInterval(0, 0);
        }
        catch(Exception e)
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
    
    public void editGoods()
    {
        try
        {
            int index = listGoodsHeader.getSelectedRow();
            String i = listGoodsHeader.getValueAt(index, 0).toString();
            aG.reset();
            aG.setVisible(true);
            aG.setResizable(false);
            aG.setLocationRelativeTo(null);
            aG.setTitle("Edit Goods - Information");
            aG.editList(i);
        }
        catch(Exception o)
        {
            DefaultTableModel model = (DefaultTableModel) listGoodsHeader.getModel();
            model.removeRow(model.getRowCount()-1);
        }     
    }
    
    public void deleteGoods()
    {
                Statement stmt = null;
        try
        {
            int index = listGoodsHeader.getSelectedRow();
            String i = listGoodsHeader.getValueAt(index, 0).toString();
            String tempQuery = "delete from goods_sold_dtl where invid="+i;
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
            DefaultTableModel removeModel = (DefaultTableModel) listGoodsHeader.getModel();
            removeModel.setRowCount(0);
        }
        catch(Exception e)
        {
            
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listGoodsHeader = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Goods Purchase Information"));

        listGoodsHeader.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "INVOICE DATE", "BILLING DATE", "AMOUNT", "REMARKS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listGoodsHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listGoodsHeaderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listGoodsHeader);
        listGoodsHeader.getColumnModel().getColumn(0).setResizable(false);
        listGoodsHeader.getColumnModel().getColumn(1).setResizable(false);
        listGoodsHeader.getColumnModel().getColumn(2).setResizable(false);
        listGoodsHeader.getColumnModel().getColumn(3).setResizable(false);
        listGoodsHeader.getColumnModel().getColumn(4).setResizable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listGoodsHeaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGoodsHeaderMouseClicked

        
        // TODO add your handling code here:
    }//GEN-LAST:event_listGoodsHeaderMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listGoodsHeader;
    // End of variables declaration//GEN-END:variables
}
