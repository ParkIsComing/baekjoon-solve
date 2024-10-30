import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][][] visited;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1]; 
        visited = new int[n + 1][m + 1][2]; 

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = input.charAt(j - 1) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        Queue<PointState> queue = new LinkedList<>();
        queue.offer(new PointState(1, 1, 0));
        visited[1][1][0] = 1; 

        while (!queue.isEmpty()) {
            PointState current = queue.poll();

            if (current.x == n && current.y == m) {
                return visited[current.x][current.y][current.breakWall];
            }

            for (int[] d : direction) {
                int dx = current.x + d[0];
                int dy = current.y + d[1];

                if (dx < 1 || dx > n || dy < 1 || dy > m) {
                    continue;
                }

                if (map[dx][dy] == 0 && visited[dx][dy][current.breakWall] == 0) {
                    visited[dx][dy][current.breakWall] = visited[current.x][current.y][current.breakWall] + 1;
                    queue.offer(new PointState(dx, dy, current.breakWall));
                }

                if (map[dx][dy] == 1 && current.breakWall == 0 && visited[dx][dy][1] == 0) {
                    visited[dx][dy][1] = visited[current.x][current.y][0] + 1;
                    queue.offer(new PointState(dx, dy, 1));
                }
            }
        }
        return -1;
    }

    static class PointState {
        int x, y, breakWall;

        PointState(int x, int y, int breakWall) {
            this.x = x;
            this.y = y;
            this.breakWall = breakWall;
        }
    }
}
