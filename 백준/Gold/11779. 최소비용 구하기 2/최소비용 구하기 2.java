import java.io.*;
import java.util.*;

class Node {
	int idx;
	int cost;

	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {
	static final int INF = 1_000_000_000; // m * max cost
	static int M, N, start, end;
	static int[] dist;
	static int[] prev;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		input();
		dijkstra();
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}

	static void dijkstra () {
		dist = new int[N + 1];
		prev = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		prev[start] = -1;

		PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(start, 0));

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if (dist[cur.idx] < cur.cost) { // 이미 우선순위큐로 업데이트 된 경우
				continue;
			}

			for (Node next : graph[cur.idx]) {
				if (dist[next.idx] > dist[cur.idx] + next.cost) {
					dist[next.idx] = dist[cur.idx] + next.cost;
					prev[next.idx] = cur.idx;
					q.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}

		printAnswer();

	}

	static void printAnswer() {
		int minCost = dist[end];
		int cityCount = 0;
		int cur = end;
		List<Integer> cities = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		while (cur != -1) {
			cityCount++;
			cities.add(cur);
			cur = prev[cur];
		}

		Collections.reverse(cities);

		System.out.println(minCost);
		System.out.println(cityCount);
		for (int i : cities) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);

	}

}