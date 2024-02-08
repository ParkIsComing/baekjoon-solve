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
        
        // Iterator를 사용하여 컬렉션을 순회하면서 삭제
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                iterator.remove(); // 반복자를 통해 안전하게 삭제
            }
        }
        List<String> keySet = new ArrayList<>(map.keySet());
        System.out.println(keySet.size());
        
        // 키 값으로 오름차순 정렬
        Collections.sort(keySet);
        for(String key : keySet) {
            System.out.println(key);
        }
    }
}