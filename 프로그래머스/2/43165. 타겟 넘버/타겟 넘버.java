class Solution {
    static int answer = 0;
    static int limit;
    
    public int solution(int[] numbers, int target) {
        limit = numbers.length;
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    public void dfs(int node, int sum, int[] numbers, int target) {
        if (node == limit) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(node+1, sum-numbers[node], numbers, target);
        dfs(node+1, sum+numbers[node], numbers, target);
    }
}