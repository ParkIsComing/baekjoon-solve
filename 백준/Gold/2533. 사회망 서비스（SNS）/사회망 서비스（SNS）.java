import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] dp; // dp[n][0] : 얼리어답터 , dp[n][1] : not 얼리어답터
    static List<Integer>[] tree;
    static boolean[] visited;
    
    static void find(int node) {
        visited[node] = true;
        dp[node][0] = 1; // 방문 및 초기화(본인이 얼리어답터인 경우)
        for (int n : tree[node]) {
            if (!visited[n]) {
                find(n);
                dp[node][0] += Math.min(dp[n][0], dp[n][1]); // 본인 얼리어답터이면 자식은 얼리어답터이든말든 상관없음.
                dp[node][1] += dp[n][0]; // 본인이 아니면 자식은 얼리어답터여야 함.
            }
        }
    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      tree = new ArrayList[n+1];
      visited = new boolean[n+1];
      dp = new int[n+1][2];
      
      for (int i=0; i<=n; i++) {
          tree[i] = new ArrayList<Integer>();
      }
      
      StringTokenizer st;
      for (int i=1; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          tree[a].add(b);
          tree[b].add(a); // 필요한가?
      }
      
      find(1);
      System.out.println(Math.min(dp[1][0], dp[1][1]));
      
    }
}
    
    