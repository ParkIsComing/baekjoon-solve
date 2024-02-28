class Solution {
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);

        return answer;
    }
    
    public void dfs(String now, String target, String[] words, int count) {
        if (now.equals(target)) {
            answer = count;
            return;
        }
        
        for (int i=0; i<words.length; i++) {
            if (!visited[i]) {
                int cnt = 0;
                for (int j=0; j<target.length(); j++) {
                    if (words[i].charAt(j) == now.charAt(j)) {
                      cnt++;
                    } 
                }
                
                if (cnt == target.length()-1) { // 한 글자만 다른 경우
                    visited[i] = true;
                    System.out.println(words[i]);
                    dfs(words[i], target, words, count+1);
                    visited[i] = false;
                }
            }
        }

    }
}