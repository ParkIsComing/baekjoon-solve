import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    static final int STR = 100;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 사람 수
        StringTokenizer st;
        StringTokenizer st2;
        
        // 체력은 제한이 있고, 기쁨은 최대로 얻어야
        int[][] data = new int[N+1][2];
        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            data[i][0] = Integer.parseInt(st.nextToken()); // 체력
            data[i][1] = Integer.parseInt(st2.nextToken()); // 기쁨
        }
        
        // STR 크기의 체력으로 1~N번까지 사람에게 인사하여 얻을 수 있는 기쁨 저장
        int[][] dp = new int[N+1][STR+1]; 
        for (int i=1; i<=N; i++) {
            for (int j=0; j<=STR; j++) {
                if (j > data[i][0]) { // 인사 가능 
                    // 인사를 하거나 하지 않거나. 더 기쁨이 큰 걸로 저장
                    dp[i][j] = Math.max(dp[i-1][j-data[i][0]] + data[i][1], dp[i-1][j]);
                } else { // 인사 불가능 (체력이 0이 되면 안 됨)
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        System.out.println(dp[N][STR]);

  }
}