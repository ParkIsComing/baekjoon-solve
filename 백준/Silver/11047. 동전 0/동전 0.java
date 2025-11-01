import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n,k;
	static int[] coin;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solution());
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
	}

	static int solution() {
		int cnt = 0;
		for (int i=n-1; i>=0; i--) {
			cnt += k / coin[i];
			k %= coin[i];
		}
		return cnt;
	}


}
