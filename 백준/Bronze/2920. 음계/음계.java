import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int[] arr= new int[8];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i=0; i<8; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      String result = "";
      
      if (arr[0] == 1) {
          result = "ascending";
          for (int i=1; i<8; i++) {
              if (arr[i-1] + 1 != arr[i]){
                  result = "mixed";
                  break;
              }
          }
      } else if (arr[0] == 8) {
          result = "descending";
          for (int i=1; i<8; i++) {
              if (arr[i-1] - 1 != arr[i]) {
                  result = "mixed";
                  break;
              }
          }
      } else {
          result = "mixed";
      }
      
      System.out.println(result);
      
    }
}