import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int type;
    int sec;
    int x;
    int y;
    
    Node (int type, int sec, int x, int y) {
        this.type = type;
        this.sec = sec;
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, e, s, x, y;
    static int[][] map;
    static ArrayList<Node> list = new ArrayList<>();
    static Queue<Node> q = new LinkedList<>();
    static int[][] direction = {{-1,0}, {1, 0}, {0,1}, {0,-1}};
    
    public static void bfs() {
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (cur.sec == s) {
                break;
            }
            
            for (int[] d : direction) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1]; 
                
                if (0 > nx || nx > n || 0 > ny || ny > n) {
                    continue;
                }
                
                if (map[nx][ny] == 0) {
                    map[nx][ny] = cur.type;
                    q.offer(new Node(cur.type, cur.sec+1, nx, ny));
                }
            }
        }
        
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken()); // n*n map
        e = Integer.parseInt(st.nextToken()); // 바이러스 종류
        
        map = new int[n+1][n+1];
    
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine()); 
            for (int j=1; j<=n; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if (input != 0) { // 바이러스 정보 저장
                    list.add(new Node(input, 0, i, j));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.type - n2.type; // 번호가 작은 바이러스부터 확산
            }
        });
        
        // 큐에 다 넣고 시작
        for (Node n : list) {
            q.offer(n);
        }
        
        bfs();

        System.out.println(map[x][y]);
    }
}