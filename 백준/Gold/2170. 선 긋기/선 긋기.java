import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class Main {
    static int N;
    
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] input = new int[]{a,b};
            q.offer(input);
        }
        
        int[] line = q.poll();
        int end = line[1];
        int start = line[0];
        int total = 0;
        while (!q.isEmpty()) {
            line = q.poll();
            if (line[0] <= end) { // 연장되는 경우
                end = Math.max(end, line[1]); // end는 이전의 끝값과 지금의 끝값중 더 큰 값으로 해야 함 

            } else { // 단절되는 경우
                total += end - start; 
                start = line[0];
                end = line[1];
            }
        }
        
        total += end - start;
        System.out.println(total);
    }
}