import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int binarySearch(int n, int goal, int[] arr) { // 최소 m미터 위한 최대 절단기 높이
        int right = Arrays.stream(arr).max().getAsInt();
        int left = 0;
        int mid = 0;
        int result = 0;
        
        if (goal == 0) {
            result = (int)(1e9);
            return result;
        }
        
        while (left <= right) {
            long sum = 0;
            mid = (left + right) / 2;
            for (int i: arr) {
                if (mid < i) {
                    sum += i - mid;
                }
            }
            
            if (sum < goal) { // 절단기 줄이기
                right = mid - 1;
            } else if (sum >= goal) {// 절단기 늘리기 (일단 ok이니까 저장)
                result = mid;
                left = mid + 1;
            }
        }
        
        return result;
    } 
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); // n개 나무
      int m = Integer.parseInt(st.nextToken()); // 최소 m미터
      
      st = new StringTokenizer(br.readLine());
      int[] arr = new int[n];
      // 나무 정보 입력 받기
      for (int i=0; i<n; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      System.out.println(binarySearch(n, m, arr));
    }
}