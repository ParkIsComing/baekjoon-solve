def calculate_direction(x, y, r, c):
    direction = {'d': 0, 'l': 0, 'r': 0, 'u': 0}

    # 위로 이동
    if x > r:
        direction['u'] = max(0, x - r)
    # 아래로 이동
    else:
        direction['d'] = max(0, r - x)
    # 왼쪽으로 이동
    if y > c:
        direction['l'] = max(0, y - c)
    # 오른쪽으로 이동
    else:
        direction['r'] = max(0, c - y)

    return direction

def solution(n, m, x, y, r, c, k):
    answer = ''
    dist = abs(x - r) + abs(y - c)
    k -= dist

    if k < 0 or k % 2 != 0: # k가 음수이거나 홀수인 경우
        return "impossible"

    direction = calculate_direction(x, y, r, c)

    # 위로 이동 가능한만큼 d 추가
    answer += 'd' * direction['d']

    # 아래로 이동 가능한 거리만큼 u 추가 
    d = min(k // 2, n - (x + direction['d']))
    answer += 'd' * d
    direction['u'] += d
    k -= 2 * d

    # 왼쪽으로 이동 가능한 거리만큼 l 추가
    answer += 'l' * direction['l']
    l = min(k // 2, y - direction['l'] - 1)
    answer += 'l' * l
    direction['r'] += l
    k -= 2 * l

    # 남은 이동 횟수만큼 rl 번갈아 이동
    answer += 'rl' * (k // 2)

    # 이동 가능한 거리만큼 r 추가
    answer += 'r' * direction['r']

    # 위로 이동 가능한 거리만큼 u 추가
    answer += 'u' * direction['u']


    return answer