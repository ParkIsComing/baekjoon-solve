count = input()
mylist = list(map(int, input().split())) 
x = 0

result = []


  

def isprimeNumber(i):
  for v in range(2,i-1):
    if(i%v==0):
      return False
  return True

  
for i in mylist:
  if(i==1):
    continue
  x = isprimeNumber(i)
  if(x==True):
    result.append(i)


print(len(result))
  


