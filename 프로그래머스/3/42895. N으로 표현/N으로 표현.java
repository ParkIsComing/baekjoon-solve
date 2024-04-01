import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> setList = new ArrayList<>(); // 중복된 연산 결과가 없도록 Set
        
        for(int i=0;i<9; i++) { 
            setList.add(new HashSet<>());
        }
        setList.get(1).add(N); // 1개로 만들 수 있는 값은 N뿐
        if (number == N) {
            return 1;
        }
        for (int i=2; i<9; i++) {
            for (int j=1; j<=i/2; j++) { // 합쳐서 N을 i개 사용하는 조합
                dp(setList.get(i), setList.get(i-j), setList.get(j));
                dp(setList.get(i), setList.get(j), setList.get(i-j));
            }
            // i번 연속된 숫자
            String n = Integer.toString(N);
            setList.get(i).add(Integer.parseInt(n.repeat(i)));
            for (int num : setList.get(i))
                if (num == number) {
                    return i;
                }
        }
        return -1; // N이 9개 이상 필요한 경우
    }
    
    static void dp(Set<Integer> cur, Set<Integer> a, Set<Integer> b) {
        for (int v1 : a) {
            for (int v2: b) {
                cur.add(v1 + v2);
                cur.add(v1 - v2);
                cur.add(v1 * v2);
                if (v2 != 0) {
                    cur.add(v1/v2);
                }
            }
        }
    }
}

// + - * % 
// 개수별 얻을 수 있는 값 저장
// 개수 i 하나씩 늘려가면서 dp