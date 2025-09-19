import java.util.*;
import java.io.*;

public class Main {
	public static final int MAX_N = 11;
	public static int solution(int num) { // 최소 m미터 위한 최대 절단기 높이
		int[] dp = new int[MAX_N];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i=4; i<=num; i++) {
			dp[i] = dp[i-1] + dp[i-2]+ dp[i-3];
		}

		return dp[num];
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i=0; i<n; i++) {
			int input = Integer.parseInt(br.readLine());
			int answer = solution(input);
			System.out.println(answer);
		}
	}
}