import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer> seq = new ArrayList<>();
    static ArrayList<Integer> lis = new ArrayList<>(); // tails
    static int[] level; // level[i] = A[i]가 lis에서 차지한 위치(0-based)

    static int findLowerBound(int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (lis.get(mid) >= target) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) seq.add(sc.nextInt());

        level = new int[n];

        lis.add(seq.get(0));
        level[0] = 0;
        int lisIdx = 0;

        for (int i = 1; i < n; i++) {
            int x = seq.get(i);
            if (x > lis.get(lisIdx)) {
                lis.add(x);
                lisIdx++;
                level[i] = lisIdx;
            } else {
                int idx = findLowerBound(0, lisIdx + 1, x);
                lis.set(idx, x);
                level[i] = idx;
            }
        }

        int length = lisIdx + 1;
        System.out.println(length);

        // 역추적
        int[] ans = new int[length];
        int need = length - 1;
        int last = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0 && need >= 0; i--) {
            int val = seq.get(i);
            if (level[i] == need && val < last) {
                ans[need] = val;
                last = val;
                need--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(ans[i]);
        }
        System.out.println(sb.toString());
    }
}
