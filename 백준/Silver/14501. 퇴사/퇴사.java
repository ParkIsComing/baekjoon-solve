import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

class Plan {
    int length;
    int price;
    
    Plan (int length, int price) {
        this.length = length;
        this.price = price;
    }
}

public class Main {
    static int n;
    static ArrayList<Plan>[] list;
    static int maxSum = 0;
    
    public static int dp() {
        int[] d = new int[n+1];
        
        for (int i=1; i<=n; i++) {
            for (Plan n : list[i]){ // 없는 경우?
                for (int j=0; j<i-n.length+1; j++) {
                    d[i] = Math.max(d[i], d[j] + n.price);
                }
            }
        }
        
        return Arrays.stream(d).max().getAsInt();
        
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[1015];
        
        for (int i=0; i<list.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            list[i+T-1].add(new Plan(T, P)); // (기간, 금액)
        }
        
        System.out.println(dp());
    }
}