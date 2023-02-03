package stock_Market;

public class ZeroCouponBond {
	
	double faceValue, annualInterestRate, purchasePrice; //PurchasePrice = price of bond during purchase
	int compoundFreq, maturityTime;		//compoundFreq = compounding frequency, maturityTime = time to maturity
	
	public ZeroCouponBond() 
	{
		faceValue = 1000;
		annualInterestRate = 0.075;
		compoundFreq = 1;
		maturityTime = 5;
		purchasePrice = 925;
	}
	
	public double effectiveInterestRate() // effective interest rate per compounding period
	{
		return annualInterestRate/compoundFreq;
	}
	
	public double bondValue() // OR bond price/present value
	{
		return faceValue/Math.pow((1+effectiveInterestRate()), (maturityTime * compoundFreq));
	}

	
	public double yieldToMaturity() //yield to maturity of a bond, AKA Spot rate or Rate of Return
	{
		return Math.pow((faceValue/purchasePrice), (1/(double)maturityTime)) - 1 ;
	}
	
	public String toString()
	{
		return String.format("The expected price of the bond is $%.2f \nThe rate of return of the bond is %.2f", bondValue(), yieldToMaturity()*100) + "%";
	}
}