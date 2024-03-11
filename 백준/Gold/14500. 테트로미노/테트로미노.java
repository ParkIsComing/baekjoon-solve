import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static int[][] board;
    static boolean[][] visited;
    static int answer = Integer.MIN_VALUE;
    static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }
        
        System.out.println(answer);
 
    }
    
    public static void dfs(int x, int y, int depth, int sum) {
        //depth 4면 종료
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }
        
        for (int[] d : direction) {
            int nx = x + d[0];
            int ny = y + d[1];
            
            if (nx < 0 || nx >=n || ny <0 || ny>=m || visited[nx][ny] == true) {
                continue;
            }
            
            visited[nx][ny] = true;
            // depth가 2일 때는 한 점에서 이어지는 두 가지 방향으로 만들 수 있음
            if (depth == 2) {
                dfs(x, y, depth+1, sum + board[nx][ny]); 
            }
            dfs(nx, ny, depth+1, sum+board[nx][ny]);
            visited[nx][ny] = false;
        }
        
    }
}