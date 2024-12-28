import java.util.*;

class Solution {
    List<List<int[]>> graph;
    
    public int[] dijkstra(int start, int n) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        distance[start] = 0;
        queue.offer(new int[]{0, start});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int d = current[0];
            int now = current[1];

            if (distance[now] < d) continue;

            for (int[] edge : graph.get(now)) {
                int nextNode = edge[0];
                int nextCost = d + edge[1];
                if (nextCost < distance[nextNode]) {
                    distance[nextNode] = nextCost;
                    queue.offer(new int[]{nextCost, nextNode});
                }
            }
        }
        return distance;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            graph.get(u).add(new int[]{v, cost});
            graph.get(v).add(new int[]{u, cost});
        }

        // 아예 따로 가는 경우
        int[] distFromS = dijkstra(s, n);
        int cost = distFromS[a] + distFromS[b];

        // i까지 합승하고, 이후 따로 가는 경우
        for (int i = 1; i <= n; i++) {
            int[] distFromI = dijkstra(i, n);
            cost = Math.min(cost, distFromS[i] + distFromI[a] + distFromI[b]);
        }

        return cost;
    }
}