import java.io.*;
import java.util.*;

public class Main {
	static char[] strA;
	static char[] strB;
	static int[][] arr;


	public static void main(String[] args) throws IOException {
		input();
		String result = findLCS();
		System.out.println(result.length());
		System.out.println(result);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		strA = br.readLine().toCharArray();
		strB = br.readLine().toCharArray();
		arr = new int[strA.length+1][strB.length+1];
	}

	private static String findLCS() {
		for (int i=1; i<=strA.length; i++) {
			for (int j=1;j<=strB.length; j++) {
				if (strA[i-1] == strB[j-1]) {
					arr[i][j] = arr[i-1][j-1] + 1;
				} else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}

		int curX = strA.length;
		int curY = strB.length;
		StringBuilder sb = new StringBuilder();
		while (arr[curX][curY] != 0) {
			int cur = arr[curX][curY];
			if (arr[curX-1][curY] == cur) {
				curX--;
			} else if (arr[curX][curY-1] == cur) {
				curY--;
			} else {
				curX--;
				curY--;
				sb.append(strA[curX]);
			}
		}

		return sb.reverse().toString();
	}

}
