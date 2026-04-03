public class MultiplyStrings {

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int n = num1.length();
        int m = num2.length();
        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';

                int pos = i + j + 1;
                int sum = d1 * d2 + result[pos];

                result[pos] = sum % 10;
                result[pos - 1] += sum / 10;
            }
        }

        // Build result string efficiently
        StringBuilder sb = new StringBuilder(n + m);

        int i = 0;
        while (i < result.length && result[i] == 0) i++;

        for (; i < result.length; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("163", "95")); // 15485
    }
}
