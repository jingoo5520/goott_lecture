
public class StaticVSinstance1 {
	static int sInt;
	int iInt;
	
	// 초기화 블럭
	{
		iInt = 5;
	}
	
	static {
		sInt = 100;
	}
	
	
	public void acc() {
		sInt++;
		this.iInt++;
		
		System.out.println(this.hashCode() + ", " + StaticVSinstance1.sInt + ", " + this.iInt);
		
	}
	
	public static void main(String[] args) {
		
		
		
		for( int i = 0; i < 10; i++) {
			StaticVSinstance1 svi = new StaticVSinstance1();
			
			svi.acc();
		}

	}

}
