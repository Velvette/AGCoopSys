/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

/**
 *
 * @author Lenovo
 */
public class loanMemberInformation {
    public int memberID;
    public int loanID;
    public String firstname;
    public String lastname;
    public String midinit;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setMidinit(String midinit) {
        this.midinit = midinit;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getLoanID() {
        return loanID;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getMidinit() {
        return midinit;
    }
   
}
