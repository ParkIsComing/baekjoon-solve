import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,L;
    static int[][] lines;
    public static void main(String args[]) throws IOException{
      Scanner sc = new Scanner(System.in);
      N = sc.nextInt();
      L = sc.nextInt();
      lines = new int[N][2];
      
      for (int i=0; i<N; i++) {
          lines[i][0] = sc.nextInt();
          lines[i][1] = sc.nextInt();
      }
      
      Arrays.sort(lines, (a, b) -> {
          if (a[0] == b[0]) {
              return Integer.compare(a[1], b[1]); 
          } else {
              return Integer.compare(a[0], b[0]);
          }
          
      });
      
      int prevStart = lines[0][0];
      int idx = 0;
      int count = 0;
      
      for (int i=0; i<N; i++) {
          int curStart = lines[i][0];
          int curEnd = lines[i][1];
          
          if (idx < curStart) {
              idx = curStart;
          }
          
          if (idx < curEnd) {
              while (idx < curEnd) {
                  idx += L;
                  count += 1;
              }
          }
      }
      
      System.out.println(count);
    }
}
    
    