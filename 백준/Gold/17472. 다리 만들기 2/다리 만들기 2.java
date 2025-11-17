import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


//
class Edge implements Comparable<Edge> {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    // 비용 올림차순 정렬
    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}


public class Main {
    static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n,m;
    static int maxIsland;
    static int[][] map;
    static boolean[][] visited;
    static int[] parent;
    static ArrayList<Edge> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // 0은 바다, 1은 땅
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static void solution() {
        labelIsland();
        findBridges();
        int minLen = findMST();
        System.out.println(minLen);
    }

    static void labelIsland() {
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && !checkSea(i,j)) {
                    bfs(i, j, ++num);
                }
            }
        }
        maxIsland = num;
        parent = new int[maxIsland+1];
        for (int i = 1; i <= maxIsland; i++) {
            parent[i] = i;
        }
    }

    static void bfs(int x, int y, int num) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        map[x][y] = num;
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : direction) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];
                if (checkInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    map[nx][ny] = num;
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }


    // 섬 사이의 거리(다리의 길이) 측정
    // 직선만 가능
    static void findBridges(){
        // 섬 사이 거리(=다리 길이=비용) 측정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 땅이면 바다와 맞닿은(다리 시작) 지점 찾기
                if (!checkSea(i, j)) {
                    for (int d=0; d<4; d++) {
                        int nx = i + direction[d][0];
                        int ny = j + direction[d][1];
                        if (checkInRange(nx, ny) && checkSea(nx, ny)) {
                            calcBridgeLength(map[i][j], nx, ny, d);
                        }
                    }

                }
            }
        }


    }

    static void calcBridgeLength(int start, int x, int y, int d) {
        // 계속 바다. 한 방향으로
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(x,y));
        int bridgeLength = 1;

        while (!q.isEmpty()){
            Point cur = q.poll();
            int nx = cur.x + direction[d][0];
            int ny = cur.y + direction[d][1];

            if (checkInRange(nx, ny)) {
                // 다른 섬의 땅에 다다르면 다리의 끝
                if (!checkSea(nx, ny) && map[x][y] != map[nx][ny]) {
                    int dest = map[nx][ny];
                    // 다리 길이는 2 이싱
                    if (bridgeLength < 2) continue;
                    graph.add(new Edge(start, dest, bridgeLength));
                    break;
                }
                bridgeLength++;
                q.offer(new Point(nx,ny));
            }
        }
    }

    static int findMST() {
        Collections.sort(graph);
        int edgeCnt = 0;
        int lengthTotal = 0;
        for (Edge e: graph) {
            if (find(e.start) == find(e.end)) continue;
            lengthTotal += e.cost;
            edgeCnt++;
            union(e.start, e.end);
            if (edgeCnt == maxIsland - 1) return lengthTotal;
        }

        return -1;
    }

    static boolean checkInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static boolean checkSea(int x, int y) {
        return map[x][y] == 0;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]); // 경로 압축
    }

}


