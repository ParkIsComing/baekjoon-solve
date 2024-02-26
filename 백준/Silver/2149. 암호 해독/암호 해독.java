import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key = br.readLine();
        
        Map<Integer, Character> map = new HashMap<>();
        for (int i=0; i<key.length(); i++) {
            map.put(i, key.charAt(i));
        }
        
        List<Map.Entry<Integer, Character>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Character>>() {
            @Override
            public int compare(Map.Entry<Integer,Character> e1,Map.Entry<Integer,Character> e2) {
                if (e1.getValue() == e2.getValue()) {
                    return e1.getKey() - e2.getKey();
                } else {
                    return e1.getValue().compareTo(e2.getValue());
                }
            } 
            
        });
        
        String ciphertext = br.readLine();
        int count = ciphertext.length() / key.length();
        
        char[][] arr = new char[count][key.length()];
        for (int j=0; j<key.length(); j++) {
            for (int i=0; i<count; i++) {
                int idx = j * count + i;
                arr[i][j] = ciphertext.charAt(idx);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        char[][] result = new char[count][key.length()];
        for (int i=0; i<key.length(); i++) {
            Map.Entry<Integer, Character> e  = list.get(i);
            int origin = e.getKey();
            for (int j=0; j<count; j++) {
                result[j][origin] = arr[j][i];
            } 
        }
        
        for (int i=0; i<count; i++) {
            String str= new String(result[i]);
            sb.append(str);
        }

        System.out.println(sb);
    }
}