import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> numList = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N*2; j++) {
				numList.add(Integer.parseInt(st.nextToken()));
			}

			List<Integer> result = new ArrayList<>();

			while (!numList.isEmpty()) {
				int nowValue = numList.remove(0);
				int target = (nowValue / 3) * 4;

				if (numList.contains(target)) {
					result.add(nowValue);
					numList.remove(Integer.valueOf(target));
				}
			}

			sb.append("Case #").append(i).append(":");
			for (int r : result) {
				sb.append(" ").append(r);
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}
}
