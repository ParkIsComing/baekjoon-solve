import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	int idx;
	int cost;

	Node (int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {
	static int v, e, start;
	static ArrayList<Node>[] graph;


	public static void main(String args[]) throws IOException {
		input();
		System.out.println(dijkstra());
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());

		graph = new ArrayList[v+1];

		for (int i=0; i<=v; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, cost)); // 단방향 그래프
		}

	}

	public static String dijkstra() {
		boolean visited[] = new boolean[v+1];
		int[] dist = new int[v+1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(start, 0));

		while (!q.isEmpty()) {
			int cur = q.poll().idx;

			if (visited[cur]) {
				continue;
			}
			visited[cur] = true;

			for (Node node : graph[cur]) {
				if (dist[node.idx] > dist[cur] + node.cost) {
					dist[node.idx] = dist[cur] + node.cost;
					q.offer(new Node(node.idx, dist[node.idx]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=v; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
		}

		return sb.toString();
	}
}