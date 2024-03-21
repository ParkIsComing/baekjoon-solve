import java.util.*;

public class Main {
    public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int m = sc.nextInt();
      boolean[] isBroken = new boolean[10];
      for (int i=0; i<m; i++) {
          int num = sc.nextInt();
          isBroken[num] = true;
      }
      
      int result = Math.abs(n - 100); // 현재 채널 100
      for (int i=0; i<=999999; i++) {
          String num = String.valueOf(i);
          boolean broken = false;
          for (int j=0; j<num.length(); j++) {
              if (isBroken[num.charAt(j) - '0']) { // 고장난 버튼인 경우
                  broken = true;
                  break;
              }
          }
          
          if (!broken) {
              int count = Math.abs(n-i) + num.length(); // +/- 누르는 횟수 + 번호 누르는 횟수
              result = Math.min(count, result);
          }
      }

      System.out.println(result);
    }
}