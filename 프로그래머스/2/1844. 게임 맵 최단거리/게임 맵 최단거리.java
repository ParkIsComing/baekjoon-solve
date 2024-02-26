import java.awt.Point;
import java.util.*;

class Solution {
    int[][] dist;
    int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    int n,m;
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        dist = new int[n][m];
        dist[0][0] = 1;
        bfs(0,0,maps);
        answer = dist[n-1][m-1] == 0? -1: dist[n-1][m-1];
        return answer;
    }
    
    public void bfs(int x, int y, int[][] maps) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        
        while(!q.isEmpty()) {
            Point p = q.poll();
            
            for (int[] d : direction) {
                int nx = d[0] + p.x;
                int ny = d[1] + p.y;
                
                if (nx<0 || nx>=n || ny<0 || ny>=m) {
                    continue;
                }
                
                if (dist[nx][ny] == 0 && maps[nx][ny] == 1) {
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
            
        }
    }
}