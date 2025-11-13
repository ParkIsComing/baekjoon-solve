import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int start;
    int end;
    double cost;


    public Edge(int start, int end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.cost, o.cost);
    }
}

public class Main {
    static int n,m;
    static int[][] nodes;
    static ArrayList<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new int[n+1][2]; // (x,y)
        edgeList = new ArrayList<>();

        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i][0] = x;
            nodes[i][1] = y;
        }

        for (int i=1; i<=n; i++) {
            for (int j=i+1; j<=n; j++) {
                double cost = Math.sqrt(Math.pow(nodes[i][0]-nodes[j][0], 2) + Math.pow(nodes[i][1]-nodes[j][1],2));
                edgeList.add(new Edge(i, j, cost));
            }
        }

        parent = new int[n+1];
        for (int i=0; i<=n; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

    }

    static void solution() {
        double costTotal = 0.0;
        for (Edge node : edgeList) {
            if (find(node.start) == find(node.end)) continue;
            union(node.start, node.end);
            costTotal += node.cost;
        }
        System.out.println(String.format("%.2f", costTotal));
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
