import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); //수의 개수
      int m = Integer.parseInt(st.nextToken()); // 케이스
      
      st = new StringTokenizer(br.readLine());
      int[] arr = new int[n];
      for (int i=0; i<n; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      int[] prefixSum = new int[n+1];// 누적합
      for (int i=0; i<n; i++) { 
          prefixSum[i+1] = prefixSum[i] + arr[i];
      }
      
      int start;
      int end;
      int partSum;
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          start= Integer.parseInt(st.nextToken());
          end = Integer.parseInt(st.nextToken());
          partSum = prefixSum[end] - prefixSum[start-1];
          sb.append(partSum).append("\n");
      }
      
      System.out.println(sb);
    }
}
