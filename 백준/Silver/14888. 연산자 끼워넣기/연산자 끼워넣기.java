import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] num;
    static int[] operator = new int[4];
    static int o = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st= new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
            o += operator[i];
        }
        
        dfs(0, num[0]);
        System.out.println(max);
        System.out.println(min);
        
    }
    
    // 벽 세우기
    public static void dfs(int count, int sum) {
        if (count == o) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);
            return;
        }
        
        for (int i=0; i<4; i++) {
            if (operator[i] > 0) {
                operator[i]--; // 연산자 사용
                
                switch(i) {
                    case 0:
                        dfs(count+1, sum + num[count+1]);
                        break;
                    case 1:
                        dfs(count+1, sum - num[count+1]);
                        break;
                    case 2:
                        dfs(count+1, sum * num[count+1]);
                        break;
                    case 3:
                        dfs(count+1, sum /num[count+1]);
                        break;
                }
                operator[i]++; // 백트래킹
            }
        }
        
        
    }
    
}