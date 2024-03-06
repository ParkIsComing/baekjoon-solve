import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n,m;
    static int root = 0;
    static int[] parent;
    static List<Integer> knowingTruth = new ArrayList<Integer>();
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람 수
        m = Integer.parseInt(st.nextToken()); //  파티 수
        parent = new int[n+1];
        for (int i=1; i<=n; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int knowingNum = Integer.parseInt(st.nextToken());
        
        if (knowingNum != 0) {
            root = Integer.parseInt(st.nextToken());
            knowingTruth.add(root);
            for (int i=1; i<knowingNum; i++) {
                int cur = Integer.parseInt(st.nextToken());
                knowingTruth.add(cur); // 진실 아는 사람 정보 저장
                union(root, cur);
            }
        } else { // 아무도 진실을 모르는 경우
            System.out.println(m);
            return;
        }
        

        int answer = 0;
        List<Integer>[] party = new ArrayList[m+1]; // 파티 정보 저장
        for (int i=0; i<=m; i++) { // 1부터 가능??????
            party[i] = new ArrayList<>();
        }
        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            party[i].add(x);
            
            for (int j=1; j<count; j++) {
                int next = Integer.parseInt(st.nextToken());
                party[i].add(next);
                union(x,next);
                x = next;
            }
        }
        
        for (int i=1; i<=m; i++) {
            boolean flag = false;
            for (int person : party[i]) {
                flag = isKnowing(person);
            }
            
            if (!flag) {
                answer++;
            }
        }
        
        
        System.out.println(answer);
 
    }
    
    // 진실을 아는 노드가 있다면 그 노드의 루트 노드를 union한 결과의 루트 노드로 
    public static void union(int x, int y) { 
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootY == root) {
            int tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }
        parent[rootY] = rootX;
    }
    
    // 재귀적으로 루트 노드를 찾음
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }
    
    // 진실을 아는 경우
    public static boolean isKnowing(int x) {
        if (find(x) == root) {
            return true;
        }
        return false;
    }

}