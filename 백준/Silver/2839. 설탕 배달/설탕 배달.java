import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      int[] d = new int[n+1];
      
      Arrays.fill(d, 50001);
      
      d[0] = 0;
      for (int i=3; i<=n; i++) {
          if (d[i-3] != 50001) {
              d[i] = Math.min(d[i], d[i-3] + 1);
          }
      }
      
      for (int i=5; i<=n; i++) {
          if (d[i-5] != 50001){
              d[i] = Math.min(d[i], d[i-5] + 1);
          }      
          
      }
      
      int result = 0;
      if (d[n] == 50001) {
          result = -1;
      } else {
          result = d[n];
      }
      
      System.out.println(result);
      
    }
}