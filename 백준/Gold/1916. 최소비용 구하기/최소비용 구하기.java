import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

class Node {
    int idx;
    int cost;
    
    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
public class Main {
    static int n,m, start, target;
    static ArrayList<Node>[] graph;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 도시 개수
        m = Integer.parseInt(br.readLine()); // 버스 개수
        
        graph = new ArrayList[1001];
        for (int i=0; i<=1000; i++) {
            graph[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        // 출발 도시, 도착 도시, 비용
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
        }
        
        // 출발도시, 도착도시
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra());
    }
    
    public static int dijkstra() {
        int[] dist = new int[n+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        q.offer(new Node(start, 0));
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if (dist[cur.idx]  < cur.cost) {
                continue;
            }
            
            for (Node next : graph[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        return dist[target];
    }
}