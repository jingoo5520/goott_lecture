import java.util.Arrays;

public class Array2Ex {
	public static void main(String[] args) {
		System.out.println("length of args in main method: " + args.length);
		
		System.out.println(Arrays.toString(args));
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("\\w")) {
				System.out.println("123");
			}
		}
	}
}
