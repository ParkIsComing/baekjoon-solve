import sys
input = sys.stdin.readline

k, n = map(int, input().split())
list = [int(input()) for _ in range(k)]

start, end = 1, max(list)

while start <= end:
    mid = (start + end) // 2
    count = 0
    for i in list:
        count += i // mid
    
    if count >= n:
        start = mid + 1
    else:
        end = mid -1

print(end)