/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Lenovo
 */
public class QueryWarehouse {
    
    public String loanFirstCommit(String memberID, String loanType, String currentdtString, String startdtString, String enddtString, float principal, int terms, float interestrt, float interest, float totalPayment, float monthlyAmortization, String checkNo)
    {
        String query = "insert into loan_hdr (memberid, loantype, grantdt,startdt,enddt,loanamt,montopay,interestrt, interestamt,payableamt,balance,amortization,checkno)"
                + "values('"+memberID+"','"+loanType+"','"+currentdtString+"','"+startdtString+"','"+enddtString+"','"+principal+"','"+terms+
                "','"+interestrt+"','"+interest+"','"+totalPayment+"','"+totalPayment+"','"+monthlyAmortization+"','"+checkNo+"')";
        return query;
    }
    
    public String updateLoanFirstCommitTerminate(int loanid) {
        String query = "update loan_hdr set status='TERMINATED' where loanid='"+loanid+"'";
        return query;
    }
    
    public String updateLoanFirstCommit(int loanid, String memberID, String loanType, String currentdtString, String startdtString, String enddtString, float principal, int terms, float interestrt, float interest, float totalPayment, float monthlyAmortization, String checkNo)
    {
        String query = "update loan_hdr set memberid='"+memberID+"',loantype='"+loanType+"',grantdt='"+currentdtString+"',startdt='"+startdtString+"',enddt='"+enddtString+"',loanamt='"+principal+"',montopay='"+terms+"',interestrt='"+interestrt+"',interestamt='"+interest+"',payableamt='"+totalPayment+"',balance='"+totalPayment+"',amortization='"+monthlyAmortization+"',checkno='"+checkNo+"'where loanid="+loanid;
        return query;
    }
    
    public String loanCashloan(String memberID, String currentdtString, String enddtString,float principal, float interestrt, float interest, float totalPayment,String checkNo)
    {
        String query = "insert into cashloan (memberid,grantdt,billingdt,loanamt,interestrt,interestamt,balance,releasedamt,checkno) values ('"+memberID+"','"+currentdtString+"','"+enddtString+"','"+principal+"','"+interestrt+"','"+interest+"','"+totalPayment+"','"+principal+"','"+checkNo+"')";
        return query;
    }
    
    public String editLoanCashloan(String memberID, String currentdtString, String enddtString,float principal, float interestrt, float interest, float totalPayment,String checkNo,int loanid)
    {
        String query = "update cashloan set memberid='"+memberID+"',grantdt='"+currentdtString+"',billingdt='"+enddtString+"',loanamt='"+principal+"',interestrt='"+interestrt+"',interestamt='"+interest+"',releasedamt='"+principal+"',balance='"+totalPayment+"',checkno='"+checkNo+"'where loanid="+loanid;
        return query;
    }
    
    public String joinDistinct(int compid)
    {
        String query = "insert into joincompany_member (select distinct compname,company.compid,memberid,lastname,firstname,midinit from company  inner join member on member.compid=company.compid where member.compid="+compid+")";
        //System.out.println(query);
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
            String query = "select distinct * from joincompany_member join current_month on current_month.memberid = joincompany_member.memberid where compid ="+compid+" order by current_month.memberid";
        return query;
    }
    
    public String bill_hdr(String fromStart, String fromUntil, String currentDate, int compid,String billingdt)
    {
        String query = "insert into bill_hdr (compid,billdt, startdt, enddt, processdt) values ('"+compid+"','"+billingdt+"','"+fromStart+"','"+fromUntil+"','"+currentDate+"')";
        return query;
    }
    
    public String getBill_HDRID()
    {
        String query = "select max(billid) as billid from bill_hdr";
        return query;
    }
    
    public String commitToBill_DTL(int billid,int memberid, String membername, float contribution, int cashid, float cashamt, int regid, float regamt, int educid, float educamt, int calamityid, float calamityamt, int emerid, float emeramt, float goodsamt,float total,int compid, String isMember)
    {
        String query = "into bill_dtl (billid,memberid,membername,contribution,cash_loanid,cash_amount,reg_loanid,reg_amount,educ_loanid,educ_amount,calamity_loanid,calamity_amount,emer_loanid,emer_amount,goods_amount,mem_total,compid,ismember) values"+
                "('"+billid+"','"+memberid+"','"+membername+"','"+contribution+"','"+cashid+"','"+cashamt+"','"+regid+"','"+regamt+"','"+educid+"','"+educamt+"','"+calamityid+"','"+calamityamt+"','"+emerid+"','"+emeramt+"','"+goodsamt+"','"+total+"','"+compid+"','"+isMember+"')\n";
        System.out.println(query);
        return query;
    }
        
    public String getCashloan(int memberid,String fromStart, String fromUntil)
    {
        String query = "SELECT * FROM (select * from cashloan where billingdt between '"+fromStart+"' AND '"+fromUntil+"') WHERE MEMBERID='"+memberid+"'";
        return query;
    }
    
    public String getGoodsMember(int memberid, String fromStart, String fromUntil)
    {
        String query = "select distinct sum(balance) as balance from goods_Sold_dtl join goods_sold_hdr on goods_sold_dtl.invid = goods_sold_hdr.invid where billingdt between '"+fromStart+"' AND '"+fromUntil+"' and memberid="+memberid;
        return query;
    }
    
    public String nonmemberGoods(String fromStart, String fromUntil)
    {
       String query = "select lastname,firstname,midinit,balance,memberid from (select memberid,balance from goods_sold_dtl natural join goods_sold_hdr where ismember = 'N' and billingdt between '"+fromStart+"' AND '"+fromUntil+"') natural join joincompany_nonmember";
       return query; 
    }
   
    
    public String commitDTL_Temp(int billid)
    {
        String query = "insert into billdtl_temp (select * from bill_dtl where billid="+billid+")";
        return query;
    }
    
