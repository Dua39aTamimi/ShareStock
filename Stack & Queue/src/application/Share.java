package application;

import java.util.Date;

public class Share {
	private int number;
	private double price;
	private String company;
	private Date date;
	public Share(int number, double price, String company, Date date) {
		
		this.number = number;
		this.price = price;
		this.company = company;
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Share [number=" + number + ", price=" + price + ", company=" + company + ", date=" + date + "]";
	}
	
}
