class BankAccount {

	private int balance = 1000;

	public synchronized void Withdraw(int amount) {

		if(balance >= amount) {

			System.out.println(Thread.currentThread().getName() +" is withdrawing" + amount);

			balance -= amount;

			System.out.println(Thread.currentThread().getName() +" completed withdrawal.Remaining balance: + balance");

		} else {

			System.out.println(Thread.currentThread().getName() +" attempted to withdraw" + amount + " but insufficient balance.");

		}

	}

}

class Customer extends Thread {

	private BankAccount account;

	public Customer(BankAccount account, String name) {

		super(name);

		this.account = account;

	}

	public void run() {

		account.Withdraw(700);

	}

}

public class BankSystem {
 
	public static void main(String[] args) {

		BankAccount account = new BankAccount();

		Customer customer1 = new Customer(account, "Customer 1");

		Customer customer2 = new Customer(account, "Customer 2");

		customer1.start();

		customer2.start();


	}
 
}
 