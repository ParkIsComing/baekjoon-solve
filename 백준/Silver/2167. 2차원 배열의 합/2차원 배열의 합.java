import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_NUM = 300;
	static int n,m,k;
	static long[][] dp = new long[MAX_NUM+1][MAX_NUM+1]; // 1-indexed
	static StringBuilder sb;
	static BufferedReader br;

	public static long getSumInRange(int i, int j, int x, int y) {
		return dp[x][y] - dp[x][j-1] - dp[i-1][y] + dp[i-1][j-1];
	}

	public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=m; j++) {
				int cur = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + cur;
			}
		}

		k =  Integer.parseInt(br.readLine());
		for (int count=0; count<k; count++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(getSumInRange(i,j,x,y));
			sb.append("\n");
		}
		System.out.println(sb);

	}
}