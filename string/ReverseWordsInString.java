public class ReverseWordsInString {

    public static String reverseWords(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder();
        int i = n - 1;

        while (i >= 0) {
            // Skip trailing spaces
            while (i >= 0 && s.charAt(i) == ' ') i--;

            if (i < 0) break;

            int end = i;

            // Find the start of the word
            while (i >= 0 && s.charAt(i) != ' ') i--;

            int start = i + 1;

            // Append the word
            result.append(s, start, end + 1);

            // Add space if more words exist
            result.append(' ');
        }

        // Remove last space if exists
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }

    public static void main(String[] args) {
        String input = "  the sky   is blue ";
        System.out.println(reverseWords(input)); // Output: "blue is sky the"
    }
}
