package stock_Market;

public class CouponBond extends Bond{
	
	private double couponRate, endPrice, faceValue, annualInterestRate, marketInterestRate;
	private int maturityYears, couponTime;		//couponTime = compounding frequency

	public CouponBond() 
	{
		couponRate = 0.09;
		couponTime = 2;
		faceValue = 1000;
		marketInterestRate = 0.1;
		maturityYears = 10;
	}

	public double yieldToMaturity() 
	{				//yield of the bond if held to maturity
		for(int i = 1; i < maturityYears*couponTime; i++) 
		{		//IE $1000 bond compounding 2 times a year with $45 a coupon for 10 years gives a total of $1900 market price
			endPrice += couponPayment(faceValue, couponRate, couponTime) / Math.pow(1 + (annualInterestRate / couponTime), i);
		}
		endPrice += (couponPayment(faceValue, couponRate, couponTime) + faceValue) / Math.pow(1 + (annualInterestRate / couponTime), maturityYears*couponTime);
		return endPrice;
	}
	
	public double bondPrice() 
	{				//Expected selling price
		double bp = 0;
		
		bp = ((couponRate*100)/(couponTime*100)) * (faceValue) * ( (1 - (Math.pow(1 + ((marketInterestRate*100)/ (couponTime*100) ), (-1 * maturityYears) * couponTime))) / ((marketInterestRate*100)/ (couponTime*100) )) + (faceValue/(Math.pow(1 + ((marketInterestRate*100)/ (couponTime*100) ), maturityYears * couponTime)));
		
		return bp;					
	}

	public double currentYield() 
	{					//research needed
		return (couponPayment(faceValue, couponRate, couponTime)*couponTime) / bondPrice()*100;
	}
	
	public String toString() 
	{
		return String.format("The expected selling price of the bond is: $%.2f "
				+ "\nThe yield of the bond to maturity is: $%.2f "
				+ "\nThe price of each coupon is: $%.2f "
				+ "\nThe current yield of the bond is: %.2f"
				, bondPrice(), yieldToMaturity(), couponPayment(faceValue, couponRate, couponTime), currentYield()) 
				+ "%";
	}
}
