import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    static int[][] graph;
    static int n, count = 0;
    static ArrayList<Integer> answer = new ArrayList<Integer>();
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y>= n) {
            return;
        }
        
        if (graph[x][y] == 1) {
            count++;
            graph[x][y] = -1; // 방문한 곳은 -1
            dfs(x-1, y);
            dfs(x, y-1);
            dfs(x+1, y);
            dfs(x, y+1);
        }

    } 
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine()); // 한 변의 길이 (2^k)
      graph = new int[n][n];
      
      StringTokenizer st;
      // 종이 정보 입력 받기
      for (int i=0; i<n; i++) {
          String input = br.readLine();
          for (int j=0; j<n; j++) {
              graph[i][j] = (int)(input.charAt(j) - '0');
          }
      }
      
      for (int i=0; i<n; i++) {
          for (int j=0; j<n; j++) {
              dfs(i,j);
              if (count != 0) {
                  answer.add(count);
                  count = 0;
              }
          }
      }
      
      System.out.println(answer.size());
      
      Collections.sort(answer);
      for (int i: answer) {
          System.out.println(i);
      }
      
    }
}