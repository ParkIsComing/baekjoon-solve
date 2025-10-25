import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        for (int i=1; i<n+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0; // 어디까지 넣었는지 나타냄
        StringBuilder sb = new StringBuilder();

        for (int i=1; i<=n; i++) {
            int target = arr[i];

            while (count < target) {
                stack.push(++count);
                sb.append("+\n");
            }

            // 뽑을게 없으면
            if (stack.isEmpty() || !stack.peek().equals(target)) {
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append("-\n");;
        }

        System.out.println(sb);
    }

}