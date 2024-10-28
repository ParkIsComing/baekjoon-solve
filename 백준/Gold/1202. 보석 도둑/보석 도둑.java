import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,K;

    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 보석 개수
        K = Integer.parseInt(st.nextToken()); // 가방 개수
        
        int[][] jewels = new int[N][2];
        int[] bags = new int[K];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i][0] = m; // 보석 무게
            jewels[i][1] = v; // 보석 가격
        }
        
        for (int i=0; i<K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(jewels, (o1, o2) -> {
            return o1[0]-o2[0];
        });
        
        Arrays.sort(bags);
        int idx = 0;
        long answer= 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 가치가 큰 게 앞으로
        for (int bag: bags) {
            
            while (idx<N && jewels[idx][0] <= bag) {
                pq.offer(jewels[idx][1]);
                idx++;
            }
            
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
            
        }
      
        System.out.println(answer);
      
    }
}
    
    