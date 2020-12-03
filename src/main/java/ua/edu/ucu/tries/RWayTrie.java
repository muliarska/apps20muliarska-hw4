package ua.edu.ucu.tries;


import ua.edu.ucu.collections.Queue;

public class RWayTrie implements Trie {
    private static int R = 256; // radix
    private Node root; // root of trie

    private static class Node
    {
        private Tuple tuple;
        private Node[] next;

        private Node(Tuple t) {
            this.tuple = t;
            this.next = new Node[R];
        }
    }

    public RWayTrie() {
        this.root = null;
    }

    @Override
    public void add(Tuple t) {

        if (root == null) {
            root = new Node(t);
        }
        else {
            Node curNode = root;
            int d = 0;

            while (d != t.weight) {
                int index = t.term.charAt(d) - 97;
                if (curNode.next[index] == null) {
                    curNode.next[index] = new Node(t);
                }
                curNode = curNode.next[index];
                d++;
            }
        }
    }

    @Override
    public boolean contains(String word) {
        if (get(word) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String word) {
        Tuple t = new Tuple(word, word.length());
        Node curNode = root;

        int d = 0;
        while (d != t.weight) {
            int index = t.term.charAt(d) - 97;
            if (curNode.next[index] == null) {
                return false;
            }
            curNode = curNode.next[index];
            d++;
        }
        curNode.tuple = null;
        return true;
    }

    private Node get(String word) {
        Node curNode = root;

        int d = 0;
        while (d != word.length()) {
            int index = word.charAt(d) - 97;
            if (curNode.next[index] == null) {
                return null;
            }
            curNode = curNode.next[index];
            d++;
        }
        return curNode;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue queue = new Queue();
        collect(get(s), s, queue);
        return queue;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) {
            return;
        }
        if (x.next != null) {
            if (pre.length() > 2) {
                q.enqueue(pre);
            }
        }

        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + (char) (c + 97), q);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null) {
            return 0;
        }
        int size = 0;
        if (x.tuple != null) {
            size++;
        }
        for (char c = 0; c < R; c++)
            size += size(x.next[c+97]);
        return size;
    }

}
