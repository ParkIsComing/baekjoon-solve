import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] visited; 
    public static int n,k;
    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = 0;
        while(!q.isEmpty()) {
            int i = q.poll();
            
            if (i == k) {
                return visited[i];
            }
            
            if (i-1 >= 0 && visited[i-1] == -1) {
                visited[i-1] = visited[i] + 1;
                q.offer(i-1);
            }
            if (i+1 <= 100000 && visited[i+1] == -1) {
                visited[i+1] = visited[i] + 1;
                q.offer(i+1);
            }
            if (i*2 <= 100000 && visited[i*2] == -1) {
                visited[i*2] = visited[i] + 1;
                q.offer(i*2);
            }
        }
        return -1;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); 
        k = Integer.parseInt(st.nextToken()); 
        
        visited = new int[100001];
        Arrays.fill(visited, -1);
        System.out.println(bfs());
    }
}
