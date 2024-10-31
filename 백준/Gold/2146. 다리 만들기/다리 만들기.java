import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int n;
    static int[][][] map;
    static boolean[][] visited;
    static int[][] direction = {{-1,0},{1,0},{0,1}, {0,-1}};
    static int minDistance = Integer.MAX_VALUE;
    
    static void measureDistance(int x, int y, int idx) {
        
        for (int i=0; i<n; i++) {
            Arrays.fill(visited[i], false);
        }
        
        map[x][y][1] = 1;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            for (int[] d: direction) {
                int dx = p.x + d[0];
                int dy = p.y + d[1];
                
                if (dx<0 || dx>=n || dy<0 || dy>=n || visited[dx][dy]) {
                    continue;
                }
                
                if (map[dx][dy][0] == 0) {
                    map[dx][dy][1] = map[p.x][p.y][1] + 1;
                    q.offer(new Point(dx, dy));
                    visited[dx][dy] = true;
                } else if (map[dx][dy][0] != idx) {
                    minDistance = Math.min(minDistance, map[p.x][p.y][1]);
                    return;
                }
                
                
            }
            
            
        }
        
    }
    
    static void bfs(int x, int y, int idx) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        map[x][y][0] = idx;
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            for (int[] d : direction) {
                int dx = p.x + d[0];
                int dy = p.y + d[1];
                
                if (dx<0 || dx>=n || dy<0 || dy>=n) {
                    continue;
                }
                
                if (map[dx][dy][0] != 0 && !visited[dx][dy]) {
                    q.offer(new Point(dx, dy));
                    map[dx][dy][0] = idx;
                    visited[dx][dy] = true;
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n][2]; // 지형 타입(섬 번호 또는 바다(0))과 거리
        visited = new boolean[n][n];
        
        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 섬 넘버링
        int idx = 1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (map[i][j][0] != 0 && !visited[i][j]) { // 방문하지 않은 섬이면 탐색
                    bfs(i, j, idx);
                    idx++;
                }
            }
        }
        
        // 다른 섬과의 거리 재기
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (map[i][j][0] != 0) {
                    for (int[] d: direction) {
                        int dx = i + d[0];
                        int dy = j + d[1];
                        if (dx<0 || dx>=n || dy<0 || dy>=n) {
                            continue;
                        }
                        if (map[dx][dy][0] == 0) { // 바다와 접한 경우
                            measureDistance(dx, dy, map[i][j][0]);
                        }
                    }
                }
                
            }
        }
        
        System.out.println(minDistance);
        
    }
}
