import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    static int n;
    static int[] arr;
    
    public static int dp() {
        int[] d = new int[n];
        Arrays.fill(d, 1);
        
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
               if (arr[j]<arr[i] && d[i] < d[j] + 1) {
                   d[i] = d[j] + 1;
               } 
            }
        }
        
        return Arrays.stream(d).max().getAsInt();
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(dp());
    }
}