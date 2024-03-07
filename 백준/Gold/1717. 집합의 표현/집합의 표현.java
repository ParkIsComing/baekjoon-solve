import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parent = new int[n+1];
        for (int i=1; i<=n; i++) {
            parent[i] = i;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 0이면 집합을 합침
            if (cmd == 0) {
                union(a,b);
            } else {
                int rootA = find(a);
                int rootB = find(b);
                if (rootA == rootB) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);
        
 
    }
    
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        parent[rootY] = rootX;
    }
    
    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        
        parent[node] = find(parent[node]);
        return parent[node];
    }
}