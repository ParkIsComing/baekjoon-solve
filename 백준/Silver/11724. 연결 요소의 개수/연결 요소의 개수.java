import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n,m;
	static ArrayList<Integer>[] graph;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		input();
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			set.add(find(parent[i]));
		}
		
		System.out.println(set.size());
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		parent = new int[n+1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
			parent[i] = i;
		}

		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[y] = x;
	}

	static int find(int node) {
		if (parent[node] == node) {
			return node;
		} else {
			return parent[node] = find(parent[node]);
		}
	}

}
