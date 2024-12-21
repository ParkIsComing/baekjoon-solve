import java.util.*;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    static final int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}}; 
    static final int STRAIGHT_COST = 100; // 직선도로 100원, 코너 500원
    static final int CORNER_COST = 500;

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        
        // 비용 초기화(INF)
        for (int[][] row : cost)
            for (int[] col : row)
                Arrays.fill(col, INF); 

        // Queue<int[]> queue = new LinkedList<>();// [x, y, 이전방향, 현재 비용]
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[3])); 
        
        // 시작점 갱신
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }
        queue.add(new int[]{0, 0, -1, 0}); 

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            int prevDir = current[2], currentCost = current[3];

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1)
                    continue;

                int newCost = currentCost + STRAIGHT_COST;
                if (prevDir != -1 && prevDir != i) {
                    newCost += CORNER_COST; // 방향이 바뀔때만 코너 비용 추가
                }

                if (newCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = newCost;
                    queue.add(new int[]{nx, ny, i, newCost});
                }
            }
        }

         return Arrays.stream(cost[n - 1][n - 1]).min().orElse(INF); // OptionalInt
    }
}
