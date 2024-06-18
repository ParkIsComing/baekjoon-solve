import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int M,N,x,y;
    
    static int findLastYear() {
        boolean check = false;
        for (int i=x; i<N*M; i+=M) {
            if (i % N == y) {
                return i+1;
            }
        }
        
        return -1;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      StringTokenizer st;
      
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          M = Integer.parseInt(st.nextToken());
          N = Integer.parseInt(st.nextToken());
          x = Integer.parseInt(st.nextToken()) -1; // 나머지가 0이 되는 것을 막기 위해
          y = Integer.parseInt(st.nextToken()) -1;
          
          sb.append(findLastYear());
          sb.append("\n");
      }
      
      System.out.println(sb);
      
    }
}
