import java.util.PriorityQueue;
import java.util.Collections;

public class LastStoneWeight {

    public static int lastStoneWeight(int[] stones) {
        // Max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all stones
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        // Process stones
        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();   // largest
            int second = maxHeap.poll();  // second largest

            if (first != second) {
                maxHeap.add(first - second);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(lastStoneWeight(stones)); // Output: 1
    }
}
