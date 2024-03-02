import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[n];
        dp[0] = arr[0];
        int max = arr[0];
        
        for (int i=1; i<n; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i] , arr[i]); // 연속된 값 + 현재값 또는 현재값 
            max = Math.max(max, dp[i]);
        }
        
        System.out.println(max);
    }
}