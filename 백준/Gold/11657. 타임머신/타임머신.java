import java.io.*;
import java.util.*;

//오는 길 != 가는 길일수 있음

class Node {
	int idx;
	int cost;

	Node (int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {
	static final int INF = 500 * 10_000;
	static int N,M; // N개 노드, M개 간선
	static int[][] data;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int[M][3]; // 시작도시, 도착도시, 걸리는 시간(정수)

		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			data[i][0] = s;
			data[i][1] = e;
			data[i][2] = cost;
		}

	}

	static void solution() {
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[1] = 0; // 시작도시 1

		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			for (int[] d : data) {
				if (dist[d[0]] == INF) continue;

				long nextDist = dist[d[0]] + d[2];
				if (nextDist < dist[d[1]]) {
					dist[d[1]] = nextDist;
					if (i == N) {
						sb.append(-1);
						System.out.println(sb.toString());
						return;
					}
				}
			}
		}


		for (int i=2; i<=N; i++) {
			if (dist[i] == INF)
				sb.append(-1).append("\n");
			else
				sb.append(dist[i]).append("\n");
		}
		System.out.println(sb.toString());
	}



}