import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if (cacheSize == 0) {
            answer += cities.length * 5;
            return answer;
        }
        
        ArrayList<String> cache = new ArrayList<>();
        for (String x : cities) {
            int pos = -1;
            x = x.toLowerCase();
            if (cache.contains(x)) {// cache hit인지 확인
                cache.remove(x);
                cache.add(x);
                answer += 1;
            } else {// cache miss인 경우 추가
                if (cache.size() == cacheSize) {
                    cache.remove(0);
                } 
                cache.add(x);
                answer += 5;
            }

        }
        return answer;
    }
}