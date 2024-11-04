import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

class Puyo {
    int x, y;
    char color;
    
    Puyo(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

public class Main {
    static int r = 12, c = 6;
    static char[][] puyo = new char[r][c];
    static Queue<Puyo> q = new LinkedList<>();
    static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
    static boolean[][] visited = new boolean[r][c];
    
    static boolean bfs() {
        boolean popFlag = false;
        for (int i = 0; i < r; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if (!visited[i][j] && puyo[i][j] != '.') {
                    List<Point> sameColor = new ArrayList<>();
                    sameColor.add(new Point(i,j));
                    q.offer(new Puyo(i,j, puyo[i][j]));
                    visited[i][j] = true;
                    
                    
                    while(!q.isEmpty()) {
                        Puyo p = q.poll();
                        char color = p.color;
                        
                        for (int[] d: direction) {
                            int nx = p.x + d[0];
                            int ny = p.y + d[1];
                            
                            if (nx<0 || nx>=r || ny<0 || ny>=c) {
                                continue;
                            }
                            
                            if (!visited[nx][ny] && puyo[nx][ny] == color) {
                                q.add(new Puyo(nx, ny, color));
                                sameColor.add(new Point(nx,ny));
                                visited[nx][ny] = true;
                            }
                        }
                        
                    }
                    
                    if (sameColor.size() >= 4) {
                        for(Point p: sameColor) {
                            puyo[p.x][p.y] = '.';
                        }
                        popFlag = true;
                    }
                }
            }
        }
        
        return popFlag;
    }
    
    // 큐를 이용해서 빈칸을 채우며 뿌요 내리기
    static void drawPuyo() {
        for (int i=0; i<c; i++) {
            Queue<Puyo> puyoQ = new LinkedList<>();
            
            for (int j=r-1; j>=0; j--) {
                if (puyo[j][i] != '.') {
                    puyoQ.add(new Puyo(j, i, puyo[j][i]));
                    puyo[j][i] = '.';
                }
            }
            
            int idx = r-1;
            while(!puyoQ.isEmpty()) {
                Puyo p = puyoQ.poll();
                puyo[idx][i] = p.color;
                idx--;
            }
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i=0; i<r; i++) {
            String input = br.readLine();
            for (int j=0; j<c; j++) {
                puyo[i][j] = input.charAt(j);
            }
        }
        
        boolean isPopped = true;
        int count = 0;
        while (isPopped) {
            // bfs로 네개이상 없어지는 그룹 찾기 (값 .으로 바꾸면서 이동)
            isPopped = bfs();
            // 큐로 내린다
            drawPuyo();
            
            if (isPopped) {
                drawPuyo();
                count++;
                }
        }
        
        
        System.out.println(count);
        
    }
}
