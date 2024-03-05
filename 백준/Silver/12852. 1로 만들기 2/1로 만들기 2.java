import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static List<Integer> list = new ArrayList<>();
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        String[] path = new String[n+1]; 
        path[1] = "1";
        
        for (int i=2; i<=n; i++) {
            int tmp = 0;
            dp[i] = dp[i-1] + 1;
            tmp = i-1;
            if (i%3 == 0 && dp[i/3] + 1 < dp[i]) {
                dp[i] = dp[i/3] + 1;
                tmp = i/3;
            }
            if (i%2 == 0 && dp[i/2] +1 < dp[i]) {
                dp[i] = dp[i/2] + 1;
                tmp = i/2;
            }
            
            path[i] = i + " " + path[tmp];
        }
        
        // solution(n);
        System.out.println(dp[n]);
        System.out.println(path[n]);

    }
}