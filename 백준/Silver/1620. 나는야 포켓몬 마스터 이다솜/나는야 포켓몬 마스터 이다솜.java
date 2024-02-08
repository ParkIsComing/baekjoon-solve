import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 포켓몬 개수 (번호 1부터)
        int m = Integer.parseInt(st.nextToken()); // 문제 개수

        HashMap<String, Integer> pokemonMap = new HashMap<>(); // 포켓몬 이름을 번호로 매핑하는 맵
        String[] pokemons = new String[n + 1]; // 포켓몬 번호에 해당하는 이름을 저장하는 배열

        for (int i = 1; i <= n; i++) {
            String pokemon = br.readLine();
            pokemonMap.put(pokemon, i); // 포켓몬 이름과 번호를 맵에 저장
            pokemons[i] = pokemon; // 포켓몬 이름을 배열에 저장
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                int num = Integer.parseInt(input);
                sb.append(pokemons[num]).append("\n");
            } else {
                sb.append(pokemonMap.get(input)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
