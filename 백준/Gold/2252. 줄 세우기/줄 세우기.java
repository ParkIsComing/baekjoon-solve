import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static int n,m;
    
    static List<Integer> makeLine(int[] inputCount) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> line = new ArrayList<>();
        
        for (int i=1; i<=n; i++) {
          if (inputCount[i] == 0) {
              q.offer(i);
          }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            line.add(cur);
            
            for (int next: graph.get(cur)) {
                inputCount[next] -= 1;
                if (inputCount[next] == 0) {
                    q.offer(next);
                }
            }
        }
        
        return line;
    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      for (int i=0; i<=n; i++) {
          graph.add(new ArrayList<Integer>());
      }
      int[] inputCount = new int[n+1];
      
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          graph.get(a).add(b);
          inputCount[b] += 1;
      }
      
      
      List<Integer> result = makeLine(inputCount);
      StringBuilder sb = new StringBuilder();
      for (int i : result) {
          sb.append(i + " ");
      }
      
      System.out.println(sb);
      
    }
}
    
    