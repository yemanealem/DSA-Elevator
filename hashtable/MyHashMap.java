import java.util.*;

class MyHashMap {

    // Bucket size (prime number reduces collisions)
    private static final int SIZE = 769;
    
    // Array of LinkedLists
    private LinkedList<Node>[] buckets;

    // Node class to store key-value pair
    private static class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        buckets = new LinkedList[SIZE];
    }

    /** Hash function */
    private int hash(int key) {
        return key % SIZE;
    }

    /** Insert or update value */
    public void put(int key, int value) {
        int index = hash(key);

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Node node : buckets[index]) {
            if (node.key == key) {
                node.value = value; // update
                return;
            }
        }

        buckets[index].add(new Node(key, value));
    }

    /** Get value */
    public int get(int key) {
        int index = hash(key);

        if (buckets[index] == null) {
            return -1;
        }

        for (Node node : buckets[index]) {
            if (node.key == key) {
                return node.value;
            }
        }

        return -1;
    }

    /** Remove key */
    public void remove(int key) {
        int index = hash(key);

        if (buckets[index] == null) {
            return;
        }

        Iterator<Node> iterator = buckets[index].iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.key == key) {
                iterator.remove();
                return;
            }
        }
    }
}
