import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      for (int i=0; i<n; i++) {
          String input = br.readLine();
          if (isCorrect(input)) {
              System.out.println("YES");
          } else {
              System.out.println("NO");
          }
      }
    }
    
    public static boolean isCorrect(String input) {
        int left = 0;
        int right = 0;
        char[] arr = input.toCharArray();
          
        // 균형잡힌 문자열 ('('과 ')'의 개수가 같음)
        for (char c : arr) {
            if ( c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            } 
              
            if (left < right) {
                return false;
            }
              
        }
        
        return left == right;
    }
    
}