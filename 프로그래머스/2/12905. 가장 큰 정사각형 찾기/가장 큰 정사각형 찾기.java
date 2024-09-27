import java.util.*;

class Solution
{
    public int dp(int n, int m, int[][] board) {
        int[][] dp = new int[n+1][m+1];
        int ans = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (board[i-1][j-1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        
        return ans;
    }
    public int solution(int [][]board)
    {
        int n = board.length;
        int m = board[0].length;
        int square_len = dp(n, m, board);
        return square_len*square_len;
    }
}