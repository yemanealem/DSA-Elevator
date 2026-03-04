public class HammingWeight {

    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1); // removes the rightmost set bit
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
