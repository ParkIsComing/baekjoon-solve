import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static final int[][] DIRECTION= {{-1,0}, {1,0}, {0,1}, {0,-1}}; // 네방향 외에 움직이는 않을 수도 있음
	static int n,m,k;
	static int[][] map;
	static int[][][][] visited; // i,j,낮0/밤1,벽부순 개수 값은 이동한 거리

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solution());
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new int[n][m][2][k+1];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j=0;j<m;j++){
				map[i][j] = s.charAt(j) - '0';
			}
		}
	}

	static int solution() {
		// 낮에만 벽을 부수고 이동 가능
		// 벽은 최대 k개 부술 수 있다.

		// 낮부터 이동 시작
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0,0,0,0});
		visited[0][0][0][0] = 1;

		while (!q.isEmpty()){
			int[] p = q.poll();
			int x = p[0],y = p[1], night = p[2], cnt = p[3];

			if (x==n-1 && y==m-1) {
				return visited[x][y][night][cnt];
			}

			int nn = night == 0 ? 1 : 0;
			// 상하좌우
			for (int[] d : DIRECTION) {
				int nx = d[0] + x;
				int ny = d[1] + y;

				if (!inRange(nx, ny)) {
					continue;
				}


				if (map[nx][ny] == 0 && visited[nx][ny][nn][cnt] == 0) { // 이동 가능 (낮과 밤 모두 가능)
					visited[nx][ny][nn][cnt] = visited[x][y][night][cnt] + 1;
					q.add(new int[] {nx, ny, nn, cnt});
				} else if (map[nx][ny]==1 && cnt < k && visited[nx][ny][nn][cnt+1] == 0) { // 벽
					if (night == 0)  { // 낮에 벽을 부수고 이동
						visited[nx][ny][nn][cnt+1] = visited[x][y][night][cnt] + 1;
						q.add(new int[] {nx,ny,nn, cnt+1});
					} else { // 밤이라 못 부수고 stay
						visited[x][y][nn][cnt] = visited[x][y][night][cnt] + 1;
						q.add(new int[] {x,y,nn, cnt});
					}
				}
			}
		}

		return -1; // 도달 불가
	}

	static boolean inRange(int x, int y){
		return x >= 0 && x < n && y >= 0 && y < m;
	}


}
