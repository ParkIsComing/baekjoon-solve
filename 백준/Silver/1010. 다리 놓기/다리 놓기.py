import math 
cases = int(input())
arr = []
for i in range(cases):
  arr.append(list(map(int,input().split())))

for n,m in arr:
  result = math.factorial(m) / (math.factorial(n) * math.factorial(m-n))
  print(int(result))