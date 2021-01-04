import java.util.Date;

public class Account {
	private int id;
	private double balance;
	private java.util.Date dateCreated; 
	public Account() {
		dateCreated = new java.util.Date();
	}
	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
		dateCreated = new java.util.Date();
	}
	public int getterId(){
		return this.id;
	}
	public void setterId(int id) {
		this.id = id;
	}
	public double getterBalance() {
		return this.balance;
	}
	public void setterBalance(double balance) {
		this.balance = balance;
	}
	public Date getterDate() {
		return dateCreated;
	}
	public double withdraw(int amount) {
		return this.balance -= amount;
	}
	public double deposit(int amount) {
		return this.balance += amount;
	}
}
