import sys
input = sys.stdin.readline

input_a = input().strip()
input_b = input().strip()
len_a = len(input_a)
len_b = len(input_b)
a = [0] * (len_a + 1)
b = [0] * (len_b + 1)


for i in range(1, len_a + 1):
    a[i] = input_a[i-1]

for i in range(1, len_b + 1):
    b[i] = input_b[i-1]


dp = [[0] * (len_a + 1) for _ in range(len_b + 1)]

# b의 모든 문자열을 a의 문자열과 비교한다.
for i in range(1, len_b + 1):
    for j in range(1, len_a + 1):
        if b[i] == a[j]:
            dp[i][j] = dp[i-1][j-1] + 1
        else:
            dp[i][j] = max(dp[i-1][j] , dp[i][j-1])

print(dp[len_b][len_a])