import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer> seq = new ArrayList<>();
    static ArrayList<Integer> lis = new ArrayList<>();
    
    static int findLowerBound(int start, int end, int target) {
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (lis.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            seq.add(sc.nextInt());
        }
        
        int lisIdx = 0;
        lis.add(seq.get(0));
        for (int i=1; i<n; i++) {
            if (seq.get(i) > lis.get(lisIdx)) {
                lisIdx++;
                lis.add(seq.get(i));
            } else {
                int idx = findLowerBound(0, lisIdx, seq.get(i));
                lis.set(idx, seq.get(i));
            }
        }
        
        System.out.println(lis.size());
    }
    
}