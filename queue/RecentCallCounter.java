/*
LeetCode 933 - Number of Recent Calls

QUESTION:
Implement the RecentCounter class that counts the number of recent requests
within a 3000 millisecond window.

- RecentCounter(): Initializes the counter.
- ping(int t): Adds a new request at time t and returns the number of
  requests in the range [t - 3000, t].

NOTE:
- Time t is strictly increasing.
*/

class RecentCounter {

    // Array to act as a queue
    private int[] arr;

    // Pointers for queue front and rear
    private int front;
    private int rear;

    /*
    HOW IT WORKS (WITHOUT BUILT-IN QUEUE):

    1. We simulate a queue using an array.
    2. 'rear' adds new request times.
    3. 'front' removes outdated request times.
    4. If arr[front] < t - 3000 → remove it by moving front forward.
    5. Number of recent calls = rear - front

    WHY THIS WORKS:
    - Times are strictly increasing
    - Oldest request is always at the front

    Time Complexity: O(1) amortized per ping
    Space Complexity: O(n)
    */

    public RecentCounter() {
        arr = new int[10000]; // enough for constraints
        front = 0;
        rear = 0;
    }

    public int ping(int t) {
        // Add new request time
        arr[rear++] = t;

        // Remove outdated requests
        while (arr[front] < t - 3000) {
            front++;
        }

        // Number of valid recent calls
        return rear - front;
    }
}

/*
Your RecentCounter object will be instantiated and called as such:
RecentCounter obj = new RecentCounter();
int param_1 = obj.ping(t);
*/
