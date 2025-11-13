import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int next;
    int cost;

    public Node(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
}

public class Main {
    static int v,e;
    static int[][] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new int[e][3]; // start, end, cost

        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = cost;
        }

        parent = new int[v+1];
        for (int i=0; i<=v; i++) {
            parent[i] = i;
        }
        Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
    }

    static void solution() {
        long cost = 0;
        for (int[] node : graph) {
            if (find(node[0]) == find(node[1])) continue;
            cost += node[2];
            union(node[0], node[1]);
        }

        System.out.println(cost);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

}
