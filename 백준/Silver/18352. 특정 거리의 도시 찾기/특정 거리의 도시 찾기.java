import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n, m, k, x;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    public static int[] d = new int[300001];
    
    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(start);
        d[start] = 0;
        
        while(!q.isEmpty()) {
            int x = q.poll();
            for (int i : graph.get(x)) {
                if (d[i] == -1) {
                    d[i] = d[x] + 1;
                    q.offer(i);
                }
            }
        }
        
        boolean check = false;
        for (int i = 1; i <= n; i++) {
            if (d[i] == k) {
                System.out.println(i);
                check = true;
            }
        }

        if (!check) {
            System.out.println(-1);
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      x = Integer.parseInt(st.nextToken());
      
      // 그래프 초기화
      for (int i=0; i<=n; i++) {
          graph.add(new ArrayList<Integer>());
          d[i] = -1; // - 1이면 미방문
      }
      
      // 도로 정보 입력
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          graph.get(a).add(b);
      }
      
      bfs(x);
    }
}