import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N개 지점 / M개의 도로 / W개의 웜홀
// 도로는 양방향, 웜홀은 단방향
// 웜홀은 시간이 뒤로 감(-cost)
class Node {
    int idx;
    int cost;
    
    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "idx:" + idx + "cost: " + cost;
    }
}

public class Main {
    static int N,M,W;
    static int TC;
    static ArrayList<Node>[] graph;
    
    // 넘치면 수정!!!
    static final int INF = Integer.MAX_VALUE;
    
    static boolean findRouteForAllNodes() { // 시작지점 == 종료지점
        // 거리 기록할 dist 배열 선언 초기화
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        
        // 시작점 거리 초기화
        dist[1] = 0;
        
        // (N -1)만큼 돌면서
        for (int i=1; i<N; i++) {
            for (int j=1; j<graph.length; j++) { // 모든 간선을 확인
                for (Node next : graph[j]) {
                    if (dist[next.idx] > dist[j] + next.cost) {
                        dist[next.idx] = dist[j] + next.cost;
                    }
                    
                }
            }
            
        }
        
        // N번째에 값이 갱신되면 음의 사이클 존재
        for (int j=1; j<graph.length; j++) {
            for (Node next :graph[j]) {
                if (dist[next.idx] > dist[j] + next.cost) {
                     return true; // 최단 경로가 존재하지 않는 경우
                }
            }
        }
        
        // System.out.println(Arrays.toString(dist));
        
        return false;
     }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        TC = Integer.parseInt(br.readLine());
        for (int tc=0; tc<TC; tc++) {
            
            // 1. 입력 받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList[N+1];
            
            for (int i=1; i<=N; i++) {
                graph[i] = new ArrayList<Node>();
            }
            
            int S, E, T;
            for (int i=0; i<M+W; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
            
                if (i<M) { // 도로인 경우 (양방향)
                    graph[S].add(new Node(E,T));
                    graph[E].add(new Node(S,T));
                } else { // 웜홀인 경우 (단방향)
                    graph[S].add(new Node(E, -T));
                }
            }
            
            // System.out.println(graph);
            
            boolean result = findRouteForAllNodes();
                
            // 시간이 되돌아가 있는 경우
            if (result) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}