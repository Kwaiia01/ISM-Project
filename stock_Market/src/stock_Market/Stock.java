/**
 * 
 */
package stock_Market;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Stock {

	String name;
	double sharpe, sortino, riskFreeRate, standardDev, dividend, RroR, currentVal, estimatedVal;
	double amount;
	Object[] arr;
	
	public Stock(String s, double inflation, double div, double ReqRoR, double am) throws IOException, InterruptedException {
				

		riskFreeRate = (( 1 + 0.0345) / (1 + inflation) ) -1;
		RroR = ReqRoR;
		amount = am;
		standardDev = getDev(s);
		dividend = div;
		currentVal = currentValue(s);
		estimatedVal = estimValue();
		arr = new Object[] { s, amount, currentVal, estimatedVal, costOfEquity(getBeta(s)), sharpeRatio()};

	}
	
	public double getBeta(String s) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://twelve-data1.p.rapidapi.com/beta?interval=1month&symbol="
						+ s +"&format=csv&series_type_2=close&series_type_1=open&outputsize=1"))
				.header("X-RapidAPI-Key", "XXX")
				.header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String bet = response.body().substring(response.body().lastIndexOf(";") + 1);

		return Double.parseDouble(bet);
	}
	
	public double getDev(String s) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://twelve-data1.p.rapidapi.com/stddev?interval=1month&symbol="
						+ s + "&series_type=close&sd=2&time_period=9&format=csv&outputsize=1"))
				.header("X-RapidAPI-Key", "XXX")
				.header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		String sub = response.body().substring(response.body().lastIndexOf(";") + 1);
//		System.out.println(response.body());
//		System.out.println(sub);
		return Double.parseDouble(sub);
	}
	
	public double estimValue(){
		
		return currentVal * (1+RroR);
	}
	
	public double currentValue(String s) throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://twelve-data1.p.rapidapi.com/time_series?symbol="
						+ s+"&interval=1day&outputsize=1&format=csv"))
				.header("X-RapidAPI-Key", "XXX")
				.header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
		String[] sub = response.body().split(";");
//		System.out.println(sub[sub.length-2]);
		return Double.parseDouble(sub[sub.length-2]);
	}
	
	public double sharpeRatio(){
		
		return ( (estimatedVal - currentVal) - riskFreeRate)/ standardDev;
	}
	
	
	private double costOfEquity(double beta) {
		
		return riskFreeRate + beta * (0.056 - riskFreeRate);
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i<arr.length; i++)
			output += arr[i] + " ";
		return output;
	}
	

}


