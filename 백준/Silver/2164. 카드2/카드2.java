import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      Deque<Integer> d = new LinkedList<>();
      
      for (int i=1;i<=n;i++){
          d.add(i);
      }
      
      while (d.size() > 1) {
          d.remove();
          d.add(d.remove());
      }
      
      System.out.println(d.remove());
      
      
    }
}