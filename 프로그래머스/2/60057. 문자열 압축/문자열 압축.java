class Solution {
    public int getShortenedLength(String target, int unit) {
        int length = target.length();
        int curLength = length;
        String prevSubstr = "";
        int count = 1;
        
        int left = 0;
        int right = unit;
        
        while (right <= length) {
            String curSubstr = target.substring(left, right);
            
            if (prevSubstr.equals(curSubstr)) {
                count++; // 반복되는 문자열인 경우
            } else {
                if (count > 1) { // 압축한 이력이 있는 경우
                    curLength += String.valueOf(count).length(); // 압축된 부분 앞 숫자 개수 반영
                    curLength -= (count-1) * unit; // 반복되는 부분 날리기
                }
                prevSubstr = curSubstr;
                count = 1; // 반복 카운트 초기화
            }
            
            left += unit;
            right += unit;
        }
        
        // 남아있는 압축 문자열 반영
        if (count > 1) {
            curLength += String.valueOf(count).length(); // 압축된 부분 앞 숫자 개수 반영
            curLength -= (count-1) * unit; // 반복되는 부분 날리기
        }
        
        return curLength;
    }
    public int solution(String s) {
        int answer = s.length();
        
        for (int i=1; i<=s.length()/2; i++) {
            answer = Math.min(answer, getShortenedLength(s, i));
        }
        return answer;
    }
}