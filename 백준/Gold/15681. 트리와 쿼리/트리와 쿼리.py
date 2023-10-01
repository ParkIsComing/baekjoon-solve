import sys

N, R, Q = map(int, sys.stdin.readline().split())
tree  = [[] for _ in range(N+1)]
sys.setrecursionlimit(10**6)

for i in range(N-1):
  a,b = map(int, sys.stdin.readline().split())
  tree[a].append(b)
  tree[b].append(a)

# 각 노드를 루트로 하는 서브트리의 노드 수 업데이트
def countNode(node):
    count[node] = 1
    for n in tree[node]:
        if not count[n]:
            countNode(n)
            count[node] += count[n]
    return

count = [0] * (N+1)

countNode(R)

for i in range(Q):
    print(count[int(sys.stdin.readline())])