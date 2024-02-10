import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int solution(int num) { // 최소 m미터 위한 최대 절단기 높이
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i=4; i<=num; i++) {
            dp[i] = dp[i-1] + dp[i-2]+ dp[i-3];
        }
        
        return dp[num];
    } 
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      for (int i=0; i<n; i++) {
          int answer = solution(Integer.parseInt(br.readLine()));
          System.out.println(answer);
      }
    }
}