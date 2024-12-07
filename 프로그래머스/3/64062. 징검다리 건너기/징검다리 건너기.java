class Solution {

    public static int solution(int[] stones, int k) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int stone : stones) {
            left = Math.min(left, stone);
            right = Math.max(right, stone);
        }

        // 이진 탐색 수행
        while (left < right) {
            int mid = (left + right + 1) / 2;

            if (canCross(mid, k, stones)) {
                left = mid; // 조건을 만족하면 더 많은 사람이 가능
            } else {
                right = mid - 1; // 조건을 만족하지 않으면 인원 줄이기
            }
        }
        return right;
    }

    // 돌을 순회하며 건널 수 있는지 확인
    public static boolean canCross(int num, int maxJump, int[] stones) {
        int consecutiveZeros = 0;

        for (int stone : stones) {
            if (stone < num) {
                consecutiveZeros++;
                if (consecutiveZeros == maxJump) {
                    return false; // maxJump 이상의 연속된 0이면 불가능
                }
            } else {
                consecutiveZeros = 0; // 건널 수 있는 돌이면 초기화
            }
        }
        return true;
    }
}