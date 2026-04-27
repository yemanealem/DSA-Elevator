public class NumberOfDigitOne {

    public int countDigitOne(int n) {
        long factor = 1;
        int count = 0;

        while (factor <= n) {
            long high = n / (factor * 10);
            long cur = (n / factor) % 10;
            long low = n % factor;

            if (cur == 0) {
                count += high * factor;
            } else if (cur == 1) {
                count += high * factor + (low + 1);
            } else {
                count += (high + 1) * factor;
            }

            factor *= 10;
        }

        return count;
    }

    // Main method to run the program
    public static void main(String[] args) {
        NumberOfDigitOne solution = new NumberOfDigitOne();

        int n = 13; // example input
        int result = solution.countDigitOne(n);

        System.out.println("Number of digit '1' from 0 to " + n + " = " + result);
    }
}
