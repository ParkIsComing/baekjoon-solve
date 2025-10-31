import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int n,m;
	static int[][] maze;


	public static void main(String[] args) throws IOException {
		input();
		bfs(0,0);
		System.out.println(maze[n-1][m-1]);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new int[n][m];

		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
	}

	private static void bfs(int x, int y) {
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y));

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int[] dir : DIR) {
				int nx = p.x + dir[0];
				int ny = p.y + dir[1];

				if (inRange(nx, ny) && maze[nx][ny] == 1) {
					maze[nx][ny] = maze[p.x][p.y] + 1;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}


}
