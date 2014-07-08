/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainaddinterface;

import agcoopsys.companyList;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainconnect.ConnectToDatabaseSys;
import mainprocesses.ProcessBillStatement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utilities.QueryWarehouse;


/**
 *
 * @author Lenovo
 */
public class AddBillingStatement extends javax.swing.JFrame {

        ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<String> arrayListCompany = new ArrayList<>();
    ConnectToDatabaseSys paramDB = new ConnectToDatabaseSys();
    private String dbUrl;
    private String dbDriver;
    private String username;
    private String password;
    private Connection conn;
    
    private String fromText;
    private String untilText;
    private String billText;
    
    public Date fromDate;
    public Date untilDate;
    public Date billDate;
    
    private String finalFrom;
    private String finalUntil;
    private String finalBill;
    
    DateFormat df; 
    DateFormat bill;
    ArrayList<String> firstname = new ArrayList<>();
    ArrayList<String> lastname = new ArrayList<>();
    ArrayList<String> midinit = new ArrayList<>();
    ArrayList<String> loanType = new ArrayList<>();
    ArrayList<Float> monAmort = new ArrayList<>();
        
    
    public AddBillingStatement() {
        initComponents();
        this.returnParams();
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.bill = new SimpleDateFormat("MMMM dd, yyyy");
        df.setLenient(false);
    }

