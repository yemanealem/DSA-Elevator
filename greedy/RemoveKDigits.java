public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {

        // Edge case
        if (k == num.length()) return "0";

        StringBuilder stack = new StringBuilder();

        for (char digit : num.toCharArray()) {

            // Remove larger digits from the end
            while (k > 0 && stack.length() > 0 
                    && stack.charAt(stack.length() - 1) > digit) {

                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        // If k still remains, remove from end
        stack.setLength(stack.length() - k);

        // Remove leading zeros efficiently
        int start = 0;
        while (start < stack.length() && stack.charAt(start) == '0') {
            start++;
        }

        String result = stack.substring(start);

        return result.isEmpty() ? "0" : result;
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // 1219
        System.out.println(removeKdigits("10200", 1));   // 200
        System.out.println(removeKdigits("10", 2));      // 0
    }
}
