paper = [[0]*101 for i in range(101)] 

num = int(input()) # 색종이 수

#겹치는 좌표를 0->1로
for _ in range(num):
    a,b = map(int,input().split())
    for i in range(10):
        for j in range(10):
            paper[a+i][b+j] = 1

r = 0
for i in paper:
    r += sum(i)
print(r)
      