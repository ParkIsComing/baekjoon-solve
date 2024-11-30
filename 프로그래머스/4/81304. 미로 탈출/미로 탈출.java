import java.util.*;

class Solution {
    int[][] matrix;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        matrix = new int[n+1][n+1];
        
        //서로 다른 두 방 사이에 직접 연결된 길이 여러 개 존재할 수도 있습니다.
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            if (matrix[from][to] == 0 || matrix[from][to] > road[2]) {
                matrix[from][to] = road[2]; // 최단시간 저장
            }
        }
        
        Map<Integer, Integer> trapMap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], i);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0, 0}); // {current room, total cost, trap state bitmask}

        // 비트마스크로 표현 가능한 상태의 수는 2^(traps.length)
        int[][] visited = new int[n + 1][1 << traps.length];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[start][0] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int room = cur[0];
            int cost = cur[1];
            int state = cur[2];

            // 이미 방문한 상태가 더 적은 비용으로 처리된 경우
            if (visited[room][state] < cost) continue;
            
            // 도착 방에 도달했을 때 최소 비용 반환
            if (room == end) return cost;
            
            boolean isRoomTrap = trapMap.containsKey(room) && (state & (1 << trapMap.get(room))) != 0;
            for (int next = 1; next <= n; next++) {
                if (matrix[room][next] == 0 && matrix[next][room] == 0) continue; // 연결되지 않은 경우
                
                // 다음 방이 함정이면 true, 아니면 false
                boolean isNextTrap = trapMap.containsKey(next) && (state & (1 << trapMap.get(next))) != 0;
                boolean reversed = isRoomTrap ^ isNextTrap; // 둘 다 true이거나 false이면 원래 방향 그대로, 아니면 역방향(XOR)
                
                int nextCost;
                if (!reversed && matrix[room][next] > 0) { // 원래 방향
                    nextCost = matrix[room][next];
                } else if (reversed && matrix[next][room] > 0) { // 역방향
                    nextCost = matrix[next][room];
                } else {
                    continue; // 이동할 수 없는 경우
                }

                int newState = state;
                if (trapMap.containsKey(next)) {
                    newState ^= (1 << trapMap.get(next)); // 다음 방이 함정이면 상태 변경
                }

                int totalCost = cost + nextCost;
                if (totalCost < visited[next][newState]) {
                    visited[next][newState] = totalCost;
                    pq.add(new int[]{next, totalCost, newState});
                }
            }
        }
        return -1; // 도달 불가능
    }
}