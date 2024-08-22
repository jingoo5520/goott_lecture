
public class Account {
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public void deposit(int money) {
		balance += money;
	}

	public void withdraw(int money) {

		if (!isValid(balance - money)) {
			System.out.println("금액 재입력 바람");
		} else {
			balance -= money;
		}
		;
	}

	public int getBalance() {
		return balance;
	}

	private boolean isValid(int balance) {
		if (balance >= 0) {
			return true;
		} else {
			return false;
		}
	}
}
