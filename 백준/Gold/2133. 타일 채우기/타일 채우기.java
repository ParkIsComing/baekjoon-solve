import java.util.*;

public class Main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] dp = new int [n+1];

		dp[0] = 1;

		for (int i=2; i<=n; i+=2) {
			dp[i] = 3 * dp[i-2];
			for (int j=i-4; j>=0; j-=2) {
				dp[i] += 2 * dp[j];
			}
		}

		System.out.println(dp[n]);
	}
}