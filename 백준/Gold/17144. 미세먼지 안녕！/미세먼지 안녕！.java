import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int[][] map;
    public static int R,C,T;
    public static int top, bottom;
    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    // 1. 미세먼지 확산
    public static void diffuse() {
        int[][] diffusion = new int[R][C]; // 확산량
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    int count = 0;
                    for (int[] d : directions) {
                        int nx = i + d[0];
                        int ny = j + d[1];
                        if (0<=nx && nx<R && 0<=ny && ny<C && map[nx][ny] != -1) {
                            count++;
                            diffusion[nx][ny] += map[i][j] / 5; 
                        }
                    }
                    map[i][j] -= (map[i][j] / 5) * count;
                }
            }
            
        }
        
        // diffusion 값 합산
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                map[i][j] += diffusion[i][j];
            }
        }
    }
    
    // 2. 공기청정기 작동 (위쪽 바람 : 동북서남)
    public static void operateUp() {
        int x = top;
        int y = 0;
        int preValue = 0;
        
        while (true) {
            if (x == top && y != C-1) { // 동쪽으로 이동
                y++;    
            } else if (0 < x && x <= top && y == C-1) { // 북쪽으로 이동
                x--;
            } else if (x == 0 && y!=0) { // 서쪽
                y--;
            } else if (0 <= x && x < top && y == 0){ // 남쪽
                x++;
            
            }
            int tmp = map[x][y];
            map[x][y] = preValue;
            preValue = tmp;
            
            // 원점
            if (x == top && y == 0) {
                map[x][y] = -1;
                break;
            }
        }
        
    }
    
    // 3. 공기청정기 작동 (아래쪽 바람 : 동남서북)
    public static void operateDown() {
        int x = bottom;
        int y = 0;
        int preValue = 0;
        
        while (true) {
            if (x == bottom && y != C-1) { // 동쪽으로 이동
                y++;    
            } else if (bottom <= x && x < R-1 && y == C-1) { // 남쪽으로 이동
                x++;
            } else if (x == R-1 && y!=0) { // 서쪽
                y--;
            } else if (bottom < x && x <= R-1 && y == 0){ // 북쪽
                x--;
            
            }
            int tmp = map[x][y];
            map[x][y] = preValue;
            preValue = tmp;
            
            // 원점
            if (x == bottom && y == 0) {
                map[x][y] = -1;
                break;
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken()); // 행
      C = Integer.parseInt(st.nextToken()); // 열
      T = Integer.parseInt(st.nextToken()); // T초 후
      map = new int[R][C];

      for(int i=0; i<R; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<C; j++) {
              map[i][j] = Integer.parseInt(st.nextToken());
          }
      }
        
      for (int i=0; i<R; i++) {// 공기청정기 위치 업데이트
          if (map[i][0] == -1) {
              top = i;
              bottom = i+1;
              break;
          }
      }      
        
      for (int i=0; i<T; i++) {
          diffuse();
          operateUp();
          operateDown();
      }
      
      int sum = 0;
      for (int i=0; i<R; i++) {
          for (int j=0;j<C; j++) {
              if (map[i][j] > 0) { //0이면 미세먼지x, -1이면 공기청정기
                  sum += map[i][j];
              }
          }
      }
        
      System.out.println(sum);

    }
}