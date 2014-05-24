/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class ConnectToDatabaseSys {
 
    String dbClass = "oracle.jdbc.driver.OracleDriver";
    String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    String password = "p@ssword"; // CHANGE PASSWORD
    String name = "test";
    Connection con;
    
    public String getDbClass() {
        return dbClass;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
       
    public void accessInputDatabase(String query)
    {
        try
        {
            Class.forName(dbClass);
            con = DriverManager.getConnection (dbUrl, name, password);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Database Update: Success", "Updating database", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
        }

        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
            e.printStackTrace();
        }
        
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectToDatabaseSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void accessLoopDatabase(String query)
    {
        try
        {
            Class.forName(dbClass);
            con = DriverManager.getConnection (dbUrl, name, password);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            con.close();
        }
        
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
        }

        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
    }
    
    public void disconnect()
    {
       try
       {
            con.close();
       } 
       catch (Exception ex)
       {
       }
    }
    
    public void connect()
    {
                
        try
        {
            Class.forName(dbClass).newInstance();
            con = DriverManager.getConnection(dbUrl,name,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void executeQuery(String query)
    {
        try
        {
            Class.forName(dbClass);
            con = DriverManager.getConnection (dbUrl, name, password);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Database Update: Success", "Updating database", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
        }

        catch(SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Database not updated", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }

    public boolean checkDuplicate(String checkThis, int choice) throws ClassNotFoundException, SQLException
    {       
        String query = "";
        if(choice == 0)
            query = "select suppname from supplier where suppname='"+checkThis+"'";
        else
            query = "select compname from company where compname='"+checkThis+"'";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs;

        try
        {
        Class.forName(dbClass);
        con = DriverManager.getConnection (dbUrl, name, password);
        stmt = con.prepareStatement(query);
        }
                
	catch (SQLException e)
        {
        }
        
        try
        {
            rs = stmt.executeQuery(query);
            if(!rs.next())
            {
                con.close();
                return true;
            }            
	}
        catch (SQLException e)
        {
            e.printStackTrace();
	} 
        con.close();
        return false;
    }
    
    public String getInsertAmortDates(Date repEnd, int terms, float[] arrayInterest, int loanID, float monthlyAmortization)
    {
        DateFormat df;
        df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        String repEndString = "";
        String insertString = "insert all ";
        Calendar calendar = Calendar.getInstance();
        
        for(int i=0; i<terms;i++)
        {
            repEndString = df.format(repEnd);
            System.out.println(repEndString);
            calendar.setTime(repEnd);
            insertString += "into loan_dtl (loanid,amordate,mon_amort,mon_interest) values ('"+loanID+"','"+repEndString+"','"+monthlyAmortization+"','"+arrayInterest[i]+"')\n";
            calendar.add(Calendar.MONTH, 1);
            repEnd = calendar.getTime();
        }
        insertString +="\nselect * from dual";
        return insertString;
    }
    
    public boolean checkDuplicateLoan(String memberID, String loantype)
    {
        String query = "select * from loan_hdr where memberid="+memberID+" and status='A' and loantype='"+loantype+"'";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs;

        try
        {
            try {
                Class.forName(dbClass);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectToDatabaseSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        con = DriverManager.getConnection (dbUrl, name, password);
        stmt = con.prepareStatement(query);
        }
                
	catch (SQLException e)
        {
        }
        
        try
        {
            rs = stmt.executeQuery(query);
            if(!rs.next())
            {
                con.close();
                return true;
            }            
	}
        catch (SQLException e)
        {
            e.printStackTrace();
	} 
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectToDatabaseSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
