import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Point {
    int x, y;
    int dir; // 0은 시작점, -1은 수직 방향, 1은 수평 방향
    int mirror;
    
    Point (int x, int y, int dir, int mirror) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.mirror = mirror;
    }
}

public class Main {
    static int n;
    static char[][] map;
    static boolean[][][] visited;
    static List<Point> doors = new ArrayList<>();
    static int[][] direction = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    static void bfs() {
        // 거울 개수 오름차순
        PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.mirror - p2.mirror;
            }
        });
        Point start = doors.get(0);
        
        for (int i = 0; i < 4; i++) {
            q.offer(new Point(start.x, start.y, i, 0));
            visited[start.x][start.y][i] = true;
        }
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            // 우선순위 큐를 사용하므로 큐에서 꺼낼 때 방문처리
            visited[p.x][p.y][p.dir] = true;
            
            // 종료 조건
            if (p.x == doors.get(1).x && p.y == doors.get(1).y) {
                System.out.println(p.mirror);
                return;
            }
            
            int curDir = p.dir;
            int curMirror = p.mirror;
            
            if (map[p.x][p.y] == '*') {
                continue;
            }

            if (map[p.x][p.y] == '!') { // 거울 설치 가능한 경우
                
                
                if (curDir == 0 || curDir == 1) { // 좌우
                    for (int i=2; i<4; i++) {
                        int nx = p.x + direction[i][0];
                        int ny = p.y + direction[i][1];
                        if (nx<0||nx>=n||ny<0||ny>=n||visited[nx][ny][i]) continue;
                        q.offer(new Point(nx, ny, i, curMirror+1));
                        //visited[nx][ny][i] = true;
                    }
                } else { // 상하
                    for (int i=0; i<2; i++) {
                        int nx = p.x + direction[i][0];
                        int ny = p.y + direction[i][1];
                        if (nx<0||nx>=n||ny<0||ny>=n||visited[nx][ny][i]) continue;
                        q.offer(new Point(nx, ny, i, curMirror+1));
                        // visited[nx][ny][i] = true;
                    }
                }
                
            }
            
            // 직진(거울 설치하지 않음)
            int nx = p.x + direction[p.dir][0];
            int ny = p.y + direction[p.dir][1];
            if (nx<0||nx>=n||ny<0||ny>=n||visited[nx][ny][curDir]) continue;
            q.offer(new Point(nx, ny, curDir, curMirror));
            // visited[nx][ny][curDir] = true;
        }
    }

    public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       
       map = new char[n][n];
       
       for (int i=0; i<n; i++) {
           String input = br.readLine();
           for (int j=0; j<n; j++) {
               map[i][j] = input.charAt(j);
               
               if (map[i][j] == '#') { // 문
                   doors.add(new Point(i,j,0,0));
               }
           }
       }
       visited = new boolean[n][n][4];
       
       bfs();
       
   }
}