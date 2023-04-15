package application;

public class Prices {
	private double price;
	private String company;
	public Prices( String company,double price) {
		super();
		this.price = price;
		this.company = company;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Prices [price=" + price + ", company=" + company + "]";
	}
	
}
