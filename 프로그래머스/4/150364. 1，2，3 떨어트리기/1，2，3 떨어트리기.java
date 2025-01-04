import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int n = edges.length + 1;
        int remainLeafCount = 0;
        
        // 트리 생성 및 자식 노드 정렬
        ArrayList<Integer>[] tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
        
        // 숫자가 지나갈 때마다 다음 큰 자식 노드로 새로 연결
        for (int i = 1; i <= n; i++) {
            Collections.sort(tree[i]);
        }
        
        // 각 노드별 상태 관리
        int[] visitCount = new int[n + 1];    // 노드 방문 횟수
        int[] numberCount = new int[n + 1];   // 노드에 쌓인 숫자 개수
        boolean[] isReachable = new boolean[n + 1];  // target 값 도달 가능 여부
        List<Integer> dropOrder = new ArrayList<>();  // 리프 노드 방문 순서
        
        // 리프 노드 개수 계산
        for (int i = 1; i <= n; i++) {
            if (tree[i].isEmpty() && target[i-1] > 0) {
                remainLeafCount++;
            }
        }
        
        // 리프 노드까지 숫자 떨어뜨리기
        while (remainLeafCount > 0) {
            int node = 1;  // 루트 노드에서 시작
            
            // 리프 노드까지 떨어뜨리기
            while (tree[node].size() > 0) {
                node = tree[node].get(numberCount[node]++ % tree[node].size()); // 지나가면서 연결 간선도 변경
            }
            
            // 리프 노드 도달
            numberCount[node]++;
            dropOrder.add(node);
            
            // target대로 숫자의 합을 만들 수 없는 경우 return -1
            if (numberCount[node] * 1 > target[node-1]) {
                return new int[]{-1};
            }
            
            // target대로 숫자의 합을 만들 수 있는 경우이면서 가장 적은 개수의 숫자 사용
            if (!isReachable[node] && target[node-1] <= 3 * numberCount[node]) {
                isReachable[node] = true;
                remainLeafCount--;
            }
        }
        
    
        
        // 전부 target에 도달 가능하면 그다음 떨어뜨린 순서대로 분배
        ArrayList<Integer> result = new ArrayList<>();
        int[] remainTarget = target.clone(); // leafNode 인덱스의 원소를 0으로 만드는 게 목표
        
        for (int leafNode : dropOrder) {
            numberCount[leafNode]--;
            
            // 사전 순으로 가장 빠른 경우
            for (int val = 1; val <= 3; val++) {
                int remainSum = remainTarget[leafNode-1] - val;
                // 남은 방문으로 목표값을 만들 수 있는지 확인
                if (numberCount[leafNode] * 3 >= remainSum && 
                    numberCount[leafNode] * 1 <= remainSum) {
                    result.add(val);
                    remainTarget[leafNode-1] = remainSum;
                    break;
                }
            }
        }
        
        // 최종 검증
        for (int i = 1; i <= n; i++) {
            if (remainTarget[i-1] != 0) {
                return new int[]{-1};
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}