    public void clearCombo()
    {
        comboCompany.removeAllItems();
        
        arrayList.clear();
        arrayListCompany.clear();
        this.returnParams();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboCompany = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        textBilling = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFrom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textUntil = new javax.swing.JTextField();
        buttonProcess = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Company");

        jLabel4.setText("Billing Date");

        jLabel2.setText("Date from");

        jLabel3.setText("Date until");

        buttonProcess.setText("Process");
        buttonProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProcessActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBilling)
                            .addComponent(comboCompany, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFrom)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textUntil, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBilling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textUntil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonProcess)
                    .addComponent(buttonClear)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        public void setTextFromDate()
    {
        fromText = textFrom.getText();
        System.out.println(fromText);
    }
    
    public void setTextUntilDate()
    {
        untilText = textUntil.getText();
        System.out.println(untilText);
    }
    
    public void setTextBillDate()
    {
        billText = textBilling.getText();
        System.out.println(billText);
    }
    
        public void processBillStatementDate()
    {        
        String errorMessages = "";
        int error = 0;
        
        try
        {  
            fromDate = df.parse(fromText);
            finalFrom = df.format(fromDate);
            untilDate = df.parse(untilText);
            finalUntil = df.format(untilDate);
            billDate = df.parse(billText);
            finalBill = df.format(billDate);
        } 
        catch (Exception p)
        { 
            errorMessages += "Date From: Format YYYY-MM-DD\n";
            error++;
        }
        
                
        if(error>=1)
        {
            System.out.println(errorMessages);
        }
        else
        {
            this.processBillNew();
            this.printResults();
        }
    }
    
    private void buttonProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProcessActionPerformed

        this.setTextFromDate();
        this.setTextUntilDate();
        this.setTextBillDate();
        this.processBillStatementDate();

        // TODO add your handling code here:
    }//GEN-LAST:event_buttonProcessActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed

        this.resetTexts();
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonClearActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        //this.resetTexts();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public void resetTexts()
    {
        textFrom.setText("");
        textUntil.setText("");
        textBilling.setText("");
    }


    
    public void returnParams()
    {        
        final String query = "select compid, compname from company order by compname";
        
        ResultSet rs;
        this.connect();
        Statement stmt = null;
        
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
            companyList compList = new companyList();
            rs = stmt.executeQuery(query);      
            while(rs.next())
            {
                comboCompany.addItem(rs.getString("compname"));
                arrayList.add(rs.getInt("compid"));
                arrayListCompany.add(rs.getString("compname"));
            }
            this.disconnect();      
	}
        
        catch (SQLException e)
        {
            e.printStackTrace();
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
    
    public int getCompanyIdCombo(int comboID)
    {
        int companyID = 0;
        companyID = arrayList.get(comboID);
        return companyID;
    }
    
    public void printResults()
    {
        for(int i = 0; i<firstname.size(); i++)
        {
            //System.out.println(lastname.get(i) +", "+ firstname.get(i) + " " + midinit.get(i) + ". --- " + monAmort.get(i) + "--" + loanType.get(i));
        }
        
    }
    

    
    public void billReport() throws JRException
    {
        String companyName = (String) comboCompany.getSelectedItem();
        String dFrom = bill.format(fromDate);
        String dUntil = bill.format(untilDate);
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        HashMap jasperParameter = new HashMap();
        String reportSource = "";
        jasperReport = JasperCompileManager.compileReport("resources//JAR//BillingStatement.jrxml");       
        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, conn);
                JasperViewer.viewReport(jasperPrint, false);        
        
    }
    
    public void processBillNew()
    {
        System.out.println("billnew");
        QueryWarehouse bank = new QueryWarehouse();
        Date date = new Date();
        String currentDate = df.format(date);
        int compid = this.getCompanyIdCombo(comboCompany.getSelectedIndex());
        Statement stmt = null;
        int billid = 0;
        String tempQuery = bank.bill_hdr(fromText, finalUntil, currentDate, compid,finalBill);
        
        this.connect();
        
        try
        {
            stmt = conn.createStatement();
            System.out.println("CONNECTING TO DATABASE..");
            //progressText = "Connecting to Database..\n";
            //textProgress.append(progressText);
            stmt.executeQuery(tempQuery);
            //progressText = "Creating Bill Header..\n";
            //textProgress.append(progressText);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        ResultSet rs;
        
        tempQuery = bank.maxBillId();
        try
        {
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
            {
                billid = rs.getInt("billid");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        rs = null;
        int memberid = 0;
        int regid = 0;
        int calamityid = 0;
        int emerid = 0;
        int educid = 0;
        int cashid = 0;
        
        float regamt = 0;
        float calamityamt = 0;
        float emeramt= 0;
        float educamt = 0;
        float total = 0;
        float cashamt = 0;
        float goodsamt = 0;
        float contribution = 0;
        
        String loanType = "";
        String lastname = "";
        String firstname = "";
        String midinit = "";
        String membername = "";
        
        //GET MONTHS WHERE MEMBER NEEDS TO PAY
        tempQuery = bank.currentMonthPeriod(finalFrom, finalUntil);
        
        try
        {
            stmt.executeQuery(tempQuery);
            tempQuery = bank.joinDistinct(compid);
            stmt.executeQuery(tempQuery);
            System.out.println("JOINING DATABASE TABLES..");
          //  progressText = ("Joining (Success)..\n Joining Non-Members - Company..\n");
          //  textProgress.append(progressText);
            tempQuery = bank.joinDistinctNon(compid);
            stmt.executeQuery(tempQuery);    
          //  progressText = ("Joining (Success)..\n");
            //textProgress.append(progressText);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ProcessBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            tempQuery = "select * from current_month natural join member where compid="+compid;
            rs = stmt.executeQuery(tempQuery);
            
           // progressText = "Gathering Member Loans..\n";
           // textProgress.append(progressText);
            int memberCount = 0;
            int prevId = 0;
            int reset = 0;
            int overTotal = 0;
            //System.out.println("here1");
            tempQuery = "insert all\n";
            while(rs.next())
            {                   
                memberid = rs.getInt("memberid");
                if(reset==0)
                    prevId = memberid;
                
                if(memberid != prevId)
                { 
                    //System.out.println("commit: " + membername);
                   // progressText = ("Current Member #:" + memberCount + " : " + membername);
                   // textProgress.append(progressText);
                    System.out.println("PROCESSING MEMBER #:" + memberCount + " : " + membername);
                    goodsamt = this.getBalance(prevId);
                    cashamt = this.getCashamt(prevId);
                    total = regamt+emeramt+educamt+cashamt+goodsamt+calamityamt+contribution;
                    overTotal += total;
                    tempQuery += bank.commitToBill_DTL(billid, prevId, membername, contribution, cashid, cashamt, regid, regamt, educid, educamt, calamityid, calamityamt, emerid, emeramt, goodsamt, total, compid, "Y"); 
                    memberCount++;
                    regid = 0;
                    regamt = 0;
                    educid = 0;
                    educamt = 0;
                    emerid = 0;
                    emeramt = 0;
                    calamityid = 0;
                    calamityamt = 0;
                }
                loanType = rs.getString("loantype");                
                switch(loanType)
                {
                    case "R": regid = rs.getInt("loanid");
                                  regamt = rs.getFloat("mon_amort");
                            break;
                    case "E": educid = rs.getInt("loanid");
                                  educamt = rs.getFloat("mon_amort");
                                  System.out.println("SWITCH: "+educamt);
                            break;
                    case "M": emerid = rs.getInt("loanid");
                                  emeramt = rs.getFloat("mon_amort");
                            break;
                    case "C": calamityid = rs.getInt("loanid");
                                  calamityamt = rs.getFloat("mon_amort");
                            break;
                }

                contribution = rs.getFloat("contribution");
                lastname = rs.getString("lastname");
                firstname = rs.getString("firstname");
                midinit = rs.getString("midinit");
                membername = lastname + ", " + firstname + " " + midinit;
                
                //System.out.println("outside:" + membername);

                reset++;
                prevId = memberid;
                
            }
            if(memberid != 0)
            {
                memberCount++;
            //System.out.println("commit: " + membername);
                //progressText = ("Current Member #:" + memberCount + " : " + membername);
                //textProgress.append(progressText);
                goodsamt = this.getBalance(prevId);
                cashamt = this.getCashamt(prevId);
                cashid = this.getCashId(memberid);
                total = regamt+emeramt+educamt+cashamt+goodsamt+calamityamt+contribution;
                overTotal += total;
                tempQuery += bank.commitToBill_DTL(billid, prevId, membername, contribution, cashid, cashamt, regid, regamt, educid, educamt, calamityid, calamityamt, emerid, emeramt, goodsamt, total, compid, "Y");
                tempQuery += "SELECT * FROM dual";
                //System.out.println(tempQuery);
                stmt.executeUpdate(tempQuery);
                System.out.println("PROCESSING MEMBER #:" + memberCount + " : " + membername);
            }
            //this.processGoods(stmt, billid, fromText, finalUntil, compid);
            
            
            //textProgress.append("\n----------------Execute : Database Commit----------------");
            
            tempQuery = bank.commitDTL_Temp(billid);
            stmt.executeQuery(tempQuery);
            
            //CASHLOANS
            tempQuery = bank.memberCashNoLoans(fromText, finalUntil, compid);
            rs = null;
            rs = stmt.executeQuery(tempQuery);
            try
            {
                lastname = "";
                firstname = "";
                midinit = "";
                float balance = 0;
                float totaltemp = 0;
                membername = "";
                memberid = 0;
                float cashamttemp  = 0;
                int cashidtemp = 0;
                float tempContribution = 0;
                float goodsamttemp = 0;
                while(rs.next())
                {
                    lastname = rs.getString("lastname");
                    firstname = rs.getString("firstname");
                    midinit = rs.getString("midinit");
                    tempContribution = rs.getFloat("contribution");
                    membername = lastname + ", " + firstname + " " + midinit;
                    cashamttemp = rs.getFloat("balance");
                    float tempHold = cashamttemp;
                    memberid = rs.getInt("memberid");
                    cashidtemp = rs.getInt("loanid");
                    goodsamttemp = this.getBalance(memberid);
                    total = cashamttemp + tempContribution + goodsamttemp;
                    tempQuery = bank.commitCashNoLoans(billid, compid, tempHold, cashidtemp, membername, memberid, contribution, total,goodsamttemp);
                    //PUSH TO TEMP
                    //System.out.println(tempQuery);
                    System.out.println("line600  " + tempQuery);
                    stmt.addBatch(tempQuery);
                    tempQuery = bank.commitCashNoLoansTemp(billid, compid, tempHold, cashidtemp, membername, memberid, contribution, total,goodsamttemp);
                    System.out.println("TEMP:" + membername + " : " + total);
                    stmt.addBatch(tempQuery);
                    System.out.println("ADDING MEMBERS WITH NO GOODS - WITH CASHLOANS");
                    
                }
                stmt.executeBatch();
               
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            //GET POEPLE WITH GOODS NO LOANS
            tempQuery = bank.memberGoodsNoLoans(fromText, finalUntil, compid);
            rs = null;
            rs = stmt.executeQuery(tempQuery);
            try
            {
                //stmt.clearBatch();
                lastname = "";
                firstname = "";
                midinit = "";
                float balance = 0;
                float totaltemp = 0;
                membername = "";
                memberid = 0;
                float tempContribution = 0;
                while(rs.next())
                {
                    lastname = rs.getString("lastname");
                    firstname = rs.getString("firstname");
                    midinit = rs.getString("midinit");
                    tempContribution = rs.getFloat("contribution");
                    membername = lastname + ", " + firstname + " " + midinit;
                    balance = rs.getFloat("balance");
                    memberid = rs.getInt("memberid");
                    total = balance + tempContribution;
                    overTotal += total;
                    tempQuery = bank.commitMemberNoLoans(billid, memberid, membername, balance,compid,contribution,total);
                    //PUSH TO TEMP
                    //System.out.println(tempQuery);
                    stmt.addBatch(tempQuery);
                    tempQuery = bank.commitMemberNoLoansTemp(billid, memberid, membername, balance, compid,contribution,total);
                    stmt.addBatch(tempQuery);
                    System.out.println("ADDING MEMBERS WITH PURCHASE GOODS - NO LOANS");
                }
                stmt.executeBatch();
//                stmt.clearBatch();
            }
            
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            //NON MEMBER
            tempQuery = bank.nonmemberGoods(fromText, finalUntil, compid);
            rs = null;
            rs = stmt.executeQuery(tempQuery);
            try
            {
                lastname = "";
                firstname = "";
                midinit = "";
                float balance = 0;
                float totaltemp = 0;
                membername = "";
                memberid = 0;
                
                while(rs.next())
                {
                    lastname = rs.getString("lastname");
                    firstname = rs.getString("firstname");
                    midinit = rs.getString("midinit");
                    membername = lastname + ", " + firstname + " " + midinit;
                    balance = rs.getFloat("balance");
                    totaltemp += balance;
                    memberid = rs.getInt("memberid");
                    tempQuery = bank.commitNonMemberGoods(billid, memberid, membername, balance,compid);
                    //PUSH TO TEMP
                    //System.out.println(tempQuery);
                    stmt.addBatch(tempQuery);
                    tempQuery = bank.commitNonMemberGoodsTemp(billid, memberid, membername, balance, compid);
                    stmt.addBatch(tempQuery);
                }
                overTotal += totaltemp;
                stmt.executeBatch();
//                stmt.clearBatch();
                System.out.println("ADDING NON-MEMBER - PURCHASE GOODS");
            }
            
            catch(Exception e)
            {
                e.printStackTrace();
            }
              
            //NOW DOUBLE COMMIT TO BILLDTL_TEMP & BILL_DTL (ORIG)
            
            tempQuery = bank.updateBillAmount(billid, overTotal);
            System.out.println(tempQuery);
            stmt.executeQuery(tempQuery);
            System.out.println("PREPARING REPORT");
            this.billReport(); //this report
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch(JRException e)
        {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error: File currently exists\n Delete existing file and try again..", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        finally{
            this.disconnect();
        }        
    }
        
    public Float getCashamt(int memberid)
    {
        QueryWarehouse bank = new QueryWarehouse();
        float cashamt = 0;
        Statement stmt = null;
        String tempQuery = bank.getCashloan(memberid, fromText, finalUntil);
        ResultSet rs;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
            {
                cashamt = rs.getFloat("loanamt");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProcessBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cashamt;
    }
    
    public int getCashId(int memberid)
    {
        QueryWarehouse bank = new QueryWarehouse();
        int loanid = 0;
        Statement stmt = null;
        String tempQuery = bank.getCashloan(memberid, fromText, finalUntil);
        ResultSet rs;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
            {
                loanid = rs.getInt("loanid");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProcessBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loanid;
    }
    
    public Float getBalance(int memberid)
    {
        float balance = 0;
        QueryWarehouse bank = new QueryWarehouse();
        Statement stmt = null;
        String tempQuery = bank.getGoodsMember(memberid, fromText, finalUntil);
        ResultSet rs;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
            {
                balance = rs.getFloat("balance");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProcessBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balance;
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
            java.util.logging.Logger.getLogger(AddBillingStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBillingStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBillingStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBillingStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBillingStatement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonProcess;
    private javax.swing.JComboBox comboCompany;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField textBilling;
    private javax.swing.JTextField textFrom;
    private javax.swing.JTextField textUntil;
    // End of variables declaration//GEN-END:variables
}
