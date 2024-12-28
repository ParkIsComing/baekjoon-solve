import java.util.*;
class Solution {
    public int solution(String[] words) {
        // 단어들을 정렬
        Arrays.sort(words);
        int totalLength = 0;
        int n = words.length;
        
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int needLength = 1;  // 최소 1글자는 필요
            
            // 이전 단어와 비교
            if (i > 0) {
                int commonWithPrev = getCommonPrefix(word, words[i-1]);
                needLength = Math.max(needLength, commonWithPrev + 1);
            }
            
            // 다음 단어와 비교
            if (i < n-1) {
                int commonWithNext = getCommonPrefix(word, words[i+1]);
                needLength = Math.max(needLength, commonWithNext + 1);
            }
            
            totalLength += Math.min(needLength, word.length());
        }
        
        return totalLength;
    }
    
    private int getCommonPrefix(String str1, String str2) {
        int len = 0;
        int minLen = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < minLen; i++) {
            if (str1.charAt(i) != str2.charAt(i)) break;
            len++;
        }
        return len;
    }
}