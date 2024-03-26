import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i=0; i<T; i++) {
            N = Integer.parseInt(br.readLine());
            int[][] board = new int[2][N];
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[0][j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[1][j] = Integer.parseInt(st.nextToken());
            }
            
            System.out.println(solution(board, N));
        }
    }
    
    static int solution(int[][] board, int N) {
        if (N == 1) {
            return Math.max(board[0][0], board[1][0]);
        }
        board[0][1] += board[1][0];
        board[1][1] += board[0][0];
        
        for (int i=2; i<N; i++) {
            board[0][i] += Math.max(board[1][i-1], board[1][i-2]);
            board[1][i] += Math.max(board[0][i-1], board[0][i-2]);
        }
        
        return Math.max(board[0][N-1], board[1][N-1]);
    }
}