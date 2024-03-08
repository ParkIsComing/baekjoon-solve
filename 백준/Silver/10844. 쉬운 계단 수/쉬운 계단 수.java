import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, count = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 시작 0 안됨 길이 n
        n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][10];
        
        dp[1][0] = 0;
        for (int i=1; i<=9; i++) {
            dp[1][i] = 1;
        }
        
        for (int i=2; i<=n; i++) {
            for (int j=0; j<=9; j++) {
                if (j==0) {
                    dp[i][j] = dp[i-1][1];
                } else if (j==9) {
                    dp[i][j] = dp[i-1][8];
                } else {
                    dp[i][j] = dp[i-1][j-1] % 1000000000 + dp[i-1][j+1] % 1000000000;
                }
            }
        }
        
        long count = 0;
        for (int i=0; i<=9; i++) {
            count += dp[n][i] % 1000000000;
        }
        System.out.println(count % 1000000000);
    }
    
    
}