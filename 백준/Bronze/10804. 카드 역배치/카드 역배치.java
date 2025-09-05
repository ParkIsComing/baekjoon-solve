import java.io.*;
import java.util.*;

public class Main {
	static final int CARD_NUM = 20;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] card = new int[CARD_NUM+1]; //1-indexed
		for (int i = 1; i <= CARD_NUM; i++) {
			card[i] = i;
		}

		for (int t = 0; t < 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			for (int l = a, r = b; l < r; l++, r--) {
				int tmp = card[l];
				card[l] = card[r];
				card[r] = tmp;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= CARD_NUM; i++) {
			if (i > 1) sb.append(' ');
			sb.append(card[i]);
		}
		System.out.println(sb.toString());
	}
}


