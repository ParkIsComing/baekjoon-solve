import java.util.*;
import java.awt.*;

class Solution {
    final int SIZE = 12;
    boolean[] is_queen_in_col = new boolean[SIZE]; // 열
    boolean[] is_queen_in_left = new boolean[SIZE * 2]; // 좌하향 대각선 (위의 행부터 두기 때문에 위쪽 대각선은 무시)
    boolean[] is_queen_in_right = new boolean[SIZE * 2]; // 우하향 대각선
    int answer = 0;
    
    public int solution(int n) {
        backtrack(0, n);
        return answer;
    }
    
    // 행마다 하나씩 놓아 보기
    public void backtrack(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }
        
        for (int col=0; col<n; col++) {
            if (!is_queen_in_col[col] && !is_queen_in_left[row+col] && !is_queen_in_right[row-col+n]) {
                is_queen_in_col[col] = true;
                is_queen_in_left[row+col] = true;
                is_queen_in_right[row-col+n] = true;
                
                backtrack(row+1, n);
                is_queen_in_col[col] = false;
                is_queen_in_left[row+col] = false;
                is_queen_in_right[row-col+n] = false;
                
            }
        }
    }
}