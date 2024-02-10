import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      int[][] data = new int[n][2];
      StringTokenizer st;
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          data[i][0] = Integer.parseInt(st.nextToken());
          data[i][1] = Integer.parseInt(st.nextToken());
      }
      
      // 종료시간 기준 정렬(같으면 시작시간이 빠른순)
       Arrays.sort(data, new Comparator<int[]>() {
           @Override
           public int compare(int[] a, int[] b) {
               if (a[1] == b[1]) {
                   return a[0] - b[0];
               }
               return a[1] - b[1];
           }
       });
       
       int count = 0;
       int end = 0;
       
       for (int[] time : data) {
           if (end <= time[0]) { // 등호 주의
               end = time[1];
               count++;
           }
       }

      System.out.println(count);
    }
}