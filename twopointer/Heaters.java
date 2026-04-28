import java.util.Arrays;

public class Heaters {

    /*
     * 🔍 PROBLEM:
     * We are given positions of houses and heaters on a number line.
     * Each heater can warm houses within a certain radius.
     *
     * 👉 Goal:
     * Find the MINIMUM radius required so that ALL houses are covered.
     *
     * ---------------------------------------------------------------
     * 💡 IDEA (Two-Pointer Greedy Approach):
     *
     * 1. Sort both houses and heaters.
     * 2. For each house, find the closest heater.
     * 3. Track the maximum distance among all houses.
     *
     * Why it works:
     * - Houses are processed from left → right.
     * - The closest heater for the next house will NEVER be to the left
     *   of the current heater pointer.
     * - So we move the heater pointer only forward (greedy).
     *
     * This avoids binary search and improves performance.
     *
     * ---------------------------------------------------------------
     * 🚀 TIME COMPLEXITY:
     *
     * Sorting:
     *   O(n log n) for houses
     *   O(m log m) for heaters
     *
     * Traversal:
     *   O(n + m) → each pointer moves at most once
     *
     * 👉 Total:
     *   O(n log n + m log m)
     *
     * ---------------------------------------------------------------
     * 🧠 SPACE COMPLEXITY:
     *   O(1) (ignoring sorting space)
     */

    public int findRadius(int[] houses, int[] heaters) {

        // Step 1: Sort both arrays
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int heaterIndex = 0; // pointer for heaters
        int radius = 0;      // final answer

        // Step 2: Iterate through each house
        for (int house : houses) {
            /*
             * Move heater pointer forward if the next heater
             * is closer to the current house.
             *
             * We compare:
             * distance to current heater  vs  next heater
             */
            while (heaterIndex < heaters.length - 1 &&
                   (heaters[heaterIndex + 1] - house) <= (house - heaters[heaterIndex])) {
                heaterIndex++;
            }

           
            int distance = Math.abs(heaters[heaterIndex] - house);

           
            radius = Math.max(radius, distance);
        }

        return radius;
    }
}
