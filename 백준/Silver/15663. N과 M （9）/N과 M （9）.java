import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int[] out;
    static int n,m;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n];
        out = new int[m];
        
        Arrays.sort(arr);
        dfs(0);
    }
    
    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int i=0; i<m; i++) {
                System.out.print(out[i] + " ");
            }
            System.out.println();
        }
        
        else {
            int before = 0;
            for (int i=0; i<n; i++) {
                if (visited[i])
                    continue;
                if (before != arr[i]) { 
                    visited[i] = true;
                    out[cnt] = arr[i];
                    before = arr[i];
                    dfs(cnt+1);
                    visited[i] = false;
                }
            }
        }
    }
}