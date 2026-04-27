class Solution {
   private final String[] below20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
        "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        StringBuilder result = new StringBuilder();

        if (num >= 1_000_000_000) {
            result.append(helper(num / 1_000_000_000)).append(" Billion ");
            num %= 1_000_000_000;
        }

        if (num >= 1_000_000) {
            result.append(helper(num / 1_000_000)).append(" Million ");
            num %= 1_000_000;
        }

        if (num >= 1000) {
            result.append(helper(num / 1000)).append(" Thousand ");
            num %= 1000;
        }

        if (num > 0) {
            result.append(helper(num));
        }

        return result.toString().trim();
    }

    private String helper(int num) {
        StringBuilder sb = new StringBuilder();

        if (num >= 100) {
            sb.append(below20[num / 100]).append(" Hundred ");
            num %= 100;
        }

        if (num >= 20) {
            sb.append(tens[num / 10]).append(" ");
            num %= 10;
        }

        if (num > 0) {
            sb.append(below20[num]).append(" ");
        }

        return sb.toString();
}
}