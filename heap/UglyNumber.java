class UglyNumber {
    // Check if a number is ugly
    public boolean isUgly(int n) {
        if (n <= 0) return false;

        int[] factors = {2, 3, 5};

        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }

        return n == 1;
    }

    public static void main(String[] args) {
        UglyNumber obj = new UglyNumber();

        System.out.println(obj.isUgly(6));  // true
        System.out.println(obj.isUgly(14)); // false
    }
}
