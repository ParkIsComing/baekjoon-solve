import java.util.*;


public class Main {
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int digits = (int)Math.log10(n) + 1; // 자릿수
        
        int answer = 0;
        for (int i=0; i<digits-1; i++) {
            answer += 9 * Math.pow(10, i) * (i+1);
        }
        
        answer += (n - Math.pow(10, digits-1) + 1) * digits;
        System.out.println(answer);
  }
}