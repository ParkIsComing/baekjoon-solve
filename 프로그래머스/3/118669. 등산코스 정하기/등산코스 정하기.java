import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<int[]>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int i = path[0], j = path[1], w = path[2];
            graph.get(i).add(new int[]{j, w});
            graph.get(j).add(new int[]{i, w});
        }

        Arrays.sort(summits);
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int gate : gates) {
            queue.offer(new int[]{0, gate});
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int d = current[0];
            int now = current[1];

            if (intensity[now] < d || summitSet.contains(now)) {
                continue;
            }

            for (int[] neighbor : graph.get(now)) {
                int next = neighbor[0];
                int weight = neighbor[1];
                int maxIntensity = Math.max(intensity[now], weight);
                if (maxIntensity < intensity[next]) {
                    intensity[next] = maxIntensity;
                    queue.offer(new int[]{maxIntensity, next});
                }
            }
        }

        int[] result = {0, Integer.MAX_VALUE};
        for (int summit : summits) {
            if (intensity[summit] < result[1]) {
                result[0] = summit;
                result[1] = intensity[summit];
            }
        }

        return result;
    }
}
