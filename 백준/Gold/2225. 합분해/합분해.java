import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int n, k;

    public static void main(String args[]) throws IOException {
       Scanner sc = new Scanner(System.in);
       n = sc.nextInt();
       k = sc.nextInt();
       
       long[][] dp = new long[k+1][n+1];
       
       for (int i=0; i<=n; i++) {
           dp[1][i] = 1;
       }
       
       for (int i=2; i<=k; i++) { // 개수
           for (int j=0; j<=n; j++) { // 합
               for (int x=0; x<=j; x++) {
                   dp[i][j] = (dp[i][j] + dp[i-1][x]) % 1000000000;
               }
           }
       }
       
       
       System.out.println(dp[k][n]);
   }
}