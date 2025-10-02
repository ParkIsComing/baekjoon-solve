import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] seq;
    static int[] lis;    

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

        seq = new int[n+1][2];
        lis = new int[n+1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            seq[i][0] = Integer.parseInt(st.nextToken());
            seq[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(seq, (o1,o2) -> {
            return o1[0] - o2[0];
        });

        int lisIdx = 1;
        lis[lisIdx] = seq[1][1];

        for (int i = 2; i <= n; i++) {
            int cur = seq[i][1];
            if (cur > lis[lisIdx]) {
                lisIdx++;
                lis[lisIdx] = cur;
            } else {
                int idx = findLowerBound(lisIdx + 1, cur);
                lis[idx] = cur;
            }
        }

        int answer = n - lisIdx;
        System.out.println(answer);

    }
}
