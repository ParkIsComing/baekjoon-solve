import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int n,m;
    static int[] seq;
    static boolean[][] dp;
    
    static void checkPalindrome() {
        // 길이를 키워가면서 팰린드롬 확인
        // 길이 1은 모두 팰린드롬
        for (int i=1; i<=n; i++) {
            dp[i][i] = true;
        }
        
        if (seq.length >= 2) {
            // 길이 2는 두 글자가 같으면 팰린드롬
            for (int i=1; i<n; i++) {
                if (seq[i] == seq[i+1]) {
                    dp[i][i+1] = true;
                }
            }
        }
            
        if (seq.length >= 3) {
            // 길이 3 이상은 dp 이용
            for (int i=3; i<=n; i++) {
                for (int j=1; j<=n-i+1; j++) {
                    if (seq[j]== seq[j+i-1] && dp[j+1][j+i-2]) {
                        dp[j][j+i-1] = true;
                    }
                }
            } 
        }
        
    }
    
    static int isPalindrome(int s, int e) {
        if (dp[s][e]) {
            return 1;
        }
        return 0;
    }

    public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       seq = new int[n+1];
       StringTokenizer st = new StringTokenizer(br.readLine());
       for (int i=1; i<=n; i++) {
           seq[i] = Integer.parseInt(st.nextToken());
       }
       dp = new boolean[n+1][n+1];
       m = Integer.parseInt(br.readLine());
       
       checkPalindrome();
       
       StringBuilder sb = new StringBuilder();
       for (int i=0; i<m; i++) {
           st = new StringTokenizer(br.readLine());
           int s = Integer.parseInt(st.nextToken());
           int e = Integer.parseInt(st.nextToken());
           sb.append(isPalindrome(s, e));
           sb.append('\n');
       }
       
       System.out.println(sb);
   }
}