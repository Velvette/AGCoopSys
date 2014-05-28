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
        String query = "select distinct compname,company.compid,memberid,lastname,firstname,midinit from company  inner join member on member.compid=company.compid";
        return query;
    }
    
    public String maxBillId()
    {
        String query = "select max(billid) as billid from joincompany_member_hdr";
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
        String query = "select * from joincompany_member join current_month on current_month.memberid = joincompany_member.memberid where compid ="+compid;
        return query;
    }
}
