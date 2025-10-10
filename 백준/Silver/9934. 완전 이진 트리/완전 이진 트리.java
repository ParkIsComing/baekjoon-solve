import java.io.*;
import java.util.*;

public class Main {
	static int K;
	static int[] arr;
	static ArrayList<Integer>[] result;

	public static void main(String[] args) throws IOException {
		input();
		findNodes(0, arr.length, 0);
		print();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		result = new ArrayList[K];
		for (int i = 0; i < K; i++) {
			result[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[(int)Math.pow(2,K)-1];
		for(int i = 0; i < arr.length; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void findNodes(int s, int e, int depth) {
		if (depth == K) {
			return;
		}

		int mid = (s + e) / 2;
		result[depth].add(arr[mid]);

		findNodes(s, mid - 1, depth + 1);
		findNodes(mid + 1, e, depth + 1);
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<K; i++) {
			sb.setLength(0);
			for (Integer num : result[i]) {
				sb.append(num).append(" ");
			}
			System.out.println(sb);
		}
	}
}
