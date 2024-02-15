import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int j : moves){
            for (int i=0; i<board.length; i++) {
                if (board[i][j-1] != 0) { // 잡는 대상
                    int tmp = board[i][j-1];
                    board[i][j-1] = 0;
                    if (!stack.isEmpty() && tmp == stack.peek()) {
                        answer += 2;
                        stack.pop();
                    } else {
                        stack.push(tmp);
                    }
                    break;
                }
            }
      }        
        return answer;
    }
}