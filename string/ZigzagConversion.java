public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder result = new StringBuilder();
        int cycle = 2 * numRows - 2;

        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < s.length(); i += cycle) {
                result.append(s.charAt(i));

                int secondIndex = i + cycle - 2 * row;
                if (row != 0 && row != numRows - 1 && secondIndex < s.length()) {
                    result.append(s.charAt(secondIndex));
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion solution = new ZigzagConversion();

        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
