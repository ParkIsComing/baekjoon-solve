import java.util.*;

class Solution {
    public int lowerBound(List<String> list, String target, int start, int end) {
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
    
    public int upperBound(List<String> list, String target, int start, int end) {
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
    
    public int countByRange(List<String> list, String leftValue, String rightValue) {
        int rightIndex = upperBound(list, rightValue, 0, list.size());
        int leftIndex = lowerBound(list, leftValue, 0, list.size());
        return rightIndex - leftIndex;
    }
    
    // 모든 단어들을 길이 기준으로 나눠서 저장
    public Map<Integer, List<String>> map = new HashMap<>();
    
    // 모든 단어들을 길이 기준으로 나눠서 뒤집어 저장 (접두사에 와일드카드가 붙은 경우를 위해)
    public Map<Integer, List<String>> reversedMap = new HashMap<>();

    public List<Integer> solution(String[] words, String[] queries) {
        List<Integer> answer = new ArrayList<>();
        
        // 있는 단어 길이 리스트만 생성
        for (String word : words) {
            int wordLen = word.length();
            // 단어길이 키에 대한 값이 없으면 리스트 생성하고 해당 키에 맵핑
            map.computeIfAbsent(wordLen, i -> new ArrayList<>()).add(word);
            reversedMap.computeIfAbsent(wordLen, i -> new ArrayList<>()).add(reverseInput(word));
        }
        
        // 키별로 리스트 정렬
        for (int key : map.keySet()) {
            map.get(key).sort(null);
            reversedMap.get(key).sort(null);
        }
        
        // 쿼리 하나씩 확인하며 처리
        for (int i = 0; i < queries.length; i++) {
            List<String> list; // 맵의 리스트가 null인 경우를 처리하기 위해
            String q = queries[i];   
            int res = 0;
            
            if (q.charAt(0) != '?') { // 접미사에 와일드 카드가 붙은 경우
                list = map.get(q.length());
                
            } else { // 접두사에 와일드카드가 붙은 경우
                q = reverseInput(q);
                list = reversedMap.get(q.length());               
            }
            
            if (list == null) {
                answer.add(0);
            } else {
                res = countByRange(list, q.replaceAll("\\?", "a"), q.replaceAll("\\?", "z")); // "fro??"라는 쿼리가 들어오면 "froaa" 보다 크거나 같으면서 "frozz" 보다 작거나 같은 단어의 개수를 센다.
            
            answer.add(res);
            }
            
        }
        
        
        return answer;
    }
    
    public String reverseInput(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}