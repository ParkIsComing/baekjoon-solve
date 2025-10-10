import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static int[] base;
	static int[] speed;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(cutTree());
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		base = new int[n];
		speed = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			base[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			speed[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static long cutTree() {
		long sumBase = 0;
		for (int b : base) {
			sumBase += b;
		}
		Arrays.sort(speed);
		long addedLength = 0;
		for (int i=0; i<n; i++) {
			addedLength += (long)speed[i] * i;
		}
		return sumBase + addedLength;
	}

}
