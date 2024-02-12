import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[][] graph;
    public static int[][] dp;
    public static int size;
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      size = Integer.parseInt(br.readLine());
      graph = new int[size][size];
      dp = new int[size][size];
      
      StringTokenizer st;
      for (int i=0; i<size; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<i+1; j++) {
              graph[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      dp[0][0] = graph[0][0];
      for (int i=1; i<size; i++) {
          dp[i][0] = dp[i-1][0] + graph[i][0]; // 가장 왼쪽은 미리 저장
      }
      
      
      for (int i=1; i<size; i++) {
          for (int j=1; j<graph[i].length; j++) {
              dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + graph[i][j];
          }
      }
      
      System.out.println(Arrays.stream(dp[size-1]).max().getAsInt());
    }
}