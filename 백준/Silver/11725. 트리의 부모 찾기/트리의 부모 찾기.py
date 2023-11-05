import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

n = int(input())

visited = [0] * (n+1)
graph = [[] for _ in range (n+1)]

for _ in range(1, n):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(start):
    for i in graph[start]:
        if visited[i] == 0:
            visited[i] = start
            dfs(i)

dfs(1)

# 결과 출력
for i in range(2, n+1):
    print(visited[i])