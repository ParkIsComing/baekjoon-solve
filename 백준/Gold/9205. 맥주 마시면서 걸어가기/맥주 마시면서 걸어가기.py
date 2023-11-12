import sys
from collections import deque
input = sys.stdin.readline

def bfs():
    q = deque()
    q.append((home_x, home_y))
    while q:
        x, y = q.popleft()
        if abs(x - feast_x) + abs(y - feast_y) <= 1000: #현재 노드에서 페스티벌까지 가는 맨하탄 거리가 1000 이하이면 happy
            print("happy") 
            return
        for i in range(cvs_num): # 아니면 편의점 거쳐야
            if not visited[i]:
                new_x, new_y = cvs[i]
                if abs(x - new_x) + abs(y - new_y) <= 1000: # 현재 노드에서 편의점까지의 맨하탄 거리가 1000 이하이면 
                    q.append((new_x, new_y)) # 편의점 노드를 데크에 추가
                    visited[i] = 1 # 해당 노드는 방문 처리
    print("sad")
    return

t = int(input())
for i in range(t):
    cvs_num = int(input())
    cvs = []
    home_x, home_y = map(int, input().split())
    for j in range(cvs_num): # 편의점 좌표 입력 받기
        x, y = map(int, input().split())
        cvs.append([x,y])
    feast_x, feast_y = map(int, input().split())
    visited = [0] * (cvs_num+1)
    
    bfs()

