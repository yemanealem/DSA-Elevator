public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;   // total gas - total cost
        int currentTank = 0; // current running tank
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];

            totalTank += diff;
            currentTank += diff;

            // If tank becomes negative, reset starting point
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
