import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long c;
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      c = Long.parseLong(st.nextToken());

      System.out.println(pow(a,b));
    }
    
    public static long pow(long base, long exponent) {
        // 지수가 1인 경우
        if (exponent == 1) {
            return base%c;
        }
        
        long tmp = pow(base, exponent/2);
        
        if (exponent % 2 == 1){
            // 모둘려 합동 공식
            // (a * b) % c = ((a % C)*(b % C)) % C
            // (tmp * tmp * A) % C = (tmp * tmp % C) * A % C
            return (tmp * tmp % c) * base % c; 
        }
        return tmp * tmp % c;
        
    }
}