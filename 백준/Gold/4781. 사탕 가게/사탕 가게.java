import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
  static int n, m;
  static int[][] candy;
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    while(true) {
        st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사탕 종류
        float tmpM = Float.parseFloat(st.nextToken());
        m = (int)(tmpM*100 + 0.5);// 가진 돈(제한)
        
        if (n==0 && m == 0.00) {
            return;
        }
        
        candy = new int[n+1][2];
        
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            candy[i][0] = Integer.parseInt(st.nextToken());//칼로리
            float tmp = Float.parseFloat(st.nextToken());
            candy[i][1] = (int)(tmp*100 + 0.5);// 가격
        }
        
        int[] dp = new int[m+1];
        
        Arrays.sort(candy,(a,b)-> a[1]-b[1]);
        
        // 같은 걸 여러개 담을 수 있음
        for (int i=1; i<=n; i++) {
            for (int j=0; j<=m; j++) {
                if (j >= candy[i][1]) { // 사탕 담을 수 있는 경우
                    dp[j] = Math.max(dp[j-candy[i][1]] + candy[i][0], dp[j]);
                } 
            }
        }
        
        System.out.println(dp[m]);
    }
  }
}