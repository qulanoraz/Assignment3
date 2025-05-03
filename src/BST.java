import java.util.ArrayList;
import java.util.Iterator;

public class BST<K extends Comparable<K>, V> {

    private Node root;
    private int size;
    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public class Entry {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue () {
            return value;
        }
    }
    public void put(K key, V val) {
        root = put(root,key, val);
    }
    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left,key, val);
        }
        else if (cmp > 0) {
            node.right = put(node.right, key, val);
        }
        else {
            node.val = val;
        }
        return node;
    }
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            }
            else if (cmp > 0) {
                current = current.right;
            }
            else {
                return current.val;
            }
        }
        return null;
    }
    public void delete(K key) {
        root = delete(root, key);
    }
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        }
        else if (cmp > 0) {
            node.right = delete(node.right, key);
        }
        else {
            size--;
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
    public Iterable<K> iteratorK() {
        ArrayList<K> keys = new ArrayList<>();
        inOrderKeys(root, keys);
        return keys;
    }
    private void inOrderKeys(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrderKeys(node.left, keys);
        keys.add(node.key);
        inOrderKeys(node.right, keys);
    }
    public Iterator<Entry> iterator() {
        ArrayList<Entry> entries = new ArrayList<>();
        inOrder(root, entries);
        return entries.iterator();
    }
    private void inOrder(Node node, ArrayList<Entry> entries) {
        if (node == null) {
            return;
        }
        inOrder(node.left, entries);
        entries.add(new Entry(node.key, node.val));
        inOrder(node.right, entries);
    }
}