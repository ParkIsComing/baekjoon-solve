import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            String input = br.readLine();
            map.put(input, map.getOrDefault(input, 0) + 1);
        }
        
        for (int i=0; i<m; i++) {
            String input = br.readLine();
            map.put(input, map.getOrDefault(input, 0) + 1);
        }
        
        //Iterator를 사용하여 맵을 순회하면서 듣보잡이 아니면 삭제
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                iterator.remove();
            }
        }
        
        List<String> result = new ArrayList<>(map.keySet());
        System.out.println(result.size());
        
        StringBuilder sb  = new StringBuilder();
        Collections.sort(result);
        for (String name : result) {
            sb.append(name).append("\n");
        }
        System.out.println(sb);

    }
}