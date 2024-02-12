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
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
 
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
 
        map[top][1] = 0;
        
    }
    
    // 3. 공기청정기 작동 (아래쪽 바람 : 동남서북)
    public static void operateDown() {
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
 
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
 
        map[bottom][1] = 0;
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