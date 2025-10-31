import java.awt.*;
import java.io.*;
import java.util.*;


public class Main {
	static final int[][] DIR = {{-1,0},{1,0},{0,1},{0,-1}};
	static int t;
	static int n,m,k;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			solution();
		}
	}


	private static void solution() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}

		int count = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j] == 1) {
					bfs(i, j);
					count++;
				}
			}
		}

		System.out.println(count);
	}


	private static void bfs(int x, int y) {
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y));

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int[] d : DIR) {
				int nx = p.x + d[0];
				int ny = p.y + d[1];
				if (inRange(nx, ny) && map[nx][ny] == 1) {
					map[nx][ny] = map[p.x][p.y] + 1;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

}
