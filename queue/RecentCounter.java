/*
LeetCode 933 - Number of Recent Calls

QUESTION:
You have a RecentCounter class that counts recent requests within
a time window of 3000 milliseconds.

Implement the class:

- RecentCounter(): Initializes the counter.
- int ping(int t): Adds a new request at time t (in milliseconds),
  and returns the number of requests that happened in the range
  [t - 3000, t].

IMPORTANT:
- Each call to ping(t) has strictly increasing time t.
*/

public class RecentCounter {

    // Array used to simulate a queue
    private int[] queue;

    // Front and rear pointers of the queue
    private int front;
    private int rear;

    /*
    HOW IT WORKS (CUSTOM QUEUE):

    - We use an array to store request times.
    - 'rear' is used to insert new times.
    - 'front' is used to remove old times.
    - Since times are increasing, oldest request is always at 'front'.

    Steps in ping(t):
    1. Add t to queue at position rear.
    2. Move front forward while queue[front] < (t - 3000).
    3. Remaining elements are valid recent calls.
    4. Number of recent calls = rear - front.

    Time Complexity: O(1) amortized
    Space Complexity: O(n)
    */

    public RecentCounter() {
        queue = new int[10000]; // safe size for constraints
        front = 0;
        rear = 0;
    }

    public int ping(int t) {
        // Step 1: Add new request time
        queue[rear++] = t;

        // Step 2: Remove outdated requests
        while (queue[front] < t - 3000) {
            front++;
        }

        // Step 3: Return number of valid requests
        return rear - front;
    }

    /*
    TRACE (STEP-BY-STEP):

    Calls:
    ping(1)
    ping(100)
    ping(3001)
    ping(3002)

    Initial:
    front = 0, rear = 0

    ping(1):
    queue = [1]
    front = 0, rear = 1
    valid range = [-2999, 1]
    result = 1

    ping(100):
    queue = [1, 100]
    front = 0, rear = 2
    valid range = [-2900, 100]
    result = 2

    ping(3001):
    queue = [1, 100, 3001]
    front = 0, rear = 3
    valid range = [1, 3001]
    result = 3

    ping(3002):
    queue = [1, 100, 3001, 3002]
    remove 1 (out of range)
    front = 1
    valid range = [2, 3002]
    result = 3
    */

    // Main method to test the implementation
    public static void main(String[] args) {

        RecentCounter obj = new RecentCounter();

        System.out.println(obj.ping(1));     // Output: 1
        System.out.println(obj.ping(100));   // Output: 2
        System.out.println(obj.ping(3001));  // Output: 3
        System.out.println(obj.ping(3002));  // Output: 3
    }
}
