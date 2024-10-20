import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, W;
    static int[][][] dp;
    
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      // 떨어지는 시간 t
      // 최대 움직이는 횟수 w
      T = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      
      int[] plum = new int[T+1];
      for (int i=1; i<=T; i++) {
          plum[i] = Integer.parseInt(br.readLine());
      }
      
      dp = new int[1001][31][3]; // 초 움직임 위치
      
      if (plum[1] == 1) { // 처음에 자두가 1번에 떨어짐
          dp[1][0][1] = 1; // 받거나
          dp[1][1][2] = 0; // 못 받거나
      } else {
          dp[1][1][2] = 1;
          dp[1][0][1] = 0;
      }
      
      for (int i=2; i<=T; i++) {
          int cur = plum[i];
          
          if (cur == 1) { // 자두가 1로 떨어짐
             // j가 0인 경우 초기화
              dp[i][0][1] = dp[i-1][0][1] + 1;
              dp[i][0][2] = dp[i-1][0][2];
              
              for (int j=1; j<=W; j++) {
                  // 먹는 경우
                  dp[i][j][1] = Math.max(dp[i-1][j-1][2], dp[i-1][j][1]) + 1;
                  // 못 먹는 경우
                  dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i-1][j][2]);
              }
              
          } else { // 자두가 2로 떨어짐
              dp[i][0][1] = dp[i-1][0][1];
              dp[i][0][2] = dp[i-1][0][2] + 1;
              
              for (int j=1; j<=W; j++) {
                  dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i-1][j][2])+1;
                  dp[i][j][1] = Math.max(dp[i-1][j-1][2], dp[i-1][j][1]);
              }
          }
      }
      
      int answer = 0;
      for (int j=0; j<=W; j++) {
          answer = Math.max(Math.max(dp[T][j][1], dp[T][j][2]), answer );
      }
      System.out.println(answer);
      
    }
}
    
    