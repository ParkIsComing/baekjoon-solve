import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 물품 수
    int K = Integer.parseInt(st.nextToken()); //최대 무게

    int[][] items = new int[N+1][2];
      for (int i=1; i<=N; i++) {
          st = new StringTokenizer(br.readLine());
          items[i][0] = Integer.parseInt(st.nextToken()); // 무게
          items[i][1] = Integer.parseInt(st.nextToken()); // 가치
      }
    
      Arrays.sort(items, (a,b) -> (a[0] - b[0])); // 무게로 정렬 
    
      int[][] dp = new int[N+1][K+1];
    
      for(int i=1; i<=N; i++) {
          for (int j=0; j<=K; j++) {
              if (j >= items[i][0]) { // 넣을 수 있음 (가치 더 큰쪽으로 저장)
                  dp[i][j] = Math.max(dp[i-1][j-items[i][0]] + items[i][1], dp[i-1][j]); // 넣거나 안 넣거나 
              } else { // 못 넣는 경우
                  dp[i][j] = dp[i-1][j];
              }
          }
      }
      
      System.out.println(dp[N][K]);

  }
}