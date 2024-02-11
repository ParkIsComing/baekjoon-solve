import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int n,m;
    public static int[][] map;
    public static int[][] distance;
    public static int[][] directions = {{-1, 0}, {1, 0}, {0,-1}, {0,1}};
    
    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        distance[x][y] = 0;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] d : directions) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 1 && distance[nx][ny] == -1) {
                    q.offer(new Point(nx, ny));
                    distance[nx][ny] = distance[p.x][p.y] + 1;
                }
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      map = new int[n][m];
      distance = new int[n][m];
      for (int[] d : distance) {
          Arrays.fill(d, -1);
      }
      
      
      int x = 0;
      int y = 0;
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<m; j++) {
              map[i][j] = Integer.parseInt(st.nextToken());
              if (map[i][j] == 2) { // 시작 지점
                  x = i;
                  y = j;
              } else if (map[i][j] == 0) { // 벽은 거리 0
                  distance[i][j] = 0;
              }
          }
      }
      
      bfs(x,y);
      
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
              sb.append(distance[i][j]).append(" ");
          }
          sb.append("\n");
      }
      
      System.out.println(sb);
    }
}