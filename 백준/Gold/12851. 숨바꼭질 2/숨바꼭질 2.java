import java.io.*;
import java.util.*;

public class Main {
	// 도달할 수 있는 최대 위치 100,000을 상수로 나타냅니다.
    public static final int MAX = 100000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 수빈이와 동생의 위치가 같다면 바로 결과를 출력합니다. 
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }
		// time 배열은 각 위치에 도달하기 위한 최소 시간을 나타냅니다.
        int[] time = new int[MAX + 1];
        // count 배열은 각 위치에 도달하는 방법의 수를 나타냅니다. 0으로 초기화됩니다.
        int[] count = new int[MAX + 1];

        // time 배열을 -1로 초기화하여 방문하지 않은 상태를 나타냅니다.
        Arrays.fill(time, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        // 시작점에 도달하기 위한 최소 시간은 0, 도달하는 방법의 수는 1입니다.
        time[N] = 0;
        count[N] = 1;

        // 큐를 이용해 목표 위치까지 도달하는 최단 경로와 시간을 찾습니다.
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            int[] nextPoints = new int[]{current - 1, current + 1, current * 2};
            for (int next : nextPoints) {
                if (next >= 0 && next <= MAX) {
                    // 처음 방문하는 경우, 해당 위치에 도달하는 데 걸린 시간과 도달하는 방법의 수를 갱신합니다.
                    if (time[next] == -1) { 
                        queue.add(next);
                        time[next] = time[current] + 1;
                        count[next] = count[current];
                    // 처음 방문하는 경우가 아니면, 도달하는 데 걸린 시간이 같은 경우에만 도달하는 방법의 수를 갱신합니다.
                    } else if (time[next] == time[current] + 1) {
                        count[next] += count[current];
                    }
                }
            }
        }

	    // 결과를 출력합니다.
        System.out.println(time[K]);
        System.out.println(count[K]);
    }
}