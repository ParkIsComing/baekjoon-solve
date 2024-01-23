from collections import deque

n, k = map(int, input().split())

graph = [] # 전체 보드 정보
data = [] # 바이러스 정보
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(n):
    graph.append(list(map(int, input().split())))
    for j in range(n):
        if graph[i][j] != 0: # 바이러스가 존재하면 바이러스 정보 저장
            data.append((graph[i][j], 0, i, j)) # 바이러스 종류, 시간, x, y
            
data.sort() # 낮은 번호의 바이러스부터 증식
q = deque(data)

target_s, target_x, target_y = map(int, input().split())

while q:
    virus, s, x, y = q.popleft()
    if s == target_s:
        break
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx and nx < n and 0 <= ny and ny < n:
            if graph[nx][ny] == 0: # 빈 공간(방문하지 않음)이면 바이러스 넣기
                graph[nx][ny] = virus
                q.append((virus, s+1, nx, ny))
                
                
print(graph[target_x-1][target_y-1])
