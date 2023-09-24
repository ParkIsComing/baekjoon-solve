n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [False] * (n+1)
result = int(1e9)

def dfs(m, idx):
    global result
    if m == n//2:
        ability1 = 0
        ability2 = 0
        for i in range(n):
            for j in range(n):
                if visited[i] and visited[j]:
                    ability1 += graph[i][j]
                elif not visited[i] and not visited[j]:
                    ability2 += graph[i][j]
        result = min(result, abs(ability1-ability2))
        return
    
    for i in range(idx, n):
        if not visited[i]:
            visited[i] = True
            dfs(m+1, i+1)
            visited[i] = False
        
dfs(0,0)
print(result)