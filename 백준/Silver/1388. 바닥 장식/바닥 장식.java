import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static char[][] board;
    static boolean[][] visited;
    static int answer = 0;
    static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n][m];
        
        for (int i=0; i<n; i++) {
            String input = br.readLine();
            for (int j=0; j<m; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    answer++;
                }
            }
        }
        
        System.out.println(answer);
 
    }
    
    public static void dfs(int x, int y) {
        visited[x][y] = true;
        int start, end;
        if (board[x][y] == '-') { // 좌우 탐색
            start = 2;
            end = 3;
        } else {
            start = 0;
            end = 1;
        }
        
        for (int i=start; i<=end; i++) {
            int nx = x + direction[i][0];
            int ny = y + direction[i][1];
            if (nx < 0 || nx >=n || ny <0 || ny>=m || visited[nx][ny] == true || board[x][y] != board[nx][ny]) {
                continue;
            }
            
            dfs(nx, ny);
        }
        
    }
}