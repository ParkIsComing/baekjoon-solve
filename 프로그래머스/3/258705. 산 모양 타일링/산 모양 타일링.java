import java.util.*;

class Solution {
    private static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int bottomLen = 2*n + 1;
        int[] dp = new int[bottomLen+1];
        dp[1] = 1;
        dp[0] = 1;
        
        for (int i=2; i<=bottomLen; i++) {
            if (i % 2 == 0 && tops[(i/2)-1] == 1) {
                dp[i] = (dp[i-1] * 2 + dp[i-2]) % MOD;
            } else {
                dp [i] = (dp[i-1] + dp[i-2]) % MOD;
            }
            
        }
        
        return dp[bottomLen] % MOD;
    }
}
