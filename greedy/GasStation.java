public class GasStation {

    /*
     * LeetCode Problem: Gas Station
     *
     * Question:
     * There are n gas stations arranged in a circle.
     * gas[i] represents the amount of gas at station i.
     * cost[i] represents the gas required to travel from station i to station i+1.
     *
     * Return the starting gas station index if you can travel around the circuit once.
     * Otherwise, return -1.
     *
     * How It Works (Greedy Approach):
     * 1. First, check if total gas >= total cost.
     *    If total gas < total cost, it is impossible to complete the circuit.
     *
     * 2. Traverse once while maintaining:
     *      - totalTank  → total gas difference
     *      - currentTank → running tank while iterating
     *
     * 3. If currentTank becomes negative at index i:
     *      - It means we cannot start from the current starting point.
     *      - Any station between previous start and i also cannot be valid.
     *      - So we reset start = i + 1 and currentTank = 0.
     *
     * 4. After the loop:
     *      - If totalTank >= 0 → return start
     *      - Otherwise → return -1
     *
     * Running Time:
     * Time Complexity: O(n)  → Single pass through the array
     * Space Complexity: O(1) → Only variables used, no extra data structure
     */

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currentTank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];

            totalTank += diff;
            currentTank += diff;

            if (currentTank < 0) {
                start = i + 1;
                currentTank = 0;
            }
        }

        return totalTank >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        System.out.println(canCompleteCircuit(gas, cost)); // Output: 3
    }
}
