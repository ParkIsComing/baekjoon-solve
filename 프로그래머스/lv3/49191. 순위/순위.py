def solution(n, results):
    answer = 0
    matrix = [[None] * n for _ in range(n)]
    
    for win,lose in results:
        matrix[win-1][lose-1] = True # 이긴 것은 1로
        matrix[lose-1][win-1] = False # 진 것은 -1로
        
    for i in range(n):
        for j in range(n):
            for k in range(n):
                # 자기자신과 싸우는 경우나 승패가 결정된(1, -1) 경우는 제외
                if matrix[i][j] == None: 
                    continue
                if matrix[i][j] == matrix[j][k]: # i가 k를 이기고, k가 j를 이긴 경우
                    matrix[i][k] = matrix[i][j] # i가 j를 이긴 것과 같음
                    matrix[k][i] = not matrix[i][k]
    
    for row in matrix:
        # 자기자신과 싸우는 경우를 제외하고 0이 아니면 순위 도출 가능
        if row.count(None) == 1:
            answer += 1
    return answer
                    
                    
        
    