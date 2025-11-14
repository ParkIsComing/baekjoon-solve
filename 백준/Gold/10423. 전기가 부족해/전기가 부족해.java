import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    // 비용 올림차순 정렬
    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}


public class Main {
    static int n,m,k;
    static int[] parent;
    static ArrayList<Edge> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 발전소는 -1, 이걸 루트로.
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= k; i++) {
            int idx = Integer.parseInt(st.nextToken());
            parent[idx] = -1;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start, end, cost));
        }

        Collections.sort(graph);

    }

    static void solution() {
        int cost = 0;

        for (int i=0; i < graph.size(); i++) {
            Edge e = graph.get(i);

            if (find(e.start) == find(e.end)) continue;

            cost += e.cost;
            union(e.start, e.end);
            // 모든 도시가 발전소와 연결되면(모든 도시의 부모가 -1이면) 종료
            if (checkAllConnected()) break;

        }

        System.out.println(cost);
    }

    static boolean checkAllConnected() {
        for (int i=1; i <= n; i++) {
            if (parent[i] != -1) return false;
        }
        return true;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 이미 발전소와 연결된 경우(발전소끼리 연결되면 안됨)
        if (x == -1 && y == -1) return;

        // 루트노드를 발전소(-1)로
        if (x == -1) {
            parent[y] = -1;
            return;
        }

        if (y == -1) {
            parent[x] = -1;
            return;
        }

        parent[y] = x;
    }

    static int find(int x) {
        if (parent[x] == -1) {
            return -1;
        }
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]); // 경로 압축
    }

}


