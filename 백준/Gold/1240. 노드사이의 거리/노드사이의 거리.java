import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    public int next;
    public int length;
    
    public Node (int next, int length) {
        this.next = next;
        this.length = length;
    }
}
public class Main {
    public static int n,m;
    public static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    
    public static int bfs(int start, int end) {
        boolean[] visited = new boolean[n+1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        visited[start] = true;
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.next == end) {
                return n.length;
            }
            for (Node x : tree.get(n.next)) {
                if (!visited[x.next]) {
                    q.offer(new Node(x.next, n.length + x.length));
                    visited[x.next] = true;
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      for (int i=0;i<n+1; i++) {
          tree.add(new ArrayList<>());
      }
      
      
      // 트리 입력
      int a,b,w;
      for (int i=0; i<n-1; i++) {
          st = new StringTokenizer(br.readLine());
          a = Integer.parseInt(st.nextToken());
          b = Integer.parseInt(st.nextToken());
          w = Integer.parseInt(st.nextToken());
          tree.get(a).add(new Node(b, w));
          tree.get(b).add(new Node(a, w));
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int start = Integer.parseInt(st.nextToken());
          int end = Integer.parseInt(st.nextToken());
          sb.append(bfs(start, end)).append("\n");
      }
      
      System.out.println(sb);
    }
}