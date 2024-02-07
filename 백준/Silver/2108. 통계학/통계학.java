import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];
      for (int i=0; i<n; i++) {
          arr[i] = Integer.parseInt(br.readLine());
      }
      
      // 산술평균
      int sum = Arrays.stream(arr).sum();
      System.out.println(Math.round((double)sum/n));
      
      // 중앙값
      Arrays.sort(arr);
      System.out.println(arr[n/2]);
      
      // 최빈값 (여래개 있을 때는 두번째로 작은 값)
      int maxMode = 0; // 최빈값 개수
      int mode = 10000; // 최빈값 
      boolean isFirst = true;

      Map<Integer, Integer> map = new LinkedHashMap<>();
      for (int i : arr) {
          map.compute(i, (key, value) -> (value == null) ? 1 : value + 1);
      }
      
      Iterator<Integer> keys = map.keySet().iterator();
      while(keys.hasNext()) {
         Integer key = keys.next();
         Integer value = map.get(key);
         if (maxMode < value) {
             mode = key;
             maxMode = value;
             isFirst = true;
             
         } else if (maxMode == value && isFirst) {
             mode = key;
             isFirst = false;
         } 
      }
      
      System.out.println(mode);
      
      // 범위
      int min = Arrays.stream(arr).min().getAsInt();
      int max = Arrays.stream(arr).max().getAsInt();
      
      System.out.println(max-min);
      
      
      
    }
}