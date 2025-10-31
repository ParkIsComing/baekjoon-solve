import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> cntPerComplex;


	public static void main(String[] args) throws IOException {
		input();
		System.out.println(findHomes());
		Collections.sort(cntPerComplex);
		cntPerComplex.stream().forEach(System.out::println);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		cntPerComplex = new ArrayList<>();

		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
	}

	private static int findHomes() {
		int complexCnt = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					cntPerComplex.add(bfs(i,j));
					complexCnt++;
				}
			}
		}

		return complexCnt;
	}

	private static int bfs(int x, int y) {
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;
		int homeCnt = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int[] dir : DIR) {
				int nx = p.x + dir[0];
				int ny = p.y + dir[1];

				if (inRange(nx, ny) &&  map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					homeCnt++;
					q.offer(new Point(nx, ny));
				}
			}
		}

		return homeCnt;
	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}


}
