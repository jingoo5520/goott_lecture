
public class AccountMain {

	public static void main(String[] args) {
		Account acc = new Account(5000000);
		System.out.println(acc.getBalance());
		
		acc.deposit(1000000);
		System.out.println(acc.getBalance());
		
		acc.withdraw(3000000);
		System.out.println(acc.getBalance());
		
		acc.withdraw(3000000);
		System.out.println(acc.getBalance());
		
		acc.withdraw(3000000);
		System.out.println(acc.getBalance());
		
		// System.out.println(acc.isValid(100));
	}

}
