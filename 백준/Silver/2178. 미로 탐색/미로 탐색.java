import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int[][] graph;
    public static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public static int n,m;
    
    public static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] d : directions) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];
                if (0<=nx && nx<n && 0<=ny && ny<m) {
                    if (graph[nx][ny] == 1) {
                        graph[nx][ny] = graph[p.x][p.y] + 1;
                        q.offer(new Point(nx, ny));
                    }                
                }

            }
        }
        
        return graph[n-1][m-1];
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      graph = new int[n][m];
      
      for (int i =0; i<n; i++) {
          String input = br.readLine();
          for (int j=0; j<m; j++) {
              graph[i][j] = (int)(input.charAt(j) -'0');
          }
      }
      
      System.out.println(bfs(0,0));
    }
}