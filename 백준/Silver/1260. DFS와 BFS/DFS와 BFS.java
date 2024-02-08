import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int m, n, v;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    public static void dfs(int node) {
        visited[node] = true; // 방문 처리
        sb.append(node + " ");
        for (int x : edges.get(node)) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
    
    public static void bfs(int start){
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            start = q.poll();
            sb.append(start + " ");
            
            for (int x : edges.get(start)) {
                if (!visited[x]) {
                    q.add(x);
                    visited[x] = true;
                } 
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      v = Integer.parseInt(st.nextToken());
      visited = new boolean[n+1];
      
      for (int i=0; i<n+1; i++) {
          edges.add(new ArrayList<Integer>());
      }
      
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int x = Integer.parseInt(st.nextToken());
          int y = Integer.parseInt(st.nextToken());
          edges.get(x).add(y);
          edges.get(y).add(x);
      }
      
      for (ArrayList<Integer> sublist: edges) {
          Collections.sort(sublist); // 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
      }
      
      Arrays.fill(visited, false);
      dfs(v);
      sb.append("\n");
      
      Arrays.fill(visited, false);
      bfs(v);
      
      System.out.println(sb);
    }
    
}