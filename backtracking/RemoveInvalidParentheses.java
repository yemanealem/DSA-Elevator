class Solution {
    public List<String> removeInvalidParentheses(String s) {
         List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private void remove(String s, List<String> result,
                        int last_i, int last_j, char[] par) {

        int stack = 0;

        for (int i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;

            if (stack >= 0) continue;

            // We found extra closing parenthesis
            for (int j = last_j; j <= i; j++) {

                // Remove only the first parenthesis in consecutive duplicates
                if (s.charAt(j) == par[1] &&
                   (j == last_j || s.charAt(j - 1) != par[1])) {

                    remove(
                        s.substring(0, j) + s.substring(j + 1),
                        result,
                        i,
                        j,
                        par
                    );
                }
            }
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();

        if (par[0] == '(') {
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            result.add(reversed);
        } 
    }
}