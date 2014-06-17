/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
 
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author admin
 */
public class processBillStatement extends javax.swing.JPanel {

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
    
    private Date fromDate;
    private Date untilDate;
    private Date billDate;
    
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
        
    private PrintStream standardOut;
    
    String progressText = "";
    
    public processBillStatement() {
        initComponents();
        this.returnParams();
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.bill = new SimpleDateFormat("MMMM dd, yyyy");
        df.setLenient(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textProgress));
        standardOut = System.out;
        System.setOut(printStream);
        //System.out.println("hello");
    }
    
    
    
    public void resetTexts()
    {
        textFrom.setText("");
        textUntil.setText("");
        textBilling.setText("");
    }

    public void clearConsole()
{
    try
    {
        final String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }
    catch (final Exception e)
    {
        //  Handle any exceptions.
    }
}
    
    public void setTextFromDate()
    {
        fromText = textFrom.getText();
    }
    
    public void setTextUntilDate()
    {
        untilText = textUntil.getText();
    }
    
    public void setTextBillDate()
    {
        billText = textBilling.getText();
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
                //System.out.println(rs.getInt("compid") + " : " + rs.getString("compname"));
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
    
    public void processBill()
    {
        queryBank bank = new queryBank();
        Date date = new Date();
        String currentDate = df.format(date);
        int compid = this.getCompanyIdCombo(comboCompany.getSelectedIndex());
        String tempQuery = "insert into joincompany_member_hdr (billdt) values ('"+currentDate+"')";
	Statement stmt = null;       
        int billid = 0;
	this.connect();
                
	try
        {
            stmt = conn.createStatement();
        }
                
	catch (SQLException e)
        {
            e.printStackTrace();
        }
		
	ResultSet rs;
        String compnameTemp = null;
        int compidTemp = 0;
        int memberidTemp = 0;
        String lastnameTemp = null;
        String firstnameTemp = null;
        String midinitTemp = null;
        
        try
        {
            stmt.executeQuery(tempQuery);
            tempQuery = bank.maxBillId();
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
                billid = rs.getInt("billid");
            
            rs = null;
            //System.out.println(billid);
            tempQuery = bank.joinDistinct(compid);
            rs = stmt.executeQuery(tempQuery);
            if(rs.next())
            {
                compnameTemp = rs.getString("compname");
                compidTemp = rs.getInt("compid");
                memberidTemp = rs.getInt("memberid");
                lastnameTemp = rs.getString("lastname");
                firstnameTemp = rs.getString("firstname");
                midinitTemp = rs.getString("midinit");
            }
            
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        
        rs = null;
        ResultSet ps;
        try
        {
            //STEP ZERO : INITIATE BILLID
            tempQuery = "insert into joincompany_member (compname,compid,memberid,lastname,firstname,midinit,billid) values('"+compnameTemp+"','"+compidTemp+"','"+memberidTemp+"','"+lastnameTemp+"','"+firstnameTemp+"','"+midinitTemp+"','"+billid+"')";
            //STEP ONE - INSERT COMPANY - MEMBER JOIN
            stmt.addBatch(tempQuery);            
            //STEP TWO
            tempQuery = bank.currentMonthPeriod(finalFrom, finalUntil);
            
            stmt.addBatch(tempQuery);
            //STEP THREE
            int[] executeBatch = stmt.executeBatch();
            tempQuery = bank.selectJoinCompanyMember(compid);
            
            ps = stmt.executeQuery(tempQuery);
            while(ps.next())
            {
                firstname.add(ps.getString("firstname"));
                lastname.add(ps.getString("lastname"));
                midinit.add(ps.getString("midinit"));
                loanType.add(ps.getString("loantype"));
                monAmort.add(ps.getFloat("mon_amort"));
            }
            this.billReport();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
	} catch (JRException ex) {
            Logger.getLogger(processBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        finally
        {
            this.disconnect();
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
            //System.out.println(errorMessages);
        }
        else
        {
            this.processBillNew();
            this.printResults();
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
        jasperReport = JasperCompileManager.compileReport("src//BillingStatement.jrxml");       
        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, conn);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "Reports//SOA//"+companyName+" - Bill Statement("+dFrom+"-"+dUntil+").pdf");
        
        
    }
    
    public void processBillNew()
    {
        this.clearConsole();
        queryBank bank = new queryBank();
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
            progressText = ("Joining Members - Company..\n");
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
            Logger.getLogger(processBillStatement.class.getName()).log(Level.SEVERE, null, ex);
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
                }
                loanType = rs.getString("loantype");
                switch(loanType)
                {
                    case "R": regid = rs.getInt("loanid");
                                  regamt = rs.getFloat("mon_amort");
                            break;
                    case "E": educid = rs.getInt("loanid");
                                  educamt = rs.getFloat("mon_amort");
                            break;
                    case "M": emerid = rs.getInt("loanid");
                                  emeramt = rs.getFloat("mon_amort");
                            break;
                    case "C": calamityid = rs.getInt("loanid");
                                  emeramt = rs.getFloat("mon_amort");
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
                total = regamt+emeramt+educamt+cashamt+goodsamt+calamityamt;
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
            
            //GET POEPLE WITH GOODS NO LOANS
            tempQuery = bank.memberGoodsNoLoans(fromText, finalUntil, compid);
            rs = null;
            rs = stmt.executeQuery(tempQuery);
            try
            {
                lastname = "";
                firstname = "";
                midinit = "";
                float balance = 0;
                membername = "";
                memberid = 0;
                
                while(rs.next())
                {
                    lastname = rs.getString("lastname");
                    firstname = rs.getString("firstname");
                    midinit = rs.getString("midinit");
                    membername = lastname + ", " + firstname + " " + midinit;
                    balance = rs.getFloat("balance");
                    memberid = rs.getInt("memberid");
                    tempQuery = bank.commitMemberNoLoans(billid, memberid, membername, balance,compid);
                    //PUSH TO TEMP
                    //System.out.println(tempQuery);
                    stmt.addBatch(tempQuery);
                    tempQuery = bank.commitMemberNoLoansTemp(billid, memberid, membername, balance, compid);
                    stmt.addBatch(tempQuery);
                    System.out.println("ADDING MEMBERS WITH PURCHASE GOODS - NO LOANS");
                }
                stmt.executeBatch();
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
                membername = "";
                memberid = 0;
                
                while(rs.next())
                {
                    lastname = rs.getString("lastname");
                    firstname = rs.getString("firstname");
                    midinit = rs.getString("midinit");
                    membername = lastname + ", " + firstname + " " + midinit;
                    balance = rs.getFloat("balance");
                    memberid = rs.getInt("memberid");
                    tempQuery = bank.commitNonMemberGoods(billid, memberid, membername, balance,compid);
                    //PUSH TO TEMP
                    //System.out.println(tempQuery);
                    stmt.addBatch(tempQuery);
                    tempQuery = bank.commitNonMemberGoodsTemp(billid, memberid, membername, balance, compid);
                    stmt.addBatch(tempQuery);
                            
                }
                stmt.executeBatch();
                System.out.println("ADDING NON-MEMBER - PURCHASE GOODS");
            }
            
            catch(Exception e)
            {
                e.printStackTrace();
            }
              
            //NOW DOUBLE COMMIT TO BILLDTL_TEMP & BILL_DTL (ORIG)
            
            tempQuery = bank.updateBillAmount(billid, overTotal);
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
            JOptionPane.showMessageDialog(null, "Error: File currently exists\n Delete existing file and try again..", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        finally{
            this.disconnect();
        }        
    }
        
    public Float getCashamt(int memberid)
    {
        queryBank bank = new queryBank();
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
            Logger.getLogger(processBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cashamt;
    }
    
    public Float getBalance(int memberid)
    {
        float balance = 0;
        queryBank bank = new queryBank();
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
            Logger.getLogger(processBillStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balance;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboCompany = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        textFrom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textUntil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textBilling = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        buttonProcess = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textProgress = new javax.swing.JTextArea();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Billing Statement"));

        jLabel1.setText("Company");

        jLabel2.setText("Date from");

        jLabel3.setText("Date until");

        jLabel4.setText("Billing Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textBilling, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(textUntil)
                            .addComponent(textFrom))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textBilling, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUntil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14))
        );

        jButton2.setText("Cancel");

        buttonProcess.setText("Process");
        buttonProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProcessActionPerformed(evt);
            }
        });

        buttonClear.setText("Clear");
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        textProgress.setEditable(false);
        textProgress.setColumns(20);
        textProgress.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        textProgress.setRows(5);
        jScrollPane1.setViewportView(textProgress);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 796, Short.MAX_VALUE)
                        .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(buttonClear)
                    .addComponent(buttonProcess))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonProcess;
    private javax.swing.JComboBox comboCompany;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField textBilling;
    private javax.swing.JTextField textFrom;
    private javax.swing.JTextArea textProgress;
    private javax.swing.JTextField textUntil;
    // End of variables declaration//GEN-END:variables
}
