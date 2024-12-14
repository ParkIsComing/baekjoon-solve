import java.util.*;

public class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        Set<Set<String>> resultSet = new HashSet<>();
        dfs(0, banned_id, new HashSet<>(), user_id, resultSet);
        return resultSet.size();
    }

    private void dfs(int index, String[] banned_id, Set<String> currentSet, String[] user_id, Set<Set<String>> resultSet) {
        if (index == banned_id.length) {
            resultSet.add(new HashSet<>(currentSet));
            return;
        }

        String pattern = banned_id[index];
        for (String user : user_id) {
            if (currentSet.contains(user) || !isMatch(pattern, user)) {
                continue;
            }
            // 백트래킹
            currentSet.add(user);
            dfs(index + 1, banned_id, currentSet, user_id, resultSet);
            currentSet.remove(user);
        }
    }

    private boolean isMatch(String pattern, String user) {
        if (pattern.length() != user.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            // 마스킹되지 않은 문자는 같아야
            if (pattern.charAt(i) != '*' && pattern.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
