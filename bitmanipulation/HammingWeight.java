public class HammingWeight {

    // Question: Number of 1 Bits (Hamming Weight)
    // Input: an integer n
    // Output: count of '1' bits in binary representation

    // How it works (Bit Manipulation Trick):
    // - n & (n - 1) removes the rightmost set bit
    // - Example: 1011 & 1010 = 1010 (one bit removed)
    // - Repeat until n becomes 0
    // - Count how many times we remove a bit

    // Running Time:
    // - Time Complexity: O(number of set bits)
    // - Space Complexity: O(1)

    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1); // removes the rightmost 1-bit
            count++;
        }

        return count;
    }

    // Main method for local testing
    public static void main(String[] args) {
        HammingWeight hw = new HammingWeight();

        int input = 11; // binary: 1011 -> has 3 ones
        int result = hw.hammingWeight(input);

        System.out.println("Number of 1 bits: " + result); // Expected output: 3
    }
}
