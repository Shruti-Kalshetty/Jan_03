class BankAccount {

	private double balance;

	public BankAccount(double intialBalance) {

		this.balance = intialBalance;

	}

	public synchronized void deposit(double amount) {

		if (amount > 0) {

			balance += amount;

			System.out.println(Thread.currentThread().getName() + "deposited: " + amount+", New Balance: " +balance);


		}

	}

	public synchronized void withdraw(double amount) {

		if (amount > 0 && amount <= balance) {

			balance -= amount;

			System.out.println(Thread.currentThread().getName() +" withdraw:"+ amount +",New Balance:"+ balance);

		} else {

			System.out.println(Thread.currentThread().getName() +" attempted to withdraw:" + amount +", Insufficient funds. Current Balance: " + balance);

		}

	}

	public double getBalance() {

		return balance;

	}

}

class BankTransaction implements Runnable {

	private BankAccount account;

	public BankTransaction(BankAccount account) {

		this.account = account;

	}

	public void run() {

		for (int i =0; i < 3; i++) {

			double amount = Math.random() * 1000;

			if(Math.random() > 0.5) {

				account.deposit(amount);

			} else {

				account.withdraw(amount);

			}

			try {

				Thread.sleep(100);

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());

			}

		}

	}

}

public class BankAccountSystem {
 
	public static void main(String[] args) {

		BankAccount sharedAccount = new BankAccount(5000);

		BankTransaction task = new BankTransaction(sharedAccount);

		Thread user1 = new Thread(task, "User1");

		Thread user2 = new Thread(task, "User2");

		Thread user3 = new Thread(task, "User3");

		user1.start();

		user2.start();

		user3.start();

 
	}
 
}
 