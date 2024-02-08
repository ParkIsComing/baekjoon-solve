import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int m, n;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] directions = {{-1,0}, {1,0}, {0,-1},{0,1}};
    
    public static void dfs(int x, int y) {
        visited[x][y] = true; // 방문 처리
        for (int[] d: directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            
            if (0<= nx && nx < m && 0 <= ny && ny <n) {
                if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(br.readLine()); // 테스트케이스 수
      StringTokenizer st;
      
      for (int i=0; i<t; i++) {
        int count = 0;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로
        int k = Integer.parseInt(st.nextToken()); // 배추 위치 개수
        graph = new int[m][n];
        visited = new boolean[m][n];

        for (int j=0; j<k; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
        }
        
        for (int j =0; j<m; j++) {
            for (int l=0; l<n; l++) {
                if (graph[j][l] == 1 && !visited[j][l]) { // 배추가 있고 아직 방문하지 않은 장소 
                    dfs(j,l);
                    count++;
                }
            }
        }
        System.out.println(count);
      }
        
    }
    
}