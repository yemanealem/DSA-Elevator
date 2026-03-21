import java.util.Stack;

class StockSpanner {
    private Stack<int[]> stack; // each element: [price, span]

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1; // today itself

        // Merge consecutive smaller prices
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        // Push current price and its span
        stack.push(new int[]{price, span});

        return span;
    }
}

/*
How it works (trace example):
Prices: 100, 80, 60, 70, 60, 75, 85

1. next(100)
   Stack empty, span=1, push [100,1] → return 1
2. next(80)
   Top=100>80, span=1, push [80,1] → return 1
3. next(60)
   Top=80>60, span=1, push [60,1] → return 1
4. next(70)
   Top=60<=70 → pop, span=1+1=2
   Top=80>70 → stop
   push [70,2] → return 2
5. next(60)
   Top=70>60 → push [60,1] → return 1
6. next(75)
   Top=60<=75 → pop, span=1+1=2
   Top=70<=75 → pop, span=2+2=4
   Top=80>75 → stop
   push [75,4] → return 4
7. next(85)
   Top=75<=85 → pop, span=4+1=5
   Top=80<=85 → pop, span=5+1=6
   Top=100>85 → stop
   push [85,6] → return 6

Time complexity: O(n) amortized
Space complexity: O(n)
*/
