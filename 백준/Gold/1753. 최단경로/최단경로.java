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
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    public static String dijkstra() {
        boolean visited[] = new boolean[v+1];
        int[] dist = new int[v+1];
        int INF = Integer.MAX_VALUE;
        
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        q.offer(new Node(start, 0));
        
        while (!q.isEmpty()) {
            int n = q.poll().idx;
            
            if (visited[n]) {
                continue;
            }
            visited[n] = true;
            
            for (Node next : graph.get(n)) {
                if (dist[next.idx] > dist[n] + next.cost) {
                    dist[next.idx] = dist[n] + next.cost;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=v; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }
        
        return sb.toString();
    } 
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        for (int i=0; i<v+1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, length));
        }
        
        System.out.println(dijkstra());
    }
}