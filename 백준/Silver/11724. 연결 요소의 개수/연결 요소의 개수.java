import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n,m;
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    public static boolean[] visited = new boolean[1001];
    
    public static boolean bfs(int node) {
        if (visited[node] == false) { // 미방문 노드
            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            visited[node] = true;
            
            while (!q.isEmpty()) {
                int x = q.poll();
                for (int y : list.get(x)) {
                    if (visited[y] == false) {
                        q.offer(y);
                        visited[y] = true;
                    }
                }
                
            }
            return true;        
            
        }
        return false;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      for (int i=0; i<=n; i++) {
          list.add(new ArrayList<Integer>());
      }
      

      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          list.get(a).add(b); // 방향없는 그래프
          list.get(b).add(a);
      }
      int count = 0;
      for (int i=1; i<=n; i++) {
          if (bfs(i)) {
              count++;
          }
      }

      System.out.println(count);
    }
}