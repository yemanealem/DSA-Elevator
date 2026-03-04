public class ReverseWordsInString {

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";

        int n = s.length();
        StringBuilder sb = new StringBuilder();

        int left = 0;

        while (left < n) {
            // Skip spaces
            while (left < n && s.charAt(left) == ' ') left++;

            if (left >= n) break;

            int right = left;
            // Find the end of the word
            while (right < n && s.charAt(right) != ' ') right++;

            // Add word to front (to reverse order)
            if (sb.length() == 0) {
                sb.insert(0, s.substring(left, right));
            } else {
                sb.insert(0, " ").insert(0, s.substring(left, right));
            }

            left = right;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "  the sky   is blue ";
        System.out.println(reverseWords(input)); // Output: "blue is sky the"
    }
}
