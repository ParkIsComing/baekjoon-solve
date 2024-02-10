import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int n;
    static boolean[] d = new boolean[101];
    
    public static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        d[start] = true;
        
        int answer = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i : graph.get(x)) {
                if (d[i] == false) {
                    d[i] = true; // 연결 노드 방문
                    answer++;
                    q.offer(i);
                }
            }
                    
        }
        
        return answer;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine()); // 노드 수
      int m = Integer.parseInt(br.readLine());// 간선 수

      StringTokenizer st;
      
      for (int i=0; i<=n; i++) {
          graph.add(new ArrayList<Integer>());
      }
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int s = Integer.parseInt(st.nextToken());
          int e = Integer.parseInt(st.nextToken());
          
          graph.get(s).add(e);
          graph.get(e).add(s);
      }
      
      System.out.println(bfs(1));
    }
}