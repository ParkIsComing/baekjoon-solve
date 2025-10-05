import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Node implements Comparable<Node>{
	int idx; // 현재 노드
	int cost; // 시작점에서 현재 노드까지의 거리

	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	public static int N,D;
	public static ArrayList<Node>[] shortcuts = new ArrayList[10001]; // 시작점, 도착점, 길이
	public static int[] dp = new int[10001];

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int i=0; i<=D; i++) {
			shortcuts[i] = new ArrayList<>();
		}

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (end > D) { // 지름길이 도착점이 D보다 크면 불필요(역주행은 불가능)
				continue;
			}
			if (cost >= end-start) { // 이득이 없는 지름길은 무시
				continue;
			}

			shortcuts[start].add(new Node(end, cost));
		}
	}

	public static int solve() {
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i=0; i<=D; i++) {
			if (i >= 1) {
				dp[i] = Math.min(dp[i-1] + 1, dp[i]); // 정상적인 경로
			}

			for (Node next: shortcuts[i]) {
				dp[next.idx] = Math.min(dp[i] + next.cost, dp[next.idx]);
			}

		}

		return dp[D];
	}

	public static void main(String[] args) throws Exception {
		input();
		int answer = solve();
		System.out.println(answer);
	}
}
