public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {

        int n = s.length();
        int[] last = new int[26];
        boolean[] visited = new boolean[26];

        // Last occurrence of each character
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < n; i++) {

            char c = s.charAt(i);

            if (visited[c - 'a']) {
                continue;
            }

            // Remove larger characters that appear later
            while (stack.length() > 0 &&
                   c < stack.charAt(stack.length() - 1) &&
                   last[stack.charAt(stack.length() - 1) - 'a'] > i) {

                visited[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(c);
            visited[c - 'a'] = true;
        }

        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));     // abc
        System.out.println(removeDuplicateLetters("cbacdcbc"));  // acdb
        System.out.println(removeDuplicateLetters("abacb"));      // abc
    }
}
