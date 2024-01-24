import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    public static int n;
    public static char[][] graph = new char[6][6];
    public static ArrayList<Point> teachers = new ArrayList<>();
    public static int[][] directions = {{1,0}, {-1,0}, {0,-1}, {0,1}};

    
    public static void dfs(int count) {
        if (count == 3) {// 벽 설치 완료
            bfs();
            return;
            
        } else { // 벽 설치 미완료
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) { // 재귀적으로 설치해보기
                    if (graph[i][j] == 'X'){ // 설치 가능 조건
                        graph[i][j] = 'O';
                        dfs(count+1);
                        graph[i][j] = 'X';
                    }
                }
            }
        }
    }
    
    public static void bfs() {
        Queue<int[]>  q = new LinkedList<>();
        boolean[][] check = new boolean[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (graph[i][j] == 'T') {
                    q.offer(new int[]{i,j});
                }
            }
        }
        
        while(!q.isEmpty()) {
            int[] p = q.poll();
            for (int[] d : directions) {
                int nx = p[0] + d[0];
                int ny = p[1] + d[1];
                while (nx>=0 && ny>=0 && nx<n && ny<n) { // 선생님 위치 중심십자가 확인
                    if(graph[nx][ny] != 'O') { // 들킨 경우
                        check[nx][ny] = true;
                        nx += d[0];
                        ny += d[1];
                    } else {
                        break;
                    }
                }
            }
        }
        
        // 가능한 경우 있으면 바로 출력 (모든 벽 세우는 경우에 가능할 필요x)
        if(checkStudent(check)) { 
            System.out.println("YES");
            System.exit(0);
        }
    }
    
    public static boolean checkStudent(boolean[][] check) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (graph[i][j] == 'S') {
                    if(check[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      
      StringTokenizer st;
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<n; j++) {
              graph[i][j] = st.nextToken().charAt(0);
            //   if (graph[i][j] == 'S') {
            //       teachers.add(new Point(i,j));
            //   }
          }
      }
      
      dfs(0);
      System.out.println("NO");

    }
}