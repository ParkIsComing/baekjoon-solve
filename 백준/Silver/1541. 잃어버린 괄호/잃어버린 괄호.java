import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();

		int total = 0;       // 최종 결과
		int curNum = 0;      // 현재 읽는 숫자
		int groupSum = 0;    // '-'로 끊긴 구간(플러스들의 묶음) 합
		boolean minusPhase = false; // 첫 '-' 이후인지

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c >= '0' && c <= '9') {
				curNum = curNum * 10 + (c - '0'); // 숫자 누적
				continue;
			}

			groupSum += curNum;
			curNum = 0;

			if (c == '-') {
				// 이전 그룹 정산
				if (!minusPhase) total += groupSum;    // 첫 '-' 전: 더하기
				else             total -= groupSum;    // 첫 '-' 후: 빼기
				groupSum = 0;
				minusPhase = true;
			}
			// '+'면 그룹만 유지 (아무 것도 안 함)
		}

		// 마지막 숫자/그룹 정산
		groupSum += curNum;
		if (!minusPhase) {
			total += groupSum;
		} else {
			total -= groupSum;
		}

		System.out.println(total);
	}
}
