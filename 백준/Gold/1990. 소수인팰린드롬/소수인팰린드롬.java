import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean isPalindrome(int n) {
        String nStr = n + "";
        StringBuffer sb = new StringBuffer(nStr);
        String reversedStr = sb.reverse().toString();
        if (nStr.equals(reversedStr)) {
            return true;
        }
        return false;
    }
    
    public static void main(String args[]) throws IOException{
      Scanner sc = new Scanner(System.in);
      int a = sc.nextInt();
      int b = sc.nextInt();
      
      boolean isPrime[] = new boolean[b+1];
      Arrays.fill(isPrime, true);
      isPrime[0] = false;
      isPrime[1] = false;
      StringBuilder sb = new StringBuilder();
      
      for (int i=2; i*i<=b; i++) {
          if(isPrime[i]) {
              for (int j=i*i; j<=b; j+=i) {
                  isPrime[j] = false;
              }
          }
      }
      
      
      for (int i = a; i <= b; i++) {
          if (isPrime[i] && isPalindrome(i)) {
              sb.append(i);
              sb.append("\n");
          }
      }
      
      if (sb.length() > 0) {
          sb.setLength(sb.length() - 1);
          System.out.println(sb);
      }
      
      System.out.println(-1);
    }
}
    
    