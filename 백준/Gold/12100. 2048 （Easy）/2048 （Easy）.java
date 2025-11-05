import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐짐
// 이미 합쳐진 블록은 또 합쳐질 수 없음
// 한번에 한 방향으로 기울인 것처럼 이동 and 합쳐짐..?

public class Main {
    static int n;
    static int[][] board;
    static int maxNum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static void solution() {
        if (n == 1) {
            System.out.println(board[0][0]);
            return;
        }

        move(0);
        System.out.println(maxNum);
    }

    static void move(int cnt) {
        // 구할것: 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
        if (cnt == 5) {
            maxNum = Math.max(maxNum, findMaxNum());
            return;
        }

        int[][] copy = new int[n][n];
        for (int i=0; i<n; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }

        for (int i=0; i<4; i++) {
            tilt(i);
            move(cnt+1);
            // backtrace
            for (int j=0; j<n; j++) {
                board[j] = copy[j].clone();
            }
        }
    }

    static int findMaxNum() {
        int max = 0;
        for (int[] r : board) {
            for (int i : r) {
                max = Math.max(max, i);
            }
        }
        return max;
    }


    static void tilt(int dir) {
        switch (dir) {
            case 0:
                tiltToLeft();
                break;
            case 1:
                tiltToRight();
                break;
            case 2:
                tiltToCeiling();
                break;
            case 3:
                tiltToFloor();
                break;
        }
    }

    static void tiltToLeft() {
        for (int i=0; i<n; i++) {
            int idx = 0;
            int block = 0; // 합칠 수 있는 후보

            for (int j=0; j<n; j++) {
                // 밀 거 없음
                if (board[i][j] == 0) continue;

                if (block == board[i][j]) { // 후보랑 같은 숫자
                    board[i][idx - 1] = block * 2;
                    block = 0;
                    board[i][j] = 0; // 왼쪽이랑 합쳤으니까 비우기
                } else {
                    block = board[i][j]; // 후보랑 합치기 실패 후보 갱신
                    board[i][j] = 0;
                    board[i][idx++] = block; // 걍 옮기기
                }
            }
        }
    }

    static void tiltToRight() {
        for (int i=0; i<n; i++) {
            int idx = n-1; // 채울 위치
            int block = 0; // 합칠 수 있는 후보

            for (int j=n-1; j>=0; j--) {
                if(board[i][j] == 0) continue;

                if (block == board[i][j]) { // 후보랑 합체 가능
                    board[i][idx+1] = block * 2; // 직전에 놓은 자리 값을 두배로
                    block = 0;
                    board[i][j] = 0; // 합쳤으니 현재 위치는 비우기
                } else { // 합체 불가능
                    block = board[i][j];
                    board[i][j] = 0;
                    board[i][idx--] = block;
                }
            }
        }
    }

    static void tiltToCeiling() {
        for (int j = 0; j < n; j++) {
            int idx = 0;    // 채울 행 (위에서부터)
            int block = 0;  // 합칠 후보

            for (int i = 0; i < n; i++) {
                if (board[i][j] == 0) continue;

                if (block == board[i][j]) {
                    // 직전에 둔 칸이 (idx-1, j)
                    board[idx - 1][j] = block * 2;
                    block = 0;
                    board[i][j] = 0;
                } else {
                    block = board[i][j];
                    board[i][j] = 0;
                    board[idx++][j] = block;  // (idx, j)에 놓고 idx 증가
                }
            }
        }
    }

    static void tiltToFloor() {
        for (int j = 0; j < n; j++) {
            int idx = n - 1; // 채울 행 (아래에서부터)
            int block = 0;   // 합칠 후보

            for (int i = n - 1; i >= 0; i--) {
                if (board[i][j] == 0) continue;

                if (block == board[i][j]) {
                    // 직전에 둔 칸이 (idx+1, j)
                    board[idx + 1][j] = block * 2;
                    block = 0;
                    board[i][j] = 0;
                } else {
                    block = board[i][j];
                    board[i][j] = 0;
                    board[idx--][j] = block;  // (idx, j)에 놓고 idx 감소
                }
            }
        }
    }


}
