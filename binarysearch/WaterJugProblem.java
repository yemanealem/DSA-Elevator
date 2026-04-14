/*
    LeetCode 365 - Water and Jug Problem

    🧩 Problem:
    You are given two jugs with capacities x and y liters.
    You can:
        1. Fill any jug completely
        2. Empty any jug
        3. Pour water from one jug to another until one is full or the other is empty

    👉 Return true if you can measure exactly z liters, otherwise false.

    ------------------------------------------------------------

    💡 How it works (Mathematical Approach - GCD):

    This problem is based on Bézout’s Identity:
        ax + by = z

    This means:
    - We can measure z if it is a multiple of gcd(x, y)
    - Also, z must be less than or equal to total capacity (x + y)

    ✅ Conditions:
        1. z == 0 → always possible
        2. x + y >= z
        3. z % gcd(x, y) == 0

    ------------------------------------------------------------

    ⏱️ Time Complexity:
        - GCD calculation takes O(log(min(x, y)))
        - Overall complexity: O(log(min(x, y)))

    🧠 Space Complexity:
        - O(1) (no extra space used)

    ------------------------------------------------------------

    ❗ Note:
    This is NOT a binary search problem because:
        - No sorted data
        - No monotonic condition
*/

public class WaterJugProblem {

    public static boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) return true;

        if (x + y < z) return false;

        return z % gcd(x, y) == 0;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int x = 3, y = 5, z = 4;

        System.out.println(canMeasureWater(x, y, z));
    }
}
