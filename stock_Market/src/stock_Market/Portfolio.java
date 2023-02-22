package stock_Market;

import java.util.ArrayList;

public class Portfolio {

	
	
	public Portfolio() {
/*		Stock no = new Stock();
		Bond b = new Bond();
		System.out.println(b);			*/
		
		ArrayList<Bond> a = new ArrayList<Bond>();
		a.add(new AnnuityBond("Basic Annuity Bond"));
		a.add(new CorporateBond("Basic Corporate Bond"));
		a.add(new CouponBond("Basic Coupon Bond"));
		a.add(new ZeroCouponBond("Basic No-Coupon Bond"));
		System.out.println(a);
		
		
		//CouponBond c = new CouponBond();
		//System.out.println(c);
	}
	

	
	public String toString() {
		return "";
		
	}
}
