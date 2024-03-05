import java.util.*;

class Solution {
    public int solution(int n) {
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;

        for (int i=4; i<=n; i++) {
            //2개씩 잡으면 3가지 가능
            dp[i] = (dp[i-2]*3)%1000000007;

            // 특이 케이스(가로 길이: 4,6,8,..)
            for (int j=i-4; j>=0; j=j-2) {
                dp[i] += (dp[j]*2)%1000000007;
            }
        }
        System.out.println(Arrays.toString(dp));
        return (int)(dp[n]%1000000007);
    }
}
