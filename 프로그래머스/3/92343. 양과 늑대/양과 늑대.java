import java.util.*;
                  
class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    Set<List<Integer>> stateCache = new HashSet<>();
    static final int SHEEP = 0;
    static final int WOLF = 1;
    int maxCount = 0;
    
    public int solution(int[] info, int[][] edges) {
        
        // 그래프 초기화
        for (int i=0; i<info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }
        
        List<Integer> initialCandidates = new ArrayList<>(graph.get(0));
        stateCache.add(initialCandidates);
        dfs(0,0,0, initialCandidates, info);
        
        return maxCount;
    }
    
    private void dfs(int node, int sheepCount, int wolfCount, List<Integer> candidates, int[] info) {
        // 동물 수 업데이트
        if (info[node] == SHEEP) {
            sheepCount++;
        } else {
            wolfCount++;
        }
        
        // 양의 수가 늑대 수보다 작아지면 탐색 중단
        if (wolfCount >= sheepCount) {
            return;
        }
        
         // 최대 양 수 업데이트
        maxCount = Math.max(maxCount, sheepCount);
        
        for (int next: candidates) {
            List<Integer> addedCandidates = graph.get(next);
            List<Integer> newCandidates = new ArrayList<>(candidates);
            newCandidates.remove(Integer.valueOf(next));
            for (int c : addedCandidates) {
                newCandidates.add(c);
            }

            //  현재 위치가 달라도 다음 갈 수 있는 노드 목록이 같으면 같은 상태
            if (stateCache.contains(newCandidates)) {
                continue;
            }

            stateCache.add(newCandidates);
            dfs(next, sheepCount, wolfCount, newCandidates, info);

        }   

    }
}