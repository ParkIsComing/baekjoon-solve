import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int s : scoville) {
            q.offer(s);
        }
        while (q.peek() < K) {
            if (q.size() == 1) {
                return -1;
            }
            int i = q.poll();
            i = i + q.poll()*2;
            answer++;
            q.offer(i);
        }
        
        return answer;
    }
}