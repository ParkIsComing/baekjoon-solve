import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    public static int[] d = new int[1001];
    
    public static void dp(int x) {
        d[x] = (d[x-1] + d[x-2]) % 10007;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      
      d[1] = 1;
      d[2] = 2;
      for (int i=3; i<=n; i++) {
          dp(i);
      }

      System.out.println(d[n]);
    }
}