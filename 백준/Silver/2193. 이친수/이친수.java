import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N == 1) { System.out.println(1); return; }

        long[] ans = new long[Math.max(3, N + 1)];
        ans[1] = 1;  // 1
        ans[2] = 1;  // 10

        for (int n = 3; n <= N; n++) {
            ans[n] = ans[n-1] + ans[n-2];
        }
        System.out.println(ans[N]);
    }
}
