import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static String solution(int r, String str) {
        String result = "";
        char[] charArr = str.toCharArray();
        for (char c: charArr) {
            for (int i=0; i<r; i++) {
                result += c;
            }
        }
        return result;
    }
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      int r = 0;
      String str = null;
      StringTokenizer st;
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          r = Integer.parseInt(st.nextToken());
          str = st.nextToken();
          System.out.println(solution(r, str));
      }
      
    }
}