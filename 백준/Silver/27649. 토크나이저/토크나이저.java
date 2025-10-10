import java.util.*;

public class Main {
	static String[] strArr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input =  sc.nextLine();

		input = input.replace("&&", " && ");
		input = input.replace("||", " || ");
		input = input.replace("<", " < ");
		input = input.replace(">", " > ");
		input = input.replace("(", " ( ");
		input = input.replace(")", " ) ");

		String[] tokens = input.trim().split("\\s+");

		System.out.println(String.join(" ", tokens));
	}

}
