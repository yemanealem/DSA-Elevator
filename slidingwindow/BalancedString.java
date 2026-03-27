public class BalancedString {

    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;

        int[] count = new int[4];

        // Count all characters
        for (char c : s.toCharArray()) {
            count[index(c)]++;
        }

        // Already balanced
        if (isBalanced(count, target)) return 0;

        int left = 0;
        int minLen = n;

        for (int right = 0; right < n; right++) {
            count[index(s.charAt(right))]--;

            while (left <= right && isBalanced(count, target)) {
                minLen = Math.min(minLen, right - left + 1);
                count[index(s.charAt(left))]++;
                left++;
            }
        }

        return minLen;
    }

    private boolean isBalanced(int[] count, int target) {
        return count[0] <= target &&
               count[1] <= target &&
               count[2] <= target &&
               count[3] <= target;
    }

    private int index(char c) {
        switch (c) {
            case 'Q': return 0;
            case 'W': return 1;
            case 'E': return 2;
            case 'R': return 3;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        BalancedString sol = new BalancedString();

        System.out.println(sol.balancedString("QWER")); // 0
        System.out.println(sol.balancedString("QQWE")); // 1
        System.out.println(sol.balancedString("QQQW")); // 2
    }
}
