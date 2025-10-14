import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<String> inputStrings = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		input();
		int result = findLCS();
		System.out.println(result);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<3; i++) {
			inputStrings.add(br.readLine());
		}
	}

	private static int findLCS() {
		String A = inputStrings.get(0);
		String B = inputStrings.get(1);
		String C = inputStrings.get(2);
		int[][][] arr = new int[A.length()+1][B.length()+1][C.length()+1];
		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				for (int k = 1; k <= C.length(); k++) {
					if (A.charAt(i-1) == B.charAt(j-1) && A.charAt(i-1) == C.charAt(k-1)) {
						arr[i][j][k] = arr[i-1][j-1][k-1] + 1;
					} else {
						arr[i][j][k] = Math.max(Math.max(arr[i-1][j][k], arr[i][j-1][k]),arr[i][j][k-1]);
					}
				}
			}
		}
		
		return arr[A.length()][B.length()][C.length()];
	}

}
