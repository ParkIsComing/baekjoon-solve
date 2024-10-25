import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<List<Integer>> numbers = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            numbers.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[N + 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            numbers.get(A).add(B);
            inDegree[B]++;
        }

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                priorityQueue.add(i);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int temp = priorityQueue.poll();
            results.add(temp);
            for (int next : numbers.get(temp)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    priorityQueue.add(next);
                }
            }
        }

        for (int result : results) {
            System.out.print(result + " ");
        }
    }
}
