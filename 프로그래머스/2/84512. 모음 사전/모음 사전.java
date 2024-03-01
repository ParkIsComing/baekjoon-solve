import java.util.*;

class Solution {
    static char[] characters = {'A','E','I','O','U'};
    static HashMap<String, Integer> map = new HashMap<>();
    static int index = 0;
    
    public int solution(String word) {
        makeWord(0, ""); // ""은 0번째로 들어가서 무관
        
        int answer = map.get(word);
        return answer;
    }
    
    public void makeWord(int count, String num) {
        map.put(num, index++); // 위치 주의 (꼭 아래 if문 위에 와야됨 다 저장하니까)
        if (count == 5) { // 다섯 글자 다 사용
            return;
        }
        
        for (int i=0; i<5; i++) {
            makeWord(count+1, num + characters[i]);
        }
        
        
    }
}