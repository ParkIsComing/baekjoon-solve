import java.util.*;

public class Main {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> tree = new ArrayList<>();
        
        while(sc.hasNext()) {
            int num = sc.nextInt();
            tree.add(num);
        }
        
        ArrayList<Integer> result = solution(tree.stream().mapToInt(i->i).toArray());
        for (int i:result) {
            System.out.println(i);
        }
        
        sc.close();
 
    }
    
    static ArrayList<Integer> solution(int[] tree) {
        ArrayList<Integer> result = new ArrayList<>();
        postOrder(0, tree.length-1, tree, result);
        
        return result;
    }
    
    static void postOrder(int left, int right, int[] tree, ArrayList<Integer> result) {
        if (left > right) { // 종료 조건
            return;
        }
        
        int root = tree[left];
        int tmp = left + 1; // root 노드의 오른쪽 자식 노드
        for (int i=left+1; i<=right; i++) { // tree가 전위순회이니까 가장 먼저 나오는 root보다 큰 값
            if (tree[i] > root) {
                tmp = i;
                break;
            }
        }
        
        postOrder(left+1, tmp-1, tree, result); // 왼쪽 서브 트리 탐색
        postOrder(tmp, right, tree, result); // 오른쪽 서브 트리 탐색
        
        result.add(root);
    }
}