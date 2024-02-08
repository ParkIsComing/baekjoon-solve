import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int n, r, c, count = 0;
    
    public static void search(int side, int r, int c) {
        
        if (side == 1) {
            return;
        }
        
        // (r,c) 위치별 분할 탐색
        if (r < side/2 && c < side/2) { // 제 1사분면
            search(side/2, r, c);
        } else if (r < side/2 && c >= side/2) { // 제 2사분면
            count += (side * side / 4); // 제 1사분면 방문 카운트
            search(side/2, r, c-side/2);
        } else if (r >= side/2 && c < side/2) {// 제 3사분면
            count += (side * side / 4) * 2; // 제 1,2사분면 방문 카운트
            search(side/2, r - side/2, c);
        } else {
            count += (side * side / 4) * 3; // 제 1,2,3사분면 방문 카운트
            search(side/2, r-side/2, c-side/2);
            
        }
    }

    public static void main(String args[]) throws IOException {
        
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      n = Integer.parseInt(st.nextToken()); // n*n
      r = Integer.parseInt(st.nextToken()); // 행
      c = Integer.parseInt(st.nextToken()); // 열
      
      int side = (int)Math.pow(2, n); // 한 변의 길이
      
      search(side, r, c);
      
      System.out.println(count);
    }
    
}