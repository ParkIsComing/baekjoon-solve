import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int add = (brown - 4) / 2;
        int x=0; //세로 (y>=x)
        int y=0; // 가로
        for (int i=1; i<brown; i++) {
            if (yellow % i == 0) {
                int a = i;
                int b = yellow / i;
                if (a+b == add) {
                    if (a>=b) {
                        y = a+2;
                        x = b+2;
                    } else {
                        y = b+2;
                        x = a+2;
                    }
                    break;
                }
            }
        }
        answer[0] = y;
        answer[1] = x;
        
        return answer;
    }
}