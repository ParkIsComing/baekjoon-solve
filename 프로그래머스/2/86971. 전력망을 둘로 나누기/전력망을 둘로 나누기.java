import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> network = new ArrayList<ArrayList<Integer>>();
    static int min = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        for (int i=0; i<n+1; i++) {
            network.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            network.get(a).add(b);
            network.get(b).add(a); // 양방향
        }
        
        // 백트래킹으로 자르기
        for (int[] wire: wires) {
            network.get(wire[0]).remove(Integer.valueOf(wire[1]));
            network.get(wire[1]).remove(Integer.valueOf(wire[0]));
            cut(wire[0], wire[1]);
            network.get(wire[0]).add(wire[1]);
            network.get(wire[1]).add(wire[0]);
        }
        
        int answer = min;
        return answer;
    }
    
    public void cut(int a, int b) {
        int aNode = countNode(a);
        int bNode = countNode(b);
        
        min = Math.min(min, Math.abs(aNode - bNode));
    }
    
    public int countNode(int root) {
        boolean[] visited = new boolean[network.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        visited[root] = true;
        int count = 0;
        
        while (!q.isEmpty()) {
            int i = q.poll();
            count++;
            
            for (int n : network.get(i)){
                if (!visited[n]) {
                    q.offer(n);
                    visited[n] = true;
                }
            }
        }
        
        return count;
        
    }
}