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
        int compid = this.getCompanyIdCombo(comboCompany.getSelectedIndex());
        String tempQuery = "insert into joincompany_member select compname,company.compid,memberid,lastname,firstname,midinit from company  inner join member on member.compid = company.compid";

	Statement stmt = null;       
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
	try
        {
            //STEP ONE - INSERT COMPANY - MEMBER JOIN
            stmt.addBatch(tempQuery);            
            //STEP TWO
            tempQuery = "insert into current_month select distinct memberid, loanid, mon_amort,loantype from (select memberid,loan_hdr.loanid,loan_dtl.amordate,loan_dtl.mon_amort,loan_hdr.loantype \n" +
                        "from loan_hdr inner join loan_dtl on loan_hdr.loanid = loan_dtl.loanid\n" +
                        "where amordate between '"+finalFrom+"' and '"+finalUntil+"') order by memberid";
            stmt.addBatch(tempQuery);
            //STEP THREE
            int[] executeBatch = stmt.executeBatch();
            tempQuery = "select * from joincompany_member join current_month on current_month.memberid = joincompany_member.memberid where compid ="+compid;
            
            rs = stmt.executeQuery(tempQuery);
            while(rs.next())
            {
                firstname.add(rs.getString("firstname"));
                lastname.add(rs.getString("lastname"));
                midinit.add(rs.getString("midinit"));
                loanType.add(rs.getString("loantype"));
                monAmort.add(rs.getFloat("mon_amort"));
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
