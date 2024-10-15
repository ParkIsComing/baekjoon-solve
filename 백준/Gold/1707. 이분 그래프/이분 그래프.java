import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int k;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean isBipartite;
    static int W = 1;
    static int B = 2;
    static int[] colors;

    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        
        while(k-- > 0) {
            int v = sc.nextInt();
            int e = sc.nextInt();
            isBipartite = true;
            
            graph = new ArrayList<>();
            colors = new int[v+1]; //0이면 색칠(방문)하지 않은 것
            
            for (int i=0; i<=v; i++) {
                graph.add(new ArrayList<Integer>());
            }
            
            for (int i=0; i<e; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph.get(a).add(b); // 양방향 그래프
                graph.get(b).add(a);
            }
            
            for (int i=1; i<=v; i++) { // 모든 정점 돌면서 확인
                if (!isBipartite) {
                    break;
                }
                
                if(colors[i] == 0) {
                    bfs(i, W);
                }
            }
            System.out.println(isBipartite?"YES":"NO");
        }
        
        
    }
    
    static void bfs(int start, int color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = color;
        
        while(!q.isEmpty() && isBipartite) {
            int now = q.poll();
            
            for (int adj : graph.get(now)) {
                if (colors[adj] == 0) {
                    q.offer(adj);
                    colors[adj] = colors[now] == W? B: W;
                } else if (colors[now] == colors[adj]) { // 인접한 노드의 색이 같으면 이분그래프 아님
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}
    
    