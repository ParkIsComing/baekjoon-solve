import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, S;
    static int[] arr;
    static int count;
    
    public static void dfs(int index, int sum) {
        if (index == N) { // 다 고려해본 경우
            if (sum == S) {
                count++;
            }
            return;
        }
        
        dfs(index+1, sum+arr[index]); // 포함하는 경우
        dfs(index+1, sum); // 포함하지 않는 경우
        
    }

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      S = Integer.parseInt(st.nextToken());
      
      arr = new int[N];
      
      st = new StringTokenizer(br.readLine());
      for (int i=0 ;i<N; i++){
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      dfs(0,0);
      System.out.println(S == 0 ? count-1 : count); // S == 0이면 공집합도 포함!!!
    }
}