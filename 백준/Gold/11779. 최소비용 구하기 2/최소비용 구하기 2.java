import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<int[]>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new int[]{b, c});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        int[] parents = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curDist = current[1];

            if (curDist > dist[curNode]) continue;

            for (int[] edge : edges[curNode]) {
                int nextNode = edge[0];
                int nextDist = curDist + edge[1];

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    parents[nextNode] = curNode;
                    pq.add(new int[]{nextNode, nextDist});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int node = end; node != 0; node = parents[node]) {
            path.add(node);
        }
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append('\n');
        sb.append(path.size()).append('\n');
        for (int node : path) {
            sb.append(node).append(' ');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
