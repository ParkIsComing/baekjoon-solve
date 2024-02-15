import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int solution(String str) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '(') { // (이면 스택 넣기
                stack.push('(');
            } else { // )이면 스택에서 빼기
                stack.pop();
                if (str.charAt(i-1) == '(') { // 바로 전 문자가 (이면
                    answer += stack.size();
                } else { // 바로 전 문자가 )이면 끝부분이라 +1
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String str = br.readLine();
      System.out.println(solution(str));
    }
}