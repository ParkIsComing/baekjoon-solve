import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[] data;
    static boolean[] visited;
    static List<Integer> result;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        data = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            data[i] = scanner.nextInt();
        }
        
        result = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            dfs(i, i);
        }
        
        System.out.println(result.size());
        result.stream().sorted().forEach(System.out::println);
    }

    static void dfs(int v, int start) {
        visited[v] = true;
        int w = data[v];
        if (!visited[w]) {
            dfs(w, start);
        } else if (visited[w] && w == start) {
            result.add(w);
        }
    }
}
