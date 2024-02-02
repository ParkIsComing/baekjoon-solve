import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String[] arr = new String[n];
      for (int i=0; i<n; i++) {
          arr[i] = br.readLine();
      }
      
      char[] colors = {'B', 'W'};
      
      int n_limit = n - 8;
      int m_limit = m - 8;
      int result = Integer.MAX_VALUE;
      
      for (int i=0; i <= n_limit; i++) {
          for (int j=0; j <= m_limit; j++) {
              for (char idx : colors) {
                  int count = 0;
                  for (int k=i; k< i+8; k++){
                      for (int l=j; l< j+8; l++) {
                          if (arr[k].charAt(l) != idx) {
                              count++;
                          }
                          
                          idx = idx == 'W' ? 'B' : 'W'; 
                      }
                      idx = idx == 'W' ? 'B' : 'W'; 
                  }   
                  
                  result = Math.min(result, count);
              }
              
          }
      }
      
      System.out.println(result);

    }
}