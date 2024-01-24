import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연산자를 하나씩 써보면서 재귀..

public class Main {
    public static int n,m;
    public static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static int[] numbers = new int[100];
    public static int[] operators = new int[4]; // + - * /
    
    public static void dfs(int sum, int count) { 
        if (count == n) {
            min = Math.min(sum, min);
            max = Math.max(sum, max);
            return;
        }
        
        for (int i=0; i<4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                
                switch(i) {
                    case 0:
                        dfs(sum + numbers[count], count+1);
                        break;
                    case 1:
                        dfs(sum - numbers[count], count+1);
                        break;
                    case 2:
                        dfs(sum * numbers[count], count+1);
                        break;
                    case 3:
                        dfs(sum / numbers[count], count+1);
                        break;
                }
                
                operators[i]++;
            }
            
            
            
        }
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i=0; i<n; i++) {
          numbers[i] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(br.readLine());
      for (int i=0; i<4; i++) {
          operators[i] = Integer.parseInt(st.nextToken());
      }
      
      m = Arrays.stream(operators).sum();

      dfs(numbers[0], 1);
      
      System.out.println(max);
      System.out.println(min);
    }
}