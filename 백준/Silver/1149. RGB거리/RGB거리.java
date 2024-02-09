import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      dp = new int[3][n];
      arr = new int[3][n];
      
      StringTokenizer st;
      for (int j=0; j<n; j++) {
          st = new StringTokenizer(br.readLine());
          for (int i=0; i<3; i++) {
              arr[i][j] = Integer.parseInt(st.nextToken()); // 행은 빨초파 순서
              
          }
      }
            
      for (int j=1; j<n; j++) { // 제일 왼쪽열은 그대로
          for (int i=0; i<3; i++) {
              int red, green, blue;
              
              if (i == 0) { // 빨강 칠할 차례
                  red = Integer.MAX_VALUE;
              } else {
                  red = arr[0][j-1];
              }
              
              if (i == 1) {
                  green = Integer.MAX_VALUE;
              } else {
                  green = arr[1][j-1];
              }
              
              if (i == 2) {
                  blue = Integer.MAX_VALUE;
              } else {
                  blue = arr[2][j-1];
              }
              
              arr[i][j] = arr[i][j] + Math.min(red, Math.min(green, blue));
          }
      }
      
      int result = arr[0][n-1];
      for (int i=1; i<3; i++) {
          result = Math.min(result , arr[i][n-1]);
      }
      
      System.out.println(result);
    }
}