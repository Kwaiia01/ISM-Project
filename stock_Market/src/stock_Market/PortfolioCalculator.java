package stock_Market;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class PortfolioCalculator extends JFrame {
	ArrayList<Object> port = new ArrayList<Object>();
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField StockTickerTextField;
	private JTextField StockInflationField;
	private JTextField StockDividendField;
	private JTextField StockRRORField;
	private JTextField StockAmountField;
	private JTextField CoupFaceVField;
	private JTextField CoupCurrentVField;
	private JTextField CoupCoupRateField;
	private JTextField CoupMIRField;
	private JTextField CorpFaceVField;
	private JTextField CorpPurchField;
	private JTextField CorpCoupRateField;
	private JTextField CorpDefPayField;
	private JTextField AnnuityRegPayField;
	private JTextField AnnuityInterestField;
	private JTextField CoupNameField;
	private JTextField ZeroCoupNameField;
	private JTextField CorpNameField;
	private JTextField AnnuityNameField;
	private JTextField ZeroCoupFaceVField;
	private JTextField ZeroCoupPurchField;
	private JTextField ZeroCoupANRField;
	private JTextField ZeroCoupMaturityField;
	private JTextField CoupMaturityField;
	private JTextField CorpMaturityField;
	private JTextField AnnuityMaturityField;
	private JTextField CorpDefProbField;
	String[] comp = {"Annually", "Bi-annually", "Quarterly", "Weekly", "Daily"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PortfolioCalculator frame = new PortfolioCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PortfolioCalculator() throws IOException, InterruptedException{
		setTitle("Portolio Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1344, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel stockPanel = new JPanel();
		stockPanel.setBorder(UIManager.getBorder("PopupMenu.border"));
		stockPanel.setBounds(10, 10, 254, 425);
		contentPane.add(stockPanel);
		stockPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New Stock");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 10, 234, 13);
		stockPanel.add(lblNewLabel_1);
		
		StockTickerTextField = new JTextField();
		StockTickerTextField.setBounds(115, 50, 96, 19);
		stockPanel.add(StockTickerTextField);
		StockTickerTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Stock Ticker:  ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(22, 53, 96, 13);
		stockPanel.add(lblNewLabel_2);
		
		JButton CheckTickerButton = new JButton("Check Ticker");
		CheckTickerButton.setBounds(67, 75, 121, 21);
		stockPanel.add(CheckTickerButton);
		
		StockInflationField = new JTextField();
		StockInflationField.setColumns(10);
		StockInflationField.setBounds(115, 142, 96, 19);
		stockPanel.add(StockInflationField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Inflation Rate:  ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1.setBounds(10, 145, 108, 13);
		stockPanel.add(lblNewLabel_2_1);
		
		StockDividendField = new JTextField();
		StockDividendField.setColumns(10);
		StockDividendField.setBounds(115, 171, 96, 19);
		stockPanel.add(StockDividendField);
		
		JLabel lblNewLabel_2_2 = new JLabel("Dividends:  ");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2.setBounds(10, 174, 108, 13);
		stockPanel.add(lblNewLabel_2_2);
		
		StockRRORField = new JTextField();
		StockRRORField.setColumns(10);
		StockRRORField.setBounds(115, 200, 96, 19);
		stockPanel.add(StockRRORField);
		
		JLabel lblNewLabel_2_3 = new JLabel("Required Rate of Return: ");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNewLabel_2_3.setBounds(10, 203, 108, 13);
		stockPanel.add(lblNewLabel_2_3);
		
		StockAmountField = new JTextField();
		StockAmountField.setColumns(10);
		StockAmountField.setBounds(115, 229, 96, 19);
		stockPanel.add(StockAmountField);
		
		JLabel lblNewLabel_2_4 = new JLabel("Amount of Stocks:  ");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4.setBounds(10, 232, 108, 13);
		stockPanel.add(lblNewLabel_2_4);
		
		JButton StockAddPortButton = new JButton("Add Stock To Portfolio");
		StockAddPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				if(checkTicker(StockTickerTextField.getText()))
					port.add(new Stock(StockTickerTextField.getText(), Double.parseDouble(StockInflationField.getText()), Double.parseDouble(StockDividendField.getText()), Double.parseDouble(StockRRORField.getText()), Double.parseDouble(StockAmountField.getText())));
			} catch (NumberFormatException | IOException | InterruptedException e1) {
				System.out.println("ERROR\n\n");
				e1.printStackTrace();
			}

			StockTickerTextField.setText("");
			StockInflationField.setText("");
			StockDividendField.setText("");
			StockRRORField.setText("");
			StockAmountField.setText("");
			}
		});
		StockAddPortButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		StockAddPortButton.setBounds(10, 375, 234, 21);
		stockPanel.add(StockAddPortButton);
		
		JLabel lblNewLabel = new JLabel("Input %'s as decimals (No %)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 110, 234, 13);
		stockPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(117, 133, 1, 2);
		stockPanel.add(separator);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(Color.BLACK);
		separator_1_1_1.setBounds(10, 99, 234, 13);
		stockPanel.add(separator_1_1_1);
		
		JSeparator separator_1_1_7 = new JSeparator();
		separator_1_1_7.setForeground(Color.BLACK);
		separator_1_1_7.setBounds(10, 352, 234, 13);
		stockPanel.add(separator_1_1_7);
		
		JPanel couponPanel = new JPanel();
		couponPanel.setLayout(null);
		couponPanel.setBorder(UIManager.getBorder("PopupMenu.border"));
		couponPanel.setBounds(274, 10, 254, 425);
		contentPane.add(couponPanel);
		
		JLabel lblNewLabel_1_1 = new JLabel("New Coupon Bond");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 10, 234, 13);
		couponPanel.add(lblNewLabel_1_1);
		
		CoupFaceVField = new JTextField();
		CoupFaceVField.setColumns(10);
		CoupFaceVField.setBounds(115, 144, 96, 19);
		couponPanel.add(CoupFaceVField);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Face Value:  ");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1.setBounds(10, 147, 108, 13);
		couponPanel.add(lblNewLabel_2_1_1);
		
		CoupCurrentVField = new JTextField();
		CoupCurrentVField.setColumns(10);
		CoupCurrentVField.setBounds(115, 173, 96, 19);
		couponPanel.add(CoupCurrentVField);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Current Value:  ");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2_1.setBounds(10, 176, 108, 13);
		couponPanel.add(lblNewLabel_2_2_1);
		
		CoupCoupRateField = new JTextField();
		CoupCoupRateField.setColumns(10);
		CoupCoupRateField.setBounds(115, 202, 96, 19);
		couponPanel.add(CoupCoupRateField);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Coupon Rate:  ");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_3_1.setBounds(10, 205, 108, 13);
		couponPanel.add(lblNewLabel_2_3_1);
		
		CoupMIRField = new JTextField();
		CoupMIRField.setColumns(10);
		CoupMIRField.setBounds(115, 231, 96, 19);
		couponPanel.add(CoupMIRField);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Market Interest Rate:  ");
		lblNewLabel_2_4_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1.setBounds(10, 234, 108, 13);
		couponPanel.add(lblNewLabel_2_4_1);
		
		JLabel lblNewLabel_3 = new JLabel("Input %'s as decimals (No %)");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 112, 234, 13);
		couponPanel.add(lblNewLabel_3);
		
		CoupNameField = new JTextField();
		CoupNameField.setColumns(10);
		CoupNameField.setBounds(115, 47, 96, 19);
		couponPanel.add(CoupNameField);
		
		JLabel lblNewLabel_2_5 = new JLabel("Bond Name:  ");
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_5.setBounds(10, 50, 108, 13);
		couponPanel.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_7 = new JLabel("Compounding Frequency:  ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_7.setBounds(10, 76, 129, 13);
		couponPanel.add(lblNewLabel_7);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(10, 260, 234, 13);
		couponPanel.add(separator_1_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Enter the time to maturity in years.");
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_1.setBounds(0, 271, 234, 13);
		couponPanel.add(lblNewLabel_8_1);
		
		CoupMaturityField = new JTextField();
		CoupMaturityField.setColumns(10);
		CoupMaturityField.setBounds(115, 296, 96, 19);
		couponPanel.add(CoupMaturityField);
		
		JLabel lblNewLabel_2_4_1_2_1 = new JLabel("Time To Maturity:  ");
		lblNewLabel_2_4_1_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1_2_1.setBounds(10, 299, 108, 13);
		couponPanel.add(lblNewLabel_2_4_1_2_1);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setForeground(Color.BLACK);
		separator_1_1_2.setBounds(10, 99, 234, 13);
		couponPanel.add(separator_1_1_2);
		
		JComboBox<String> CoupCombBox = new JComboBox<String>();
		for(String s : comp)
			CoupCombBox.addItem(s);
		CoupCombBox.setBounds(137, 72, 74, 21);
		couponPanel.add(CoupCombBox);
		
		JSeparator separator_1_1_6 = new JSeparator();
		separator_1_1_6.setForeground(Color.BLACK);
		separator_1_1_6.setBounds(10, 352, 234, 13);
		couponPanel.add(separator_1_1_6);
		
		JPanel zeroCouponPanel = new JPanel();
		zeroCouponPanel.setLayout(null);
		zeroCouponPanel.setBorder(UIManager.getBorder("PopupMenu.border"));
		zeroCouponPanel.setBounds(538, 10, 254, 425);
		contentPane.add(zeroCouponPanel);
		
		JLabel lblNewLabel_1_2 = new JLabel("New Zero Coupon Bond");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 10, 234, 13);
		zeroCouponPanel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_4 = new JLabel("Input %'s as decimals (No %)");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 112, 234, 13);
		zeroCouponPanel.add(lblNewLabel_4);
		
		ZeroCoupNameField = new JTextField();
		ZeroCoupNameField.setColumns(10);
		ZeroCoupNameField.setBounds(115, 46, 96, 19);
		zeroCouponPanel.add(ZeroCoupNameField);
		
		JLabel lblNewLabel_2_6 = new JLabel("Bond Name:  ");
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_6.setBounds(10, 49, 108, 13);
		zeroCouponPanel.add(lblNewLabel_2_6);
		
		ZeroCoupFaceVField = new JTextField();
		ZeroCoupFaceVField.setColumns(10);
		ZeroCoupFaceVField.setBounds(115, 143, 96, 19);
		zeroCouponPanel.add(ZeroCoupFaceVField);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Face Value:  ");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2_1_1.setBounds(10, 146, 108, 13);
		zeroCouponPanel.add(lblNewLabel_2_2_1_1);
		
		ZeroCoupPurchField = new JTextField();
		ZeroCoupPurchField.setColumns(10);
		ZeroCoupPurchField.setBounds(115, 172, 96, 19);
		zeroCouponPanel.add(ZeroCoupPurchField);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("Purchase Price:  ");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_3_1_1.setBounds(10, 175, 108, 13);
		zeroCouponPanel.add(lblNewLabel_2_3_1_1);
		
		ZeroCoupANRField = new JTextField();
		ZeroCoupANRField.setColumns(10);
		ZeroCoupANRField.setBounds(115, 201, 96, 19);
		zeroCouponPanel.add(ZeroCoupANRField);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Annual Interest Rate:  ");
		lblNewLabel_2_4_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1_1.setBounds(10, 204, 108, 13);
		zeroCouponPanel.add(lblNewLabel_2_4_1_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("CheckBox.focus"));
		separator_1.setBounds(10, 230, 234, 13);
		zeroCouponPanel.add(separator_1);
		
		JLabel lblNewLabel_8 = new JLabel("Enter the time to maturity in years.");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 241, 234, 13);
		zeroCouponPanel.add(lblNewLabel_8);
		
		ZeroCoupMaturityField = new JTextField();
		ZeroCoupMaturityField.setColumns(10);
		ZeroCoupMaturityField.setBounds(115, 266, 96, 19);
		zeroCouponPanel.add(ZeroCoupMaturityField);
		
		JLabel lblNewLabel_2_4_1_2 = new JLabel("Time To Maturity:  ");
		lblNewLabel_2_4_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1_2.setBounds(10, 269, 108, 13);
		zeroCouponPanel.add(lblNewLabel_2_4_1_2);
		
		JSeparator separator_1_1_3 = new JSeparator();
		separator_1_1_3.setForeground(Color.BLACK);
		separator_1_1_3.setBounds(10, 99, 234, 13);
		zeroCouponPanel.add(separator_1_1_3);
		
		JLabel lblNewLabel_7_1 = new JLabel("Compounding Frequency:  ");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_7_1.setBounds(23, 76, 129, 13);
		zeroCouponPanel.add(lblNewLabel_7_1);
		
		JComboBox<String> ZeroCoupCombBox = new JComboBox<String>();
		for(String s : comp)
			ZeroCoupCombBox.addItem(s);
		ZeroCoupCombBox.setBounds(150, 72, 74, 21);
		zeroCouponPanel.add(ZeroCoupCombBox);
		
		JSeparator separator_1_1_9 = new JSeparator();
		separator_1_1_9.setForeground(Color.BLACK);
		separator_1_1_9.setBounds(10, 352, 234, 13);
		zeroCouponPanel.add(separator_1_1_9);
		
		JPanel corporatePanel = new JPanel();
		corporatePanel.setLayout(null);
		corporatePanel.setBorder(UIManager.getBorder("PopupMenu.border"));
		corporatePanel.setBounds(802, 10, 254, 425);
		contentPane.add(corporatePanel);
		
		JLabel lblNewLabel_1_3 = new JLabel("New Corporate Bond");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 10, 234, 13);
		corporatePanel.add(lblNewLabel_1_3);
		
		CorpFaceVField = new JTextField();
		CorpFaceVField.setColumns(10);
		CorpFaceVField.setBounds(115, 142, 96, 19);
		corporatePanel.add(CorpFaceVField);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Face Value:  ");
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_3.setBounds(10, 145, 108, 13);
		corporatePanel.add(lblNewLabel_2_1_3);
		
		CorpPurchField = new JTextField();
		CorpPurchField.setColumns(10);
		CorpPurchField.setBounds(115, 171, 96, 19);
		corporatePanel.add(CorpPurchField);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("Purchase Price:  ");
		lblNewLabel_2_2_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2_3.setBounds(10, 174, 108, 13);
		corporatePanel.add(lblNewLabel_2_2_3);
		
		CorpCoupRateField = new JTextField();
		CorpCoupRateField.setColumns(10);
		CorpCoupRateField.setBounds(115, 200, 96, 19);
		corporatePanel.add(CorpCoupRateField);
		
		JLabel lblNewLabel_2_3_3 = new JLabel("Coupon Rate:  ");
		lblNewLabel_2_3_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_3_3.setBounds(10, 203, 108, 13);
		corporatePanel.add(lblNewLabel_2_3_3);
		
		CorpDefPayField = new JTextField();
		CorpDefPayField.setColumns(10);
		CorpDefPayField.setBounds(115, 229, 96, 19);
		corporatePanel.add(CorpDefPayField);
		
		JLabel lblNewLabel_2_4_3 = new JLabel("Default Payout:  ");
		lblNewLabel_2_4_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_3.setBounds(10, 232, 108, 13);
		corporatePanel.add(lblNewLabel_2_4_3);
		
		JLabel lblNewLabel_5 = new JLabel("Input %'s as decimals (No %)");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 112, 234, 13);
		corporatePanel.add(lblNewLabel_5);
		
		CorpNameField = new JTextField();
		CorpNameField.setColumns(10);
		CorpNameField.setBounds(115, 46, 96, 19);
		corporatePanel.add(CorpNameField);
		
		JLabel lblNewLabel_2_7 = new JLabel("Bond Name:  ");
		lblNewLabel_2_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_7.setBounds(10, 49, 108, 13);
		corporatePanel.add(lblNewLabel_2_7);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Color.BLACK);
		separator_1_2.setBounds(10, 287, 234, 13);
		corporatePanel.add(separator_1_2);
		
		JLabel lblNewLabel_8_2 = new JLabel("Enter the time to maturity in years.");
		lblNewLabel_8_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_2.setBounds(10, 298, 234, 13);
		corporatePanel.add(lblNewLabel_8_2);
		
		CorpMaturityField = new JTextField();
		CorpMaturityField.setColumns(10);
		CorpMaturityField.setBounds(115, 323, 96, 19);
		corporatePanel.add(CorpMaturityField);
		
		JLabel lblNewLabel_2_4_1_2_2 = new JLabel("Time To Maturity:  ");
		lblNewLabel_2_4_1_2_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1_2_2.setBounds(10, 326, 108, 13);
		corporatePanel.add(lblNewLabel_2_4_1_2_2);
		
		CorpDefProbField = new JTextField();
		CorpDefProbField.setColumns(10);
		CorpDefProbField.setBounds(115, 258, 96, 19);
		corporatePanel.add(CorpDefProbField);
		
		JLabel lblNewLabel_2_4_3_1 = new JLabel("Default Probability:  ");
		lblNewLabel_2_4_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_3_1.setBounds(10, 261, 108, 13);
		corporatePanel.add(lblNewLabel_2_4_3_1);
		
		JSeparator separator_1_1_4 = new JSeparator();
		separator_1_1_4.setForeground(Color.BLACK);
		separator_1_1_4.setBounds(10, 99, 234, 13);
		corporatePanel.add(separator_1_1_4);
		
		JLabel lblNewLabel_7_2 = new JLabel("Compounding Frequency:  ");
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_7_2.setBounds(25, 76, 129, 13);
		corporatePanel.add(lblNewLabel_7_2);
		
		JComboBox<String> CorpCombBox = new JComboBox<String>();
		for(String s : comp)
			CorpCombBox.addItem(s);
		CorpCombBox.setBounds(152, 72, 74, 21);
		corporatePanel.add(CorpCombBox);
		
		JSeparator separator_1_1_8 = new JSeparator();
		separator_1_1_8.setForeground(Color.BLACK);
		separator_1_1_8.setBounds(10, 352, 234, 13);
		corporatePanel.add(separator_1_1_8);
		
		JPanel annuityPanel = new JPanel();
		annuityPanel.setLayout(null);
		annuityPanel.setBorder(UIManager.getBorder("PopupMenu.border"));
		annuityPanel.setBounds(1066, 10, 254, 425);
		contentPane.add(annuityPanel);
		
		JLabel lblNewLabel_1_4 = new JLabel("New Annuity Bond");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(10, 10, 234, 13);
		annuityPanel.add(lblNewLabel_1_4);
		
		AnnuityRegPayField = new JTextField();
		AnnuityRegPayField.setColumns(10);
		AnnuityRegPayField.setBounds(115, 142, 96, 19);
		annuityPanel.add(AnnuityRegPayField);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Regular Payment:  ");
		lblNewLabel_2_1_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_4.setBounds(10, 145, 108, 13);
		annuityPanel.add(lblNewLabel_2_1_4);
		
		AnnuityInterestField = new JTextField();
		AnnuityInterestField.setColumns(10);
		AnnuityInterestField.setBounds(115, 171, 96, 19);
		annuityPanel.add(AnnuityInterestField);
		
		JLabel lblNewLabel_2_2_4 = new JLabel("Interest:  ");
		lblNewLabel_2_2_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2_4.setBounds(10, 174, 108, 13);
		annuityPanel.add(lblNewLabel_2_2_4);
		
		JLabel lblNewLabel_6 = new JLabel("Input %'s as decimals (No %)");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 112, 234, 13);
		annuityPanel.add(lblNewLabel_6);
		
		AnnuityNameField = new JTextField();
		AnnuityNameField.setColumns(10);
		AnnuityNameField.setBounds(115, 48, 96, 19);
		annuityPanel.add(AnnuityNameField);
		
		JLabel lblNewLabel_2_8 = new JLabel("Bond Name:  ");
		lblNewLabel_2_8.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_8.setBounds(10, 51, 108, 13);
		annuityPanel.add(lblNewLabel_2_8);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(Color.BLACK);
		separator_1_3.setBounds(10, 200, 234, 13);
		annuityPanel.add(separator_1_3);
		
		JLabel lblNewLabel_8_3 = new JLabel("Enter the time to maturity in years.");
		lblNewLabel_8_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_3.setBounds(10, 211, 234, 13);
		annuityPanel.add(lblNewLabel_8_3);
		
		AnnuityMaturityField = new JTextField();
		AnnuityMaturityField.setColumns(10);
		AnnuityMaturityField.setBounds(115, 236, 96, 19);
		annuityPanel.add(AnnuityMaturityField);
		
		JLabel lblNewLabel_2_4_1_2_3 = new JLabel("Time To Maturity:  ");
		lblNewLabel_2_4_1_2_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_4_1_2_3.setBounds(10, 239, 108, 13);
		annuityPanel.add(lblNewLabel_2_4_1_2_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ordinary");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(48, 304, 89, 21);
		annuityPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnDue = new JRadioButton("Due");
		buttonGroup.add(rdbtnDue);
		rdbtnDue.setBounds(139, 304, 49, 21);
		annuityPanel.add(rdbtnDue);
		
		JSeparator separator_1_3_1 = new JSeparator();
		separator_1_3_1.setForeground(Color.BLACK);
		separator_1_3_1.setBounds(10, 266, 234, 13);
		annuityPanel.add(separator_1_3_1);
		
		JLabel lblNewLabel_8_3_1 = new JLabel("Due Annuity or an Ordinary Annuity?");
		lblNewLabel_8_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_3_1.setBounds(10, 278, 234, 13);
		annuityPanel.add(lblNewLabel_8_3_1);
		
		JSeparator separator_1_1_5 = new JSeparator();
		separator_1_1_5.setForeground(Color.BLACK);
		separator_1_1_5.setBounds(10, 99, 234, 13);
		annuityPanel.add(separator_1_1_5);
		
		JSeparator separator_1_1_8_1 = new JSeparator();
		separator_1_1_8_1.setForeground(Color.BLACK);
		separator_1_1_8_1.setBounds(10, 352, 234, 13);
		annuityPanel.add(separator_1_1_8_1);
		
		JButton PrintToConsoleButton = new JButton("Print Portfolio To Console");
		PrintToConsoleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object b : port)
					System.out.println(b.toString());
				System.out.println(percentageBondvStock());
			}
		});
		PrintToConsoleButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		PrintToConsoleButton.setBounds(802, 511, 254, 75);
		contentPane.add(PrintToConsoleButton);
		
		JButton CoupToPortButton = new JButton("Add CoupBond To Portfolio");
		CoupToPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				port.add(new CouponBond((CoupNameField.getText()), Double.parseDouble(CoupCoupRateField.getText()), Integer.parseInt(CoupMaturityField.getText()), (String)CoupCombBox.getSelectedItem(), Double.parseDouble(CoupFaceVField.getText()), Double.parseDouble(CoupCurrentVField.getText()), Double.parseDouble(CoupMIRField.getText())));
				CoupNameField.setText(""); CoupCoupRateField.setText(""); CoupMaturityField.setText(""); CoupCombBox.setSelectedIndex(0); CoupFaceVField.setText(""); CoupCurrentVField.setText(""); CoupMIRField.setText("");
			}

		});
		CoupToPortButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CoupToPortButton.setBounds(10, 375, 234, 21);
		couponPanel.add(CoupToPortButton);
		
		JButton AnnuityBondToPort = new JButton("Add AnnuityBond To Portfolio");
		AnnuityBondToPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				if(rdbtnNewRadioButton.isSelected())
					i = 0;
				else i = 1;
				port.add(new AnnuityBond(AnnuityNameField.getText(), Double.parseDouble(AnnuityRegPayField.getText()), Double.parseDouble(AnnuityInterestField.getText()),Integer.parseInt(AnnuityMaturityField.getText()), i));
				AnnuityNameField.setText(""); AnnuityRegPayField.setText(""); AnnuityInterestField.setText(""); AnnuityMaturityField.setText(""); 
			}
		});
		AnnuityBondToPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AnnuityBondToPort.setBounds(10, 375, 234, 21);
		annuityPanel.add(AnnuityBondToPort);
		
		JButton ZeroCoupToPortButton = new JButton("Add ZeroCoupBond To Portfolio");
		ZeroCoupToPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				port.add(new ZeroCouponBond(ZeroCoupNameField.getText(), Double.parseDouble(ZeroCoupFaceVField.getText()), Double.parseDouble(ZeroCoupANRField.getText()), Double.parseDouble(ZeroCoupPurchField.getText()), (String)ZeroCoupCombBox.getSelectedItem(), Integer.parseInt(ZeroCoupMaturityField.getText())));
				ZeroCoupNameField.setText(""); ZeroCoupFaceVField.setText(""); ZeroCoupANRField.setText(""); ZeroCoupPurchField.setText(""); ZeroCoupCombBox.setSelectedIndex(0); ZeroCoupMaturityField.setText("");
			}
		});
		ZeroCoupToPortButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ZeroCoupToPortButton.setBounds(10, 375, 234, 21);
		zeroCouponPanel.add(ZeroCoupToPortButton);
		
		JButton CorpBondToPort = new JButton("Add CorpBond To Portfolio");
		CorpBondToPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				port.add(new CorporateBond(CorpNameField.getText(), Double.parseDouble(CorpFaceVField.getText()), Double.parseDouble(CorpPurchField.getText()), Double.parseDouble(CorpCoupRateField.getText()), (String)CorpCombBox.getSelectedItem(), Integer.parseInt(CorpMaturityField.getText()), Double.parseDouble(CorpDefProbField.getText()), Double.parseDouble(CorpDefPayField.getText())));
				CorpNameField.setText(""); CorpFaceVField.setText(""); CorpPurchField.setText(""); CorpCoupRateField.setText(""); CorpCombBox.setSelectedIndex(0); CorpMaturityField.setText(""); CorpDefProbField.setText(""); CorpDefPayField.setText("");
			}
		});
		CorpBondToPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CorpBondToPort.setBounds(10, 375, 234, 21);
		corporatePanel.add(CorpBondToPort);
		
		JButton CreateTXTFileButton = new JButton("Create Portfolio Text File");
		CreateTXTFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					File PortfolioList = new File("PortfolioList.txt");
					FileWriter writer = null;
					try {
						writer = new FileWriter("PortfolioList.txt");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					for(Object b : port)
						try {
							writer.write(b.toString());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					try {
						writer.write(percentageBondvStock());
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		CreateTXTFileButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		CreateTXTFileButton.setBounds(274, 511, 254, 75);
		contentPane.add(CreateTXTFileButton);
	}
	
	public boolean checkTicker(String s) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://twelve-data1.p.rapidapi.com/stocks?exchange=NASDAQ&symbol="
						+ s + "&format=json"))
				.header("X-RapidAPI-Key", "4097731f17msh4284dac54bcb157p15e638jsne1dd97ec0275")
				.header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		if(response.body().indexOf(s)>=0)
			return true;
		else return false;
	}
	
	public String percentageBondvStock() {
		int count, bcount, btos;
		bcount = 0;
		count = port.size();
		for(Object b : port)
			if(b instanceof Bond)
				bcount++;
		btos = (bcount/count) * 100;
				
				
		return "\n\n" + btos + "% of the portfolio are bonds";
	}
}
