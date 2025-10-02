import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] seq;
    static int[] lis;    
    static int[] level;  // for backtrace

    static int findLowerBound(int end, int target) {
        int l = 1, r = end;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (lis[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        seq = new int[n+1];
        level = new int[n+1];
        lis = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int lisIdx = 1;
        lis[lisIdx] = seq[1];
        level[1] = lisIdx;

        for (int i = 2; i <= n; i++) {
            int cur = seq[i];
            if (cur > lis[lisIdx]) {
                lisIdx++;
                lis[lisIdx] = cur;
                level[i] = lisIdx;
            } else {
                int idx = findLowerBound(lisIdx + 1, cur);
                lis[idx] = cur;
                level[i] = idx;
            }
        }

        int length = lisIdx;
        System.out.println(length);

        int[] ans = new int[length+1];
        int need = length;
        for (int i = n; i >= 1 && need >= 1; i--) {
            if (level[i] == need) {
                ans[need] = seq[i];
                need--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            if (i > 1) sb.append(' ');
            sb.append(ans[i]);
        }
        System.out.println(sb.toString());
    }
}
