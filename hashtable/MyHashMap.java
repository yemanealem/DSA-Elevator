import java.util.Arrays;

class MyHashMap {

    /*
     LeetCode 706 - Design HashMap

     Question:
     Design a HashMap without using any built-in hash table libraries.
     Implement the following methods:
       - MyHashMap() → initialize the object
       - put(int key, int value) → insert (key, value). If key exists, update it.
       - get(int key) → return value of key, or -1 if not found.
       - remove(int key) → remove the key if it exists.

     Constraints:
       0 <= key <= 10^6
       0 <= value <= 10^6

     How it works:
     Since the key range is limited (0 to 10^6), we use Direct Addressing.
     We create an array of size 10^6 + 1.
     Each index represents a key.
     
     Example:
       map[5] stores value for key = 5

     We initialize all values with -1 to indicate "key not present".
     When we put a key, we store the value directly at index = key.
     When we get a key, we return the value at that index.
     When we remove a key, we reset it to -1.

     Running Time:
       put    → O(1)
       get    → O(1)
       remove → O(1)

     Space Complexity:
       O(n) where n = 10^6 (fixed array size)

     Note:
     This works because the key range is small.
     If keys were up to 10^9, we would need a real hash table
     with hashing and collision handling.
    */

    int[] map = new int[1000001];

    public MyHashMap() {
        Arrays.fill(map, -1);
    }
    
    public void put(int key, int value) {
        map[key] = value;
    }
    
    public int get(int key) {
        return map[key];
    }
    
    public void remove(int key) {
        map[key] = -1;
    }
}
