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
    static int n, d;
	static ArrayList<Node>[] graph;
	static int[] dist;

	public static void dijkstra(int x) { // 하나씩 움직이면서 지름길 있으면 갱신
		if (x > d) {
		    return;
		}
		
		if (dist[x+1] > dist[x] + 1) { // x에서 x+1하나 움직이는게 더 작으면 업데이트
		    dist[x+1] = dist[x] + 1;
		}

		for (Node next : graph[x]) { // 지름길 있으면 업데이트
		    if (dist[next.idx] > dist[x] + next.cost ) {
		        dist[next.idx] = dist[x] + next.cost;
		    }
		}
		
		dijkstra(x+1);

	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지름길 개수
		d = Integer.parseInt(st.nextToken()); // 고속도로 길이

		graph = new ArrayList[10001];

		for (int i=0; i < 10001; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i=0; i<n; i++) {
		    st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int shortcut =  Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, shortcut)); // 유향 그래프
		}
		
		dist = new int[10001];
		for (int i=0; i< dist.length; i++) {
		    dist[i] = i;
		}

        dijkstra(0);
        System.out.println(dist[d]);
	}
}