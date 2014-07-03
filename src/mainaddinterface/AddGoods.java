/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainaddinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainconnect.ConnectToDatabaseSys;

/**
 *
 * @author admin
 */
public class AddGoods extends javax.swing.JFrame {

    private String current;
    
    public String dbUrl;
    public String dbDriver;
    public String username;
    public String password;
    public Connection conn;
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
        
    ArrayList<String> arrayMemberName = new ArrayList<>();
    ArrayList<Integer> arrayMemberid = new ArrayList<>();
    
    ArrayList<String> arrayNonMemberName = new ArrayList<>();
    ArrayList<Integer> arrayNonMemberid = new ArrayList<>();
    
    ArrayList<Object> arrayPersonToAdd = new ArrayList<>();
        
    int isMember = 0;
    float totalAmount = 0;
    
    String invid = "";
    
    DateFormat df;
    
    public DefaultListModel listModelMember = new DefaultListModel();
    public DefaultListModel listModelNonMember = new DefaultListModel();
    
    ArrayList<String> arrayQuery = new ArrayList<>();
        
    int choice = 0;
    public AddGoods() {
         initComponents();
        
        df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        Date date = new Date();
        current = df.format(date);
        invdate.setText(current);
        listMember.setModel(listModelMember);
        listNonMember.setModel(listModelNonMember);
        
        this.initAll();
    }
    
    public void initAll()
    {
        this.getListMember(0);
        this.getListNonMember(0);
    }
    
