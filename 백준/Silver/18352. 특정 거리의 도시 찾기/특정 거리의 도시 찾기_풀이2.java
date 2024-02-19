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
	static ArrayList<Integer>[] graph;

	public static ArrayList<Integer> dijkstra(int n, int start, int target) {
		boolean[] visited = new boolean[n+1];
		int[] dist = new int[n+1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dist, INF); // 큰값으로 거리 배열 초기화
		dist[start] = 0; // 시작점 거리 0으로 세팅

		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(start, 0));

		while(!q.isEmpty()) {
			int now = q.poll().idx;

			if (visited[now]) continue;
			visited[now] = true;

			for (Integer next : graph[now]) {
				if (dist[next] > dist[now] + 1) {
					dist[next] = dist[now] + 1; // 현재 노드를 거쳐가면 더 짧은 경우 갱신
					q.offer(new Node(next, dist[next]));
				}
			}
		}
		
		ArrayList<Integer> answer = new ArrayList<>();
		for (int i=0; i<n+1; i++) {
		    if (dist[i] == target) {
		        answer.add(i);
		    }
		}
		
		return answer;

	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];

		for (int i=0; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i=0; i<m; i++) {
		    st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b); // 유향 그래프
		}

        ArrayList<Integer> answer = dijkstra(n, start, k);
		if (answer.size() == 0) {
		    System.out.println(-1);
		} else {
		    for (int i : answer) {
		        System.out.println(i);
		    }
		}
	}
}
