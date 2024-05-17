import java.io.*;
import java.util.*;

//오는 길 != 가는 길일수 있음

class Node {
    int idx;
    int cost;
    
    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N,M,X;
    static ArrayList<Node>[] map;
    
    static int dijkstra (int start, int dest) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        q.offer(new Node(start, 0));
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if (dist[cur.idx] < cur.cost) { // 이미 우선순위큐로 업데이트 된 경우
                continue;
            }
            
            for (Node next: map[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        return dist[dest];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// 학생 수 (=마을 수)
        M = Integer.parseInt(st.nextToken());// 단방향 도로 개수
        X = Integer.parseInt(st.nextToken()); // 파티를 여는 마을
        
        map = new ArrayList[1001];
        for (int i=1; i<=N; i++) {
            map[i] = new ArrayList<>();
        }
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            map[a].add(new Node(b, time)); // 단방향 가중치 그래프
        }
        
        int maxTime = Integer.MIN_VALUE;
        for (int i=1; i<=N; i++) {
            int timeToX = dijkstra(i, X);
            int timeToHome = dijkstra(X, i);
            maxTime = Math.max(maxTime, timeToX+timeToHome);
        }
        
        System.out.println(maxTime);

    }
}