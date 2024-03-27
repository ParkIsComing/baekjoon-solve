import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Node {
    char cur;
    Node left;
    Node right;
    
    Node(char cur) {
        this.cur = cur;
    }
}

class Tree {
    public Node root;
    
    public void createNode(char cur, char left, char right) {
        if(root == null) { // 트리의 루트 노드인 경우
            root = new Node(cur);
            root.left = left == '.' ? null : new Node(left);
            root.right = right == '.' ? null : new Node(right);
        } else {
            searchNode(root, cur, left, right);
        }
    }
    
    public void searchNode(Node node, char cur, char left, char right) {
        if (node == null) {
            return;
        } else if (node.cur == cur) {
            node.left = left == '.' ? null : new Node(left);
            node.right = right == '.' ? null : new Node(right);
        } else {
            searchNode(node.left, cur, left, right);
            searchNode(node.right, cur, left, right);
        }
    }
    
    // 전위 순회
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.cur);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }
    }
    
    // 중위 순회
    public void inOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                inOrder(node.left);
            }
            System.out.print(node.cur);
            if (node.right != null) {
                inOrder(node.right);
            }
        }
    }
    
    // 후위 순회
    public void postOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            System.out.print(node.cur);
        }
    }
}
public class Main {
    static int N,M;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Tree t = new Tree();
        
        StringTokenizer st;
        for (int i=0; i<num; i++) {
            st = new StringTokenizer(br.readLine());
            char cur = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            
            t.createNode(cur, left, right);
        }
        
        t.preOrder(t.root);
        System.out.println();
        t.inOrder(t.root);
        System.out.println();
        t.postOrder(t.root);
        
        
    }
    

}