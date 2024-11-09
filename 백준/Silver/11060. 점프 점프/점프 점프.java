import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                        
        
        if (n == 1) {
            System.out.println(0);
            return;
        }
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
    
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;

            for (int j = 1; j <= a[i]; j++) { 
                if (i + j < dp.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
    
        if (dp[n - 1] < Integer.MAX_VALUE) {
            System.out.println(dp[n - 1]);
        } else {
            System.out.println(-1);
        }
    }
}