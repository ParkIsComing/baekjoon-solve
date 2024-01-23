import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    
    public static int solution(int[][] arr) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        
        for (int i=1; i<=n; i++) {
            int count = 0;
            for (int j=1; j<=n; j++) {
              for (int k=1; k<=5; k++) {
                  if (arr[i][k] == arr[j][k]) {
                     count++;
                     break;
                  }
              }
          }
          if (max < count) {
              max = count;
              answer = i;
          }
        }
        
        
        return answer;
    }

    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      
      int[][] arr = new int[n+1][6];
      for (int i=1; i<n+1; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for (int j=1; j<6; j++) {
              arr[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      System.out.println(solution(arr));
    }
}