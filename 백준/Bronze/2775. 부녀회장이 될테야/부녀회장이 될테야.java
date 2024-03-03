import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int cases, k, n;
    static int[][] dp = new int[15][15];
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cases = Integer.parseInt(br.readLine());
        
        for (int i=1; i<=14; i++) {
            dp[0][i] = i;
        }
        
        for (int i=1; i<=14; i++) {
            for (int j=1; j<=14; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        for (int i=0; i<cases; i++) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[k][n]);
        }
    }
    
}