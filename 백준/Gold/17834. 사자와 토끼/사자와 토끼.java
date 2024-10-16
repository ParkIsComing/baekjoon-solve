import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static ArrayList<ArrayList<Integer>> board = new ArrayList<>();
    static int[] colors;

    static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = 1;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int x: board.get(cur)) {
                if (colors[x]!=0 && colors[x] == colors[cur]) { // 이분 그래프가 아닌 경우
                    return false;
                }
                
                if(colors[x] == 0) {
                    colors[x] = colors[cur] == -1 ? 1: -1;
                    q.offer(x);
                }
            }
        }
        return true;
    }
    
    static int countEndlessCase() {
        int group1 = 0;
        int group2 = 0;
        for (int i=1; i<=n; i++) {
            if (colors[i] == 1) {
               group1 += 1;
            } else {
               group2 += 1;
            }
        }
        return group1*group2*2;
    }
    
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      colors = new int[n+1]; // 0으로 초기화
      
      for (int i=0; i<=n; i++) {
          board.add(new ArrayList<>());
      }
      
      // 양방향 그래프
      for (int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          board.get(a).add(b); 
          board.get(b).add(a);
      }
      
      if (bfs(1)) { // 이분 그래프. 다른 그룹인 순열 구하기
          System.out.println(countEndlessCase());
      } else {
          System.out.println(0);
      }
    }
}
    
    