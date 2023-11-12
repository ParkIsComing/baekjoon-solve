from collections import deque


def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    graph = [[-1] * 102 for i in range(102)] # 2배로 늘려서 구한다.
    visited = [[1] * 102 for i in range(102)]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    q = deque()
    
    
# graph 세팅 : 테두리(1), 테두리 밖(-1), 테두리 안(0)
    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x*2, r) #r의 각 값을 2배로 만들어서 할당 (나중에 //2 해서 answer 구함)
        for i in range(x1, x2+1):
            for j in range(y1, y2+1):
                if x1 < i < x2 and y1 < j < y2: # 테두리는 제외하고 0으로 채우기
                    graph[i][j] = 0
                elif graph[i][j] != 0: # 다른 직사각형의 내부가 아님 & 테두리 -> 1로 채움
                    graph[i][j] = 1
#캐릭터와 아이템 좌표도 2배로
    cx, cy, ix, iy = 2*characterX, 2*characterY, 2*itemX, 2*itemY
    q.append((cx, cy))
    
    while q:
        x, y = q.popleft()
        
        if x == ix and y == iy: # 아이템 줍기(도착)
            answer = visited[x][y] // 2
            break
        for k in range(4): # 아니면 다음 이동할 곳 탐색
            nx = x + dx[k]
            ny = y + dy[k]
            
            if graph[nx][ny] == 1 and visited[nx][ny] == 1:
                visited[nx][ny] += visited[x][y]
                q.append((nx, ny))

    return answer