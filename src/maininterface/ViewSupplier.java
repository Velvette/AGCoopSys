     /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maininterface;

import utilities.DbUtils;
import mainaddinterface.AddSupplier;
import mainconnect.ConnectToDatabaseSys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ViewSupplier extends javax.swing.JPanel {

    AddSupplier aS = new AddSupplier();
    
    public ViewSupplier() {
        
        initComponents();
        listSupplier.getTableHeader().setReorderingAllowed(false);
    }
    
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    DbUtils tableUtils = new DbUtils();
    String query = "Select suppid,suppname,contactno1,contactno2,email from supplier order by suppname";

    public void getList(int rep,String searchText)
    {
        String tempQuery = "";
        if(rep == 0) {
            tempQuery = query;
        }    
        else if(rep == 1) {
            searchText = searchText.toUpperCase();
            tempQuery = "select * from (Select suppid,suppname,contactno1,contactno2,email,suppvname from supplier order by suppname) where suppname like '"+searchText+"'";
        }

	Statement stmt = null;       
	this.connect();
	conn = this.getConnection();
                
	try {
            stmt = conn.createStatement();
        }
                
	catch (SQLException e) {
            e.printStackTrace();
        }
		
	ResultSet rs;
	try {
            rs = stmt.executeQuery(tempQuery);
            try {
                tableUtils.updateTableModelData((DefaultTableModel) listSupplier.getModel(), rs,5);
            } 
            catch (Exception ex) {
                Logger.getLogger(ViewMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        catch (SQLException e) {
            e.printStackTrace();
	}
        finally {
            this.disconnect();
        }
        try {
            listSupplier.setRowSelectionInterval(0, 0);
        }
        catch(Exception e) { }
    }
        
    public void connect()
    {
        dbDriver = paramDB.getDbClass();
        dbUrl = paramDB.getDbUrl();
        password = paramDB.getPassword(); // CHANGE PASSWORD
        username = paramDB.getName();
                
        try {
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbUrl,username,password);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
	
    public Connection getConnection()
    {
	return conn;
    }
	
    public void disconnect()
    {
       try {
            conn.close();
       } 
       catch (Exception ex) {
            ex.printStackTrace();
       }
    }
    
    public void deleteSupplier()
    {
        try {
            int index = listSupplier.getSelectedRow();
            String i = listSupplier.getValueAt(index, 0).toString();
            String query = "delete from supplier where suppid ="+i;
            ConnectToDatabaseSys connectDB = new ConnectToDatabaseSys();
            connectDB.accessLoopDatabase(query);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Select row to delete", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }

    public void editSupplier()
    {
            int index = listSupplier.getSelectedRow();
            String i = listSupplier.getValueAt(index, 0).toString();
            aS.setVisible(true);
            aS.setLocationRelativeTo(null);
            aS.setResizable(false);
            aS.setTitle("Edit Supplier - Information");
            aS.checkInputEditSupplier(i);
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
        listSupplier = new javax.swing.JTable();

        listSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "SUPPLIER NAME", "CONTACT (1)", "CONTACT (2)", "E-MAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(listSupplier);
        listSupplier.getColumnModel().getColumn(0).setPreferredWidth(30);
        listSupplier.getColumnModel().getColumn(1).setPreferredWidth(400);
        listSupplier.getColumnModel().getColumn(3).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listSupplier;
    // End of variables declaration//GEN-END:variables
}
