import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> uniqueGems = new HashSet<>(Arrays.asList(gems)); // 보석 종류
        Map<String, Integer> gemCount = new HashMap<>(); // 투포인터(보석, 보석 개수)
        
        int start = 0, end = 0, minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while (end < gems.length) {
            // end 포인터가 가리키는 보석을 추가
            gemCount.put(gems[end], gemCount.getOrDefault(gems[end], 0) + 1);
            end++;

            while (gemCount.size() == uniqueGems.size()) {
                if (end - start < minLength) { // 더 짧은 구간이 있으면 업데이트
                    minLength = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }

                // Map에 start 변경 반영
                gemCount.put(gems[start], gemCount.get(gems[start]) - 1);
                if (gemCount.get(gems[start]) == 0) {
                    gemCount.remove(gems[start]);
                }
                start++;
            }
        }

        return answer;
    }
}
