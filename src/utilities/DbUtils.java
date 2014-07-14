/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author admin
 */
public class DbUtils {
    
    public TableModel resultSetToTableModel(ResultSet rs) {
	try {
	    ResultSetMetaData metaData = rs.getMetaData();
	    int numberOfColumns = metaData.getColumnCount();
	    Vector<String> columnNames = new Vector<String>();

	    // Get the column names
	    for (int column = 0; column < numberOfColumns; column++) {
		columnNames.addElement(metaData.getColumnLabel(column + 1));
	    }

	    // Get all rows.
	    Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

	    while (rs.next()) {
		Vector<Object> newRow = new Vector<Object>();

		for (int i = 1; i <= numberOfColumns; i++) {
		    newRow.addElement(rs.getObject(i));
		}

		rows.addElement(newRow);
	    }

	    return new DefaultTableModel(rows, columnNames);
	} catch (Exception e) {
	    e.printStackTrace();

	    return null;
	}
    }

    public List<List<Object>> resultSetToNestedList(ResultSet rs, boolean includeColumnNames) {
	try {
	    // To contain all rows.
	    List<List<Object>> rows = new ArrayList<List<Object>>();
	    ResultSetMetaData metaData = rs.getMetaData();
	    int numberOfColumns = metaData.getColumnCount();

	    // Include column headers as first row if required
	    if (includeColumnNames) {
		List<Object> columnNames = new ArrayList<Object>();

		// Get the column names
		for (int column = 0; column < numberOfColumns; column++) {
		    columnNames.add(metaData.getColumnLabel(column + 1));
		}
		rows.add(columnNames);
	    }

	    // Get the data
	    while (rs.next()) {
		List<Object> newRow = new ArrayList<Object>();

		for (int i = 1; i <= numberOfColumns; i++) {
		    newRow.add(rs.getObject(i));
		}

		rows.add(newRow);
	    }
	    return rows;
	} catch (Exception e) {
	    e.printStackTrace();

	    return null;
	}
    }

    public List<List<Object>> resultSetToNestedList(ResultSet rs) {
	try {
	    // To contain all rows.
	    List<List<Object>> rows = new ArrayList<List<Object>>();
	    ResultSetMetaData metaData = rs.getMetaData();
	    int numberOfColumns = metaData.getColumnCount();

	    // Get the data
	    while (rs.next()) {
		List<Object> newRow = new ArrayList<Object>();

		for (int i = 1; i <= numberOfColumns; i++) {
		    newRow.add(rs.getObject(i));
		}

		rows.add(newRow);
	    }
	    return rows;
	} catch (Exception e) {
	    e.printStackTrace();

	    return null;
	}
    }
    
    public void updateTableModelData(DefaultTableModel tModel, ResultSet rs, int column) throws Exception
    {
        tModel.setRowCount(0);
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next())
        {
            Vector newRow = new Vector();
            for (int i = 1; i <= column; i++) {
                newRow.addElement(rs.getObject(i));
        }
            tModel.addRow(newRow);
        }
    }
    
   public String getSysparam (Connection p_Conn, String p_Param) {
		Statement v_Stmt;
		ResultSet v_Rec;

    	String v_Retv = "";

		try {
	        v_Stmt = p_Conn.createStatement();
	        if (p_Param.equals("SYSDATETIME")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY HH24:MI:SS') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("SYSDATE")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("FMSYSDATE")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("FIRSTDAYYR")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT '01/01/'||TO_CHAR(SYSDATE,'YYYY') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("LASTDAYYR")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT '12/31/'||TO_CHAR(SYSDATE,'YYYY') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("FIRSTDAYMO")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT TO_CHAR(SYSDATE,'MM')||'/01/'||TO_CHAR(SYSDATE,'YYYY') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("LASTDAYMO")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT TO_CHAR(LAST_DAY(SYSDATE),'MM/DD/YYYY') DTIME" +
			            " FROM DUAL");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("MAINCOMP")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT MAINCOMPANY" +
			            " FROM PARAM" +
			            " WHERE ROWNUM = 1");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        } else if (p_Param.equals("MAINADDR")) {
		        v_Rec = v_Stmt.executeQuery(
			            "SELECT MAINADDRESS" +
			            " FROM PARAM" +
			            " WHERE ROWNUM = 1");
			        while(v_Rec.next()) { v_Retv = v_Rec.getString(1);}
	        }
	        v_Stmt.close();
	    } catch (Exception e) {
	        //aipTools.showMessageErr(e.toString(),"DB Connection Error (Level 2)");
        }

    	return v_Retv;
    }

}
