import java.awt.*;
import java.io.*;
import java.util.*;

// 첨에 집 개수 정렬에서 출력하라는 거 놓쳐서 틀림

public class Main {
	static int n,m;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs(1));
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		graph = new ArrayList[n+1];

		for (int i=0; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[n+1];

		int a=0, b=0;
		StringTokenizer st;
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
	}


	private static int bfs(int node) {
		int count = 0;
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(node);
		visited[node] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();
			for (int i: graph[cur]) {
				if (!visited[i]) {
					visited[i] = true;
					count++;
					q.offer(i);
				}
			}
		}

		return count;
	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

}
