import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Edge {
    int start;
    int end;
    int cost;
    
    Edge (int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
// 단방향 가중치그래프
// 음의 간선 ㅇ

public class Main {
    static int N, M;
    static ArrayList<Edge> graph = new ArrayList<>();
    static long[] dist = new long[501];
    static final int INF = 60000000;
    
    
    static String findShortestTime(int base) {
        Arrays.fill(dist, INF);
        dist[base] = 0;
        
        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M; j++) {
                Edge e = graph.get(j);
                if (dist[e.start] != INF && dist[e.start] + e.cost < dist[e.end]) {
                    dist[e.end] = dist[e.start] + e.cost;                                                                            
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        // 음의 사이클 있으면 -1 출력
        for (int i=0; i<M; i++) {
            Edge e = graph.get(i);
            if (dist[e.start] != INF && dist[e.start] + e.cost < dist[e.end]) {
                sb.append(-1);
                return sb.toString();
            }
        }
        
        
        for (int i=2; i<=N; i++) {
            if (dist[i] == INF) {
                sb.append(-1);
            } else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 노드
        M = Integer.parseInt(st.nextToken()); // 간선
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph.add(new Edge(a, b, cost));
        }
        
        System.out.println(findShortestTime(1));
    }
}