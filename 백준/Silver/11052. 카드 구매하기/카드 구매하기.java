import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] card;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        card = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=i; j++) {
                dp[i] = Math.max(dp[i], dp[i-j] + card[j]); 
            }
        }
          
        System.out.println(dp[n]);
    }
}