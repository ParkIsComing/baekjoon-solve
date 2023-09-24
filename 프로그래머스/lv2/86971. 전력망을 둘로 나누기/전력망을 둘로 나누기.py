from collections import deque

def solution(n, wires):
    result = 0
    result = n #초기값 최대로
    graph = [[] for _ in range(n+1)]
    for a,b in wires:
        graph[a].append(b)
        graph[b].append(a)
        
    def bfs(start):
        queue = deque([start])
        visited = [0] * (n+1)
        visited[start] = 1
        cnt = 1
        while queue:
            v = queue.popleft()
            for i in graph[v]:
                if not visited[i]:
                    queue.append(i)
                    visited[i] = 1
                    cnt += 1
        return cnt
        
    for a,b in wires:
        graph[a].remove(b) #하나씩 끊어보기
        graph[b].remove(a)
        
        result = min(abs(bfs(a)-bfs(b)), result)
        
        graph[a].append(b)
        graph[b].append(a)
        
    return result




    