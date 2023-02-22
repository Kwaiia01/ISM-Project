package stock_Market;

public class CouponBond extends Bond{
	String name = "";
	private double couponRate, faceValue, marketInterestRate, currentValue;
	private int maturityYears, couponTime;		//couponTime = compounding frequency

	public CouponBond(String n) 
	{
		couponRate = 0.08;
		couponTime = 1;
		faceValue = 1000;
		marketInterestRate = 0.1;
		maturityYears = 12;
		name = n;
		currentValue = 940;
	}
	
	private double yieldToMaturity() 
	{				//yield of the bond if held to maturity
		double ytm = 0;
		
		ytm = (couponPayment(faceValue, couponRate, couponTime) + (faceValue - currentValue)/12) / ( (faceValue + currentValue) / 2);
		
		return ytm * 100;
	}
	
	private double bondPrice() 
	{				//Expected selling price
		double bp = 0;
		
		bp = ((couponRate*100)/(couponTime*100)) * (faceValue) * ( (1 - (Math.pow(1 + ((marketInterestRate*100)/ (couponTime*100) ), (-1 * maturityYears) * couponTime))) / ((marketInterestRate*100)/ (couponTime*100) )) + (faceValue/(Math.pow(1 + ((marketInterestRate*100)/ (couponTime*100) ), maturityYears * couponTime)));
		
		return bp;					
	}

	
	public double getValue() {
		return bondPrice();
	}
	
	public double getYTM() {
		return yieldToMaturity();
	}
	
	
	public String toString() 
	{
		return String.format("\n\n" + name + "\nThe expected selling price of the bond is: $%.2f "
				+ "\nThe price of each coupon is: $%.2f "				
				+ "\nThe yield of the bond to maturity is: $%.2f"
				, bondPrice(),couponPayment(faceValue, couponRate, couponTime), yieldToMaturity()) + "%";
	}
}
