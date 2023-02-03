package stock_Market;

public class CorporateBond extends Bond 
{

	double faceValue, purchasePrice, defaultProbability, discountRate, couponRate; //PurchasePrice = price of bond during purchase
	int compoundFreq, maturityTime, defaultPayout;		//compoundFreq = compounding frequency, maturityTime = time to maturity
	
	public CorporateBond() 
	{
		compoundFreq = 1;
		maturityTime = 3;
		faceValue = 1000;
		couponRate = 0.05;
		defaultPayout = 600;
		defaultProbability = .10;
		discountRate = 0.05;
	}
	
	public double bondValue() //aka bond Price
	{
		double price = 0;
		
		for(int i = 0; i<maturityTime-1; i++)
		{
			price += Math.pow((1 - defaultProbability), i) * ( (defaultProbability * defaultPayout)  + ( (1-defaultProbability) * couponPayment(faceValue, couponRate, compoundFreq) ) ) / Math.pow(1 + discountRate, i+1);
		}
		price += Math.pow((1 - defaultProbability), maturityTime-1) * ( (defaultProbability * defaultPayout)  + ( (1-defaultProbability) * (couponPayment(faceValue, couponRate, compoundFreq) + faceValue) ) ) / Math.pow(1 + discountRate, maturityTime);
		return price;
	}

	public double yieldToMaturityPercent()
	{
		for(int i = 1; i<maturityTime; i++)
		{
			//https://www.omnicalculator.com/finance/bond-ytm#how-to-calculate-yield-to-maturity-the-bond-yield-to-maturity-formula
		}
		
		return 0;
	}
	
	public String toString()
	{
		return String.format("The expected price of the bond is $%.2f \nThe rate of return of the bond is %.2f", bondValue(), yieldToMaturityPercent()*100) + "%";
	}
}
