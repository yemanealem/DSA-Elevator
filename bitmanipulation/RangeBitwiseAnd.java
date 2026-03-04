public class RangeBitwiseAnd {

    // Question: Bitwise AND of Numbers Range
    // Input: two integers left and right
    // Output: bitwise AND of all numbers in range [left, right]

    // How it works (Bit Manipulation Trick):
    // - The common prefix of left and right remains
    // - Bits that differ will become 0 in AND result
    // - Shift both numbers right until they are equal
    // - Count shifts, then shift result back

    // Running Time:
    // - Time Complexity: O(number of bits) -> at most 32
    // - Space Complexity: O(1)

    public int rangeBitwiseAnd(int left, int right) {
        int shifts = 0;

        // Find common prefix by shifting until equal
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shifts++;
        }

        // Shift back to original position
        return left << shifts;
    }

    // Main method for local testing
    public static void main(String[] args) {
        RangeBitwiseAnd solver = new RangeBitwiseAnd();

        int left = 5;
        int right = 7;
        int result = solver.rangeBitwiseAnd(left, right);

        System.out.println("Bitwise AND of range: " + result); // Expected: 4
    }
}
