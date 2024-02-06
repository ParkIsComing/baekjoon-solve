import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int cases = Integer.parseInt(br.readLine()); 
      
      StringTokenizer st;
      for (int i=0; i<cases; i++) {
          st = new StringTokenizer(br.readLine());
          int n = Integer.parseInt(st.nextToken()); // 문서 개수
          int m = Integer.parseInt(st.nextToken()); // 찾는 문서 m번째 
          
          Queue<int[]> q = new LinkedList<>();
          st = new StringTokenizer(br.readLine());
          
          for (int j=0; j<n; j++) {
              int num = Integer.parseInt(st.nextToken());
              q.add(new int[] {j, num}); // {초기 위치, 중요도}
          }
          
          int count = 0; // 인쇄한 개수
          while (!q.isEmpty()) {
              int[] first = q.poll();
              boolean isMax = true;
              
              for (int[] tmp : q) {
                  if (tmp[1] > first[1]) {
                      isMax = false;
                      break;
                  }
              }
              
              if (isMax) { // 가장 큰 값 인쇄
                  count++;
                  if (first[0] == m) { // 찾는 값이면 while 탈출
                      break;
                  }
              } else {
                  q.add(first);
              }
          }
          
          System.out.println(count);

      }

    }
}