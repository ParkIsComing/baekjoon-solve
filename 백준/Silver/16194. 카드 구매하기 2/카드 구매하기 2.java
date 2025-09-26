import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		int[] P = new int[N + 1]; // 1-index
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) P[i] = Integer.parseInt(st.nextToken());

		int INF = 1_000_000_000;
		int[] dp = new int[N + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {          
			for (int k = 1; k <= i; k++) {       
				dp[i] = Math.min(dp[i], dp[i - k] + P[k]);
			}
		}

		System.out.println(dp[N]);
	}
}
