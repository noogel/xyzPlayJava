package noogel.xyz.algorithm;

public class Trie {
    private final int SIZE = 26;
    private Node root;

    private class Node {
        private boolean isStr;
        private int num;
        private Node[] child;

        public Node() {
            child = new Node[SIZE];
            isStr = false;
            num = 1;
        }
    }

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        if (null == word || word.isEmpty()) {
            return;
        }
        Node pNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pNode.child[index] == null) {
                Node tmp = new Node();
                pNode.child[index] = tmp;
            } else {
                pNode.child[index].num++;
            }
            pNode = pNode.child[index];
        }
        pNode.isStr = true;
    }

    public boolean search(String word) {
        if (null == word || word.isEmpty()) {
            return false;
        }
        Node pNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pNode.child[index] == null || (word.length() - i == 1 && pNode.child[index].isStr == false)) {
                return false;
            }
            pNode = pNode.child[index];
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        if (null == prefix || prefix.isEmpty()) {
            return false;
        }
        Node pNode = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (pNode.child[index] == null) {
                return false;
            }
            pNode = pNode.child[index];
        }
        return true;
    }
}
