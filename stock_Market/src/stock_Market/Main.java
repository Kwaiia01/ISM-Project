/**
 * 
 */
package stock_Market;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;


public class Main extends JFrame implements ActionListener{
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		PortfolioCalculator p = new PortfolioCalculator();
		p.setVisible(true);
		
		
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
