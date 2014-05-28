/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;
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
import javax.swing.table.DefaultTableModel;

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
    
    private Date fromDate;
    private Date untilDate;
    
    private String finalFrom;
    private String finalUntil;
    
    DateFormat df; 
    
    ArrayList<String> firstname = new ArrayList<>();
    ArrayList<String> lastname = new ArrayList<>();
    ArrayList<String> midinit = new ArrayList<>();
    ArrayList<String> loanType = new ArrayList<>();
    ArrayList<Float> monAmort = new ArrayList<>();
        
    
    public processBillStatement() {
        initComponents();
        this.returnParams();
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
    }

    public void setTextFromDate()
    {
        fromText = textFrom.getText();
    }
    
    public void setTextUntilDate()
    {
        untilText = textUntil.getText();
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
                System.out.println(rs.getInt("compid") + " : " + rs.getString("compname"));
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
            System.out.println(billid);
            tempQuery = bank.joinDistinct();
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
        for(int i = 0; i<firstname.size();i++)
        {
            System.out.println(lastname.get(i) + ", " + firstname.get(i) + " " + midinit.get(i) + " -- " + loanType + " || " + monAmort);
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
            System.out.println(lastname.get(i) +", "+ firstname.get(i) + " " + midinit.get(i) + ". --- " + monAmort.get(i) + "--" + loanType.get(i));
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
            this.processBill();
            this.printResults();
        }
    }
    
    public void billReport() throws JRException
    {
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        
        HashMap jasperParameter = new HashMap();
        jasperReport = JasperCompileManager.compileReport("C://Users//Lenovo//Documents//GitHub//AGCoopSys//AGCOOPSys//src//BillingStatement.jrxml");       
        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, conn);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C://Users//Lenovo//Documents//GitHub//AGCoopSys//AGCOOPSys//src//BillingStatement.pdf");
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
        jTextField3 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        buttonProcess = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Billing Statement"));

        jLabel1.setText("Company");

        jLabel2.setText("Date from");

        jLabel3.setText("Date until");

        jLabel4.setText("Bill date");

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
                            .addComponent(textUntil)
                            .addComponent(textFrom)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(buttonProcess))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProcessActionPerformed

        this.setTextFromDate();
        this.setTextUntilDate();
        this.processBillStatementDate();
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonProcessActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonProcess;
    private javax.swing.JComboBox comboCompany;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField textFrom;
    private javax.swing.JTextField textUntil;
    // End of variables declaration//GEN-END:variables
}
