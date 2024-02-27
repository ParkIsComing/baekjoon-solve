import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<Integer>();
        int pre = arr[0];
        list.add(arr[0]);
        for (int i : arr) {
            if (pre!=i) {
                list.add(i);
            }
            pre = i;
        }
        int answer[] = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}