import java.util.*;

class Solution {
    static char[] characters = {'A','E','I','O','U'};
    static List<String> list = new ArrayList<>();
    
    public int solution(String word) {
        int answer = 0;
        makeWord(0, "");
        
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    
    public void makeWord(int count, String num) {
        list.add(num);
        if (count == 5) { // 다섯 글자 다 사용
            return;
        }
        
        for (int i=0; i<5; i++) {
            makeWord(count+1, num + characters[i]);
        }
        
        
    }
}