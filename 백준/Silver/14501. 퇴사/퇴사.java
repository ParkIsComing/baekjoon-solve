import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    static int n;
    static int[] length;
    static int[] price;
    
    public static int dp() {
        int[] d = new int[n+1];
        
        for (int i=0; i<n; i++) {
            if (i + length[i] <= n) { // 채택 가능한 경우
                d[i + length[i]] = Math.max(d[i + length[i]], d[i] + price[i]);
            }
            d[i+1] = Math.max(d[i+1], d[i]); 
        }
        
        return d[n];
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        length = new int[n];
        price = new int[n];
        
        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            length[i] = T;
            price[i] = P;
        }
        
        System.out.println(dp());
    }
}