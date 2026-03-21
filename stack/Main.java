import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    private Deque<int[]> stack; // Stack stores [price, span]

    public StockSpanner() {
        stack = new ArrayDeque<>(); // ArrayDeque is faster than Stack
    }

    public int next(int price) {
        int span = 1;

        // Merge previous smaller or equal prices
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        stack.push(new int[]{price, span});
        return span;
    }
}

public class Main {
    public static void main(String[] args) {
        StockSpanner s = new StockSpanner();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        System.out.println("Price -> Span");
        for (int price : prices) {
            int span = s.next(price);
            System.out.println(price + " -> " + span);
        }
    }
}

/*
Expected Output:
Price -> Span
100 -> 1
80  -> 1
60  -> 1
70  -> 2
60  -> 1
75  -> 4
85  -> 6

Explanation:
- Each call to next(price) calculates the span of consecutive days where price <= current price.
- Stack stores [price, span] to merge consecutive smaller prices efficiently.
- Time Complexity: O(n) amortized
- Space Complexity: O(n)
*/
