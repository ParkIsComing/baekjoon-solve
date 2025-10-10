import java.io.*;
import java.util.*;

public class Main {
	static int n,m; // 점은 0~n-1까지 번호
	static int[] nodeParent;
	static int[][] records;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		input();
		for (int i=1; i<=m; i++) {
			playSingleRound(i);
			if (answer != 0) break; // 사이클 발견한 즉시 종료ㅁ
		}
		print();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nodeParent = new int[n];
		for (int i = 0; i < n; i++) {
			nodeParent[i] = i;
		}
		records = new int[m+1][2];
		for (int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			records[i][0] = Integer.parseInt(st.nextToken());
			records[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	private static void playSingleRound(int round) {
		int left = records[round][0];
		int right = records[round][1];

		left = find(left);
		right = find(right);

		// 사이클이 되는 경우
		if (left == right) {
			answer = round;
			return;
		}

		nodeParent[right] = left;
	}

	private static int find(int x) {
		if (nodeParent[x] ==x) {
			return x;
		} else {
			return nodeParent[x] = find(nodeParent[x]); // 경로 압축 필요
		}
	}

	private static void print() {
		System.out.println(answer);
	}
}
