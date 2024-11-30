import java.util.*;
                  
class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    Set<List<Integer>> stateCache = new HashSet<>();
    static final int SHEEP = 0;
    static final int WOLF = 1;
    
    class State {
        int node;
        int sheepCount;
        int wolfCount;
        List<Integer> candidates = new ArrayList<>();
        
        public State(int node, int sheepCount, int wolfCount, List<Integer> candidates) {
            this.node = node;
            this.sheepCount = sheepCount;
            this.wolfCount = wolfCount;
            this.candidates = candidates;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int maxCount = 0;
        
        // 그래프 초기화
        for (int i=0; i<info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }
        
        
        Stack<State> states = new Stack<>();
        State start = new State(0,0,0, new ArrayList<>(graph.get(0))); // 시작은 0
        states.push(start);
        stateCache.add(start.candidates);
        
        while (!states.isEmpty()) {
            State cur = states.pop();
            
            // 동물 수 업데이트
            if (info[cur.node] == SHEEP) {
                cur.sheepCount++;
            } else {
                cur.wolfCount++;
            }
            
            // 더이상 이동 불가한 경우 (양 수 < 늑대 수 || 이동 가능한 노드 없음)
            if (cur.sheepCount <= cur.wolfCount || cur.candidates.size() == 0) {
                maxCount = Math.max(maxCount, cur.sheepCount);
                continue;
            }
            
            // 다음 노드로 이동
            for (int next: cur.candidates) {
                List<Integer> addedCandidates = graph.get(next);
                List<Integer> newCandidates = new ArrayList<>(cur.candidates);
                newCandidates.remove(Integer.valueOf(next));
                for (int node : addedCandidates) {
                    newCandidates.add(node);
                }
                State nextState = new State(next, cur.sheepCount, cur.wolfCount, newCandidates);
                
                //  현재 위치가 달라도 다음 갈 수 있는 노드 목록이 같으면 같은 상태
                if (stateCache.contains(nextState.candidates)) {
                    continue;
                }
                
                states.push(nextState);
                stateCache.add(nextState.candidates);
                
            }   
        }
        return maxCount;
    }
}