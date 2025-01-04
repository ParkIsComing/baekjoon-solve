// 모든 주사위 눈 조합을 따른 합을 다 저장해두고 시작
// 주사위 개수 : n, 주사위를 뽑는 조합의 수 : nCn/2
// 승패(무/승/패)를 확인할 때 모든 경우의 수를 다 확인 x
// a와 b의 합 조합을 담은 리스트를 각각 정렬해두고 이분탐색

import java.util.*;

class Solution {
    int maxSum = 500;
        
    List<int[]> getCombinations(int n) {
        List<int[]> combinations = new ArrayList<>();
        createCombinationsRecursive(combinations, n, n/2, new int[n/2], 0, 0);
        return combinations;
    }
    
    // 조합 nCr
    void createCombinationsRecursive(List<int[]> combinations, int n, int r, int[] tmp, int start, int index) {
        if (index == r) {
            combinations.add(tmp.clone());
            return;
        }
        
        for (int i = start; i < n; i ++) {
            tmp[index] = i;
            createCombinationsRecursive(combinations, n, r, tmp, i+1, index + 1);
        }
    }
    
    List<Integer> getSums(int[][] dice, int[] pickedDice) {
        List<Integer> result = new ArrayList<>();
        generateSums(dice, pickedDice, 0, 0, result);
        return result;
    }
    
    void generateSums(int[][] dice, int[] pickedDice, int index, int sum, List<Integer> sums) {
        if (index == pickedDice.length) {
            sums.add(sum);
            return;
        }
        
        for (int number : dice[pickedDice[index]]) {
            generateSums(dice,pickedDice, index+1, sum + number, sums);
        }
        
    }
    
    int calculateTotalWins(List<Integer> aSums, List<Integer> bSums) {
        int totalWins = 0;
        
        for (int aSum : aSums) {
            int count = countAWins(aSum, bSums);
            totalWins += count;
        }
        
        return totalWins;
    }
    
    
    int countAWins(int target, List<Integer> list) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }
    
    int calculateTotalWinsFreq(List<Integer> aSums, List<Integer> bSums) {
        int[] freq = new int[maxSum + 1];
        for (int sum : bSums) {
            freq[sum]++;
        }

        // 2. 누적 빈도수 계산: i보다 작거나 같은 값의 총 개수
        for (int i = 1; i <= maxSum; i++) {
            freq[i] += freq[i-1];
        }

        // 3. A가 이기는 경우 계산
        int wins = 0;
        for (int aSum : aSums) {
            if (aSum > 0) {
                wins += freq[aSum - 1];  // aSum보다 작은 값의 개수
            }
        }
        return wins;
    }
    
    public int[] solution(int[][] dice) {
        int len = dice.length;
        List<int[]> combinations = getCombinations(len);
        
        int maxWins = Integer.MIN_VALUE;
        int[] bestCombination = null;
        
        for(int[] aPick : combinations) {
            // b가 고른 주사위(=a가 고르지 않은 주사위) 세팅
            boolean[] isAPick = new boolean[len];
            for (int a : aPick) {
                isAPick[a] = true;
            }

            int[] bPick = new int[len / 2];
            int index = 0;
            for (int i = 0; i < len; i++) {
                if (!isAPick[i]) {
                    bPick[index++] = i;
                }
            }
            
            List<Integer> aSums = getSums(dice, aPick);
            List<Integer> bSums = getSums(dice, bPick);

            Collections.sort(aSums); // for binary search
            Collections.sort(bSums);
            
            int totalWins = calculateTotalWinsFreq(aSums, bSums);
            
            if (totalWins > maxWins) {
                maxWins = totalWins;
                bestCombination = aPick;
            }
            
        }
        
        // 주사위 번호를 1-based로
        int[] answer = new int[bestCombination.length];
        for(int i = 0; i < bestCombination.length; i++) {
            answer[i] = bestCombination[i] + 1;
        }
        
        return answer;
    }
}