public class ReverseWordsInString {

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";

        // Trim leading/trailing spaces and split by whitespace
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        // Traverse from the end to reverse the order
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) result.append(" ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "  the sky   is blue ";
        String output = reverseWords(input);
        System.out.println(output);  // Output: "blue is sky the"
    }
}
