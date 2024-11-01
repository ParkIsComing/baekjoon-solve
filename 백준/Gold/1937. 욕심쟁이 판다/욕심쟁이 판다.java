import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
    static int maxVal = 1;
    
    // 이미 방문한 곳에서부터는 다시 경로 탐색을 하지 않도록 함
    static int findRoute(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];  // 이미 경로 탐색을 마친 경로
        
        dp[r][c] = 1;
        
        for (int[] d : direction) {
            int nr = r + d[0];
            int nc = c + d[1];
            
            if (0>nr || nr>=n || 0>nc || nc>=n) {
                continue;
            }
            
            if (map[nr][nc] > map[r][c]) { // 이동 가능 조건
                dp[r][c] = Math.max(dp[r][c], findRoute(nr, nc) + 1);
                
            }
            
        }
        
        return dp[r][c];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        
        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 위치별 대나무의 양은 자연수
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (dp[i][j] == 0) { // 미방문인 경우
                    maxVal = Math.max(maxVal, findRoute(i, j));
                }
            }
        }
        
        // for (int i=0; i<n; i++) {
        //     for (int j=0; j<n; j++) {
        //         System.out.print(dp[i][j]);
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }
        
        
        System.out.println(maxVal);
        
    }
}
