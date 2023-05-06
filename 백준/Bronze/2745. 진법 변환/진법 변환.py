N,B = input().split()

B = int(B)
N = list(N)
N.reverse()
n = ''.join(N)
number = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
sum =0
j=0

for i in n:
  sum += int(number.index(i)) * B**j
  j+=1
  
print(sum)

# 간단한 방법
# print(int(N,int(B)))
