import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] time; 
    static int n,k, minTime = Integer.MAX_VALUE;
    static int count = 0;
    
    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        time[n] = 0;
        while(!q.isEmpty()) {
            int i = q.poll();
            
            if (minTime < time[i]) { // 최소시간보다 오래 걸리는 경우
                return;
            }

            if (i == k) {
                count++;
                minTime = time[i];
            }

            if (i-1 >= 0) {
                if (time[i-1] == -1 || time[i-1] == time[i] + 1){
                   time[i-1] = time[i] + 1;
                    q.offer(i-1); 
                }
            }
            if (i+1 <= 100000) {
                if (time[i+1] == -1 || time[i+1] == time[i] +1) {
                    time[i+1] = time[i] + 1;
                    q.offer(i+1);
                }
            }
            if (i*2 <= 100000) {
                if (time[i*2] == -1 || time[i*2] == time[i] +1){
                    time[i*2] = time[i] + 1;
                    q.offer(i*2);
                }
            }
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); 
        k = Integer.parseInt(st.nextToken()); 

        time = new int[100001];
        Arrays.fill(time, -1);
        bfs();
        System.out.println(minTime);
        System.out.println(count);
    }
}