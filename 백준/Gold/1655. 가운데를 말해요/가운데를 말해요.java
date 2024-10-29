import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> leftQ = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        PriorityQueue<Integer> rightQ = new PriorityQueue<>(); // 오름차순
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (leftQ.size() == rightQ.size()) {
                leftQ.offer(tmp);
            } else {
                rightQ.offer(tmp);
            } 
            
            if (!rightQ.isEmpty()) {
                if (leftQ.peek() > rightQ.peek()) {
                    int lpeek = leftQ.poll();
                    int rpeek = rightQ.poll();
                    
                    rightQ.offer(lpeek);
                    leftQ.offer(rpeek);
                }
            }
            
            sb.append(leftQ.peek());
            sb.append('\n');
        }
      
        System.out.println(sb);
    }
}
    
    