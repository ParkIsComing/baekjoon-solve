import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static int[][] apps;
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken()); // 앱 개수
      m = Integer.parseInt(st.nextToken()); // 확보할 메모리
      apps = new int[n+1][2];
      
      st = new StringTokenizer(br.readLine());
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      int sum = 0;
      for (int i=1; i<=n; i++) {
          apps[i][0] = Integer.parseInt(st.nextToken()); // 메모리 (근데 넘겨도 됨)
          apps[i][1] = Integer.parseInt(st2.nextToken());// 비용
          sum += apps[i][1];
      }
      
      int[][] dp = new int[n+1][sum+1];
      for (int i=1; i<=n; i++) {
          for (int j=0; j<=sum; j++) {
              if (j-apps[i][1] >= 0) { // 종료하거나 안 하거나
                  dp[i][j] = Math.max(dp[i-1][j-apps[i][1]]+ apps[i][0], dp[i][j]);
              }
              dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
          }
      }
      
      // 최소 비용 만족해야
      for (int j=0; j<=sum; j++) {
        if (dp[n][j] >= m) {
            System.out.println(j);
            break;
        }
          
      }
      
    }
}
    
    