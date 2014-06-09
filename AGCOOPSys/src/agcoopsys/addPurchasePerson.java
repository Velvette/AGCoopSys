/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;
/**
 *
 * @author Lenovo
 */
public class addPurchasePerson {
    private int memberid;
    private int invid;
    private float balance;
    private String status;
    private String desc;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public float getBalance() {
        return balance;
    }

    public int getInvid() {
        return invid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public int getMemberid() {
        return memberid;
    }

    public String getStatus() {
        return status;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
