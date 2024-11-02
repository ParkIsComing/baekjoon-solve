import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;


class Node {
    int type; //0이면 불, 1이면 지훈
    int sec;
    int x;
    int y;
    
    Node(int type, int sec, int x, int y) {
        this.type = type;
        this.sec = sec;
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int r, c;
    static char[][] map;
    static Queue<Node> q = new LinkedList<>();
    static int[][] direction = {{-1,0},{1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
    
    // 지훈이는 매분마다 이동. 불은 매분마다 확산
    static int bfs() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            int x = n.x;
            int y = n.y;
            int type = n.type;
            int sec = n.sec;
            
            // 탈출(종료) 조건
            if (type == 1) {
                // 탈출 조건 : 미로의 가장자리로 가면 탈출 가능
                if (x == 0 || x == r-1 || y == 0 || y == c-1) {
                    if (map[x][y] == '#' || map[x][y] == 'F') continue;
                    return sec + 1;
                }
            }
            
        
            for (int[] d: direction) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (0>nx || nx>=r || 0>ny || ny>=c) {
                    continue;
                }
                
                // 불이 번지는 경우
                if (map[nx][ny] != '#' && type == 0) {
                    map[nx][ny] = '#';
                    q.offer(new Node(0, sec+1, nx, ny));
                } else if (map[nx][ny] == '.' && type == 1) { // 지훈이 이동하는 경우
                    map[nx][ny] = 'J';
                    q.offer(new Node(1, sec+1, nx, ny));
                }
                
                
            }
            
        }
        return -1; // IMPOSSIBLE
        
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        List<Node> fireList = new ArrayList<>();

        map = new char[r][c];
        for (int i=0; i<r; i++) {
            String input = br.readLine();
            for (int j=0; j<c; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'J') {
                    q.offer(new Node(1, 0, i, j));
                }else if (map[i][j] == 'F') { // 불이 난 위치는 여러개일수도?
                    fireList.add(new Node(0, 0, i ,j));
                }
                
            }
        }
        
        for (Node fire : fireList) {
            q.offer(fire);
        }
        
        int result = bfs();
        if (result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
        
    }
}
