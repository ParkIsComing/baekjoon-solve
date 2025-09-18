
import java.util.*;

public class Main {
	public static final int MAX_NUM = 10; // 0~2
	public static int k;
	public static long minAns = Long.MAX_VALUE, maxAns = Long.MIN_VALUE;
	public static ArrayList<Character> inequality = new ArrayList<>();
	public static boolean[] visited = new boolean[MAX_NUM]; // 숫자별 채택 유무
	public static ArrayList<Integer> pickedNumList = new ArrayList<>(); // 선택한 값을 저장

	// 부등호를 제거하고 연결한 하나의 값
	public static void getOneNum () {
		long sum = 0;
		long mul = 1;
		for (int i=0; i<pickedNumList.size(); i++) {
			int size = pickedNumList.size();
			int index = size - i - 1;
			sum += pickedNumList.get(index) * mul;
			mul *= 10;
		}

		minAns = Math.min(minAns, sum);
		maxAns = Math.max(maxAns, sum);
	}


	public static void backtrack(int left, int cnt) { // 좌변값, 선택한 값 개수
		// 백트래킹 종료 조건
		if (cnt == k) {
			getOneNum();
			return;
		}

		char curSign = inequality.get(cnt);

		if (curSign == '<') {
			if (left == 9) return;
			for (int i=left+1; i<MAX_NUM; i++) {
				if (visited[i]) continue;
				pickedNumList.add(i);
				visited[i] = true;
				backtrack(i,cnt+1);
				pickedNumList.remove(pickedNumList.size() - 1);
				visited[i] = false;
			}

		} else {
			if (left == 0) return;
			for (int i=left-1; i>=0; i--) {
				if (visited[i]) continue;
				pickedNumList.add(i);
				visited[i] = true;
				backtrack(i,cnt+1);
				pickedNumList.remove(pickedNumList.size() - 1);
				visited[i] = false;
			}

		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();

		for (int i=0; i<k; i++) {
			char c = sc.next().charAt(0);
			if (c != ' ') {
				inequality.add(c);
			}

		}

		for (int i=0; i<MAX_NUM; i++) { // 첫 자리가 0인 경우도 정수에 포함
			pickedNumList.add(i);
			visited[i] = true;
			backtrack(i,0);
			pickedNumList.remove(pickedNumList.size() - 1);
			visited[i] = false;
		}

		int totalDigits = k + 1;
		String maxStr = String.valueOf(maxAns);
		if (maxStr.length() < totalDigits) {
			maxStr = "0".repeat(totalDigits - maxStr.length()) + maxStr;
		}
		String minStr = String.valueOf(minAns);
		if (minStr.length() < totalDigits) {
			minStr = "0".repeat(totalDigits - minStr.length()) + minStr;
		}

		System.out.println(maxStr);
		System.out.println(minStr);
	}
}