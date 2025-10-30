import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 0 이동가능 / 1 벽
// 벽 하나 부술 수 있음
// 최단경로로 이동

class Node {
    int x;
    int y;
    int depth;
    int isBroken;

    Node(int x, int y, int depth, int isBroken) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.isBroken = isBroken;
    }
}

public class Main {
    static final int[][] DIRECTION = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n,m;
    static int[][] map;
    static boolean[][][] visited;
    static Deque<Node> q = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2]; // 0: 벽을 안 부숨/ 1: 벽을 부숨
        for (int i=0; i<n; i++) {
            String input = br.readLine();
            for (int j=0; j<m; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }
    
    static int bfs() {

        q.offer(new Node(0,0,1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur =  q.poll();
            if(cur.x == n-1 && cur.y == m-1) {
                return cur.depth;
            }

            for (int[] d : DIRECTION) {
                int nx = cur.x+  d[0];
                int ny = cur.y +  d[1];

                if (inRange(nx, ny)) {
                    // 0이면 그냥 이동
                    if (map[nx][ny] == 0) {
                        if (!visited[nx][ny][cur.isBroken]) {
                            visited[nx][ny][cur.isBroken] = true;
                            q.offer(new Node(nx,ny,cur.depth+1, cur.isBroken));
                        }
                    } else {
                        // 1인 경우
                        // 아직 벽을 안 부쉈으면 부수고 이동
                        if (cur.isBroken == 0) {
                            if (!visited[nx][ny][1]) {
                                visited[nx][ny][1] = true;
                                q.offer(new Node(nx,ny, cur.depth+1, 1));
                            }
                        }

                    }
                }
            }
        }

        return -1;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
