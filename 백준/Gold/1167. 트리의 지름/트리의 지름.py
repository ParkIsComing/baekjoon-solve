from collections import deque, defaultdict

class Node:
    def __init__(self, vertex, dist):
        self.vertex = vertex
        self.dist = dist

def BFS(graph, start):
    Q = deque()
    Q.append(Node(start, 0))
    visited = set()
    visited.add(start)

    max_dist = 0
    max_vertex = start

    while Q:
        current = Q.popleft()

        if current.dist > max_dist:
            max_dist = current.dist
            max_vertex = current.vertex

        for neighbor in graph[current.vertex]:
            next_vertex, next_dist = neighbor.vertex, neighbor.dist

            if next_vertex not in visited:
                Q.append(Node(next_vertex, current.dist + next_dist))
                visited.add(next_vertex)

    return max_dist, max_vertex

V = int(input())
graph = defaultdict(list)

for _ in range(V):
    temp = list(map(int, input().split()))[:-1]
    index = temp.pop(0)
    graph[index] = [Node(temp[i], temp[i+1]) for i in range(0, len(temp), 2)]

a, b = BFS(graph, 1)
answer, _ = BFS(graph, b)
print(answer)
