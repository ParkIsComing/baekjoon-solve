from collections import deque


def solution(n, computers):
    answer = 0
    visited = [0] * (n+1)
    for i in range(n):
        if not visited[i]:
            bfs(n, i, visited, computers)
            answer += 1
    return answer

def bfs(n, i, visited, computers):
    visited[i] = 1
    q = deque()
    q.append(i)
    while q:
        i = q.popleft()
        visited[i] = 1
        for j in range(n):
            if i != j and computers[i][j] == 1:
                if not visited[j]:
                    q.append(j)
    