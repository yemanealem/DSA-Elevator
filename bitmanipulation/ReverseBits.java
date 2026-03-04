public class ReverseBits {

    // Question: Reverse Bits
    // Input: a 32-bit unsigned integer
    // Output: the integer with bits reversed

    // How it works (Bit Manipulation):
    // - Take each bit from n (LSB to MSB)
    // - Shift result left and add bit
    // - Shift input right to process next bit
    // - Repeat 32 times (because integer is 32 bits)

    // Running Time:
    // - Time Complexity: O(32) -> constant
    // - Space Complexity: O(1)

    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1); // shift result and add last bit
            n = n >>> 1; // unsigned shift (fills with 0)
        }

        return result;
    }

    // Main method for local testing
    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();

        int input = 43261596; // example input
        int output = rb.reverseBits(input);

        System.out.println("Reversed bits: " + output);
    }
}
