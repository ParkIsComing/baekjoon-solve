import java.util.*;


public class Main {
	public static int getSumOfSquares (int n) {
		return n * (n + 1) * (2 * n + 1) / 6;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();

		int startRoot = (int)Math.ceil(Math.sqrt(m));
		int endRoot = (int)Math.floor(Math.sqrt(n));

		if (startRoot >= endRoot) {
			System.out.println(-1);
			return;
		}

		int min = startRoot * startRoot;
		int sum = getSumOfSquares(endRoot) - getSumOfSquares(startRoot-1);

		System.out.println(sum);
		System.out.println(min);

	}
}