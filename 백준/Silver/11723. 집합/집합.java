import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int m = Integer.parseInt(br.readLine()); 
      List<Integer> list = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      StringTokenizer st;
      
      String cmd;
      int x;
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          cmd = st.nextToken();
          switch (cmd) {
            case "add":
                x = Integer.parseInt(st.nextToken());
                if (!list.contains(x)) {
                    list.add(x);
                }
                break;
            case "remove":
                x = Integer.parseInt(st.nextToken());
                if (list.contains(x)) {
                    list.remove(Integer.valueOf(x));
                }
                break;
            case "check":
                x = Integer.parseInt(st.nextToken());
                int result = list.contains(x)? 1: 0;
                sb.append(result).append("\n");
                break;
            case "toggle":
                x = Integer.parseInt(st.nextToken());
                if (list.contains(x)) {
                    list.remove(Integer.valueOf(x));
                } else {
                    list.add(x);
                }
                break;
            case "all":
                Integer[] array = new Integer[20];
                for (int j = 0; j < 20; j++) {
                    array[j] = j + 1;
                }
                list = new ArrayList<>(Arrays.asList(array));
                break;
            case "empty":
                list.clear();
                break;
            
          }
      }
      
      System.out.println(sb);
    }
}