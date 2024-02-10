import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    static int[][] paper;
    static int n;
    static int blue = 0, white = 0;

    public static void find(int r, int c, int size) {
        if (checkFull(r,c,size)) {
            if (paper[r][c] == 1) {
                blue += 1;
            }  else {
                white += 1;
            }
            return;
        }
        
        
        // 제 1사분면
        find(r, c + size/2, size/2);
        // 제 2사분면
        find(r, c, size/2);
        // 제 3사분면
        find(r + size/2, c, size/2);
        // 제 4사분면
        find(r + size/2, c + size/2, size/2);

    } 
    
    public static boolean checkFull(int r, int c, int size) {
        int x = paper[r][c];
        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (paper[i][j] != x) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine()); // 한 변의 길이 (2^k)
      paper = new int[n][n];
      
      StringTokenizer st;
      // 종이 정보 입력 받기
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<n; j++) {
              paper[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      find(0, 0, n);
      System.out.println(white);
      System.out.println(blue);
    }
}