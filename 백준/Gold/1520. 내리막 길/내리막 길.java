import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static int[][] board, dp;
    static int[][] direction = {{-1,0},{1,0}, {0,1}, {0,-1}};
    
    static int dfs(int x, int y) {
        if (x==m-1 && y==n-1) {
            return 1;
        }
        
        // 이미 계산된 경우
        if (dp[x][y] != -1) { 
            return dp[x][y];
        }
        
        dp[x][y] = 0;
        for (int[] d: direction) {
            int dx = x + d[0];
            int dy = y + d[1];
            if (dx<0 || dx>=m || dy<0 || dy>=n) {
                continue;
            }
            
            if (board[x][y] > board[dx][dy]) {
                dp[x][y] += dfs(dx, dy);
            }
            
        }
        
        return dp[x][y];

    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      board = new int[m][n];
      dp = new int[m][n];
      
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<n; j++) {
              board[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      for (int i=0; i<m; i++) {
            Arrays.fill(dp[i], -1);
          
      }

      System.out.println(dfs(0,0));
     
    }
}