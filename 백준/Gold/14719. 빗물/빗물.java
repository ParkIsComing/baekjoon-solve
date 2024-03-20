import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int h,w;
    static int[] heights;
    static int answer = 0;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        
        heights = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<w; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i=1; i<w-1; i++) { 
            answer += calculate(i);
        }
        
        System.out.println(answer);
    }
    
    public static int calculate(int col) {
        int leftMax = 0;
        int rightMax = 0;
        
        // 왼쪽에서 가장 높은 블록 높이
        for (int i=0; i<col; i++) {
            leftMax = Math.max(leftMax, heights[i]);
        }
        // 오른쪽에서 가장 높은 블록 높이
        for (int i=col+1; i<w; i++) {
            rightMax = Math.max(rightMax, heights[i]);
        }
        
        // 둘 중 낮은 블록 높이
        int limit = Math.min(leftMax, rightMax);
        int amount = Math.max(0, limit - heights[col]); // 현재 위치의 높이가 limit보다 높으면 안 고임
        return amount;
    }
}