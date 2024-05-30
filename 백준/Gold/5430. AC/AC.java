import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;



// 두 가지 연산 : R, D
// R : 배열에 있는 수의 순서를 뒤집음
// D: 첫번째 수를 버림 (비어있는데 쓰면 error 출력)
// 조합으로 사용 가능
// 배열의 크기 + 함수(조합)의 길이 < 70만 ??
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Main {
    static int T;
    static boolean isReversed;

    static String getResult(char[] commands, Deque<Integer> deque) {
        StringBuilder sb = new StringBuilder();
        for (char c : commands) {
            if (c == 'R') {
                isReversed = !isReversed;
            } else if (c == 'D') {
                if (deque.isEmpty()) {
                    return "error";
                } else {
                    if (isReversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }
        }
        sb.append("[");
        while (!deque.isEmpty()) {
            if (isReversed) {
                sb.append(deque.removeLast());
            } else {
                sb.append(deque.removeFirst());
            }
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < T; i++) {
            isReversed = false;
            char[] methods = br.readLine().toCharArray();
            int size = Integer.parseInt(br.readLine());
            String input = br.readLine();
            String str = input.substring(1, input.length() - 1);
            
            Deque<Integer> deque = new LinkedList<>();
            if (!str.isEmpty()) {
                deque = Arrays.stream(str.split(","))
                              .map(Integer::parseInt)
                              .collect(Collectors.toCollection(LinkedList::new));
            }
            
            String result = getResult(methods, deque);
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }
}
