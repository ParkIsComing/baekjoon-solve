import sys
from collections import deque

n, k = map(int, input().split()) # 올리는 위치: 1, 내리는 위치 : n,내구도 0인 칸 개수가 k개 이상이면 종료
d = deque(list(map(int, sys.stdin.readline().split())))#내구도 값 리스트
robot = deque([False] * n) #로봇이 있는 위치는 True3
turn = 0


while True:
    # 벨트와 로봇 함께 한칸 회전
    d.rotate(1)
    robot.rotate(1)
    turn += 1

    robot[n-1] = False # 내리는 위치에 도달하면 즉시 내린다

    # 먼저 올라간 로봇부터 이동
    
    for i in range(n-2, -1, -1):
        if robot[i]== True and robot[i+1] == False and d[i+1] >= 1:
            robot[i+1] = True
            robot[i] = False
            d[i+1] -= 1
        
    robot[n-1] = False
    
    # 로봇 올리기
    if d[0] > 0:
        robot[0] = True
        d[0] -= 1

    # 내구성이 0인 곳 k개 이상이면 종료    
    if d.count(0) >= k:
        break

print(turn)
