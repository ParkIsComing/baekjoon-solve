import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
   static int n, m;
   static char[][] map;
   static int count = 0;
   static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
   static boolean[][] visited;

   // 벽으로는 이동 불가
   // 사람이 있거나 빈공간으로 이동
   static void bfs(Point doyeon) {
       Queue<Point> q = new LinkedList<>();
       q.offer(doyeon);
       visited[doyeon.x][doyeon.y] = true; // Mark the starting point as visited

       while (!q.isEmpty()) {
           Point p = q.poll();

           for (int[] d : direction) {
               int nx = d[0] + p.x;
               int ny = d[1] + p.y;

               if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                   continue;
               }

               // Check if it's an unvisited empty space or person ('O' or 'P')
               if (!visited[nx][ny] && (map[nx][ny] == 'O' || map[nx][ny] == 'P')) {
                   q.offer(new Point(nx, ny));
                   visited[nx][ny] = true;

                   // Count the people ('P')
                   if (map[nx][ny] == 'P') {
                       count++;
                   }
               }
           }
       }
   }

   public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       map = new char[n][m];
       visited = new boolean[n][m];
       Point doyeon = null;

       for (int i = 0; i < n; i++) {
           String line = br.readLine();
           for (int j = 0; j < m; j++) {
               map[i][j] = line.charAt(j);

               // 도연 위치
               if (map[i][j] == 'I') {
                   doyeon = new Point(i, j);
               }
           }
       }

       if (doyeon != null) {
           bfs(doyeon);
       }
       
       if (count == 0) {
           System.out.println("TT");
       } else {
           System.out.println(count);
       }
       
   }
}
