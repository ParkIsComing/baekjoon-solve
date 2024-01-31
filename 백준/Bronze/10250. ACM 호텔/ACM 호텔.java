import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(br.readLine());
      
      int h, w, n, answer;
      StringTokenizer st;
      for (int i=0; i<t; i++) {
          st = new StringTokenizer(br.readLine());
          h = Integer.parseInt(st.nextToken());
          w = Integer.parseInt(st.nextToken());
          n = Integer.parseInt(st.nextToken());
          
          int floor = n % h;
          int roomNumber = (n/h) + 1;
          
          if (floor == 0) {
              floor = h;
              roomNumber -= 1;
          }
          answer = floor * 100 + roomNumber;
          System.out.println(answer);
          
      }
    }
}