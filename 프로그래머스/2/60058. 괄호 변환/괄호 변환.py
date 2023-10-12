def is_balanced(p): # 균형잡힌 괄호 문자열
    balance = 0
    for char in p:
        if char == '(':
            balance += 1
        else:
            balance -= 1
        if balance < 0:
            return False
    return balance == 0

def split_uv(p): # 균형 잡힌 괄호 문자열을 분리
    balance = 0
    for i in range(len(p)):
        if p[i] == '(':
            balance += 1
        else:
            balance -= 1
        if balance == 0:
            return p[:i + 1], p[i + 1:]

def solution(p):
    if not p:
        return ""

    u, v = split_uv(p)

    if is_balanced(u):
        return u + solution(v)
    else:
        result = '(' + solution(v) + ')'
        result += ''.join(['(' if char == ')' else ')' for char in u[1:-1]])
        return result
