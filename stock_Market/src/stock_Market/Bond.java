/**
 * 
 */
package stock_Market;

/**
 * @author tacom
 * Information on bonds: https://www.slideshare.net/zapak13/bond-valuation-14624262
 */
abstract class Bond { 
	
	public abstract double getYTM();
	public abstract double getValue();
	
	public double couponPayment(double faceValue, double couponRate, int compoundFreq) 
	{				//Price of coupon per compounding period. 
		return (faceValue * couponRate) / compoundFreq;	//IE $1000 bond compounding 2 times a year at 0.9% coupon rate gives $45 per period.
	}
	
}



