from collections import deque

def solution(n, edge):
    answer = 0
    graph = [[] for _ in range(n+1)]
    distance = [-1]*(n+1) # 최단거리를 나타내기 위한 distance. 초기세팅 -> -1
    distance[1] = 0 # 시작노드 1의 최단거리 -> 0
    
    for a,b in edge:
        graph[a].append(b)
        graph[b].append(a)
    
    
    queue = deque([1])
    while queue:
        v = queue.popleft()
        for i in graph[v]:
            if distance[i] == -1: # 방문하지 않은 노드는
                queue.append(i) # 큐에 추가하고
                distance[i] = distance[v] + 1 #최단거리 업데이트
                    
    answer = distance.count(max(distance))

    return answer