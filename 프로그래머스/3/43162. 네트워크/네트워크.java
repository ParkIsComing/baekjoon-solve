import java.util.*;

class Solution {
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (bfs(i, computers)) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean bfs(int node, int[][] computers) {
        if (visited[node]) {
            return false;
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j=0; j<computers.length; j++) {
                if (computers[i][j] == 1 && !visited[j] && i != j) {
                    q.offer(j);
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}