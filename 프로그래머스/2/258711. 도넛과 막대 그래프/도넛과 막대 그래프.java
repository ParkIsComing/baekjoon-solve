import java.util.ArrayList;
import java.util.List;

class Solution {
    static int MAX_VERTEX = 1000001;
    static class Degree {
        int out;
        int in;
        
        Degree() {
            this.out = 0;
            this.in = 0;
        }
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Degree[] vertexDegree = new Degree[MAX_VERTEX]; // 정점 차수 저장
        List<Integer> [] adjacentList = new ArrayList[MAX_VERTEX]; // 인접 간선 저장
        
        for (int i=0; i<MAX_VERTEX; i++) {
            vertexDegree[i] = new Degree();
            adjacentList[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
           
            vertexDegree[start].out += 1; 
            vertexDegree[end].in += 1;
            adjacentList[start].add(end);
        }
        
        // 생성된 정점 찾기 (들어오는 간선 없고, 나가는 간선은 2개 이상)
        for (int vertex=1; vertex<MAX_VERTEX; vertex++) {
            if (vertexDegree[vertex].in == 0 && vertexDegree[vertex].out >= 2) {
                answer[0] = vertex;
                continue;
            }
                
        }
        
        int barGraphs = 0;
        int eightGraphs = 0;
        
        // 생성된 정점에 연결된 간선 제거
        for (int vertex: adjacentList[answer[0]]) {
            vertexDegree[vertex].in -= 1;
            if (vertexDegree[vertex].out == 0 && vertexDegree[vertex].in == 0) {
                barGraphs += 1;
            }
        }
        
        // 모든 정점을 돌면서 간선 수를 확인
        for (int vertex = 1; vertex <MAX_VERTEX; vertex++) {
            int outDegree = vertexDegree[vertex].out;
            int inDegree = vertexDegree[vertex].in;
            // 8자 그래프의 중심점
            if (outDegree == 2 && inDegree == 2) {
                eightGraphs += 1;                
            } else if (outDegree == 0 && inDegree == 1) { // 막대 그래프의 끝점
                barGraphs += 1;
            }
        }
        
        // 도넛 그래프 수 = (전체 그래프 수) - (8자 or 막대 그래프 수)
        answer[1] = vertexDegree[answer[0]].out - barGraphs - eightGraphs;
        answer[2] = barGraphs;
        answer[3] = eightGraphs;
        
        return answer;
        
    }
}