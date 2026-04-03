public class MultiplyStrings {

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int n = num1.length();
        int m = num2.length();

        int[] result = new int[n + m];

        // Multiply digits
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';

                int sum = digit1 * digit2 + result[i + j + 1];

                result[i + j + 1] = sum % 10;       // current digit
                result[i + j] += sum / 10;          // carry
            }
        }

        // Convert to string
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";

        System.out.println("Result: " + multiply(num1, num2)); // 56088
    }
}
