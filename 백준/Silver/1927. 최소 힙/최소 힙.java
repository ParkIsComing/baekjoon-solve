import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long c;
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소 힙
      
      int input;
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<n; i++) {
          input = Integer.parseInt(br.readLine());
          if (input == 0) {
              Integer tmp = minHeap.poll();
              if (tmp == null) {
                  sb.append(0).append("\n");
              } else {
                  sb.append(tmp).append("\n");
              }
              
          } else {
              minHeap.offer(input);
          }
          
      }

      System.out.println(sb);
    }
    
}