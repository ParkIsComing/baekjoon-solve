
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class RollRes {
    int x,y;
    boolean inHole;
    public RollRes(int x, int y, boolean inHole) {
        this.x = x; this.y = y; this.inHole = inHole;
    }
}

class State {
    int rx, ry;
    int bx, by;
    int depth;

    State(int rx, int ry, int bx, int by, int depth) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.depth = depth;
    }
}

public class Main {
    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int n,m;
    static char[][] board;
    static boolean[][][][] visited; // [rx][ry][bx][by]
    static State start;


    public static void main(String[] args) throws IOException{
        input();
        int answer = bfs();
        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        int srx= 0, sry=0, sbx=0, sby=0;
        for (int i=0; i<n; i++) {
            String tmp = br.readLine();
            for (int j=0; j<m; j++) {
                board[i][j] = tmp.charAt(j);
                if (board[i][j] == 'R') {
                    srx = i;
                    sry = j;
                }else if (board[i][j] == 'B') {
                    sbx = i;
                    sby = j;
                }
            }
        }

        visited = new boolean[n][m][n][m];
        start = new State(srx, sry, sbx, sby, 0);
    }

    // 움직이기
    // 기울이면 누굴 먼저 움직이지?
    static int bfs() {
        // 큐에 넣어야 하는건 현재 빨강, 파랑 위치, 움직인 횟수(depth)
        Queue<State> q = new LinkedList<>();
        q.offer(start);
        visited[start.rx][start.ry][start.bx][start.by] = true;

        while (!q.isEmpty()) {
            State curState = q.poll();
            // 10번 이하로 움직여서 빨강 빼낼 수 없으면 -1 출력
            if (curState.depth == 10) {
                continue;
            }


            for (int i=0; i<4; i++) {
                RollRes red, blue;
                // 굴리는 방향 기준 벽에 가까운 거 먼저
                if (whoFirst(curState, i) == 0) { // 0=빨강, 1=파랑
                    red = roll(curState.rx, curState.ry, i);
                    blue = rollWithBlock(curState.bx, curState.by, i, red);
                } else {
                    blue = roll(curState.bx, curState.by, i);
                    red =  rollWithBlock(curState.rx, curState.ry, i, blue);
                }
                
                // 종료 조건 : 홀에 빠졌는지 체크
                // 파랑은 나오면 안됨 (7)
                // 빨강과 파랑이 동시에 나와도 실패
                if (blue.inHole) {
                    continue;
                }
                if (red.inHole) {
                    return curState.depth + 1;
                }

                // 종료하지 않았다면 계속
                if (!visited[red.x][red.y][blue.x][blue.y]) {
                    visited[red.x][red.y][blue.x][blue.y] = true;
                    q.offer(new State(red.x, red.y, blue.x, blue.y, curState.depth+1));
                }
            }
        }

        return -1;
    }

    static boolean checkSamePath(State cur, int direction) {
        if (direction == 0 || direction == 1) { // 상하좌우 순서
            return cur.ry == cur.by;
        }
        return cur.rx == cur.by;
    }

    // 같은 경로일 때 누가 더 먼저 움직일 수 있는지 (0=빨강, 1=파랑)
    static int whoFirst(State curState, int direction) {
        if (direction == 0) { // 상
            return curState.rx < curState.bx ? 0 : 1;
        }
        if (direction == 1) { // 하
            return curState.rx > curState.bx ? 0 : 1;
        }
        if (direction == 2) { // 좌
            return curState.ry < curState.by ? 0 : 1;
        }

        return curState.ry > curState.by ? 0 : 1; // 우
    }

    // 특정 구슬을 벽이나 구멍을 만날 때까지 굴린다.
    static RollRes roll(int x, int y, int direction) {
        int nx = x + DIR[direction][0];
        int ny = y + DIR[direction][1];
        while (inRange(nx, ny)) {
            if (board[nx][ny] == '#') { // 벽을 만남
                return new RollRes(nx-DIR[direction][0], ny-DIR[direction][1], false);
            }
            if (board[nx][ny] == 'O') {// 구멍을 만남
                return new RollRes(nx, ny, true);
            }
            nx += DIR[direction][0];
            ny += DIR[direction][1];
        }
        return new RollRes(x, y, false);
    }

    static RollRes rollWithBlock(int x, int y, int d, RollRes first){
        if (first.inHole) return roll(x, y, d); // 앞 구슬이 O로 사라졌다면 차단 없음
        int dx = DIR[d][0], dy = DIR[d][1];
        int nx = x + dx, ny = y + dy;
        while (inRange(nx, ny)) {
            char cell = board[nx][ny];
            if (cell == '#' || (nx == first.x && ny == first.y)) {
                return new RollRes(nx - dx, ny - dy, false); // 앞 구슬 바로 앞에서 정지
            }
            if (cell == 'O') return new RollRes(nx, ny, true);
            nx += dx; ny += dy;
        }
        return new RollRes(x, y, false); // (실제로는 테두리가 #라 여기 안 옴)
    }

    static boolean inRange(int x, int y) {
        return x>=0 && x<n && y>=0 && y<m;
    }

}