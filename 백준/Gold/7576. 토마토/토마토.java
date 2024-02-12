import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int[][] box;
    public static int n,m;
    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public static Queue<Point> q = new LinkedList<>();
    
    public static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] d: directions) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];
                if (0<=nx && nx<n && 0<=ny && ny<m && box[nx][ny] == 0) {
                    box[nx][ny] = box[p.x][p.y] + 1;
                    q.offer(new Point(nx,ny));
                }
            }        
            
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      box = new int[n][m];

      for(int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<m; j++) {
              box[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
              if (box[i][j] == 1) {
                  q.offer(new Point(i,j)); // 큐에 다 넣고 bfs
              }
          }
      }
      
      bfs();
      
      int answer = 0;
      for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
              if (box[i][j] == 0) {
                  System.out.println(-1);
                  return;
              }
              answer = Math.max(answer, box[i][j]);
          }
      }
      System.out.println(answer-1);
    }
}