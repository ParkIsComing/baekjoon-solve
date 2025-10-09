import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_N = 200;
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N,K,S,X,Y;
	static int[][] map = new int[MAX_N+1][MAX_N+1]; // 1-based
	static PriorityQueue<Cell> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		input();
		spreadVirus();
		System.out.println(map[X][Y]);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					pq.offer(new Cell(i,j,map[i][j],0));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
	}

	private static void spreadVirus() {
		while (!pq.isEmpty()) {
			Cell cur = pq.poll();
			if (cur.sec == S) { // S초간 확산이 완료된 경우
				break;
			}
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0];
				int ny = cur.y + dir[1];
				if (isInRange(nx, ny) && map[nx][ny] == 0) {
					map[nx][ny] = cur.status;
					pq.offer(new Cell(nx, ny, map[nx][ny], cur.sec + 1	));
				}
			}
		}
	}

	private static boolean isInRange(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
}

class Cell implements Comparable<Cell> {
	int x;
	int y;
	int status;
	int sec;

	public Cell(int x, int y, int status, int sec) {
		this.x = x;
		this.y = y;
		this.status = status;
		this.sec = sec;
	}

	@Override
	public int compareTo(Cell cell) {
		if (this.sec != cell.sec) {
			return this.sec - cell.sec;
		} else {
			return this.status - cell.status;
		}
	}
}

// 작은 숫자부터 확산
// 바이러스가 없는 곳에는 확산 가능(상하좌우), 덮어쓰기 불가능
// 바이러스 없으면 0, 있으면 자연수