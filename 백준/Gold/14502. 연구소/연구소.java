import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n, m, result = 0;
    public static int[][] graph = new int[8][8];
    public static int[][] tmp = new int[8][8];
    public static int[][] directions = {{-1,0}, {1,0}, {0,-1},{0,1}};
    
    public static void spreadVirus(int x, int y) {
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (0 <=nx && nx< n && 0<=ny && ny<m) {
                if (tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 2;
                    spreadVirus(nx, ny); // 재귀적으로 바이러스 확산!!!
                }
            }
            
        }
        
    }

    public static void dfs(int count) {
        // 설치된 울타리가 3개인 경우 -> 현재 설치상태에서 바이러스 확산
        if (count == 3) {
            for (int i =0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    tmp[i][j] = graph[i][j];
                }
            }
            
            // 바이러스 확산
            for (int i =0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if(tmp[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }
            
            int sum = 0;
            // 안전영역 크기 갱신
            for (int i =0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if(tmp[i][j] == 0) {
                        sum += 1;
                    }
                }
            }
            
            result = Math.max(sum, result);
        }
            
        // 설치된 울타리가 3개 미만인 경우 -> 울타리 하나씩 세워보기
        else {
            for (int i = 0; i<n; i++) {
                for (int j = 0; j<m; j++) {
                    if (graph[i][j] == 0) {
                        graph[i][j] = 1;
                        count += 1;
                        dfs(count);
                        graph[i][j] = 0;
                        count -= 1;
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
      
      // 그래프 입력
      for (int i=0; i< n; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<m; j++){
              graph[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      dfs(0);
      System.out.println(result);
    }
}