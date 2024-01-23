import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        // 1 :ok
        if (p.isEmpty()) {
            answer = p;
            return answer;
        }
        
        // 2. 균형잡힌 문자열로 분리
        String[] split = makeBalanced(p);
        String u = split[0];
        String v = split[1];
        
        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행        
        if (checkRight(u)) {
            v = solution(v);
            answer = u + v;
        } else {
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행 
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
            // 4-3. ')'를 다시 붙입니다. 
            String s = "(" + solution(v) + ")";
            
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
            String reversed = reverse(u);
            // 4-5. 생성된 문자열을 반환합니다.
            answer = s + reversed;
        }
        
        return answer;        
        
    }
    
    public String[] makeBalanced(String str) {
        int left = 0;
        int right = 0;
        String[] result = new String[2];
        String u = "";
        String v = "";
        
        for (char c: str.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            if (left == right) {
                u = str.substring(0, left + right);
                v = str.substring(left + right);
                break;
            }
            
        }
        
        result[0] = u;
        result[1] = v;
        return result;
    }
    
    public boolean checkRight(String str) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }

            if (right > left) {
                return false;
            }
        }
        return left == right;
    }
    
    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<str.length()-1; i++) {
            if (str.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}