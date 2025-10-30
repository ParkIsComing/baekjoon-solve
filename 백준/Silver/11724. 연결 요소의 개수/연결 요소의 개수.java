import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(findConnectComponent());

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b); // 무방향 그래프
            graph[b].add(a);
        }
    }

    static int findConnectComponent() {
        int count = 0;

        for (int i=1; i<=n; i++) {
            if (!visited[i]) {
                count++;
                bfs(i);
            }
        }

        return count;
    }

    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (int i: graph[cur]) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }



}
