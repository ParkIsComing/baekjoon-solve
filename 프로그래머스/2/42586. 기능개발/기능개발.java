import java.util.*;

class Solution {
    private static final int TOTAL_AMOUNT = 100;
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> period = new ArrayList<>();
        for (int i=0; i<progresses.length; i++) {
            int left = TOTAL_AMOUNT - progresses[i];
            int day = (int)Math.ceil((double)left / speeds[i]);
            period.add(day);
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<period.size();) {
            int cur = period.get(i);
            int count = 1;
            for (int j=i+1; j<period.size(); j++) {
                if (period.get(j) <= cur) {
                    count++;
                    i++;
                } else {
                    break;
                }
            }
            list.add(count);
            i++;
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        return answer;
    }
}