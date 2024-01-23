import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

class Virus implements Comparable<Virus> {
    private int number;
    private int second;
    private int x;
    private int y;
    
    public Virus (int number, int second, int x, int y) {
        this.number = number;
        this.second = second;
        this.x = x;
        this.y = y;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getSecond() {
        return this.second;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public int compareTo(Virus other) {
        if (this.number < other.number) {
            return -1;
        }
        return 1;
    }
}

public class Main {
    public static int n,k,s,x,y;
    public static int[][] directions = {{1,0},{-1,0}, {0,-1}, {0,1}};
    public static ArrayList<Virus> viruses = new ArrayList<Virus>();
    
    public static int solution(int[][] arr) {
        
        Queue<Virus> q = new LinkedList<Virus>();
        
        // 정렬된 바이러스 정보를 큐에 삽입
        for (Virus v : viruses) {
            q.offer(v);
        }
        
        while(!q.isEmpty()) {
            Virus v = q.poll();
            if (v.getSecond() == s) { //s초가 흘렀으면 종료
                break;
            }
            
            for (int[] d : directions) {
                int nx = v.getX() + d[0];
                int ny = v.getY() + d[1];
                
                if (0 < nx && nx <= n && 0 < ny && ny<=n) {
                    if (arr[nx][ny] == 0) { // 전염되지 않은 곳이면 전염시킴
                        arr[nx][ny] = v.getNumber();
                        q.offer(new Virus(v.getNumber(), v.getSecond() +1, nx, ny));
                    }
                }
            }
            
        }
        
        return arr[x][y];
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // n * n 시험관
        k = Integer.parseInt(st.nextToken()); // 바이러스 종류
        
        int[][] arr = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) { // 바이러스 존재하면 저장
                    viruses.add(new Virus(arr[i][j], 0, i, j));
                }
            }
        }
        
        // 낮은 번호가 먼저 증식하도록 오름차순 정렬
        Collections.sort(viruses);
        
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // s초 후
        x = Integer.parseInt(st.nextToken()); // (x,y)
        y = Integer.parseInt(st.nextToken()); 
        
        System.out.println(solution(arr));

    }
}