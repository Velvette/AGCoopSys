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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lenovo
 */
public class viewCompany extends javax.swing.JPanel {

    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    int rep = 0;
    DbUtils tableUtils = new DbUtils();
    String query = "Select compid,compname,contactperson,contactno1,contactno2,email from company order by compname";
    addCompany aC = new addCompany();
    
    public viewCompany() {
        
        initComponents();
        listCompany.getTableHeader().setReorderingAllowed(false);
    }

    public void getList()
    {
        String tempQuery = "";
        if(rep==0)
        {
            tempQuery = query;
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
            
            try
            {
                tableUtils.updateTableModelData((DefaultTableModel) listCompany.getModel(), rs, 6);
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
        try{
            listCompany.setRowSelectionInterval(0, 0);
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
    
    public void deleteCompany()
    {
        try
        {
            int index = listCompany.getSelectedRow();
            String i = listCompany.getValueAt(index, 0).toString();
        
            String query = "delete from company where compid ="+i;
            ConnectToDatabaseSys connectDB = new ConnectToDatabaseSys();
            connectDB.accessLoopDatabase(query);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: Select row to delete", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void editCompany()
    {
            int index = listCompany.getSelectedRow();
            String i = listCompany.getValueAt(index, 0).toString();
            aC.setVisible(true);
            aC.setLocationRelativeTo(null);
            aC.setResizable(false);
            aC.setTitle("Edit Company - Information");
            aC.checkInputEditCompany(i);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listCompany = new javax.swing.JTable();

        listCompany.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COMPANY NAME", "CONTACT 1", "CONTACT 2", "E-MAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listCompanyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listCompany);
        listCompany.getColumnModel().getColumn(0).setResizable(false);
        listCompany.getColumnModel().getColumn(0).setPreferredWidth(30);
        listCompany.getColumnModel().getColumn(1).setResizable(false);
        listCompany.getColumnModel().getColumn(1).setPreferredWidth(250);
        listCompany.getColumnModel().getColumn(2).setResizable(false);
        listCompany.getColumnModel().getColumn(2).setPreferredWidth(150);
        listCompany.getColumnModel().getColumn(3).setResizable(false);
        listCompany.getColumnModel().getColumn(3).setPreferredWidth(150);
        listCompany.getColumnModel().getColumn(4).setResizable(false);
        listCompany.getColumnModel().getColumn(4).setPreferredWidth(150);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCompanyMouseClicked

        
        // TODO add your handling code here:
    }//GEN-LAST:event_listCompanyMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listCompany;
    // End of variables declaration//GEN-END:variables
}
