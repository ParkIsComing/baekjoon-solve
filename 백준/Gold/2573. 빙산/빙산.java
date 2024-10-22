import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<Point> glacier = new ArrayList<>();
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] visited;

    static void updateMelting() {
        int[][] tempMap = new int[n][m];
        ArrayList<Point> newGlacier = new ArrayList<>();
        
        for (Point p : glacier) {
            int meltCount = 0;
            for (int[] d : direction) {
                int dx = d[0] + p.x;
                int dy = d[1] + p.y;
                if (0 <= dx && dx < n && 0 <= dy && dy < m && map[dx][dy] == 0) {
                    meltCount++;
                }
            }
            tempMap[p.x][p.y] = Math.max(map[p.x][p.y] - meltCount, 0);
            if (tempMap[p.x][p.y] > 0) {
                newGlacier.add(p); // 아직 남아있는 빙산을 기록
            }
        }
        glacier = newGlacier;
        map = tempMap; // 업데이트된 맵으로 변경
    }

    static int countGlacier(int time) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] != time && map[i][j] != 0) {
                    bfs(i, j, time);
                    count++;
                }
            }
        }
        return count;
    }

    static void bfs(int x, int y, int time) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        visited[x][y] = time;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] d : direction) {
                int dx = d[0] + p.x;
                int dy = d[1] + p.y;
                if (0 <= dx && dx < n && 0 <= dy && dy < m) {
                    if (visited[dx][dy] != time && map[dx][dy] != 0) {
                        visited[dx][dy] = time;
                        q.offer(new Point(dx, dy));
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;

                if (tmp != 0) {
                    glacier.add(new Point(i, j));
                }
            }
        }

        int time = 0;
        visited = new int[n][m];
        while (true) {
            time++;
            updateMelting();

            if (glacier.isEmpty()) { // 모든 빙산이 녹아 없어졌다면
                time = 0;
                break;
            }

            int count = countGlacier(time);
            if (count >= 2) {
                break;
            }
        }

        System.out.println(time);
    }
}
