package webjingoo.compare;

public class User implements Comparable<User> {
	private String userId;
	private int age;
	
	
	public User(String userId, int age) {
		super();
		this.userId = userId;
		this.age = age;
	}
	
	public String getUserId() {
		return userId;
	}
	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(User o) {
		
		return this.age < o.age ? -1 : this.age == o.age ? 0 : 1;

	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", age=" + age + "]";
	}
}
