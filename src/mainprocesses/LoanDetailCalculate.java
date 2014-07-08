/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainprocesses;

/**
 *
 * @author admin
 */
public class LoanDetailCalculate {
    
    float monthlyPayment;
    float principalBalance;
    float monthlyInterest;
    int totalNumberOfPayment;
    float totPrincipal = (float) 0.00;
    float totAmortization = (float) 0.00;
    float totInterest = (float) 0.00;
    float interest = (float) 0.00;
    float interestRecur = (float) 0.00;
    float cashPayment = (float) 0.00;
    

    public void setMonthlyInterest(float monthlyInterest) {
        monthlyInterest /= 100;
        this.monthlyInterest = monthlyInterest;
    }

    public void setMonthlyPayment(float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setPrincipalBalance(float principalBalance,float prevBal) {
        this.principalBalance = principalBalance + prevBal;
    }

    public void setTotalNumberOfPayment(int totalNumberOfPayment) {
        this.totalNumberOfPayment = totalNumberOfPayment;
    }

    public float getMonthlyPayment() {
        return monthlyPayment;
    }    

    public float getTotAmortization() {
        return totAmortization;
    }

    public float getTotPrincipal() {
        return totPrincipal;
    }

    public float getTotInterest() {
        totInterest = (float) (Math.round(totInterest * 100.00) / 100.00);
        return totInterest;
    }

    public float getInterest() {
        return interest;
    }
    
    
        
    public void getAmortization()
    {
        float principal = (float) 0.00;
        float balance = principalBalance;

        float numerator = monthlyInterest * principalBalance * this.getInterestRateComp(monthlyInterest, totalNumberOfPayment);
        float denominator = this.getInterestRateComp(monthlyInterest, totalNumberOfPayment) -1;
        //System.out.println("D: "+denominator);
        monthlyPayment = numerator / denominator;
       // System.out.println(monthlyPayment);
        
        for(int i = 0; i < totalNumberOfPayment; i++)
        {
            // COMPUTATION FOR ITNEREST - AMORTIZATION - PRINCIPAL
            interest = monthlyInterest * balance;
            interest = (float) (Math.round(interest * 100.00) / 100.00);
            principal = monthlyPayment - interest;
            principal = (float) (Math.round(principal * 100.00) / 100.00);
            balance -= principal;
            balance = (float) (Math.round(balance * 100.00) / 100.00);
            //System.out.println("Amortization: "+ monthlyPayment + " || Principal: "+ principal+" || Interest: " + interest+ " || Balance: "+balance);
            totPrincipal += principal;
            totAmortization += monthlyPayment;
            totInterest += interest;
            //COMMIT
        }
        totPrincipal = (float) (Math.round(totPrincipal * 100.00)/100.00);
        totAmortization = (float) (Math.round(totAmortization * 100.00)/100.00);
        totInterest = (float) (Math.round(totInterest * 100.00)/100.00);
        monthlyPayment = (float) (Math.round(monthlyPayment * 100.00)/100.00);
        //System.out.println(totInterest);
    }
    
    public float getCashloan()
    {
        float principal = this.principalBalance;
        interest = principal * monthlyInterest;
        float cashBalance = (principal * monthlyInterest) + principal;
        return cashBalance;
    }
    
    public float getInterestRateComp(float interest, int number)
    {
        float compute = 0;
        compute = (float) Math.pow(1+interest, number);
        //System.out.println(compute);
        return compute;
    }
    
    public float[] getInterestRecur(int terms, float balance)
    {
        float[] arrayInterest = new float[terms];
        float interestRecur = 0;
        float principal = 0;
        for(int i = 0; i < terms; i++)
        {
            // COMPUTATION FOR ITNEREST - AMORTIZATION - PRINCIPAL
            interestRecur = monthlyInterest * balance;
            interestRecur = (float) (Math.round(interestRecur * 100.00) / 100.00);
            principal = monthlyPayment - interestRecur;
            principal = (float) (Math.round(principal * 100.00) / 100.00);
            balance -= principal;
            balance = (float) (Math.round(balance * 100.00) / 100.00);
            arrayInterest[i] = interestRecur;
          //  System.out.println(arrayInterest[i]);
        }
        return arrayInterest;
    }
    
    public float[] getPremiumRecur(int terms, float balance)
    {
        float[] arrayPremium = new float[terms];
        float interestRecur = 0;
        float principal = 0;
        for(int i = 0; i < terms; i++)
        {
            // COMPUTATION FOR ITNEREST - AMORTIZATION - PRINCIPAL
            interestRecur = monthlyInterest * balance;
            interestRecur = (float) (Math.round(interestRecur * 100.00) / 100.00);
            principal = monthlyPayment - interestRecur;
            principal = (float) (Math.round(principal * 100.00) / 100.00);
            balance -= principal;
            balance = (float) (Math.round(balance * 100.00) / 100.00);
            arrayPremium[i] = principal;
           // System.out.println(arrayPremium[i]);
        }
        return arrayPremium;
    }
    
    public void reset()
    {
        totPrincipal = 0;
        totAmortization = 0;
        totInterest = 0;
        monthlyPayment = 0;
        interest = 0;
        cashPayment = 0;
    }
}
