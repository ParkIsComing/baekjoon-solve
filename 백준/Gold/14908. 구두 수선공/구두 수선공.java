import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Job {
    int idx;
    int t;
    int s;

    public Job(int idx, int t, int s) {
        this.idx = idx;
        this.t = t;
        this.s = s;
    }
}

public class Main {
    static int n;
    static List<Job> orders;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        orders = new ArrayList<>();
        StringTokenizer st;
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            orders.add(new Job(i, t, s));
        }

    }

    static void solution() {

        orders.sort((a,b) -> {
            int task1 = a.s * b.t;
            int task2 = b.s * a.t;
            if (task1 != task2) return Integer.compare(task2, task1); //  보상금/작업기간이 큰 작업부터
            return Integer.compare(a.idx, b.idx);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(orders.get(i).idx);
            sb.append(" ");
        }

        System.out.println(sb);
    }

}
