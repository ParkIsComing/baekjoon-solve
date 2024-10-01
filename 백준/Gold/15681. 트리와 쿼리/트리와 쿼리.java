import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static void countNode(int node, int[] count, ArrayList<Integer>[] tree) {
        count[node] = 1; //기준 노드 자신(루트)
        for (int n: tree[node]) {
            if(count[n] == 0) {
                countNode(n, count, tree);
                count[node] += count[n];
            }
        }
    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); // 정점 수
      int r = Integer.parseInt(st.nextToken()); // 루트 번호
      int q = Integer.parseInt(st.nextToken()); // 쿼리 
      
      ArrayList<Integer>[] tree = new ArrayList[n+1];
      for (int i=1; i<=n; i++) {
          tree[i] = new ArrayList<Integer>();
      }
      for (int i=1; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          int u = Integer.parseInt(st.nextToken());
          int v = Integer.parseInt(st.nextToken());
          tree[u].add(v);//양방향
          tree[v].add(u);
      }
      
      int[] count = new int[n+1];
      countNode(r, count, tree);
      
      for (int i=0; i<q; i++) {
          st = new StringTokenizer(br.readLine());
          sb.append(count[Integer.parseInt(st.nextToken())]);
          sb.append("\n");
      }
      
      System.out.println(sb);
      
    }
}
    
    