/**
 * 
 */
package stock_Market;

/**
 * @author tacom
 * Information on bonds: https://www.slideshare.net/zapak13/bond-valuation-14624262
 */
public class Bond {

	
	public Bond() {

	}
	
	public double couponPayment(double faceValue, double couponRate, int compoundFreq) 
	{				//Price of coupon per compounding period. 
		return (faceValue * couponRate) / compoundFreq;	//IE $1000 bond compounding 2 times a year at 0.9% coupon rate gives $45 per period.
	}
	
	
	public String toString() {
		return "";
	}
	
}


