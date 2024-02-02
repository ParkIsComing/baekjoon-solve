import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;
        
        while (low <= high) {
          int mid = (low + high) / 2;
          
          if (target == arr[mid]) {
              return mid;
          } else if (target < arr[mid]) {
              high = mid - 1;
          } else {
              low = mid + 1;
          }
        }
        return -1;
    }
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i=0; i<n; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      
      
      int m = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<m; i++) {
          int tmp = Integer.parseInt(st.nextToken());
          if (binarySearch(arr, tmp) >= 0) {
              sb.append(1).append('\n');
          } else {
              sb.append(0).append('\n');
          }
      }
      System.out.println(sb);
      
    }
}