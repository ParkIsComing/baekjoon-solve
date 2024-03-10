import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int[][] board;
    static int n, m, count;
    static int[][] direction = {{-1,0},{1,0}, {0,-1},{0,1}};
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int answer = 0;
        dfs(0,0); // 외부 공간 체크
        while (true) {
            List<Point> list = findCheese();
            // 종료 조건
            if (list.size() == 0) {
                break;
            }
            
            for (int i=0; i<list.size(); i++) {
                Point p = list.get(i);
                int x = p.x;
                int y = p.y;
                board[x][y] = 0;
                dfs(x,y); // 외부공간 업데이트
                
            }
            
            answer++;
        }
        System.out.println(answer);
 
    }
    
    public static void dfs(int x, int y) {
        if (x < 0 || x >= n || y<0 || y>=m || board[x][y] != 0) {
            return;
        }
        
        board[x][y] = -1; // 치즈 외부는 -1
        for (int[] d : direction) {
            int nx = x + d[0];
            int ny = y + d[1];
            dfs(nx, ny);
        }
    }
    
    public static boolean checkMeltable(int x, int y) { // 외부공간이 맞닿아 있는 면이 2개 이상
        int count = 0;
        for (int[] d: direction) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (board[nx][ny] == -1) {
                count++;
            }
        }
        
        return count >= 2;
    }
    
    public static List<Point> findCheese() {
        List<Point> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 1  && checkMeltable(i,j)) {
                    list.add(new Point(i,j));
                }
            }
        }
        return list;
    }
}