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
	public static int[] dist = new int[10001];

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
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0));
		dist[0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.cost > dist[cur.idx]) { //현재 큐에서 꺼낸 거리 vs. 지금 알고 있는 그 노드의 가장 짧은 거리
				continue;
			}

			// 지름길 업데이트
			for (Node next: shortcuts[cur.idx]) {
				if (dist[next.idx] > dist[cur.idx] + next.cost) {
					dist[next.idx] = dist[cur.idx] + next.cost;
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}

			// 지름길 vs. 그냥 이동
			if (cur.idx+1 <= D && dist[cur.idx + 1] > cur.cost + 1) {
				dist[cur.idx + 1] = cur.cost + 1;
				pq.offer(new Node(cur.idx + 1, dist[cur.idx + 1]));
			}

		}

		return dist[D];
	}

	public static void main(String[] args) throws Exception {
		input();
		int answer = solve();
		System.out.println(answer);
	}
}
