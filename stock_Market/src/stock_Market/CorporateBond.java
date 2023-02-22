package stock_Market;

public class CorporateBond extends Bond 
{
	String name = "";
	double faceValue, purchasePrice, defaultProbability, discountRate, couponRate; //PurchasePrice = price of bond during purchase
	int compoundFreq, maturityTime, defaultPayout;		//compoundFreq = compounding frequency, maturityTime = time to maturity
	
	public CorporateBond(String n) 
	{
		compoundFreq = 1;
		maturityTime = 3;
		faceValue = 1000;
		couponRate = 0.05;
		defaultPayout = 600;
		defaultProbability = .10;
		discountRate = 0.05;
		name = n;
	}
	
	private double bondValue() //aka bond Price
	{
		double price = 0;
		
		for(int i = 0; i<maturityTime-1; i++)
		{
			price += Math.pow((1 - defaultProbability), i) * ( (defaultProbability * defaultPayout)  + ( (1-defaultProbability) * couponPayment(faceValue, couponRate, compoundFreq) ) ) / Math.pow(1 + discountRate, i+1);
		}
		price += Math.pow((1 - defaultProbability), maturityTime-1) * ( (defaultProbability * defaultPayout)  + ( (1-defaultProbability) * (couponPayment(faceValue, couponRate, compoundFreq) + faceValue) ) ) / Math.pow(1 + discountRate, maturityTime);
		return price;
	}

	private double yieldToMaturity()
	{
		return (couponPayment(faceValue, couponRate, compoundFreq) +  ( (faceValue - bondValue() )/ maturityTime ) ) / ( ( faceValue + bondValue() ) / 2) * 100;
	}
	
	public double getValue() {
		return bondValue();
	}
	
	public double getYTM(){
		return yieldToMaturity();
	}
	
	public String toString()
	{
		return String.format("\n\n" + name + ":  \nThe expected price of the bond is $%.2f \nThe yield to maturity of the bond is %.2f", bondValue(), yieldToMaturity()) + "%";
	}
}
