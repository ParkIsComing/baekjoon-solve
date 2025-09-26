import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] w = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            total += w[i];
        }

        // dp[d] = 지금까지 고려한 추들로 "무게 차이 d"를 만들 수 있는가
        int[] dp = new int[total + 1];
        dp[0] = 1;

        for (int weight : w) {
            int[] next = dp.clone(); // 이번 추를 적용해 생기는 새 상태들 반영
            for (int d = 0; d <= total; d++) {
                if (dp[d] == 0) continue;
                int d1 = d + weight;
                if (d1 <= total) next[d1] = 1;     // 왼쪽에 올려 차이 커짐
                int d2 = Math.abs(d - weight);
                next[d2] = 1;                       // 오른쪽에 올려 차이 줄임
            }
            dp = next;
        }

        int q = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int m = Integer.parseInt(st.nextToken());
            sb.append((m <= total && dp[m] == 1) ? "Y " : "N ");
        }

        System.out.println(sb.toString());
    }
}
