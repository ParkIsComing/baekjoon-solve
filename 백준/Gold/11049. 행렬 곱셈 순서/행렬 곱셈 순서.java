import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       int[][] matrix = new int[n][2];
       int[][] dp = new int[n][n];
       
       for (int i=0; i<n; i++) {
           StringTokenizer st = new StringTokenizer(br.readLine());
           matrix[i][0] = Integer.parseInt(st.nextToken());
           matrix[i][1] = Integer.parseInt(st.nextToken());
       }
       
       for (int diagonal = 1; diagonal <n; diagonal++) {
           for (int i=0; i< n-diagonal; i++) {
               int j = i + diagonal;
               if (diagonal == 1) { // 인접한 두 행렬의 곱
                   dp[i][j] = matrix[i][0] * matrix[j][0] * matrix[j][1];
                   continue;
               }
               dp[i][j] = Integer.MAX_VALUE;
               for (int k=i; k<j; k++) {
                   dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
               }
           }
       }
       
       System.out.println(dp[0][n-1]);
   }
}