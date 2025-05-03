import java.util.LinkedList;

public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private LinkedList<HashNode<K, V>>[] chainArray; // or Object[]
    private int M = 11; // default number of chains
    private int size;

    public MyHashTable() {
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }
    public void put(K key, V value) {
        int index = hash(key);
        for(HashNode<K, V> node : chainArray[index]) {
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        chainArray[index].add(new HashNode<>(key, value));
        size++;
    }
    public V get(K key) {
        int index = hash(key);
        for(HashNode<K, V> node : chainArray[index]) {
            if(node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        for(HashNode<K, V> node : chainArray[index]) {
            if(node.key.equals(key)) {
                V value = node.value;
                chainArray[index].remove(node);
                size--;
                return value;
            }
        }
        return null;
    }
    public boolean contains(V value) {
        for(LinkedList<HashNode<K,V>> bucket : chainArray) {
            for(HashNode<K, V> node : bucket) {
                if(node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public K getKey(V value) {
        for(LinkedList<HashNode<K, V>> bucket : chainArray) {
            for(HashNode<K, V> node : bucket) {
                if(node.value.equals(value)) {
                    return node.key;
                }
            }
        }
        return null;
    }
    public int getSize() {
        return size;
    }
    public void printBucketSize() {
        for(int i = 0;i < M; i++) {
            System.out.println("bucket " + i + ": " + chainArray[i].size() + " elements");
        }
    }
}
