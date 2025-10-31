import java.awt.*;
import java.io.*;
import java.util.*;


public class Main {
	static final int[][] DIR = {{-1,0},{1,0},{0,1},{0,-1}};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] meltingLevel;


	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solution());
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solution() {
		int years = 0;
		while (!isAllMelted()) {
			if (isSplited()) {
				return years;
			}
			melt();
			years++;
		}
		return 0;
	}

	private static boolean isAllMelted() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	private static void melt() {
		meltingLevel = new int[n][m];

		// 빙산이면(1이상) 바닷물과 접한 면의 수만큼 녹음
		// 그 시점에 녹을 양을 저장
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j] >= 1) {
					int cnt = countSeaCoasts(i,j);
					meltingLevel[i][j] = cnt;
				}
			}
		}

		// 한번에 녹을 양 반영 (녹은 결과가 영향을 주는 걸 막음)
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (meltingLevel[i][j] > 0) {
					map[i][j] = map[i][j] < meltingLevel[i][j] ? 0 : map[i][j] - meltingLevel[i][j];
				}
			}
		}
	}

	private static int countSeaCoasts(int x, int y) {
		int cnt = 0;
		for (int[] dir : DIR) {
			int nx = x + dir[0];
			int ny = y + dir[1];

			if (inRange(nx, ny) && map[nx][ny] == 0) {
				cnt++;
			}
		}
		return cnt;
	}


	private static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	// 두 덩어리 이상으로 나뉘었는지 확인
	private static boolean isSplited() {
		visited = new boolean[n][m];
		int cnt = 0;

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					bfs(i, j);
					cnt++;
					if (cnt >= 2) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static void bfs(int x, int y) {
		Deque<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int[] dir : DIR) {
				int nx = p.x + dir[0];
				int ny = p.y + dir[1];
				if (inRange(nx, ny) && map[nx][ny] > 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx, ny));
				}
			}
		}
	}

}
