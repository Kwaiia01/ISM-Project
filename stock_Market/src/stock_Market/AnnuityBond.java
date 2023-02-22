package stock_Market;

public class AnnuityBond extends Bond{		//Ordinary methods are for ordinary annuity bonds ... payed at the end of a period. I.E. bonds
						//Due methods are for due annuity bonds ... payed at the start of a period. I.E. rent
	String name = "";			//if you were paying 1k a year to rent a house for 5 years at 5% interest, if pay at the end of each period or the start
	double regPayment, interest; 		//https://www.investopedia.com/retirement/calculating-present-and-future-value-of-annuities/
	int maturityTime, ordinaryOrDue; // 0 = ordinary, 1 = due
	public AnnuityBond(String n) {
		regPayment = 10000;
		interest = 0.035;
		maturityTime = 25;
		ordinaryOrDue = 0;
		name = n;
	}
	
	
	public double futureVal() {
		if(ordinaryOrDue == 1)
			return futureValueDue();
		else return futureValueOrdinary();
	}
	
	public double presentVal() {
		if(ordinaryOrDue == 1)
			return presentValueDue();
		else return presentValueOrdinary();
	}
	
	private double futureValueOrdinary() {	//Measure how much a series of regular payments will be worth [at some point in the future / to maturity]
		return regPayment * ( ( Math.pow(1 + interest, maturityTime) - 1 ) / interest );
	}
	
	private double presentValueOrdinary() {	//Measure how much money would be required to produce the future value
		return regPayment * ( 1 - (Math.pow(1 + interest, -maturityTime) ) ) / interest;
	}
	
	private double futureValueDue() {
		return regPayment * ( (Math.pow(1 + interest, maturityTime) - 1) / interest ) * (1 + interest);
	}
	
	private double presentValueDue() {
		return regPayment * ( 1 - (Math.pow(1 + interest, -maturityTime) ) ) / interest * (1 + interest);
	}
	
	public double regPaymentTotalVal() {
		return regPayment * maturityTime;
	}
	
	public double totalInterest() {
		return futureVal() - regPaymentTotalVal();
	}
	
	public double compoundInterestFactor() {
		return 1 + (totalInterest() / regPaymentTotalVal());
	}
	
	public double getValue() {
		return presentVal();
	}
	
	public double getYTM()
	{
		return futureVal();
	}
	
	public String toString() {
		return "\n\n" + name + ":  \nCompount Interest Factor = " + compoundInterestFactor();
	}
}
