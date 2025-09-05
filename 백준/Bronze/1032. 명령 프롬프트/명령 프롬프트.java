import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.nextLine());
		String[] arr = new String[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextLine();
		}

		int len = arr[0].length();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < len; i++) {
			final int idx = i;
			char first = arr[0].charAt(i);
			boolean allSame = Arrays.stream(arr)
				.allMatch(s -> s.charAt(idx) == first);

			result.append(allSame ? first : '?');
		}

		System.out.println(result.toString());
	}
}
