import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static int[][] map;
    static int INF = 1234567;
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());// 도시 수
        m = Integer.parseInt(br.readLine()); // 여행 계획한 도시 수
        
        StringTokenizer st;
        map = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input == 0 ? INF : 1;
            }
        }
        
        for (int i=1; i<=n; i++) {
            map[i][i] = 0;
        }
        
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        
        
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        
        int start = list.get(0);
        for (int i=1; i<m; i++) {
            int dest = list.get(i);
            if (map[start][dest] == INF) {
                System.out.println("NO");
                return;
            }
            start = dest;
        }
        
        System.out.println("YES");
        
 
    }
}