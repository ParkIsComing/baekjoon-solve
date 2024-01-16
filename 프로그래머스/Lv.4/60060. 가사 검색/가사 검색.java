import java.util.*;

class Solution {
    public int lowerBound(ArrayList<String> list, String target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            // arr[mid]가 target보다 사전순으로 같거나 뒤에 있는 경우
            if (list.get(mid).compareTo(target) >= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
    
    public int upperBound(ArrayList<String> list, String target, int start, int end) {
        while (start < end) {
            int mid = (start+end) / 2;
            // arr[mid]가 target보다 사전순으로 뒤에 있다면
            if (list.get(mid).compareTo(target) > 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
    
    public int countByRange(ArrayList<String> list, String leftValue, String rightValue) {
        int rightIndex = upperBound(list, rightValue, 0, list.size());
        int leftIndex = lowerBound(list, leftValue, 0, list.size());
        return rightIndex - leftIndex;
    }
    
    // 모든 단어들을 길이 기준으로 나눠서 저장
    public ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    
    // 모든 단어들을 길이 기준으로 나눠서 뒤집어 저장 (접두사에 와일드카드가 붙은 경우를 위해)
    public ArrayList<ArrayList<String>> reversedList = new ArrayList<ArrayList<String>>();

    public int[] solution(String[] words, String[] queries) {
        ArrayList<Integer> answers = new ArrayList<Integer>();
        
        for (int i = 0; i<10001; i++) {
            list.add(new ArrayList<String>());
            reversedList.add(new ArrayList<String>());
        }
        
        for (int i=0; i < words.length; i++) {
            String word = words[i];
            list.get(word.length()).add(word); // 단어길이와 같은 인덱스값으로 삽입
            
            word = (new StringBuffer(word)).reverse().toString();
            reversedList.get(word.length()).add(word);
        }
        
        // 이진탐색을 위한 정렬 (같은 길이의 단어 리스트 내에서)
        for (int i = 0; i < 10001; i++) {
            Collections.sort(list.get(i));
            Collections.sort(reversedList.get(i));
        }
        
        // 쿼리 하나씩 확인하며 처리
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];   
            int res = 0;
            if (q.charAt(0) != '?') { // 접미사에 와일드 카드가 붙은 경우
                res = countByRange(list.get(q.length()), q.replaceAll("\\?", "a"), q.replaceAll("\\?", "z")); // "fro??"라는 쿼리가 들어오면 "froaa" 보다 크거나 같으면서 "frozz" 보다 작거나 같은 단어의 개수를 센다.
            } else { // 접두사에 와일드카드가 붙은 경우
                q = (new StringBuffer(q)).reverse().toString();
                res = countByRange(reversedList.get(q.length()), q.replaceAll("\\?", "a"), q.replaceAll("\\?", "z"));                
            }
            
            answers.add(res);
        }
        
        // list -> array
        int[] answer = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }
}