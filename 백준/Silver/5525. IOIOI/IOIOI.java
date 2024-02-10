import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int solution(int num) { // 최소 m미터 위한 최대 절단기 높이
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i=4; i<=num; i++) {
            dp[i] = dp[i-1] + dp[i-2]+ dp[i-3];
        }
        
        return dp[num];
    } 
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine()); //"I" + N번의 "OI"로 접근
      int m = Integer.parseInt(br.readLine());
      String str = br.readLine();
      
      int count = 0;
      int result = 0;
      
      for (int i=1; i<m-1;) {
          if (str.charAt(i) == 'O' && str.charAt(i+1) == 'I') {
              count++;
              
              if (count == n) { // n번의 "OI" -> 앞에 "I"가 있으면 됨
                  if (str.charAt(i-(count*2-1)) == 'I') {
                      result++;
                  }
                  count--;
              }
              i += 2; // i와 i+1 인덱스는 체크했으니까
          } else {
              count = 0; // "OI" 카운팅 리셋
              i++;
          }
      }
      System.out.println(result);
    }
}