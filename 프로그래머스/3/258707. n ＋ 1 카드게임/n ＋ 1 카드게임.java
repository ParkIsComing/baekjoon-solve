import java.util.*;

class Solution {
    private int pairCount;
    private int leftCoin;
    private int targetSum;
    private Set<Integer> mine = new HashSet<>();
    private Set<Integer> bin = new HashSet<>();
    
    private void checkPairWithInit(int cur) {
        int pairVal = targetSum - cur;
        if (leftCoin > 0 && mine.contains(pairVal)) {
            pairCount++;
            leftCoin--;
            mine.remove(pairVal);
            return;
        }
        
        bin.add(cur);
    }
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        this.leftCoin = coin;
        targetSum = n + 1;
        int initialCards = n / 3;

        
        //  초기 세팅
        for (int i=0; i<initialCards; i++) {
            int cur = cards[i];
            mine.add(cur);
            
            int pairVal = targetSum - cur;
            if (mine.contains(pairVal)) {
                pairCount++;
                mine.remove(pairVal);
            } 
        }
        
        
        // round 진행
        int round = 1;
        for (int i=n/3; i<n; i+=2) {
            int cur1 = cards[i];
            int cur2 = cards[i+1];
            
            checkPairWithInit(cur1);
            checkPairWithInit(cur2);
            
            // 버린 것도 다시 보자
            if (pairCount < 1 && leftCoin >= 2) {
                for (int b : bin) {
                    int pair = targetSum - b;
                    if (bin.contains(pair)) {
                        pairCount++;
                        leftCoin -= 2;
                        bin.remove(b);
                        break;
                    }
                    
                }
            }
            
            System.out.println("pairCount: " + pairCount);
            System.out.println("coin: " + leftCoin);
            if (pairCount <= 0) break;
            
            round++;
            pairCount--;
        }
        
        return round;
    }
}