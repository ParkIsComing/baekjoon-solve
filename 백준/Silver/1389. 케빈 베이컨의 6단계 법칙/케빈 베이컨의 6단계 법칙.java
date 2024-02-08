import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int m, n, v;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
    static int[] d;
    static StringBuilder sb = new StringBuilder();
    
    public static int bfs(int node) {

        Queue<Integer> q = new LinkedList<>();
        q.offer(node);

        while (!q.isEmpty()) {
            node = q.poll();
            for (int i : edges.get(node)) {
                if (d[i] == 0) {
                    d[i] = d[node] +1;
                    q.offer(i);
                }
            }
        }
        
        return Arrays.stream(d).sum();
    }
    
    public static void main(String args[]) throws IOException {
        
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken()); // 유저 수
      m = Integer.parseInt(st.nextToken()); // 친구 관계 수
      d = new int[n+1];
      int answer = 0;
      
      for (int i=0; i<n+1; i++) {
          edges.add(new ArrayList<Integer>());
      }
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int x = Integer.parseInt(st.nextToken());
          int y = Integer.parseInt(st.nextToken());
          edges.get(x).add(y); // 양방향
          edges.get(y).add(x);
          
      }
      
      int minValue = Integer.MAX_VALUE;
      for (int i=1; i<=n; i++) {
          Arrays.fill(d, 0);
          int tmp = bfs(i);
          if (minValue > tmp) {
              minValue = tmp;
              answer = i;
          }
      }
      
      System.out.println(answer);
    }
    
}