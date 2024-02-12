import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, S;
    static Integer[] arr;

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      arr = new Integer[N];
      for (int i=0 ;i<N; i++){
          arr[i] = Integer.parseInt(br.readLine());
      }
      
      // 삼각형 조건 : 두 변의 합 > 나머지 한변 (모든 경우에 대해)
      Arrays.sort(arr,Collections.reverseOrder());
      int sum = 0;
      int maxSum = Integer.MIN_VALUE;
      for (int i =0; i+2 <N; i++) {
          if (arr[i] < arr[i+1] + arr[i+2]) {
              sum = arr[i] + arr[i+1] + arr[i+2];
              maxSum = Math.max(maxSum, sum);
          }
      }
      
      System.out.println(maxSum == Integer.MIN_VALUE ? -1 : maxSum);
      
    }
}