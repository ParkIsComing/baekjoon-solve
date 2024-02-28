import java.util.*;

class Solution {
    static String[] result;
    static boolean[] visited;
    static ArrayList<String> answerList;

    public String[] solution(String[][] tickets) {
        result = new String[tickets.length + 1];
        visited = new boolean[tickets.length]; // 티켓 사용 여부
        answerList = new ArrayList<>();

        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].equals(o2[0])) {
                    return o1[1].compareTo(o2[1]);
                } else {
                    return o1[0].compareTo(o2[0]);
                }
            }
        });

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                visited[i] = true;
                result[0] = "ICN";
                dfs(tickets[i][1], 1, tickets);
                visited[i] = false; // 백트래킹
            }
        }

        Collections.sort(answerList); // 알파벳순으로 정렬

        return answerList.get(0).split(" ");//가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 
    }

    public void dfs(String now, int count, String[][] tickets) {
        result[count] = now;

        if (count == tickets.length) { // 종료 조건
            StringBuilder sb = new StringBuilder();
            for (String s : result) {
                sb.append(s).append(" ");
            }
            answerList.add(sb.toString());
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(now)) {
                visited[i] = true;
                dfs(tickets[i][1], count + 1, tickets);
                visited[i] = false; // 백트래킹
            }
        }
    }
}