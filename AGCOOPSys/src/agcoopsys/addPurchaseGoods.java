/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
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

/**
 *
 * @author admin
 */
public class addPurchaseGoods extends javax.swing.JFrame {

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
    
    DateFormat df;
    
    public DefaultListModel listModelMember = new DefaultListModel();
    public DefaultListModel listModelNonMember = new DefaultListModel();
    public DefaultListModel listPurchasing = new DefaultListModel();
    
    ArrayList<String> arrayQuery = new ArrayList<>();
        
    public addPurchaseGoods() {
        initComponents();
        
        df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        Date date = new Date();
        current = df.format(date);
        
        listMember.setModel(listModelMember);
        listNonMember.setModel(listModelNonMember);
        listPurchase.setModel(listPurchasing);
        
        this.initAll();
    }
    
    public void initAll()
    {
        this.getListMember(0);
        this.getListNonMember(0);
    }
    
    public void reset()
    {
        textBillDate.setText("");
        labelName.setText("");
        textAmount.setText("0.0");
        textDesc.setText("");
        totalAmount = 0;
        labelAmount.setText("0.0");
        listPurchasing.clear();
        textRemarks.setText("");
        listPurchasing.clear();
        arrayPersonToAdd.clear();
        searchBox.setText("");
    }
    
    public void editList(String id)
    {
        String tempQuery = "select * from goods_sold_dtl where invid='"+id+"'";
        ResultSet rs = null;
        this.connect();
        conn = this.getConnection();
        Statement stmt = null;
        
        addPurchasePerson person = new addPurchasePerson();
        
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
            String priceName = "";
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {                
                person.setInvid(rs.getInt("invid"));
                person.setMemberid(rs.getInt("memberid"));
                person.setStatus(rs.getString("ismember"));
                person.setName(rs.getString("membername"));
                person.setBalance(rs.getFloat("balance"));
                person.setDesc((rs.getString("description")));
                priceName = rs.getString("membername") + " : " + rs.getFloat("balance");
                listPurchasing.addElement(priceName);
                totalAmount+= rs.getFloat("balance");
                arrayPersonToAdd.add(person);
            }
            this.disconnect();      
            labelAmount.setText(String.valueOf(totalAmount));
        }
        catch (SQLException e)
        {
        
        }
    }
    
    public void initNames()
    {
        
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCancel = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        panelDetail = new javax.swing.JPanel();
        labelBill = new javax.swing.JLabel();
        textBillDate = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        tabPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMember = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listNonMember = new javax.swing.JList();
        buttonAdd = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listPurchase = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        labelAmount = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textDesc = new javax.swing.JTextArea();
        buttonNext = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        textRemarks = new javax.swing.JTextArea();
        labelSearch = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirm");
        buttonConfirm.setEnabled(false);
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        panelDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Detail Header"));

        labelBill.setText("Billing Date");

        javax.swing.GroupLayout panelDetailLayout = new javax.swing.GroupLayout(panelDetail);
        panelDetail.setLayout(panelDetailLayout);
        panelDetailLayout.setHorizontalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBill)
                .addGap(18, 18, 18)
                .addComponent(textBillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDetailLayout.setVerticalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBill)
                    .addComponent(textBillDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneStateChanged(evt);
            }
        });

        jScrollPane1.setViewportView(listMember);

        tabPane.addTab("Member", jScrollPane1);

        jScrollPane2.setViewportView(listNonMember);

        tabPane.addTab("Non Member", jScrollPane2);

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(listPurchase);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Details"));

        jLabel6.setText("Total amount");

        labelAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelAmount.setText("0.0");
        labelAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAmount))
                .addGap(40, 40, 40))
        );

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Name");
        jLabel2.setEnabled(false);

        labelName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Amount");
        jLabel3.setEnabled(false);

        textAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textAmount.setText("0.0");
        textAmount.setEnabled(false);
        textAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAmountActionPerformed(evt);
            }
        });
        textAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textAmountFocusGained(evt);
            }
        });

        jLabel4.setText("Description");
        jLabel4.setEnabled(false);

        textDesc.setColumns(20);
        textDesc.setRows(5);
        textDesc.setEnabled(false);
        jScrollPane3.setViewportView(textDesc);

        buttonNext.setText("Next>>");
        buttonNext.setEnabled(false);
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.setEnabled(false);
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonRemove.setText("<<Remove");
        buttonRemove.setEnabled(false);
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        jLabel1.setText("Remarks");
        jLabel1.setEnabled(false);

        textRemarks.setColumns(20);
        textRemarks.setRows(5);
        textRemarks.setEnabled(false);
        jScrollPane5.setViewportView(textRemarks);

        labelSearch.setText("Search");

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDetail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonNext)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSearch)
                            .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonSearch)
                                    .addComponent(buttonRefresh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonCancel)
                                    .addComponent(buttonAdd))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonRemove)
                                    .addComponent(buttonConfirm)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonBack)
                                    .addComponent(buttonNext))))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelActionPerformed
           
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
        
    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed

        this.goodsHeader();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void enabledFalse()
    {
        buttonAdd.setEnabled(false);
        tabPane.setEnabled(false);
        panelDetail.setEnabled(false);
        labelBill.setEnabled(false);
        listMember.setEnabled(false);
        listNonMember.setEnabled(false);
        buttonSearch.setEnabled(false);
        labelSearch.setEnabled(false);
        buttonRefresh.setEnabled(false);
        searchBox.setEnabled(false);
        
        textAmount.setEnabled(true);
        textDesc.setEnabled(true);
        jLabel3.setEnabled(true);
        jLabel4.setEnabled(true);
        buttonNext.setEnabled(true);
        buttonBack.setEnabled(true);
        
    }
    
    private void enabledTrue()
    {
        buttonAdd.setEnabled(true);
        tabPane.setEnabled(true);
        textBillDate.setEnabled(true);
        panelDetail.setEnabled(true);
        labelBill.setEnabled(true);
        listMember.setEnabled(true);
        listNonMember.setEnabled(true);
        buttonSearch.setEnabled(true);
        labelSearch.setEnabled(true);
        buttonRefresh.setEnabled(true);
        searchBox.setEnabled(true);
        
        textAmount.setEnabled(false);
        textDesc.setEnabled(false);
        jLabel3.setEnabled(false);
        jLabel4.setEnabled(false);
    }
    
    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed

        String holdName = "";
        
        textAmount.setText("0");
        //CHECK IF BILLDATE IS FILLED
        //THEN PROCEED TO ADD DETAILS FOR COMMIT
        if(tabPane.getSelectedIndex() == 0)
        {
            holdName = arrayMemberName.get(listMember.getSelectedIndex());
            labelName.setText(holdName);
            this.enabledFalse();
            isMember = 0;
        }
        else if(tabPane.getSelectedIndex() == 1)
        {
           holdName = arrayNonMemberName.get(listNonMember.getSelectedIndex());
           labelName.setText(holdName);
           this.enabledFalse();
           isMember = 1;
           // System.out.println("asd");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddActionPerformed

    private void tabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneStateChanged

       
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPaneStateChanged

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed

        float amount = 0;
        addPurchasePerson person = new addPurchasePerson();
        
        String name = "";
        jLabel1.setEnabled(true);
        textRemarks.setEnabled(true);
        buttonBack.setEnabled(false);
        buttonNext.setEnabled(false);

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
            String memberAdd = arrayMemberName.get(listMember.getSelectedIndex()) + "   :  " + amount;
            name = arrayMemberName.get(listMember.getSelectedIndex());
            listPurchasing.addElement(memberAdd);
            status = "Y";
        }
        else
        {
            memberid = arrayNonMemberid.get(listNonMember.getSelectedIndex());
            String memberAdd = arrayNonMemberName.get(listNonMember.getSelectedIndex()) + "  :  " + amount;
            name = arrayNonMemberName.get(listMember.getSelectedIndex());
            listPurchasing.addElement(arrayNonMemberName.get(listNonMember.getSelectedIndex()));
            status = "N";
        }
        
        person.setBalance(amount);
        person.setMemberid(memberid);
        person.setStatus(status);
        person.setName(name);
        
        
        arrayPersonToAdd.add(person);
        
        totalAmount += amount;
        labelAmount.setText(String.valueOf(totalAmount));
        buttonConfirm.setEnabled(true);
        buttonRemove.setEnabled(true);
        
        listPurchase.setSelectedIndex(0);
        this.enabledTrue();
        name = "";
    }//GEN-LAST:event_buttonNextActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed

        this.enabledTrue();
        textAmount.setText("0");
        buttonBack.setEnabled(false);
        buttonNext.setEnabled(false);
        
        
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed

        addPurchasePerson person = (addPurchasePerson) arrayPersonToAdd.get(listPurchase.getSelectedIndex());
        float tempAmount = Float.parseFloat(labelAmount.getText());
        float tempChangedAmount = tempAmount - person.getBalance(); // CHANGE VALUE OF LABEL
        labelAmount.setText(Float.toString(tempChangedAmount));
        
        arrayPersonToAdd.remove(listPurchase.getSelectedIndex()); //FIRST :- BEFORE DELETE IN JLIST
        listPurchasing.remove(listPurchase.getSelectedIndex());
        
        try
        {
            listPurchase.setSelectedIndex(0);
        }
        catch(Exception e)
        {
            
        }
        
        if(listPurchase.getSelectedIndex() == -1)
        {
            buttonRemove.setEnabled(false);
            buttonConfirm.setEnabled(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void textAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAmountActionPerformed

    private void textAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textAmountFocusGained

        
        // TODO add your handling code here:
    }//GEN-LAST:event_textAmountFocusGained

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed

        if(tabPane.getSelectedIndex() == 0)
        {
            this.getListMember(1);
        }
        else if(tabPane.getSelectedIndex() == 1)
        {
            this.getListNonMember(1);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed

        this.initAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRefreshActionPerformed


    
    public void goodsHeader()
    {
        this.connect();
        Statement stmt = null;
        Date billDate = null;
        int error = 0;
        String dateBill = "";
        String errorMessages = "";
        
        try // GET BILL DATE
        {
            billDate = df.parse(textBillDate.getText());
            dateBill = df.format(billDate);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            error++;
            errorMessages += "BIll date: Must not be empty\n";
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
                Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            addPurchasePerson person = new addPurchasePerson();
            int loopIndex = arrayPersonToAdd.size();
        
            //IF ADD
            String headerQuery = "insert into goods_sold_hdr (invdt,billingdt,invamt,remarks) values"+
                "('"+current+"','"+dateBill+"','"+totalAmount+"','"+remarks+"')";
        
            try
            {
            stmt.executeQuery(headerQuery);
            }
            catch (SQLException ex) {
                Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            headerQuery = "select max(invid) as invid from goods_sold_hdr";
            ResultSet rs = null;
            try
            {
                rs = stmt.executeQuery(headerQuery);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //OPTIMIZE WITH BATCH QUERY - NOT YET FINAL
            //IF ADD
            String query = "insert all\n";
            for(int i = 0; i<loopIndex; i++)
            {
                person = (addPurchasePerson) arrayPersonToAdd.get(i);
                query += "into goods_sold_dtl (invid,memberid,amount,balance,description,ismember,membername) values"+
                    "('"+invid+"','"+person.getMemberid()+"','"+person.getBalance()+"','"+person.getBalance()+"','"+person.getDesc()+"','"+person.getStatus()+"','"+person.getName()+"')\n";
            
            }
            query += "select * from dual";
            //System.out.println(query);
            try
            {
                stmt.executeQuery(query);
                JOptionPane.showMessageDialog(null, "Database Update: Success", "Updating database", JOptionPane.INFORMATION_MESSAGE);
            } 
            catch (SQLException ex) {
                Logger.getLogger(addPurchaseGoods.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(addPurchaseGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addPurchaseGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addPurchaseGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addPurchaseGoods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addPurchaseGoods().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelAmount;
    private javax.swing.JLabel labelBill;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JList listMember;
    private javax.swing.JList listNonMember;
    private javax.swing.JList listPurchase;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textBillDate;
    private javax.swing.JTextArea textDesc;
    private javax.swing.JTextArea textRemarks;
    // End of variables declaration//GEN-END:variables
}
