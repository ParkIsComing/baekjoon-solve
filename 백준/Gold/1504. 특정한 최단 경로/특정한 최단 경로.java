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

// 양방향 가중치그래프
//
public class Main {
    static int N, E;
    static ArrayList<Node>[] graph = new ArrayList[801];
    static final int INF = 200000000; // 최대 간선 수 * 최대 가중치
    
    
    // 두 개 정점 지나는 최단 경로의 길이를 구하기
    static int dijkstra(int start, int dest) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        q.offer(new Node(start, 0));
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (dist[cur.idx] < cur.cost) {
                continue;
            }
            
            for (Node next: graph[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        return dist[dest];
    }
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        // 경로를 쪼개서 생각한다.
        // case1) 1 -> v1 -> v2 -> N
        int dist1toV1 = dijkstra(1,v1);
        int distV1toV2 = dijkstra(v1,v2);
        int distV2toN = dijkstra(v2,N);
        int result1 = dist1toV1 + distV1toV2 + distV2toN;
        
        // case2) 1 -> v2 -> v1 -> N
        int dist1toV2 = dijkstra(1,v2);
        int distV2toV1 = dijkstra(v2,v1);
        int distV1toN = dijkstra(v1,N);
        int result2 = dist1toV2 + distV2toV1+ distV1toN;
        
        int answer = 0;
        if (result1 >= INF && result2 >= INF) {
            answer = -1;
        } else {
            answer = Math.min(result1, result2);
        }
        
        System.out.println(answer);
        
    }
}