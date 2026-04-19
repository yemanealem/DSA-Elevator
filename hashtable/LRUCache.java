import java.util.*;

/*
 * 🔴 PROBLEM: LRU Cache (Least Recently Used)
 *
 * Design a data structure that supports:
 * 1. get(key): Return the value of the key if it exists, otherwise return -1.
 * 2. put(key, value): Update or insert the value.
 *    If the cache exceeds capacity, remove the least recently used (LRU) key.
 *
 * 🧠 HOW IT WORKS:
 * - We use TWO data structures:
 *   1. HashMap → O(1) lookup of nodes
 *   2. Doubly Linked List → maintain usage order
 *
 * - Most recently used (MRU) node is placed at the FRONT (head)
 * - Least recently used (LRU) node is at the END (tail)
 *
 * - On get(key):
 *     → If exists, move node to front (mark as recently used)
 *
 * - On put(key, value):
 *     → If key exists → update value + move to front
 *     → If new key:
 *         - If capacity full → remove node from tail (LRU)
 *         - Insert new node at front
 *
 * ⏱️ TIME COMPLEXITY:
 * - get()  → O(1)
 * - put()  → O(1)
 *
 * 🚀 SPACE COMPLEXITY:
 * - O(capacity)
 */

public class LRUCache {

    // Doubly Linked List Node
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Dummy head and tail (avoid null checks)
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) return -1;

        remove(node);
        insertToFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            remove(node);
            insertToFront(node);

        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertToFront(newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); 

        cache.put(3, 3); 
        System.out.println(cache.get(2));

        cache.put(4, 4); 
        System.out.println(cache.get(1)); 
        System.out.println(cache.get(3)); 
        System.out.println(cache.get(4)); 
    }
}
