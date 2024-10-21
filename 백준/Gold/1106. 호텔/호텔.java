import java.util.*;


public class Main {
    static int c,n;
    
    public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      c = Integer.parseInt(sc.next()); // 원하는 고객수
      n = Integer.parseInt(sc.next());// 도시개수
      
      int[][] city = new int[n][2]; // 비용 고객
      int[] dp = new int[c+101];
       Arrays.fill(dp,987654321);
       dp[0] = 0;

      for (int i=0; i<n; i++) {
          int cost = sc.nextInt();
          int customer = sc.nextInt();
          for (int j=customer; j<c+101; j++) {
              
              dp[j] = Math.min(dp[j-customer] + cost, dp[j]);
          }
      }
      
      int result = Integer.MAX_VALUE;
      for (int i=c; i<c+101; i++) {
          result = Math.min(dp[i], result);
      }
      
      System.out.println(result);
      
    }
}
    
    