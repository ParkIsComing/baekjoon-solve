import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] d = new int[1001][10];
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Arrays.fill(d[1], 1);
        
        for (int i=2; i<=n; i++){
            for (int j=0; j<=9; j++) {
                for (int k=j; k<=9; k++) {
                    d[i][j] += d[i-1][k]%10007;
                }
            }
        }
        
        System.out.println(Arrays.stream(d[n]).sum()%10007);
        
    }
}