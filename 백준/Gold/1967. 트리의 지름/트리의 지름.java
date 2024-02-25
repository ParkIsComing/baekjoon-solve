import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int idx;
    int cost;
    
    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class Main {
    static int max, leaf;
    static int n;
    static ArrayList<ArrayList<Node>> tree = new ArrayList<ArrayList<Node>>();
    static boolean[] visited;
    
    public static void dfs(int cur, int sum) {
        if (visited[cur]) {
            return;
        }
        
        visited[cur] = true;
        if (max < sum) {
            max = sum;
            leaf = cur;
        }
        
        for (Node n : tree.get(cur)) {
            dfs(n.idx, sum + n.cost);
        }
    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      for (int i=0; i<=n; i++) {
          tree.add(new ArrayList<>());
      }
      StringTokenizer st;
      for (int i=0; i<n-1; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          int cost = Integer.parseInt(st.nextToken());
          tree.get(a).add(new Node(b, cost));
          tree.get(b).add(new Node(a, cost));
      }
      
      visited = new boolean[n+1];
      max = Integer.MIN_VALUE;
      dfs(1,0); // 루트노드로부터 가장 먼 리프노드 탐색

      Arrays.fill(visited, false);
      max = Integer.MIN_VALUE;
      dfs(leaf,0);
      
      System.out.println(max);
      
    }
}