    public String updateBillAmount(int billid,int overTotal)
    {
        String query = "update bill_hdr set billamt="+overTotal+" where billid="+billid;
        return query;
    }
    
    public String joinDistinctNon(int compid)
    {
        String query = "insert into joincompany_nonmember (select distinct compname,company.compid,memberid,lastname,firstname,midinit from company  inner join nonmember on nonmember.compid=company.compid where nonmember.compid="+compid+")";
        return query;
    }    
    
    public String memberGoodsNoLoans(String fromStart, String fromUntil,int compid)
    {
        String query = "SELECT * FROM (SELECT * FROM (select distinct MEMBERID from (SELECT MEMBERID,sum(BALANCE) AS BALANCE FROM GOODS_SOLD_HDR INNER JOIN GOODS_SOLD_DTL ON GOODS_SOLD_HDR.INVID = GOODS_SOLD_DTL.INVID WHERE GOODS_SOLD_HDR.BILLINGDT BETWEEN '"+fromStart+"' AND '"+fromUntil+"' AND ISMEMBER ='Y' GROUP BY MEMBERID) natural join member where compid ="+compid+") MINUS (SELECT MEMBERID FROM BILLDTL_TEMP)) NATURAL JOIN (select distinct * from (SELECT MEMBERID,sum(BALANCE) AS BALANCE FROM GOODS_SOLD_HDR INNER JOIN GOODS_SOLD_DTL ON GOODS_SOLD_HDR.INVID = GOODS_SOLD_DTL.INVID WHERE GOODS_SOLD_HDR.BILLINGDT BETWEEN '"+fromStart+"' AND '"+fromUntil+"' AND ISMEMBER ='Y' GROUP BY MEMBERID) natural join member where compid ="+compid+")";
        System.out.println(query);
        return query;
    }
    
    public String memberCashNoLoans(String fromStart, String fromUntil, int compid)
    {
        String query = "SELECT * FROM (SELECT * FROM (select distinct MEMBERID from (SELECT MEMBERID,BALANCE,loanid FROM CASHLOAN WHERE BILLINGDT BETWEEN '"+fromStart+"' AND '"+fromUntil+"') natural join member where compid ="+compid+") MINUS (SELECT MEMBERID FROM BILLDTL_TEMP)) NATURAL JOIN (select distinct * from (SELECT MEMBERID,BALANCE,loanid FROM CASHLOAN WHERE BILLINGDT BETWEEN '"+fromStart+"' AND '"+fromUntil+"') natural join member where compid ="+compid+")";
        return query;
    }
    
    public String commitCashNoLoans(int billid, int compid,float cashamt,int cashid,String membername,int memberid,float contribution,float total,float goods_amount)
    {
        String query = "insert into bill_dtl (billid,memberid,membername,cash_loanid,cash_amount,mem_total,COMPID,contribution,goods_amount) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+cashid+"','"+cashamt+"','"+total+"','"+compid+"','"+contribution+"','"+goods_amount+"')";
        System.out.println(query);
        return query;
    }
    
    public String commitCashNoLoansTemp(int billid, int compid,float cashamt,int cashid,String membername,int memberid,float contribution,float total,float goods_amount)
    {
        String query = "insert into billdtl_temp (billid,memberid,membername,cash_loanid,cash_amount,mem_total,COMPID,contribution) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+cashid+"','"+cashamt+"','"+total+"','"+compid+"','"+contribution+"')";
        System.out.println(query);
        return query;
    }
    
    public String nonmemberGoods(String fromStart, String fromUntil, int compid)
    {
        String query = "select memberid,sum(balance) as balance, firstname, midinit, lastname,compid from (SELECT * FROM (SELECT MEMBERID,balance FROM (select MEMBERID,sum(BALANCE) as balance from (select INVID,MEMBERID,BALANCE from goods_sold_Dtl where ismember = 'N') natural join GOODS_SOLD_HDR where billingdt between '"+fromStart+"' AND '"+fromUntil+"' group by memberid, balance) MINUS (SELECT MEMBERID,mem_total as balance FROM BILLDTL_TEMP)) NATURAL JOIN NONMEMBER where compid ="+compid+") group by memberid, firstname, midinit, lastname, compid";
        return query;
    }
    
    
    public String commitMemberNoLoans(int billid, int memberid, String membername, float goodsamt, int compid,float contribution,float total)
    {
        String query = "insert into bill_dtl (billid,memberid,membername,goods_amount,mem_total,COMPID,contribution) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+goodsamt+"','"+total+"','"+compid+"','"+contribution+"')";
        return query;
    }
    
    public String commitMemberNoLoansTemp(int billid,int memberid, String membername, float goodsamt,int compid, float contribution,float total)
    {
        String query = "insert into billdtl_temp (billid,memberid,membername,goods_amount,mem_total,COMPID,contribution) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+goodsamt+"','"+total+"','"+compid+"','"+contribution+"')";
        return query;       
    }
    
    public String commitNonMemberGoods(int billid,int memberid, String membername, float goodsamt, int compid)
    {
        String query = "insert into bill_dtl (billid,memberid,membername,goods_amount,mem_total,COMPID, ismember) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+goodsamt+"','"+goodsamt+"','"+compid+"','N')";
        return query;
    }
    
    public String commitNonMemberGoodsTemp(int billid,int memberid, String membername, float goodsamt,int compid)
    {
        String query = "insert into billdtl_temp (billid,memberid,membername,goods_amount,mem_total,COMPID, ismember) VALUES ('"+billid+"','"+memberid+"','"+membername+"','"+goodsamt+"','"+goodsamt+"','"+compid+"','N')";
        return query;
    }
}
