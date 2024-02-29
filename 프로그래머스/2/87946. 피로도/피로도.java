class Solution {
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length+1];
        venture(0, dungeons, k);
        answer = max;
        return answer;
    }
    
    public void venture(int count, int[][] dungeons, int cur) {
        for (int i=0; i<dungeons.length; i++) {
            if (!visited[i] && cur >= dungeons[i][0]) {
                visited[i] = true;
                venture(count+1, dungeons, cur-dungeons[i][1]);
                visited[i] = false;
            }
        }
        
        max = Math.max(max, count);
    }
}