import java.io.*;
import java.util.*;

public class Main {
	static char[] strA;
	static char[] strB;
	static int[][] arr;


	public static void main(String[] args) throws IOException {
		int answer = 0;
		input();
		answer = findLCS();
		System.out.println(answer);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		strA = br.readLine().toCharArray();
		strB = br.readLine().toCharArray();
		arr = new int[strA.length+1][strB.length+1];
	}

	private static int findLCS() {
		for (int i=1; i<=strA.length; i++) {
			for (int j=1;j<=strB.length; j++) {
				if (strA[i-1] == strB[j-1]) {
					arr[i][j] = arr[i-1][j-1] + 1;
				} else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}

		return arr[strA.length][strB.length];
	}

}