    public void getListMember(int choice)
    {
        String tempQuery = "";
        
        if(choice == 0)
        {
            tempQuery = "SELECT * from member";
        }
        
        else if(choice == 1)
        {
            String searchText = searchBox.getText().toUpperCase();
            tempQuery = "select * from member where lastname like '"+searchText+"'";
        }
        
        listModelMember.clear();
        arrayMemberName.clear();
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
            String nameTemp = "";
            int memberid;
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {                
                nameTemp += rs.getString("lastname");
                nameTemp += ", " + rs.getString("firstname");
                nameTemp += " " + rs.getString("midinit");
                memberid = rs.getInt("memberid");
                arrayMemberName.add(nameTemp);
                arrayMemberid.add(memberid);
                listModelMember.addElement(nameTemp);
                nameTemp = "";
            }
            this.disconnect();      
        }
        catch (SQLException e)
        {
        
        }
        listMember.setSelectedIndex(0);
    }
       
    public void getListNonMember(int choice)
    {
        String tempQuery = "";
        
        if(choice == 0)
        {
            tempQuery = "SELECT * from nonmember";
        }
        
        else if(choice == 1)
        {
            String searchText = searchBox.getText().toUpperCase();
            tempQuery = "select * from nonmember where lastname like '"+searchText+"'";
        }
        
        listModelNonMember.clear();
        arrayNonMemberid.clear();
        arrayNonMemberName.clear();
        
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
            String nameTemp = "";
            int memberid;
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {                
                nameTemp += rs.getString("lastname");
                nameTemp += ", " + rs.getString("firstname");
                nameTemp += " " + rs.getString("midinit");
                memberid = rs.getInt("memberid");
                arrayNonMemberName.add(nameTemp);
                arrayNonMemberid.add(memberid);
                listModelNonMember.addElement(nameTemp);
                nameTemp = "";
            }
            this.disconnect();      
        }
        catch (SQLException e)
        {
        
        }
        listNonMember.setSelectedIndex(0);
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
        jLabel4 = new javax.swing.JLabel();
        invdate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textBillDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        labelAmount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        textRemarks = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        tabPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMember = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listNonMember = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textDesc = new javax.swing.JTextArea();
        buttonNext = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        buttonRemove = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel4.setText("Invoice Date");

        jLabel5.setText("Billing Date");

        textBillDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBillDateActionPerformed(evt);
            }
        });

        jLabel6.setText("Invoice Amount");

        labelAmount.setEditable(false);
        labelAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        labelAmount.setText("0.00");
        labelAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Remarks");

        textRemarks.setColumns(20);
        textRemarks.setRows(5);
        jScrollPane5.setViewportView(textRemarks);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invdate, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(textBillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(labelAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(invdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(labelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textBillDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchasing"));

        jScrollPane1.setViewportView(listMember);

        tabPane.addTab("Member", jScrollPane1);

        jScrollPane2.setViewportView(listNonMember);

        tabPane.addTab("Non-Member", jScrollPane2);

        jLabel1.setText("Search");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Amount");

        textAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textAmount.setText("0.00");

        jLabel3.setText("Description");

        textDesc.setColumns(20);
        textDesc.setRows(5);
        jScrollPane3.setViewportView(textDesc);

        buttonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        buttonClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eraser.png"))); // NOI18N
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textAmount)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 52, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonNext)
                        .addComponent(buttonClear))
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchasing Details"));

        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Amount", "Description"
            }
        ));
        jScrollPane4.setViewportView(purchaseTable);

        buttonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemove)
                .addContainerGap())
        );

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        jButton6.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void textBillDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBillDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBillDateActionPerformed

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed

        float amount = 0;
        AddPurchasePerson person = new AddPurchasePerson();
        
        String name = "";
        jLabel1.setEnabled(true);
        
        try
        {
            amount = Float.parseFloat(textAmount.getText());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String desc = textDesc.getText();
        int memberid;
        String status;
        
        if(isMember == 0)
        {
            memberid = arrayMemberid.get(listMember.getSelectedIndex());
            name = arrayMemberName.get(listMember.getSelectedIndex());
            status = "Y";
        }
        else
        {
            memberid = arrayNonMemberid.get(listNonMember.getSelectedIndex());
            name = arrayNonMemberName.get(listMember.getSelectedIndex());
            status = "N";
        }
        
        person.setBalance(Float.parseFloat(String.format("%.2f", amount)));
        person.setMemberid(memberid);
        person.setStatus(status);
        person.setName(name);
        person.setDesc(desc);
        
        DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
        model.addRow(new Object[]{person.getName(),person.getBalance(),person.getDesc()});
        
        arrayPersonToAdd.add(person);
        
        totalAmount += Float.parseFloat(String.format("%.2f", amount));
        labelAmount.setText(String.valueOf(totalAmount));
        name = "";
    
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(tabPane.getSelectedIndex() == 0)
        {
            this.getListMember(1);
        }
        else if(tabPane.getSelectedIndex() == 1)
        {
            this.getListNonMember(1);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.initAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed

        try
        {
            int index = purchaseTable.getSelectedRow();
            
            AddPurchasePerson person = (AddPurchasePerson) arrayPersonToAdd.get(index);
            
            
            float tempAmount = Float.parseFloat(labelAmount.getText());
            float tempChangedAmount = tempAmount - person.getBalance(); // CHANGE VALUE OF LABEL
            labelAmount.setText(Float.toString(tempChangedAmount));
        
            arrayPersonToAdd.remove(index); //FIRST :- BEFORE DELETE IN JLIST
            DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
            model.removeRow(index);
            
            try{
                purchaseTable.setRowSelectionInterval(0, 0);
            }
            catch(Exception e)
            {
            
            }
        }
        
        catch(Exception e)
        {
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed

        textAmount.setText("0.00");
        textDesc.setText("");
        searchBox.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonClearActionPerformed

    public void goodsHeader()
    {
        this.connect();
        Statement stmt = null;
        Date billDate = null;
        int error = 0;
        String dateBill = "";
        String errorMessages = "";
        String dateInv = "";
        Date invDate = null;
        try // GET BILL DATE
        {
            billDate = df.parse(textBillDate.getText());
            dateBill = df.format(billDate);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            error++;
            errorMessages += "Bill date: Must not be empty\n";
        }
        
        try // GET BILL DATE
        {
            invDate = df.parse(invdate.getText());
            dateInv = df.format(invDate);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            error++;
            errorMessages += "Invoice date: Must not be empty\n";
        }
        
        if(error == 0)
        {
            String remarks = textRemarks.getText();
        
            try
            {
                stmt = conn.createStatement();
            }
        
            catch(SQLException ex)
            {
                Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            AddPurchasePerson person = new AddPurchasePerson();
            int loopIndex = arrayPersonToAdd.size();
        
            //IF ADD
            String headerQuery = "";
            if(choice == 0)
            {
                headerQuery = "insert into goods_sold_hdr (invdt,billingdt,invamt,remarks) values"+
                "('"+dateInv+"','"+dateBill+"','"+totalAmount+"','"+remarks+"')";
            }
            else if(choice == 1)
            {
                headerQuery = "update goods_sold_hdr set invdt='"+dateInv+"',billingdt='"+dateBill+"',invamt='"+totalAmount+"',remarks='"+remarks+"' where invid="+this.invid;
            }
            try
            {
                stmt.executeQuery(headerQuery);
            }
            catch (SQLException ex) {
                Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            headerQuery = "select max(invid) as invid from goods_sold_hdr";
            ResultSet rs = null;
            try
            {
                rs = stmt.executeQuery(headerQuery);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
            int invid = 0;
            
            try
            {
                if(rs.next())
                {
                    invid = rs.getInt("invid");
                }
            }
        
            catch (SQLException ex)
            {
                Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //OPTIMIZE WITH BATCH QUERY - NOT YET FINAL
            //IF ADD
            String query = "insert all\n";
            String holdQuery = "";
            if(choice == 0)
                for(int i = 0; i<loopIndex; i++)
                {
                    person = (AddPurchasePerson) arrayPersonToAdd.get(i);
                    query += "into goods_sold_dtl (invid,memberid,amount,balance,description,ismember,membername) values"+
                        "('"+invid+"','"+person.getMemberid()+"','"+person.getBalance()+"','"+person.getBalance()+"','"+person.getDesc()+"','"+person.getStatus()+"','"+person.getName()+"')\n";
            
                }
            
            else if(choice == 1)
            {
                holdQuery = "delete from goods_sold_dtl where invid="+this.invid;
                for(int i = 0; i<loopIndex; i++)
                {
                    person = (AddPurchasePerson) arrayPersonToAdd.get(i);
                    query += "into goods_sold_dtl (invid,memberid,amount,balance,description,ismember,membername) values"+
                        "('"+this.invid+"','"+person.getMemberid()+"','"+person.getBalance()+"','"+person.getBalance()+"','"+person.getDesc()+"','"+person.getStatus()+"','"+person.getName()+"')\n";
            
                }
            }
                query += "select * from dual";
            //System.out.println(query);
            try
            {
                if(choice == 1)
                    stmt.execute(holdQuery);
                stmt.executeQuery(query);
                JOptionPane.showMessageDialog(null, "Database Update: Success", "Updating database", JOptionPane.INFORMATION_MESSAGE);
            } 
            catch (SQLException ex) {
                Logger.getLogger(AddPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            finally
            {
                this.disconnect();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, errorMessages, "Error: Goods Purchased", JOptionPane.ERROR_MESSAGE);
        }
        choice = 0;
    }
    
    public void reset()
    {
        textBillDate.setText("");
        textAmount.setText("0.0");
        textDesc.setText("");
        totalAmount = 0;
        labelAmount.setText("0.0");
        textRemarks.setText("");
        DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
        model.setRowCount(0);
        arrayPersonToAdd.clear();
        searchBox.setText("");
        
    }
    
    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed

        this.goodsHeader();
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.reset();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    
    public void initBill(String hiredt) throws ParseException
    {
        Date Hired = df.parse(hiredt);
        String sHired = df.format(Hired);
        textBillDate.setText(sHired);
    }
    
    public void initInv(String hiredt) throws ParseException {
        Date Hired = df.parse(hiredt);
        String sHired = df.format(Hired);
        invdate.setText(sHired);
    }
        
    public void editHDR(String id)
    {
        String tempQuery = "select * from goods_sold_hdr where invid='"+id+"'";
        
        ResultSet rs;
        this.connect();
        conn = this.getConnection();
        Statement stmt = null;
        String tempDate = "";
        String invDate = "";
        invid = id;
        try
        {
            stmt = conn.createStatement();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            rs = stmt.executeQuery(tempQuery);
            if(rs.next()) {
                tempDate = rs.getString("billingdt");
                invDate = rs.getString("invdt");
            }
            try {
                this.initBill(tempDate);
                this.initInv(invDate);
            }
            catch(Exception e) { }   
        }
        catch (SQLException e)
        {
        
        }
        finally {
            this.disconnect();    
        }
    }
    
    public void editList(String id) {
        
        
        String tempQuery = "select * from goods_sold_dtl where invid='"+id+"'";
        ResultSet rs = null;
        this.connect();
        conn = this.getConnection();
        Statement stmt = null;
        String tempdate = "";
        AddPurchasePerson person = new AddPurchasePerson();
        
        try
        {
            stmt = conn.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            DefaultTableModel model = (DefaultTableModel) purchaseTable.getModel();
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {                
                person.setInvid(rs.getInt("invid"));
                person.setMemberid(rs.getInt("memberid"));
                person.setStatus(rs.getString("ismember"));
                person.setName(rs.getString("membername"));
                person.setBalance(rs.getFloat("balance"));
                person.setDesc((rs.getString("description")));
                totalAmount+= rs.getFloat("balance");
                arrayPersonToAdd.add(person);
                model.addRow(new Object[]{person.getName(),person.getBalance(),person.getDesc()});
            }
            totalAmount = Float.parseFloat(String.format("%.2f", totalAmount));
            labelAmount.setText(String.valueOf(totalAmount));
        }
        catch (SQLException e)
        {
        
        }
        finally {
            this.disconnect();
        }
        this.editHDR(id);
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
            java.util.logging.Logger.getLogger(AddGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddGoods().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JTextField invdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField labelAmount;
    private javax.swing.JList listMember;
    private javax.swing.JList listNonMember;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textBillDate;
    private javax.swing.JTextArea textDesc;
    private javax.swing.JTextArea textRemarks;
    // End of variables declaration//GEN-END:variables
}
