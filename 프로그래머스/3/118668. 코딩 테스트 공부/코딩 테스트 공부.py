# DP

def solution(alp, cop, problems):
    max_alp, max_cop = 0,0 # 목표값을 초과하면 안 되기 때문에 사용
    
    for problem in problems:
        max_alp = max(max_alp, problem[0])
        max_cop = max(max_cop, problem[1])
    
    alp = min(max_alp, alp)
    cop = min(max_cop, cop)
    
    dp = [[float('inf')] * (max_cop + 1) for _ in range(max_alp + 1)]
    dp[alp][cop] = 0
    
    for i in range(alp, max_alp + 1):
        for j in range(cop, max_cop + 1):
            # 알고리즘 공부를 통한 갱신
            if i < max_alp:
                dp[i+1][j] = min(dp[i+1][j], dp[i][j] + 1) 
            if j < max_cop:
                dp[i][j+1] = min(dp[i][j+1], dp[i][j] + 1)
            # 문제 풀이를 통한 갱신
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if i >= alp_req and j >= cop_req: # 풀기 위한 조건
                    next_alp = min(max_alp, i + alp_rwd)
                    next_cop = min(max_cop, j + cop_rwd)
                    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[i][j] + cost)
                
    
    
    return dp[max_alp][max_cop]