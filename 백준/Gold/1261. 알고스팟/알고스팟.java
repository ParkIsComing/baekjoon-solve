import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	int cost;

	Node (int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}

public class Main {
	static final int INF = 20_000; // 간선 개수 : m(n-1) + n(m-1)
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int M, N;
	static int[][] map;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		input();
		int answer = dijkstra();
		System.out.println(answer);
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

	}

	static int dijkstra () {
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][0] = 0; // (0,0) to (n-1,m-1)

		PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(0, 0, 0));

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if (dist[cur.x][cur.y] < cur.cost) { // 이미 우선순위큐로 업데이트 된 경우
				continue;
			}

			for (int i=0; i<4; i++) {
				int nx = cur.x + DIR[i][0];
				int ny = cur.y + DIR[i][1];

				if (!checkInRange(nx, ny)) continue;
				if (dist[nx][ny] > dist[cur.x][cur.y] + map[nx][ny]) {
					dist[nx][ny] = dist[cur.x][cur.y] + map[nx][ny];
					q.offer(new Node(nx, ny, dist[nx][ny]));
				}
			}

		}

		return dist[N-1][M-1];
	}

	static boolean checkInRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}


}