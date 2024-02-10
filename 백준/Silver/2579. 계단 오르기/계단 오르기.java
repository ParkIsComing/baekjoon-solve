import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int n;
    
    public static int solution(int[] steps) {
        int[] dp = new int[n+1];
        
        dp[1] = steps[1];
        
        // n==1일 수 있음
        if (n>=2) {
            dp[2] = dp[1] + steps[2];    
        }
        
        // bottom-up
        for (int i=3; i<=n; i++) {
            // n-1의 메모이제이션 값은 안 쓴다. (이전에 뭘 밟았는지알 수 있는 정보가 없음)
            dp[i] = Math.max(dp[i-2] + steps[i], dp[i-3] + steps[i-1] + steps[i]);
            
        }
        
        return dp[n];
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      int[] steps = new int[n+1];
      for (int i=1; i<=n; i++) {
          steps[i] = Integer.parseInt(br.readLine());
      }
      
      System.out.println(solution(steps));
    }
}