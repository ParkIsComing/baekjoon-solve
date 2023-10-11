def solution(n, times): # 이진탐색
    answer = 0
    
    times.sort()
    left = times[0]
    right = times[len(times)-1] * n #최대 소요 시간

    while left <= right:
        mid = (left + right)//2
        total = 0 # 심사관이 mid분 동안 심사한 사람수

        for time in times:
            total += mid // time
        
        if total >= n:
            answer = mid
            right = mid - 1
        elif total < n:
            left = mid + 1

    return answer