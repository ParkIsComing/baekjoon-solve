import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


// 선물 기록이 있는지(누가 더 많이 줬는지)
// 선물 지수

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> gift_dict = new HashMap<>();
        int[][] gift_table = new int[n][n]; //누가에게 누구에게 줬는지
        int [] gift_score = new int[n]; // 선물 지수
        int[] total_gift = new int[n];
        
        for (int i=0; i<n; i++) {
            gift_dict.put(friends[i], i); // 이름과 idx
        }
        
        for (String gift : gifts) {
            String[] people = gift.split(" ");
            int a_idx = gift_dict.get(people[0]); // 누가
            int b_idx = gift_dict.get(people[1]); // 누구에게
            
            gift_table[a_idx][b_idx] += 1;
            gift_score[a_idx] += 1;
            gift_score[b_idx] -= 1;
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) continue; // 본인에게 줄 순 없음
                if (gift_table[i][j] > gift_table[j][i]) { // 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물 받음.
                    total_gift[i] += 1; 
                } else if (gift_table[i][j] == gift_table[j][i]) { // 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면
                    if (gift_score[i] > gift_score[j]) {
                        total_gift[i] += 1;
                    }
                }
            }
        }
        
        return Arrays.stream(total_gift).max().getAsInt();
    }
}