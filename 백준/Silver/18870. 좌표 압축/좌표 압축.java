import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      List<Integer> list = new ArrayList<>();
      for(int i=0; i<n; i++) {
          list.add(Integer.parseInt(st.nextToken()));
      }
      List<Integer> copyList = new ArrayList<>(list);
      Collections.sort(copyList);
      
      // indexOf 쓰면 시간 초과 -> hashmap으로 변경
      HashMap<Integer, Integer> map = new HashMap<>();
      int count = 0;
      for (int i : copyList) {
          if (!map.containsKey(i)) {
              map.put(i, count++);
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i : list) {
          sb.append(map.get(i)).append(" ");
      }
      
      System.out.println(sb);
    }
}