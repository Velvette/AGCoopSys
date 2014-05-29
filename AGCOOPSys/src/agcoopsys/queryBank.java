/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

/**
 *
 * @author Lenovo
 */
public class queryBank {
    
    public String loanFirstCommit(String memberID, String loanType, String currentdtString, String startdtString, String enddtString, float principal, int terms, float interestrt, float interest, float totalPayment, float monthlyAmortization, String checkNo)
    {
        String query = "insert into loan_hdr (memberid, loantype, grantdt,startdt,enddt,loanamt,montopay,interestrt, interestamt,payableamt,balance,amortization,checkno)"
                + "values('"+memberID+"','"+loanType+"','"+currentdtString+"','"+startdtString+"','"+enddtString+"','"+principal+"','"+terms+
                "','"+interestrt+"','"+interest+"','"+totalPayment+"','"+totalPayment+"','"+monthlyAmortization+"','"+checkNo+"')";
        return query;
    }
    
    public String joinDistinct()
    {
        String query = "insert into joincompany_member (select distinct compname,company.compid,memberid,lastname,firstname,midinit from company  inner join member on member.compid=company.compid)";
        return query;
    }
    
    public String maxBillId()
    {
        String query = "select max(billid) as billid from bill_hdr";
        return query;
    }
    
    public String currentMonthPeriod(String finalFrom, String finalUntil)
    {
        String query = "insert into current_month select distinct memberid, loanid, mon_amort,loantype from (select memberid,loan_hdr.loanid,loan_dtl.amordate,loan_dtl.mon_amort,loan_hdr.loantype " +
                        "from loan_hdr inner join loan_dtl on loan_hdr.loanid = loan_dtl.loanid " +
                        "where amordate between '"+finalFrom+"' and '"+finalUntil+"') order by memberid";
        return query;
    }
    
    public String selectJoinCompanyMember(int compid)
    {
        String query = "select distinct * from joincompany_member join current_month on current_month.memberid = joincompany_member.memberid where compid ="+compid+" order by memberid";
        return query;
    }
    
    public String bill_hdr(String fromStart, String fromUntil, String currentDate, int compid)
    {
        String query = "insert into bill_hdr (compid, startdt, enddt, processdt) values ('"+compid+"','"+fromStart+"','"+fromUntil+"','"+currentDate+"')";
        return query;
    }
    
    public String getBill_HDRID()
    {
        String query = "select max(billid) as billid from bill_hdr";
        return query;
    }
    
    public String commitToBill_DTL(int billid,int memberid, String membername, float contribution, int cashid, float cashamt, int regid, float regamt, int educid, float educamt, int calamityid, float calamityamt, int emerid, float emeramt, float goodsamt,float total,int compid)
    {
        String query = "into bill_dtl (billid,memberid,membername,contribution,cash_loanid,cash_amount,reg_loanid,reg_amount,educ_loanid,educ_amount,calamity_loanid,calamity_amount,emer_loanid,emer_amount,goods_amount,mem_total,compid) values"+
                "('"+billid+"','"+memberid+"','"+membername+"','"+contribution+"','"+cashid+"','"+cashamt+"','"+regid+"','"+regamt+"','"+educid+"','"+educamt+"','"+calamityid+"','"+calamityamt+"','"+emerid+"','"+emeramt+"','"+goodsamt+"','"+total+"','"+compid+"')\n";
        //System.out.println(query);
        return query;
    }
    
    public String getCashloan(int memberid,String fromStart, String fromUntil)
    {
        String query = "SELECT * FROM (select * from cashloan where billingdt between '"+fromStart+"' AND '"+fromUntil+"') WHERE MEMBERID='"+memberid+"'";
        return query;
    }
    
    public String getGoods(int memberid, String fromStart, String fromUntil)
    {
        String query = "SELECT BALANCE FROM (select * from goods_sold_dtl natural join goods_sold_hdr where billingdt between '"+fromStart+"' AND '"+fromUntil+"') WHERE MEMBERID =" + memberid;
        return query;
    }
    
    public String commitDTL_Temp(int billid)
    {
        String query = "insert into billdtl_temp (select * from bill_dtl where billid="+billid+")";
        return query;
    }
}
