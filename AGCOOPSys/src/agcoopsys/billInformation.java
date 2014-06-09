/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

/**
 *
 * @author Lenovo
 */
public class billInformation {
    float cashAmount = 0;
    float regularAmount = 0;
    float calamityAmount = 0;
    float emergencyAmount = 0;
    float educationAmount = 0;
    float goodsAmount = 0;
    float totalAmount = 0;
    float contribution = 0;
    
    int cashId = 0;
    int regularId = 0;
    int calamityId = 0;
    int emergencyId = 0;
    int educationId = 0;
    
    String membername = "";
    int memberid = 0;

    public float getCalamityAmount() {
        return calamityAmount;
    }

    public int getCalamityId() {
        return calamityId;
    }

    public float getCashAmount() {
        return cashAmount;
    }

    public int getCashId() {
        return cashId;
    }

    public float getContribution() {
        return contribution;
    }

    public float getEducationAmount() {
        return educationAmount;
    }

    public int getEducationId() {
        return educationId;
    }

    public float getEmergencyAmount() {
        return emergencyAmount;
    }

    public int getEmergencyId() {
        return emergencyId;
    }

    public float getGoodsAmount() {
        return goodsAmount;
    }

    public int getMemberid() {
        return memberid;
    }

    public String getMembername() {
        return membername;
    }

    public float getRegularAmount() {
        return regularAmount;
    }

    public int getRegularId() {
        return regularId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setCalamityAmount(float calamityAmount) {
        this.calamityAmount = calamityAmount;
    }

    public void setCalamityId(int calamityId) {
        this.calamityId = calamityId;
    }

    public void setCashAmount(float cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public void setContribution(float contribution) {
        this.contribution = contribution;
    }

    public void setEducationAmount(float educationAmount) {
        this.educationAmount = educationAmount;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public void setEmergencyAmount(float emergencyAmount) {
        this.emergencyAmount = emergencyAmount;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    public void setGoodsAmount(float goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public void setRegularAmount(float regularAmount) {
        this.regularAmount = regularAmount;
    }

    public void setRegularId(int regularId) {
        this.regularId = regularId;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void resetValues()
    {
        cashAmount = 0;
        regularAmount = 0;
        calamityAmount = 0;
        emergencyAmount = 0;
        educationAmount = 0;
        goodsAmount = 0;
        totalAmount = 0;
        contribution = 0;
    
        cashId = 0;
        regularId = 0;
        calamityId = 0;
        emergencyId = 0;
        educationId = 0;
    
        membername = "";
        memberid = 0;
    }
}
