import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int i,j;
    
    public static void main(String args[]) throws IOException{
      Scanner sc = new Scanner(System.in);
      char[] arr = sc.next().toCharArray();
      Arrays.sort(arr);
     
      int n = arr.length;
      // 투 포인터로 안쪽->바깥쪽으로 확인
      if(n%2 == 0) {
          i = n/2-1;
          j = n/2;
      } else {
          i = n/2-1;
          j = n/2+1;
      }
      
      boolean isImpossible = false;
      while (i>=0 && j<n) {
          int idx = j;
          if (arr[i] == arr[j]) { //팰린드롬인 경우 다른거 찾기
              while (arr[i] == arr[idx]) {
                  idx += 1;
                  if (idx == n) { // 바꿀 수 없는 경우
                      isImpossible = true;
                      break;
                  }
                  
              }
              if (isImpossible) {
                  break;
              }
              char tmp = arr[idx];
              arr[idx] = arr[j];
              arr[j] = tmp;
          }
          i -= 1; 
          j += 1;
      }
      
      if (isImpossible) {
          System.out.println(-1);
      } else {
          System.out.println(String.valueOf(arr));
      }
    }
}
    
    